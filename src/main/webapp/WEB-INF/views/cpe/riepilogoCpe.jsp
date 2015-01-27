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
										<a href="#" title="Permanent Link to this Post">Riepilogo
											Vsg</a>
									</h2>
									<div class="art-PostContent">
										</br>
										<div class='provideCSS-3 art-Block artBlockCorners'
											style='padding: 10px; margin: 0px;'>
											<div class=titleTab align="center">Vsg creato con
												successo!</div>
											</br>
											<table id='tabs' height="300px">
												<tr valign=top>
													<td class=titleTab width=120px>Nome Cpe:</td>
													<td>${cpe.cpeNome}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Modello:</td>
													<td>${cpe.cpeModello}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Versione Core:</td>
													<td>${cpe.cpeVersCore}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Programma:</td>
													<td>${cpe.cpeVersCoreProg}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>IP Address:</td>
													<td>${cpe.cpeIpAddress}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>MAC Address:</td>
													<td>${cpe.cpeMacAddress}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Management Key:</td>
													<td>
													<script>
													var cpeManagementKey = "${cpe.cpeManagementKey}";
													var tmpCpeManagementKey="";
													for(i=0; i<=cpeManagementKey.length;i++){
														tmpCpeManagementKey += cpeManagementKey.slice(i,i+10)+" ";
														i=i+10;
													}
													document.write(tmpCpeManagementKey);
													</script>
													
													
													</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Company Key:</td>
													<td>${cpe.cpeCompanyKey}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Registration Port:</td>
													<td>${cpe.cpeRegistrationPort}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Data Attivazione:</td>
													<td>
													<fmt:formatDate value="${cpe.cpeDataAttivazione}" type="date" pattern="dd MMM yyyy"/>
													</td>
												</tr>
												
											</table>
										</div>
										</br>
										<div align="right">
											<input type="button" value="Nuovo Vsg" name="nuovo"
												class="button artBlockCorners"
												onclick=document.location.href="<c:url value="/cpe/New" />">
												<input type="button" value="Home" name="home"
												class="button artBlockCorners"
												onclick=document.location.href="<c:url value="/index" />">
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