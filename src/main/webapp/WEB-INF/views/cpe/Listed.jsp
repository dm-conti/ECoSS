<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import = "java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Ecoss</title>
	
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
		
	<script type='text/javascript'>
	var dataRows = ${listaCpe} ;
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
	   	multiselect: true,
	   	colNames:['idCPE', 'Nome', 'Modello', 'Versione', 'Attivo dal'], 
		colModel:[
			{name:'idCPE',index:'idCPE', width:50, hidden:true},
			{name:'cpeNome',index:'cpeNome', width:228},
		  	{name:'cpeModello',index:'cpeModello', width:158},
		  	{name:'cpeVersCore',index:'cpeVersCore', width:197, formatter:currencyFmatter},
		  	{name:'cpeDataAttivazione',index:'cpeDataAttivazione', width:128}
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
			var idCPE = grid.jqGrid('getCell', id,'idCPE');
			var link = "<c:url value='/cpe/Details?idCPE="+idCPE+"' />";
			//var uteEamil = grid.jqGrid('getCell', id, 'uteEmail');
			var selectedRow;
			var arrayIds = grid.jqGrid('getGridParam', 'selarrrow');
			
			if(isChecked){
				selectedRow = grid.jqGrid('getRowData', id);
				
				// Setto variabile nascosta id utente per invio email
				//jq("#uteEmail").val(idUtente);
				
				//Devo costruire l'URL come quello usato quando edito un elemento
				grid.jqGrid('setGridParam', { url : "Edit?" + idCPE, page : 1 });

				jq("#titleTab").html("<a href='"+link+"' >"+"Visualizza dettagli del CPE"+"</a>");
				
				// Visualizzazione dettagli utente
				document.getElementById('cpeDetails').style.display = "inline";

				for(var key in selectedRow){
					if(selectedRow[key] != null){
						var tmpKey = key.replace(/\./g,"_");
						var tmpHtml = selectedRow[key];
						jq("#"+tmpKey).html(tmpHtml);
					}
				}
			}

			if(!isChecked && arrayIds.length != 0){
				id = arrayIds[arrayIds.length-1];
				selectedRow = grid.jqGrid('getRowData', id);
				idCPE = grid.jqGrid('getCell', id,'idCPE');


				// Setto variabile nascosta id utente per invio email
				//jq("#uteEmail").val(idCPE);
				
				//Devo costruire l'URL come quello usato quando edito un elemento
				grid.jqGrid('setGridParam', { url : "Edit?" + idCPE, page : 1 });

				jq("#titleTab").html("<a href='"+link+"' >"+"Visualizza dettagli del CPE"+"</a>");
				
				// Visualizzazione dettagli cpe
				document.getElementById('cpeDetails').style.display = "inline";

				for(var key in selectedRow){
					if(selectedRow[key] != null){
						var tmpKey = key.replace(/\./g,"_");
						var tmpHtml = selectedRow[key];
						jq("#"+tmpKey).html(tmpHtml);
					}
				}
			}			

			if(arrayIds.length == 0){
				document.getElementById('cpeDetails').style.display = "none";
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

		colNames:[ 'Licenza', 'Nome Servizio','Attivo dal', 'Termine Servizio'], 
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

	// Toolbar Search
	//jq("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});

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
	
	function ShowHideUsers(){
		jq("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
	}

	function ShowHideVSG(){
		jq("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
	}	
</script>

<script type="text/javascript">

function fillDetails(idDiv, row){
	document.getElementById(idDiv).innerHTML += row.cpeNome;
	document.getElementById(idDiv).innerHTML += row.cpeNome;
	document.getElementById(idDiv).innerHTML += row.cpeNome;
	document.getElementById(idDiv).innerHTML += row.cpeNome;
}

function ShowHideUsers(){
	jq("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
}

function ShowHideVSG(){
	jq("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
}

function idChecked(listChecked){
	
	/*RECUPERO LA LUNGHEZZA DELLA LISTA*/
	var n = listChecked.split(",").length;
	listId = new Array();
	for (i=1; i <= n; i++){
		checkObj = document.getElementById("check_"+ i);
		if(checkObj == null)
				continue;
		if(checkObj.checked){
			listId.push(checkObj.value);
		}
	}
	varLength = listId.length;
	if(varLength > 0){
		var message = "Hai selezionato "+varLength+" utenti. \n Procedo con l'operazione ?";
		document.getElementById("dialog-message_2").innerHTML = message;
		jq('#dialog-message_2').dialog('open');
	}else {
		message = "Selezionare almeno un vsg!";
		document.getElementById("dialog-message").innerHTML = message;
		jq('#dialog-message').dialog('open');
	}
}

function elimina(){
	jq(function(){
		jq.post("/springSecurityIntegration/cpe/Delete",
			     {  arrayId:  listId.toString() },
			      function(data){
			       alert("Eliminazione Riuscita!");
			       location.reload();
		});
		});
}	
</script>
</head>
<body>
	<div id="art-page-background-simple-gradient"></div>
    <div id="art-main">
        <br />
        <div id="art-Sheet" class="art-Sheet artBlockCorners">
            <div class="art-Sheet-body">
            <div id="dialog-message" title="Operazione non consentita"></div>
            <div id="dialog-message_2" title="Operazione non consentita"></div>
            
            <div id="modal" class="modal">
				<div id="textModal"></div>
			</div>
            
            <!--  Import dell'Header -->
            <%@include file='../template/header.jspf'%>
            <%@include file='../template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
						<div class="art-Post-body">
							<div class="art-Post-inner art-article">
								<h2 class="art-PostHeader">
									<a href="#" title="Permanent Link to this Post"><spring:message code="cpeList" /></a>
								</h2>
									<div class="art-PostContent">
										<div id="jqgrid">
											<table id="grid"></table>
											<div id="pager"></div>
											
											<br />
											<table id="details"></table>
											<br />
											<div id="cpeDetails" style="display: none;">
											
											<div class='provideCSS-3 art-Block artBlockCorners' style='padding:10px; margin:0px;'>
											
											<span class=titleTab>DETTAGLI VSG</span> </br></br>
												
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
												</tr></table>
											
											</div>
											</div>
											<div id="pagerDetails"></div>
										</div>
										<div id="dialog" title="Feature not supported" style="display:none">
											<p>That feature is not supported.</p>
										</div>
										<div id="dialogSelectRow" title="Warning" style="display:none">
											<p>Please select row</p>
										</div>
									</div><!-- END art-PostContent -->
								<div class="cleared" ></div>
							</div><!-- END - art-Post-inner art-article - -->
                           	<div class="cleared" ></div>
						</div><!-- END -art-Post-body- -->
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