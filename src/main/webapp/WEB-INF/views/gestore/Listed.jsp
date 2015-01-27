<%@page import="java.util.Date"%>
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
		
	<script type="text/javascript">
	
	//Recupero le righe da inserire in tabella
	var dataRows =  ${listaGestori};
	
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
		   	colNames:['idGestore', '<fmt:message key="societyName" />',
		  		   	'<fmt:message key="description" />',	'<fmt:message key="taxCode" />',
					'<fmt:message key="VAT" />',			'<fmt:message key="address" />',
		  			'<fmt:message key="city" />',			'<fmt:message key="zipCode" />',
		  			'<fmt:message key="phone" />',			'<fmt:message key="cellPhone" />',
		  			'<fmt:message key="fax" />',			'<fmt:message key="eMail" />',
		  			'<fmt:message key="eMail2" />'],
			colModel:[{name:'idGestore',index:'idGestore', width:55, hidden:true, editable:true},
						{name:'gestRagSociale',index:'gestRagSociale', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'gestDescrizione',index:'gestDescrizione', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'gestCF',index:'gestCF', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'gestPIVA',index:'gestPIVA', width:100, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'gestIndirizzo',index:'gestIndirizzo', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'gestCitta',index:'gestCitta', width:100, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'gestCap',index:'gestCap', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'gestTel1',index:'gestTel1', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'gestTel2',index:'gestTel2', width:100, editrules:{edithidden:true, required:false}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'gestFax',index:'gestFax', width:100, editable:true, editoptions: { size: 35}},
				   		{name:'gestEmail',index:'gestEmail', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'gestEmail2',index:'gestEmail2', width:100, editrules:{edithidden:true, required:false}, editable:true, hidden:true, editoptions: { size: 35}}],
		   	postData: { },
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="managerList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,
		    
			onSelectRow : function(id, isChecked) {
				var grid = jq("#grid");

				var nPage = grid.jqGrid('getGridParam', 'page');
				var nRow = grid.jqGrid('getGridParam', 'rowNum');
				var idGestore = grid.jqGrid('getCell', id,'idGestore');
				var link = "<c:url value='/gestore/Details?idGestore="+idGestore+"' />";
				var selectedRow;
				var arrayIds = grid.jqGrid('getGridParam', 'selarrrow');
				
				if(isChecked){
					selectedRow = grid.jqGrid('getRowData', id);
					
					//Devo costruire l'URL come quello usato quando edito un elemento
					grid.jqGrid('setGridParam', { url : "Edit?" + idGestore, page : 1 });

					jq("#titleTab").html("<a href='"+link+"' >"+"Visualizza dettagli del Gestore"+"</a>");
					
					// Visualizzazione dettagli del Gestore
					document.getElementById('divDettagliGestore').style.display = "inline";

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
					idGestore = grid.jqGrid('getCell', id,'idGestore');
					
					//Devo costruire l'URL come quello usato quando edito un elemento
					grid.jqGrid('setGridParam', { url : "Edit?" + idGestore, page : 1 });

					jq("#titleTab").html("<a href='"+link+"' >"+"Visualizza dettagli del Gestore"+"</a>");
					
					// Visualizzazione dettagli del Gestore
					document.getElementById('divDettagliGestore').style.display = "inline";

					for(var key in selectedRow){
						if(selectedRow[key] != null){
							var tmpKey = key.replace(/\./g,"_");
							var tmpHtml = selectedRow[key];
							jq("#"+tmpKey).html(tmpHtml);
						}
					}
				}			

				if(arrayIds.length == 0){
					document.getElementById('divDettagliGestore').style.display = "none";
				}
			}//END - onSelectRow
		});
	
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
	
	jq("#grid").navButtonAdd('#pager',
			{  	caption:"Edit",
				buttonicon:"ui-icon-pencil",
				onClickButton: editRow,
				position: "last",
				title:"",
				cursor: "pointer"
	});
	
	jq("#grid").navButtonAdd('#pager',
			{  	caption:"Delete",
		    	buttonicon:"ui-icon-trash",
		    	onClickButton: deleteRow,
		    	position: "last",
		    	title:"",
		    	cursor: "pointer"
	});
	
	jq("#btnFilter").click(function(){
		jq("#grid").jqGrid('searchGrid',
				{	multipleSearch: false,
		      		sopt:['eq']
				});
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

	function editRow() {
		// Recupero la riga selezionata
		var row = jq("#grid").jqGrid('getGridParam', 'selrow');
		//var row = jq("#grid").jqGrid('getRowData',id); 
		var leftValue = screen.width / 2 - 200;
		if( row != null || row != undefined) 
			jq("#grid").jqGrid(
					'editGridRow',
					row,
					//INIZIO SET DI PARAMETRI
					{	url: '<c:url value="/gestore/ajaxEdit" />', 
						editData: { },
						
						caption : "Modifica Gestore",
						width : 400,
						height : 440,
						dataheight : 340,
						top : 200,
						left : leftValue,
						resize : false,
						modal : true,
					    
						
						recreateForm: true,
						beforeShowForm: function(form) { },
						closeAfterEdit: true,
						reloadAfterSubmit:false,
						afterSubmit : function(response, postdata) {
							var result = eval('(' + response.responseText + ')');
							var errors = "";
							
							if (result.success == false) {
								for (var i = 0; i < result.message.length; i++) {
									errors +=  result.message[i] + "<br/>";
								}
		            		}  
							else {
								jq("#dialog").text('Gestore modificato correttamente');
								jq("#dialog").dialog({	
									title: 'Modifica',
									modal: true,
									buttons: {
										"Ok": function()  {
											jq(this).dialog("close");} 
										}
									});
							}
							return [result.success, errors, null];
						}//END - afterSubmit
					}//END SET DI PARAMETRI: jqGrid( 'editGridRow', 'new', 'parameters');
				);//END - funzione jqGrid: editGridRow
				
				else 
					jq("#dialogSelectRow").dialog('open');
	}
	
	jq(function() {
		jq("#dialogSelectRow").dialog({
			autoOpen : false,
			modal : true,
			width : 220,
			height : 130,
			resizable : false,
			buttons : {
				'Ok' : function() {
					jq("#dialogSelectRow").dialog('close');
				}
			}
		});
	});

	function deleteRow() {
		
		/*RECUPERO LA LISTA DEGLI ID DELLE RIGHE SELEZIONATE*/
		var rows = jq('#grid').jqGrid('getGridParam','selarrrow');
		
		/* PREPARO UN ARRAY PER CONTENERE LA LISTA DEGLI idGestore
		 * TALI INFORMAZIONI SONO NECESSARIE PER LA CORRETTA INDIVIDUAZIONE
		 * DEI GESTORI DA ELIMINARE*/
		var ids = new Array();
		
		//CONTROLLO INUTILE SE VIENE INSERITO IL CONTROLLO SULLA SELEZIONE DELLE RIGHE
		if(rows.length) {
			for(var i=0;i<rows.length;i++){
				var idTmp = jq('#grid').jqGrid('getCell', rows[i],'idGestore');
				ids.push(idTmp);
			}
		}
		var leftValue = screen.width / 2 - 125;
		if (ids.length > 0)
        jQuery("#grid").jqGrid(
        		'delGridRow',
        		rows,
        		{	url:'<c:url value="/gestore/ajaxDelete" />',
        			delData : {
						arrayOfIds : ids.toString()
					},
					caption : "Cancellazione",
					msg : "Confermare eliminazione?",
					bSubmit : "Ok",
					bCancel : "Annulla",
					width : 250,
					height : 130,
					dataheight : 50,
					top : 300,
					left : leftValue,
					resize : false,
					modal : true,
					reloadAfterSubmit : true,
					closeAfterDelete : true,
					afterSubmit : function(response, postdata) {
						var result = eval('('
								+ response.responseText + ')');
						var errors = "";

						if (result.success == false) {
							for ( var i = 0; i < result.message.length; i++) {
								errors += result.message[i]
										+ "<br/>";
							}
						} else {
							jq("#dialog")
									.text(
											'Eliminazione effettuata correttamente');
							jq("#dialog")
									.dialog(
											{
												title : 'Eliminazione',
												modal : true,
												buttons : {
													"Ok" : function() {
														jq(this)
																.dialog(
																		"close");
														window.location.href = window.location.pathname;
													}

												}
											});

						}
						// only used for adding new records
						var new_id = null;

						return [ result.success, errors, new_id ];
					}
        		});
		else
			jq("#dialogSelectRow").dialog('open');
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
									<a href="#" title="Permanent Link to this Post"><spring:message code="managerList" /></a>
								</h2>
									<br />
									<div class="art-PostContent">
										<div id="jqgrid">
											<table id="grid"></table>
											<div id="pager"></div>
										</div>
										<br />
										<br />
										<div id="divDettagliGestore" style="display: none;">
											<div class='provideCSS-3 art-Block artBlockCorners'
												style='padding: 10px; margin: 0px;'>
												<span class=titleTab> DETTAGLI GESTORE </span> </br>
												<br /> 
												<table id='tabs' height="320px">
														<tr valign=top>
															<td class=titleTab>Ragione Sociale:</td>
															<td><div id="gestRagSociale"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Descrizione:</td>
															<td><div id="gestDescrizione"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Codice fiscale:</td>
															<td><div id="gestCF"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Partiva Iva:</td>
															<td><div id="gestPIVA"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Indirizzo:</td>
															<td><div id="gestIndirizzo"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Città:</td>
															<td><div id="gestCitta"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Cap:</td>
															<td><div id="gestCap"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Telefono:</td>
															<td><div id="gestTel1"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Cellulare:</td>
															<td><div id="gestTel2"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Fax:</td>
															<td><div id="gestFax"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Email primaria:</td>
															<td><div id="gestEmail"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Email secondaria:</td>
															<td><div id="gestEmail2"></div></td>
														</tr>
													</table>
											</div>
										</div>
										<div id="dialog" title="Feature not supported" style="display:none">
											<p>That feature is not supported.</p>
										</div>
										<div id="dialogSelectRow" title=""
											style="display: none; font-size: 11px;">Selezionare un Gestore</div>
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