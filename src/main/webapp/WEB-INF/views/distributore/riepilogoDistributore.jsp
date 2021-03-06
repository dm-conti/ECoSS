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
											Subgestore</a>
									</h2>
									<div class="art-PostContent">
										</br>
										<div class='provideCSS-3 art-Block artBlockCorners'
											style='padding: 10px; margin: 0px;'>
											<div class=titleTab align="center">Subgestore creato con
												successo!</div>
											</br>
											<table id='tabs' height="300px">
												<tr valign=top>
													<td class=titleTab width=120px>Ragione Sociale:</td>
													<td>${distributore.distRagSociale}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Descrizione:</td>
													<td>${distributore.distDescrizione}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=120px>Username:</td>
													<td>${userId}</td>
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
													<td class=titleTab width=120px>Gestore:</td>
													<td>${distributore.gestore.gestRagSociale}</td>
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
										</br>
										<div align="right">
											<input type="button" value="Nuovo Subgestore" name="nuovo"
												class="button artBlockCorners"
												onclick=document.location.href="<c:url value="/distributore/New" />">
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