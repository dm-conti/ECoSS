<%@page import="java.util.Date"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page import = "java.util.List"%> 

<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE" />
	<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
	<META NAME="ROBOTS" CONTENT="NONE" /> 
	<META NAME="GOOGLEBOT" CONTENT="NOARCHIVE" />
    
    <c:if test=" ${request.session} == null}">
    	<c:redirect url="/login"/>
	</c:if>
    
    <title>Ecoss</title>
	<script language="javascript">
		if(history.length>0)
			history.forward();
	</script>
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/stepy/jquery.stepy.css" />" type="text/css" media="screen" />

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.5.1.min.js" />" ></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.11.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
		
	
	<script type="text/javascript">
		
	/* -------------------------------
	 * SERVE A SETTARE CORRETTAMENTE
	 * IL COLORE DEI PASSI
	 -------------------------------*/
	function setCurrentStep(idStep){
		if(idStep > 0){
			var idPreviousStep = idStep-2; 
			jq("#callback-title-"+idPreviousStep).removeClass("current-step");
			jq("#callback-title-"+idStep).addClass("current-step");
		}
	}

	function controlla(){
		if(jq("#idVSG").val()==""){
			alert("Selezionare un Vsg");
			return false;
		}
		else
			return true;
	}

	jq(function() {
		
		//SETTO LO STEP CORRENTE
		setCurrentStep(2);

		var dataRows = ${listaVsg} ;
		var detailDataRows = "<c:url value='/cpe/getDetail?ids=' />";
		
		jq(function() {
			
		/* ------------------------------------
		 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
		 --------------------------------------*/ 
		jq("#grid").jqGrid({
			data: dataRows,
			datatype: "local",
			rowNum:10,
		   	rowList:[10,20,40],
		   	width: "100%",
		   	height: "100%",
		   	colNames:[	'idCPE',	'<fmt:message key="vsgName" />',
					'<fmt:message key="vsgModel" />',  			'<fmt:message key="vsgVersion" />',
					'<fmt:message key="enabledSince" />',		'<fmt:message key="vsgVersCore" />',
					'<fmt:message key="vsgVersCoreProg" />',	'<fmt:message key="vsgIpAddress" />',
					'<fmt:message key="vsgMacAddress" />',		'<fmt:message key="vsgManagementKey" />',
					'<fmt:message key="vsgState" />'], 
			colModel:[
				{name:'idCPE',index:'idCPE', width:50, hidden:true},
				{name:'cpeNome',index:'cpeNome', width:228},
			  	{name:'cpeModello',index:'cpeModello', width:158},
			  	{name:'cpeVersCore',index:'cpeVersCore', width:197, formatter:currencyFmatter},
			  	{name:'cpeDataAttivazione',index:'cpeDataAttivazione', width:128},
			 	
			  	// Colonne nascoste
			  	{name:'cpeVersCore',index:'cpeVersCore', width:91},
			  	{name:'cpeVersCoreProg',index:'cpeVersCoreProg', width:50},
			  	{name:'cpeIpAddress',index:'cpeIpAddress', width:50, hidden:true},
			  	{name:'cpeMacAddress',index:'cpeMacAddress', width:50, hidden:true},
			  	{name:'cpeManagementKey',index:'cpeManagementKey', width:50, hidden:true},
			  	{name:'tabellastati.stato',index:'tabellastati.stato', width:50, formatter:stateFmatter}
			],
		   	postData: { },
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="cpeList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,	

		    onSelectRow : function(id, isChecked) {
		    	var grid = jq("#grid");

				var nPage = grid.jqGrid('getGridParam', 'page');
				var nRow = grid.jqGrid('getGridParam', 'rowNum');
				var idVSG = grid.jqGrid('getCell', id,'idCPE');
				jq("#idVSG").val(idVSG);
				var tmpManagementKey = grid.jqGrid('getCell', id,'cpeManagementKey');
				var selectedRow;
				var arrayIds = grid.jqGrid('getGridParam', 'selarrrow');

				selectedRow = grid.jqGrid('getRowData', id);
					
				//Devo costruire l'URL come quello usato quando edito un elemento
				grid.jqGrid('setGridParam', { url : "Edit?" + idVSG, page : 1 });
					
				// Visualizzazione dettagli utente
				document.getElementById('dettagliCpe').style.display = "inline";

				for(var key in selectedRow){
					if(selectedRow[key] != null){
					/*Intercetto la Management Key per splittarla*/
						if(key == "cpeManagementKey"){
							var cpeManagementKey="";
							for(i=0; i<tmpManagementKey.length;i++){
								cpeManagementKey += tmpManagementKey.slice(i,i+10)+" ";
								i=i+10;
							}
							jq("#"+key).html(cpeManagementKey);
							continue;
						}

						var tmpKey = key.replace(/\./g,"_");
						var tmpHtml = selectedRow[key];
						
						jq("#"+tmpKey).html(tmpHtml);
					}
				}				
			}//END - onSelectRow
		});

		function currencyFmatter (cellvalue, options, rowObject)
		{
			var versione = cellvalue+"."+rowObject.cpeVersCoreProg;
			return versione;
		}

		jq("#details").jqGrid({
		   	url: detailDataRows+"1",
			datatype: 'json',
			mtype: 'POST',

			colNames:[	'<fmt:message key="licenceService" />',	'<fmt:message key="serviceName" />',
			          	'<fmt:message key="serviceSince" />', 	'<fmt:message key="expiryDate" />'], 
			colModel:[
				{name:'licNome',index:'licNome', sortable:false, search:false, hidden:true},
				{name:'nomeServizio',index:'nomeServizio', sortable:false, search:false},
				{name:'dataInizio',index:'dataInizio', sortable:false, search:false}, 
			   	{name:'dataFine',index:'dataFine', sortable:false, search:false}
			],

			postData: { },
			rowNum:5, 
			rowList:[5,10,20],
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pagerDetails', //id del DIV successivo a TABLE, dove andranno visualizzate le informazioni
			sortname: 'id', 
			viewrecords: true, 
			sortorder: "asc", 
			multiselect: false,
			height: "100%",
			width: "100%",
			caption: "Dettagli",
			emptyrecords: "Empty records",
		    loadonce: false,
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    }	    
		});

		jq("#dialog-message").dialog({
			modal: true,
			autoOpen: false,
	        width: 300,
	        resizable: false,
			buttons: {
				Ok: function() {
					$( this ).dialog( "close" );
				}
			}
	    });

		function linkFormatter(cellvalue, options, rowObject) {
			var link = "<c:url value='/cpe/Details?idCPE="+rowObject.idCPE+"' />"; 
			return "<a href='"+link+"' >"+cellvalue+"</a>";
	    }
		
		jq("#grid").jqGrid('navGrid','#pager',
			    {edit:false,add:false,del:false,search:true},
			    { },
			          { },
			          { },
			    {
			        sopt:['eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew'],
			           closeOnEscape: true,
			            multipleSearch: true,
			             closeAfterSearch: true 
		});
			
	});

	function stateFmatter (cellvalue, options, rowObject)
	{
			jq("#statoContratto").text(CPE0);

			var CPE0 = '<fmt:message key="CPE0" />';
			var CPE1 = '<fmt:message key="CPE1" />';
			var CPE2 = '<fmt:message key="CPE2" />';
			var CPE3 = '<fmt:message key="CPE3" />';
			
			if(cellvalue == "CPE0" ){
				jq("#statoContratto").text(CPE0);
				return CPE0;
			}

			if(cellvalue == "CPE1" ){
				jq("#statoContratto").text(CPE1);
				return CPE1;
			}

			if(cellvalue == "CPE2" ){
				jq("#statoContratto").text(CPE2);
				return CPE2;
			}

			if(cellvalue == "CPE3" ){
				jq("#statoContratto").text(CPE3);
				return CPE3;
			}
		}

		function ShowHideUsers(){
			jq("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}

		function ShowHideVSG(){
			jq("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}
});
	
	</script>

</head>
<body>
<c:if test=" ${newContratto} == null}">
    	<c:redirect url="/login"/>
	</c:if>
	<div id="art-page-background-simple-gradient"></div>
    <div id="art-main">
        <br />
        <div id="art-Sheet" class="art-Sheet artBlockCorners">
            <div class="art-Sheet-body">
            
            <!--  Import dell'Header -->
            <%@include file='../template/header.jspf'%>
            <%@include file='../template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
						<h2 class="art-PostHeader">
						<a href="#" title="Permanent Link to this Post"><spring:message code="usersList" /></a>
					</h2>
					
					<div class="art-PostContent">
						
						<ul class="stepy-titles">
							<li id="callback-title-0" class="current-step">
							Step 1
							<span>Nuovo Contratto</span>
							</li>
							<li id="callback-title-1" >
							Step 2
							<span>Utente</span>
							</li>
							<li id="callback-title-2" >
							Step 3
							<span>VSG</span>
							</li>
							<li id="callback-title-2" >
							Step 4
							<span>Licenza Utente</span>
							</li>
							<li id="callback-title-3" >
							Finish
							<span>Summary</span>
							</li>
						</ul>
							<form:form method="POST"  modelAttribute="newContratto" name="submitnewForm">
								<table id="grid"></table>
								<div id="pager"></div>
								<br />
								<div id="dettagliCpe" style="display:none;">
							<div class='provideCSS-3 art-Block artBlockCorners'
											style='padding: 10px; margin: 0px;'>
											<span class=titleTab>DETTAGLI VSG</span> <br /><br />
												
													<table id='tabs' height="250px">
														<tr valign=top>
													<td class=titleTab width=120px>Nome:</td>
													<td><div id="cpeNome"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Modello:</td>
													<td><div id="cpeModello"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Versione Core:</td>
													<td><div id="cpeVersCore"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Programma:</td>
													<td><div id="cpeVersCoreProg"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Data attivazione:</td>
													<td><div id="cpeDataAttivazione"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Indirizzo IP:</td>
													<td><div id="cpeIpAddress"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Mac Address:</td>
													<td><div id="cpeMacAddress"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Company Key:</td>
													<td><div id="cpeCompanyKey"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Management Key:</td>
													<td><div id="cpeManagementKey"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Stato:</td>
													<td><div id="tabellastati_stato"></div></td>
												</tr>
												</table>
										</div>
							</div>					
								<input type="hidden" value="" name="_idVSG" id="idVSG"/>
								<input type="hidden" value="2" name="_page" />
								<br />
								<input type="submit" value="Indietro" name="_target1" class="button artBlockCorners" />
								<input type="submit" value="Avanti"  name="_target3" class="button artBlockCorners" onclick="return controlla()"/>
							</form:form>
							
						</div><!-- END art-PostContent -->
						<div class="cleared" ></div>
					</div><!-- END -art-Post- -->
				</div><!-- END -art-content- -->
			
				<div class="art-sidebar1">
					<%@include file="../template/leftMenu.jspf" %>                        
					<div class="cleared" ></div>
				</div><!-- END -art-sidebar1- -->
			</div><!-- END -art-contentLayout- -->
			<div class="cleared"></div>
		</div><!-- END -art-Sheet-body- -->
		<div class="cleared" ></div>
		<%@include file="../template/footer.jspf" %>
	</div><!-- END art-main -->
</div>
</body>
</html>