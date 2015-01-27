<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ecoss</title>

<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />"
	type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/formToWizard.css" />"
	type="text/css" />


<!--  IMPORT DI JQuery --SHARED-- -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />"></script>
<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/grid.subgrid.js" />"></script>

<!-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />" ></script> -->
<script type="text/javascript"
	src="<c:url value="/resources/newEcoss/script.js" />"></script>

<script type="text/javascript">
var id = ${distributore.idDistributore};

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

var users = doAjaxRequest("<c:url value='/distributore/ajaxGetUsers?idDistributore="+id+"' />");

jq(function() {

	if(users != null){
		/* ------------------------------------
		 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
		 --------------------------------------*/ 
		jq("#gridUtenti").jqGrid({
			data: users,
			datatype: "local",
			rowNum:10,
		   	rowList:[10,20,40],
			autowidth: true,
		   	width: "500px",
		   	height: "100%",
			colNames:['idUtente', '<fmt:message key="tableHeader.headerRow1" />', 
			  		   	'<fmt:message key="tableHeader.headerRow2" />', 
			  		   	'<fmt:message key="tableHeader.headerRow3" />', 
			  		   	'<fmt:message key="tableHeader.headerRow4" />'
			],
			colModel:[
				   		{name:'idUtente',index:'idUtente', width:55, hidden:true, editable:true},
				   		{name:'uteNome',index:'uteNome', width:130, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'uteCognome',index:'uteCognome', width:130, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
				   		{name:'uteCF',index:'uteCF', width:130, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
				   		{name:'utePIVA',index:'utePIVA', width:130, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}}   		
			],
		   	postData: { },
			rownumbers: true,
		   	pager: '#pager',
		    viewrecords: true,
		    caption:'<fmt:message key="usersList" />',
		    emptyrecords: "Empty records",
		    loadonce: false,	
		    
			onSelectRow : function(id) {
				var grid = jq("#gridUtenti");
				var idUtente = grid.jqGrid('getCell', id,'idUtente');
				var link = "<c:url value='/utente/Details?idUtente="+idUtente+"' />";
				window.location.replace(link);
			}//END - onSelectRow
		});

		jq("#displayGid").show();
		jq("#userImgEnable").show();
	}else{
		jq("userImgDisable").show();
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
										<a href="#" title="Permanent Link to this Post">Riepilogo Subgestore</a>
									</h2>
									<div class="art-PostContent">
									<br />
									<div id="distributoreDetail_one" class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px;'>
										<div class=titleTab align="center"></div>
										<br />
										<img src="<c:url value='/resources/images/Provider_120.png' />" style="float: left; border: none;"/>
										<table id='userTab' class="linkDetails" style="height:300px;" >
											<tr valign=top>
												<td class=titleTab width=120px>Ragione Sociale:</td>
												<td>${distributore.distRagSociale}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Username:</td>
												<td>${distributore.credenzialiutente.userId}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Codice Fiscale:</td>
												<td>${distributore.distCF}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Partita Iva:</td>
												<td>${distributore.distPIVA}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Descrizione:</td>
												<td>${distributore.distDescrizione}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Indirizzo:</td>
												<td>${distributore.distIndirizzo}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Città:</td>
												<td>${distributore.distCitta}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>CAP:</td>
												<td>${distributore.distCap}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Telefono:</td>
												<td>${distributore.distTel1}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cellulare:</td>
												<td>${distributore.distTel2}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Fax:</td>
												<td>${distributore.distFax}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email primaria:</td>
												<td>${distributore.distEmail}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email secondaria:</td>
												<td>${distributore.distEmail2}</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="distributoreDetail_two" class='provideCSS-3 art-Block artBlockCorners"' style='padding: 10px; margin: 0px; background-color: white;'>
										<div id="divUser"  class="linkDetails" style="width: 100%; text-align: left; font-size: 14px; color: black; text-decoration: none; display: inline;">
											<img id='userImgEnable' src="<c:url value='/resources/images/User_100.png' />" style="border: none; display:none;"/>
											<div id='displayGid' style="float:right; display:inline; margin-top: 20px; display:none"><table id="gridUtenti"></table></div>
											<img id='userImgDisable' src="<c:url value='/resources/images/User_100_opaco.png' />" style="border: none; display:none;"/>
										</div>
									</div>
									<br />
									<security:authorize access="hasRole('Admin')">
										<div id="distributoreDetail_three" class='provideCSS-3 art-Block artBlockCorners' style="padding:10px; margin:0px; background-color:white;">
											<div id="divManager"  class="linkDetails" style="width:100%; font-weight:bolder; vertical-align:middle; font-size:x-large; color:black; text-decoration:none; display:inline-block;">													
												<table>
													<tr>
														<td>
															<img src="<c:url value='/resources/images/Manager_100.png' />" style="border:none;"/>
														</td>
														<td>
															<a href="<c:url value='/gestore/Details?idGestore=${distributore.gestore.idGestore}' />" >
																<c:out value="${distributore.gestore.gestRagSociale}" />
															</a>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</security:authorize>
									<br />
									
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