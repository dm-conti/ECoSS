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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<!-- 
    <script type="text/javascript">
    	var jq = jQuery.noConflict();
 	</script>
    -->
<title>Ecoss - Administration System</title>

<link href="<c:url value="/resources/css/style.css" />"
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />


<link href="<c:url value="/resources/css/tableStyle.css" />"
	rel="stylesheet" type="text/css" media="screen" />

<!-- Core files -->
<link
	href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />"
	rel="stylesheet" type="text/css" media="screen" />

<!--  IMPORT DI JQuery --SHARED-- -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.dataTables.js" /> "></script>
<script type="text/javascript"
	src="<c:url value="/resources/newEcoss/script.js" />"></script>

<script>
	var varLength = 0;

	$(document)
			.ready(
					function() {

						oTable = $('#mainTable')
								.dataTable(
										{
											"bJQueryUI" : false,
											"oLanguage" : {
												"sLengthMenu" : "<fmt:message key='tableHeader.lengthLabel' />",
												"sZeroRecords" : "<fmt:message key='tableHeader.zeroRecors' />",
												"sInfo" : "<fmt:message key='tableHeader.info' />",
												"sInfoEmpty" : "<fmt:message key='tableHeader.infoEmpty' />",
												"sInfoFiltered" : "<fmt:message key='tableHeader.infoFiltered' />",
												"sFilter" : "Ricerca"
											}
										});

						$("#emptyDialog").dialog({
							modal : true,
							autoOpen : false,
							width : 300,
							resizable : false,
							buttons : {
								Close : function() {
									$(this).dialog("close");
								}
							}
						});

						$("#detailServicesDialog").dialog({
							modal : false,
							autoOpen : false,
							width : 300,
							resizable : true,
							buttons : {
								Close : function() {
									$(this).dialog("close");
								}
							}
						});

						$("#dialog-message_2").dialog({
							modal : true,
							autoOpen : false,
							width : 300,
							resizable : false,
							buttons : {
								Ok : function() {
									elimina();
									$(this).dialog("close");
								},
								Annulla : function() {
									$(this).dialog("close");
								}
							}
						});
					});/*END $(document).ready*/

	function ShowHideUsers() {
		$("#utentiSlidingDiv").animate({
			"height" : "toggle"
		}, {
			duration : 1000
		});
	}

	function ShowHideVSG() {
		$("#vsgSlidingDiv").animate({
			"height" : "toggle"
		}, {
			duration : 1000
		});
	}

	function idChecked(listChecked) {

		/*RECUPERO LA LUNGHEZZA DELLA LISTA*/
		var n = listChecked.split(",").length;
		listId = new Array();
		for (i = 1; i <= n; i++) {
			checkObj = document.getElementById("check_" + i);
			if (checkObj == null)
				continue;
			if (checkObj.checked) {
				listId.push(checkObj.value);
			}
		}
		varLength = listId.length;
		if (varLength > 0) {
			var message = "Hai selezionato " + varLength
					+ " Licenzecommerciali. \n Procedo con l'operazione ?";
			document.getElementById("dialog-message_2").innerHTML = message;
			$('#dialog-message_2').dialog('open');
		} else {
			message = "Selezionare almeno una Licenzacommerciale!";
			document.getElementById("emptyDialog").innerHTML = message;
			$('#emptyDialog').dialog('open');
		}
	}

	function elimina() {
		$(function() {
			$.post("/springSecurityIntegration/licenzecommerciali/Delete", {
				arrayId : listId.toString()
			}, function(data) {
				alert("Eliminazione Riuscita!");
				location.reload();
			});
		});
	}

	function getInfoServices(idLicComm) {
		var message = "";
		$(function() {
			$
					.post(
							"<c:url value='/licenzecommerciali/InfoServices' />",
							{
								sIdLicCommerciale : idLicComm
							},
							function(data) {
								for (i = 0; i < data.length; i++) {
									message = message + data[i] + "<br />";
								}
								document.getElementById("detailServicesDialog").innerHTML = message;
								$('#detailServicesDialog').dialog('open');
							});
		});
	}
</script>

</head>
<body>
<div id="art-page-background-simple-gradient"></div>
<div id="art-main"><br />
<div id="art-Sheet" class="provideCSS-3 art-Sheet artBlockCorners">
<div class="art-Sheet-body">
<div id="emptyDialog" title="Operazione non consentita"></div>
<div id="dialog-message_2" title="Operazione non consentita"></div>
<div id="detailServicesDialog" title="Info Servizi"></div>
<div id="modal" class="modal"><div id="textModal"></div></div>

<!--  Import dell'Header --> <%@include file='template/header.jspf'%>
<%@include file='template/topNav.jspf'%>

<div class="art-contentLayout">
<div class="art-content">

<div class="art-Post"><!--  TAG necessario per il mantenimento dei contenuti -->
<div class="art-Post-body">
<div class="art-Post-inner art-article">
<h2 class="art-PostHeader"><spring:message code="index.title" /></h2>

<div class="art-PostContent">
	<table id="indexTable">
		<tr>
			<td>
			<br /><br />
				<div id="divLink" class="provideCSS-3 divShadow indexIcons" onclick="location.href='istanzautentecpe/index';" style="cursor: pointer;" >
					<img src="<c:url value="/resources/images/indexLicense.png" />" />
    				<span><spring:message code="index.contractsLink.title" /></span>
    			</div>
			</td>
			<td>
			<br /><br />
				<div id="divLink" class="provideCSS-3 divShadow indexIcons" onclick="location.href='utente/index';" style="cursor: pointer;">
					<img src="<c:url value="/resources/images/indexUser.png" />" />
					<span><spring:message code="index.usersLink.title" /></span>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<br /><br />
				<div id="divLink" class="provideCSS-3 divShadow indexIcons" onclick="location.href='licenzecommerciali/index';" style="cursor: pointer;" >
					<img src="<c:url value="/resources/images/indexLicense.png" />" />
					<span><spring:message code="index.licCommLink.title" /></span>
			    </div>
			</td>
			<td>
			<br /><br />
				<div id="divLink" class="provideCSS-3 divShadow indexIcons" onclick="location.href='cpe/index';" style="cursor: pointer;" >
					<img src="<c:url value="/resources/images/indexShield.png" />"/>
					<span><spring:message code="index.VSGLink.title" /></span>
				</div>
			</td>
		</tr>
	</table>
</div>
<div class="cleared"></div>

</div>

<div class="cleared"></div>
</div>
</div>
</div>

<div class="art-sidebar1"><%@include file='template/leftMenu.jspf'%>

</div>
<!-- END art-sidebar1 --></div>
<div class="cleared"></div>
<%@include file="template/footer.jspf" %>
<!-- END aet-main -->
</body>
</html>