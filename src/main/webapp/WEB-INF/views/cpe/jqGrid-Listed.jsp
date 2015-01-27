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
	<!-- <link rel="stylesheet" href="<c:url value="/resources/css/tableStyle.css" />" type="text/css" media="screen" /> -->
	

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />" ></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.locale-en.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.subgrid.js" />"></script>
	
	<!-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />" ></script> -->
	<script type="text/javascript" src="<c:url value="/resources/newEcoss/script.js" />" ></script>
	
	<script type="text/javascript">
	var urlCpeListed = "<c:url value='/cpe/Listed' />";
	var urlLicenzecpeGetDetails = "<c:url value='/cpe/getDetail?ids=' />";

	jq(function() {
		//MainGrid
		jq("#grid").jqGrid({
		   	url: urlCpeListed,
			datatype: 'json',
			mtype: 'POST',
			colNames:['idCpe', 'Nome', 'Modello', 'Versione Core', 'Prog.', 'Versione', 'Attivo dal'], 
			colModel:[
				{name:'id',index:'id', width:55,editable:false,editoptions:{readonly:true,size:10},hidden:true},
				{name:'cpeNome',index:'cpeNome', width:150,editable:true, editrules:{required:true}, editoptions:{size:10}},
			  	{name:'cpeModello',index:'cpeModello', width:90,editable:true, editrules:{required:true}, editoptions:{size:10}},
			  	{name:'cpeVersCore',index:'cpeVersCore', width:30},
			  	{name:'cpeVersCoreProg',index:'cpeVersCoreProg', width:30},
			  	{name:'cpeVersCore'+'cpeVersCoreProg', index:'cpeVersCore'+'cpeVersCoreProg', wigth:100},
			  	{name:'cpeDataAttivazione',index:'cpeDataAttivazione', width:100}
			],
			postData: { rows: "rows"},
			rowNum:10,
		   	rowList:[10,20,30],
		   	height: 250,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager', //id del DIV successivo a TABLE, dove andranno visualizzate le informazioni
			sortname: 'id', 
			viewrecords: true, 
			sortorder: "asc", 
			multiselect: false,
			width: "100%",
			height: "100%", 
			caption: '<fmt:message key="usersList" />',
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
		    },
		  //OnSelectRow
		    onSelectRow: function(ids) {
				var grid = jq("#grid")[3]; 
				alert(grid);
			    if(ids == null) { 
				    ids=0;
				    if(jq("#details").jqGrid('getGridParam','records') >0 ) { 
						//Devo costruire l'URL come quello usato quando edito un elemento
					    jq("#details").jqGrid('setGridParam', {url:urlLicenzecpeGetDetails+ids, page:1}); 
					    jq("#details").jqGrid('setCaption',"Invoice Detail: "+ids).trigger('reloadGrid'); 
					} 
				} else {
					//Devo costruire l'URL come quello usato quando edito un elemento
					jq("#details").jqGrid('setGridParam',{url:urlLicenzecpeGetDetails+ids, page:1}); 
					jq("#details").jqGrid('setCaption',"Invoice Detail: "+ids).trigger('reloadGrid'); 
				}
			}   
		});

		jq("#grid").jqGrid('navGrid','#pager',{add:false,edit:false,del:false}); 


		jq("#details").jqGrid({
		   	url: urlLicenzecpeGetDetails+"1",
			datatype: 'json',
			mtype: 'POST',

			colNames:[ 'Licenza', 'Nome Servizio','Attivo dal', 'Termine Servizio'], 
			colModel:[
				{name:'licNome',index:'licNome', width:100, sortable:false, search:false, hidden:true},
				{name:'nomeServizio',index:'nomeServizio', width:100, sortable:false, search:false},
				{name:'dataInizio',index:'dataInizio', width:100, sortable:false, search:false}, 
			   	{name:'dataFine',index:'dataFine', width:60, sortable:false, search:false}
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
			width: "100%",
			height: "100%",
			caption: 'Undefined',
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
 

		jq("#ms1").click( function() { 
				var s; 
				s = jQuery("#pagerDetails").jqGrid('getGridParam','selarrrow'); 
				alert(s); 
		});

		// Toolbar Search
		jq("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});

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

		jq("#dialog-message_2").dialog({
			modal: true,
			autoOpen: false,
	        width: 300,
	        resizable: false,
			buttons: {
				Ok: function() {
					elimina();
					$( this ).dialog( "close" );
				},
				Annulla: function(){
					$( this ).dialog( "close" );
				}
			}
	    });

	});
</script>

<script type="text/javascript">
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
		message = "Selezionare almeno un utente!";
		document.getElementById("dialog-message").innerHTML = message;
		jq('#dialog-message').dialog('open');
	}
}

function elimina(){
	jq(function(){
		jq.post("/springSecurityIntegration/utente/Delete",
			     {  arrayId:  listId.toString() },
			      function(data){
			       alert("Eliminazione Riuscita!");
			       location.reload();
		});
		});
}	
</script>
<script type="text/javascript">

function addRow() {

	// Get the currently selected row
    jq("#grid").jqGrid('editGridRow','new',
    		{ 	url: '<c:url value="/utente/add" />', 
					editData: {
			    },
			    recreateForm: true,
			    beforeShowForm: function(form) {
			    },
				closeAfterAdd: true,
				reloadAfterSubmit:false,
				afterSubmit : function(response, postdata) 
				{ 
			        var result = eval('(' + response.responseText + ')');
					var errors = "";
					
			        if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
			        }  else {
			        	jq("#dialog").text('Entry has been added successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});
	                }
			    	// only used for adding new records
			    	var new_id = null;
			    	
					return [result.success, errors, new_id];
				}
    		});

}

function editRow() {
	// Get the currently selected row
	var row = jq("#grid").jqGrid('getGridParam','selrow');
	
	if( row != null ) 
		jq("#grid").jqGrid('editGridRow',row,
			{	url: '<c:url value="/utente/edit" />', 
				editData: {
		        },
		        recreateForm: true,
		        beforeShowForm: function(form) {
		        },
				closeAfterEdit: true,
				reloadAfterSubmit:false,
				afterSubmit : function(response, postdata) 
				{ 
		            var result = eval('(' + response.responseText + ')');
					var errors = "";
					
		            if (result.success == false) {
						for (var i = 0; i < result.message.length; i++) {
							errors +=  result.message[i] + "<br/>";
						}
		            }  else {
		            	jq("#dialog").text('Entry has been edited successfully');
						jq("#dialog").dialog( 
								{	title: 'Success',
									modal: true,
									buttons: {"Ok": function()  {
										jq(this).dialog("close");} 
									}
								});
	                }
		        	
					return [result.success, errors, null];
				}
			});
	else jq( "#dialogSelectRow" ).dialog();
}

function deleteRow() {
	// Get the currently selected row
    var row = jq("#grid").jqGrid('getGridParam','selrow');

    // A pop-up dialog will appear to confirm the selected action
	if( row != null ) 
		jq("#grid").jqGrid( 'delGridRow', row,
          	{ url: '<c:url value="/utente/delete" />', 
						recreateForm: true,
			            beforeShowForm: function(form) {
			              //change title
			              jq(".delmsg").replaceWith('<span style="white-space: pre;">' +
			            		  'Delete selected record?' + '</span>');
	            		  
						  //hide arrows
			              jq('#pData').hide();  
			              jq('#nData').hide();  
			            },
          				reloadAfterSubmit:false,
          				closeAfterDelete: true,
          				afterSubmit : function(response, postdata) 
						{ 
			                var result = eval('(' + response.responseText + ')');
							var errors = "";
							
			                if (result.success == false) {
								for (var i = 0; i < result.message.length; i++) {
									errors +=  result.message[i] + "<br/>";
								}
			                }  else {
			                	jq("#dialog").text('Entry has been deleted successfully');
								jq("#dialog").dialog( 
										{	title: 'Success',
											modal: true,
											buttons: {"Ok": function()  {
												jq(this).dialog("close");} 
											}
										});
			                }
		                	// only used for adding new records
		                	var new_id = null;
		                	
							return [result.success, errors, new_id];
						}
          	});
	 else jq( "#dialogSelectRow" ).dialog();
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
									<a href="#" title="Permanent Link to this Post"><spring:message code="usersList" /></a>
								</h2>
									<div class="art-PostContent">
										<div id="jqgrid">
											<table id="grid"></table>
											<div id="pager"></div>
											<br />
											<table id="details"></table>
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