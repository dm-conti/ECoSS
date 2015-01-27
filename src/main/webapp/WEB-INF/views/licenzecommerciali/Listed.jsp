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
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css"/>
	

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />" ></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.subgrid.js" />"></script>
	
	<!-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />" ></script> -->
	<script type="text/javascript" src="<c:url value="/resources/newEcoss/script.js" />" ></script>
	
	<script type="text/javascript">
	var ajaxSubmitUrl = "<c:url value='/licenzecommerciali/Listed' />";
	var ajaxGetDetails = "<c:url value='/licenzecommerciali/getDetails?ids=' />";
	
	function controlla(){
		if(jq("#_idLicenzeCommerciali").val()==""){
			alert("Selezionare una Licenza Commerciale");
			return false;
		}
		if(jq("#dataCessazione").val()==""){
			alert("Selezionare una data come scadenza del Contratto");
			return false;
		}
		return true;
	}
	
	/* ---------------------------------------
	 * INTERROGHIAMO IL DB PER RECUPERARE LA
	 * LISTA DI Licenzautente CENSITE
	 ---------------------------------------*/ 
	 var xmlHttpRequest = jq.ajax({
		 url: ajaxSubmitUrl,
		 type: "POST",
		 async:   false,
		 success: function(data){
			 xmlHttpRequest = data;
			 return xmlHttpRequest;
		},
		cache: false,
		dataType: "json"
	});
	
	/*
	 * Effettuo il parsing dell'oggetto jqXHR ed ottengo
	 * un oggetto Json. In questo modo posso usare la 
	 * notazione 'dot' per poter accedere comodamente 
	 * alle informazioni inviate dal server 
	 */
	var jsonResponse = jq.parseJSON(xmlHttpRequest.responseText);
	
	//Recupero le righe da inserire in tabella
	var dataRows = jsonResponse.rows;

	/*
	Effettuo il parsing dell'oggetto jqXHR ed ottengo
	un oggetto Json. In questo modo posso usare la 
	notazione 'dot' per poter accedere comodamente 
	alle informazioni inviate dal server 
	 */
	var jsonResponse = jq.parseJSON(xmlHttpRequest.responseText);

	//Recupero le righe da inserire in tabella
	var dataRows = jsonResponse.rows;
	
	/* -------------------------------
	 * SERVE A SETTARE CORRETTAMENTE
	 * IL COLORE DEI PASSI
	 -------------------------------*/
	function setCurrentStep(idStep){
		if(idStep > 0){
			var idPreviousStep = idStep-3; 
			jq("#callback-title-"+idPreviousStep).removeClass("current-step");
			jq("#callback-title-"+idStep).addClass("current-step");
		}
	}

	jq(function() {
		
		jq("#datepicker").datepicker({ 
			minDate: "+15D", 
			maxDate: "+12M",
			onSelect: function(dateText, inst) {
				var id = jq("grid").jqGrid('getGridParam','selrow');
				var licNome =  jq("grid").jqGrid('getCell', id, 'licomNomeLicenza');
				var message = "<span id='dataExpired'>Il contratto scadrà il:<br />"+dateText+"</span>"; 
				jq("#dataCessazione").val(dateText);
				jq("#summary").html(message);
				jq("#summary").fadeIn();
			}
		});
		
		//SETTO LO STEP CORRENTE
		setCurrentStep(3);
		
		/* ------------------------------------
		 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
		 --------------------------------------*/ 
		jq("#grid").jqGrid({
			data: dataRows,
			datatype: "local",
			rowNum:10,
		   	width:"60px",
		   	height: "100%",
		   	colNames:[	'idLicenzeCommerciali', 
		   	       		'Nome Licenza'
		    		 ], 
			colModel:[	{name:'idLicenzeCommerciali',index:'idLicenzeCommerciali', width:50, hidden:true},
						{name:'licomNomeLicenza',index:'licomNomeLicenza', width:700}
					 ],
		   	postData: { },
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="licUteList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,
		    
		  //OnSelectRow
		    onSelectRow: function(id) {
		    	var idTmp;
		    	if(id == null) {
			    	var id=1;
			    	idTmp = jq('#grid').jqGrid('getCell', id,'idLicenzeCommerciali');
				    var newCaption = dataRows[(id-1)].licomNomeLicenza;
				    if(jq("#details").jqGrid('getGridParam','records') >0 ) { 
				    	//Devo costruire l'URL come quello usato quando edito un elemento
					    jq("#details").jqGrid('setGridParam', {url:ajaxGetDetails+idTmp, page:1}); 
					    jq("#details").jqGrid('setCaption',"Invoice Detail: "+newCaption).trigger('reloadGrid');
					} 
				} else {
					idTmp = jq('#grid').jqGrid('getCell', id, 'idLicenzeCommerciali');
					var newCaption = dataRows[(id-1)].licomNomeLicenza;
					jq("#details").jqGrid('setGridParam',{url:ajaxGetDetails+idTmp, page:1}); 
					jq("#details").jqGrid('setCaption',"Invoice Detail: "+newCaption).trigger('reloadGrid');
				}
		    	document.getElementById("_idLicenzeCommerciali").value = idTmp;
			}		    
		});
		
		jq("#details").jqGrid({
		   	url: ajaxGetDetails+"1",
			datatype: 'json',
			mtype: 'POST',

			colNames:[ 'Nome Servizio','Attivo dal', 'Termine Servizio'], 
			colModel:[
				{name:'vocelicenza.voceLicenza',index:'vocelicenza.voceLicenza', width:200, sortable:false, search:false},
				{name:'dataInizio',index:'dataInizio', width:235, sortable:false, search:false}, 
			   	{name:'dataCessazione',index:'dataCessazione', width:240, sortable:false, search:false}
			],

			postData: { },
			rowNum:5, 
			rowList:[5,10,20],
			rownumbers: true,
		   	pager: '#pagerDetails', //id del DIV successivo a TABLE, dove andranno visualizzate le informazioni
			sortname: 'id', 
			viewrecords: true, 
			sortorder: "asc", 
			multiselect: false,
			height: "100%",
			caption: "Invoice Detail: "+dataRows[0].voceLicenza,
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
									<a href="#" title="Permanent Link to this Post">Licenze Commerciali</a>
								</h2>
									<div class="art-PostContent">
										</br>
											<table id="grid"></table>
											</br>
											<table id="details"></table>
									</div><!-- END art-PostContent -->
									<input type="hidden" value="" name="_idLicenzeCommerciali" id="_idLicenzeCommerciali"/>
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