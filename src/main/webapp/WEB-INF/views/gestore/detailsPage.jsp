<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="it.obiectivo.ecoss.domain.Gestore"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ecoss</title>

<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css" />


<!--  IMPORT DI JQuery --SHARED-- -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />"></script>
<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.subgrid.js" />"></script>

<!-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />" ></script> -->
<script type="text/javascript" src="<c:url value="/resources/newEcoss/script.js" />"></script>

<script type="text/javascript">
var id = ${gestore.idGestore};

function doAjaxRequest(ajaxSubmitUrl){
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
	Effettuo il parsing dell'oggetto jqXHR ed ottengo
	un oggetto Json. In questo modo posso usare la 
	notazione 'dot' per poter accedere comodamente 
	alle informazioni inviate dal server 
	*/
	return jq.parseJSON(xmlHttpRequest.responseText);
};

var providers = doAjaxRequest("<c:url value='/gestore/ajaxGetProviders?idGestore="+id+"' />");
var users = doAjaxRequest("<c:url value='/gestore/ajaxGetUsers?idGestore="+id+"' />");

jq(function() {

	if(providers != null){
		/* ------------------------------------
		 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
		 --------------------------------------*/ 
		jq("#gridDistributori").jqGrid({
			data: providers,
			datatype: "local",
			rowNum:10,
		   	rowList:[10,20,40],
		   	width: "60%",
		   	height: "60%",
		   	colNames:['idDistributore', 
			  		   	'Ragione sociale', 
			  		   	'Descrizione', 
			  		   	'Codice fiscale',
			  		   	'Partita IVA',
			  		  	],
			colModel:[
				   		{name:'idDistributore',index:'idDistributore', width:55, hidden:true, editable:true},
				   		{name:'distRagSociale',index:'distRagSociale', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'distDescrizione',index:'distDescrizione', width:100, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'distCF',index:'distCF', width:160, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'distPIVA',index:'distPIVA', width:130, editoptions: { size: 35}, editrules:{edithidden:true, required:true}}
			],
		   	postData: { },
			rownumbers: true,
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="usersList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,	
		    
			onSelectRow : function(id) {
				var grid = jq("#gridDistributori");
				var idDistributore = grid.jqGrid('getCell', id,'idDistributore');
				var link = "<c:url value='/distributore/Details?idDistributore="+idDistributore+"' />";
				window.location.replace(link);
			}//END - onSelectRow
		});

		jq("#gestoreDetail_two").show();
	}

	if(users != null){
		jq("#gridUtenti").jqGrid({
			data: users,
			datatype: "local",
			rowNum:10,
		   	rowList:[10,20,40],
		   	width: "60%",
		   	height: "60%",
		   	colNames:['idUtente', '<fmt:message key="tableHeader.headerRow1" />', 
			  		   	'<fmt:message key="tableHeader.headerRow2" />', 
			  		   	'<fmt:message key="tableHeader.headerRow3" />', 
			  		   	'<fmt:message key="tableHeader.headerRow4" />'
			],
			colModel:[
				   		{name:'idUtente',index:'idUtente', width:55, hidden:true, editable:true},
				   		{name:'uteNome',index:'uteNome', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'uteCognome',index:'uteCognome', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'uteCF',index:'uteCF', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'utePIVA',index:'utePIVA', width:100, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}}   		
			],
			postData: { },
			rownumbers: true,
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="usersList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,	
		    
			onSelectRow : function(id, isChecked) {
				var grid = jq("#gridUtenti");
				var idUtente = grid.jqGrid('getCell', id,'idUtente');
				var link = "<c:url value='/utente/Details?idUtente="+idUtente+"' />";
				window.location.replace(link);
			}//END - onSelectRow
		});

		jq("#gestoreDetail_three").show();
	};	
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
				<%@include file='../template/topNav.jspf'%>

				<div class="art-contentLayout">
					<div class="art-content">
						<div class="art-Post">
							<!--  TAG necessario per il mantenimento dei contenuti -->
							<div class="art-Post-body">
								<div class="art-Post-inner art-article">
									<h2 class="art-PostHeader">
										<a href="#" title="Permanent Link to this Post">Riepilogo Gestore</a>
									</h2>
									<div class="art-PostContent">
									<br />
									<div id="gestoreDetail_one" class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px;'>
										<div class=titleTab align="center"></div>
										<br />
										<img src="<c:url value='/resources/images/Manager_120.png' />" style="float: left; border: none;"/>
										<table id='userTab' class="linkDetails" style="height:300px;" >
											<tr valign=top>
												<td class=titleTab width=120px>Ragione Sociale:</td>
												<td>${gestore.gestRagSociale}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Codice Fiscale:</td>
												<td>${gestore.gestCF}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Partita Iva:</td>
												<td>${gestore.gestPIVA}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Descrizione:</td>
												<td>${gestore.gestDescrizione}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Indirizzo:</td>
												<td>${gestore.gestIndirizzo}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Città:</td>
												<td>${gestore.gestCitta}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>CAP:</td>
												<td>${gestore.gestCap}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Telefono:</td>
												<td>${gestore.gestTel1}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cellulare:</td>
												<td>${gestore.gestTel2}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Fax:</td>
												<td>${gestore.gestFax}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email primaria:</td>
												<td>${gestore.gestEmail}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email secondaria:</td>
												<td>${gestore.gestEmail2}</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="gestoreDetail_two" class='provideCSS-3 art-Block artBlockCorners"' style='padding: 10px; margin: 0px; background-color: white; display: hiden;'>
										<div id="divProvider"  class="linkDetails" style="width: 100%; text-align: left; font-size: 14px; color: black; text-decoration: none; display: inline;">
											<img src="<c:url value='/resources/images/Provider_100.png' />" style="border: none;"/>
											<div style="float:right; display:inline; margin-top: 20px;"><table id="gridDistributori"></table></div>
										</div>
									</div>
									<br />
									<div id="gestoreDetail_three" class='provideCSS-3 art-Block artBlockCorners"' style='padding: 10px; margin: 0px; background-color: white; display: hiden;'>
										<div id="divUser"  class="linkDetails" style="width: 100%; left; font-size: 14px; color: black; text-decoration: none; display: inline;">
											<img src="<c:url value='/resources/images/User_100.png' />" style="border: none;" />
											<div style="display:inline; position: relative; "><table id="gridUtenti"></table></div>
										</div>
									</div>
									<br />
									<div align="right">
										<input type="button" value="Back" name="back" class="button artBlockCorners" onclick=history.back() " />
									</div>
									</div>
									<!-- END art-PostContent -->
									<div class="cleared"></div>
								</div>
								<!-- END - art-Post-inner art-article - -->
								<div class="cleared"></div>
							</div>
							<!-- END -art-Post-body- -->
						</div>
						<!-- END -art-Post- -->
					</div>
					<!-- END -art-content- -->

					<div class="art-sidebar1">
						<%@include file="../template/leftMenu.jspf"%>
						<div class="cleared"></div>
					</div>
					<!-- END -art-sidebar1- -->
				</div>
				<!-- END -art-contentLayout- -->

				<div class="cleared"></div>
			</div>
			<!-- END -art-Sheet-body- -->

			<div class="cleared"></div>
			<%@include file="../template/footer.jspf"%>
		</div>
		<!-- END art-main -->
	</div>
</body>
</html>