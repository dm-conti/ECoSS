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

function showGestore(){
	if(document.getElementById('divDettagliGestore').style.display == "none"){
		document.getElementById('divDettagliGestore').style.display = "inline";
		document.getElementById('divDettagliDistributore').style.display = "none";
		document.getElementById('linkGestore').style.fontWeight = "bold";
		document.getElementById('linkDistributore').style.fontWeight = "normal";
	}
	else{
		document.getElementById('divDettagliGestore').style.display = "none";
		document.getElementById('linkGestore').style.fontWeight = "normal";
	}
}

function showDistributore(){	
	if(document.getElementById('divDettagliDistributore').style.display == "none"){
		document.getElementById('divDettagliDistributore').style.display = "inline";
		document.getElementById('divDettagliGestore').style.display = "none";
		document.getElementById('linkDistributore').style.fontWeight = "bold";
		document.getElementById('linkGestore').style.fontWeight = "normal";
	}
	else{
		document.getElementById('divDettagliDistributore').style.display = "none";
		document.getElementById('linkDistributore').style.fontWeight = "normal";
	}
	
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
				<%@include file='../template/topNav.jspf'%>

				<div class="art-contentLayout">
					<div class="art-content">
						<div class="art-Post">
							<!--  TAG necessario per il mantenimento dei contenuti -->
							<div class="art-Post-body">
								<div class="art-Post-inner art-article">
									<h2 class="art-PostHeader">
										<a href="#" title="Permanent Link to this Post">Riepilogo Utente</a>
									</h2>
									<div class="art-PostContent">
									<br />
									<div id="userDetail_one" class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px;'>
										<div class=titleTab align="center"></div>
										<br />
										<img src="<c:url value='/resources/images/User_120.png' />" style="float: left; border: none;"/>
										<table id='userTab' class="linkDetails" style="height:300px;" >
											<tr valign=top>
												<td class=titleTab width=120px>Nome:</td>
												<td>${utente.uteNome}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cognome:</td>
												<td>${utente.uteCognome}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Username:</td>
												<td>${utente.credenzialiutente.userId}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Codice Fiscale:</td>
												<td>${utente.uteCF}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Partita Iva:</td>
												<td>${utente.utePIVA}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Descrizione:</td>
												<td>${utente.uteDescrizione}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Indirizzo:</td>
												<td>${utente.uteIndirizzo}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Città:</td>
												<td>${utente.uteCitta}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>CAP:</td>
												<td>${utente.uteCap}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Telefono:</td>
												<td>${utente.uteTel1}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cellulare:</td>
												<td>${utente.uteTel2}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Fax:</td>
												<td>${utente.uteFax}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email primaria:</td>
												<td>${utente.uteEmail}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email secondaria:</td>
												<td>${utente.uteEmail2}</td>
											</tr>
										</table>
									</div>
									<br />
									<div id="userDetail_two" class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px;'>
										<div id="divManager"  class="linkDetails" style="width: 150px; text-align: center; font-size: 14px; color: black; text-decoration: none;  display: inline-block;">
											<img src="<c:url value='/resources/images/Manager_100.png' />" style="border:none;"/>
											<span id="linkGestore">
												<a href="javascript:void(0)" onclick="showGestore();"  ><c:out value="${utente.gestore.gestRagSociale}" /></a>
											</span>
										</div>
										<div id="divProvider" class="linkDetails" style="width: 150px; text-align: center; font-size: 14px; color: black; text-decoration: none; display: inline-block;">
											<c:if test="${utente.distributore != null}">
												<img src="<c:url value='/resources/images/Provider_100.png' />" style="border: none;"/>
												<span id="linkDistributore">
													<a href="javascript:void(0)" onclick="showDistributore();"  ><c:out value="${utente.distributore.distRagSociale}" /></a></span>
											</c:if>
										</div> 
										</br> </br> 
										<div id="divDettagliGestore" style="display: none; position:relative; left:20px">
										</br> 
										<span class=titleTab> DETTAGLI GESTORE </span> </br>
												</br> 
											<table id='tabs' height="320px">
														<tr valign=top>
												<td class=titleTab width=120px>Ragione Sociale:</td>
												<td>${utente.gestore.gestRagSociale}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Codice Fiscale:</td>
												<td>${utente.gestore.gestCF}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Partita Iva:</td>
												<td>${utente.gestore.gestPIVA}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Descrizione:</td>
												<td>${utente.gestore.gestDescrizione}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Indirizzo:</td>
												<td>${utente.gestore.gestIndirizzo}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Città:</td>
												<td>${utente.gestore.gestCitta}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>CAP:</td>
												<td>${utente.gestore.gestCap}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Telefono:</td>
												<td>${utente.gestore.gestTel1}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cellulare:</td>
												<td>${utente.gestore.gestTel2}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Fax:</td>
												<td>${utente.gestore.gestFax}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email primaria:</td>
												<td>${utente.gestore.gestEmail}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email secondaria:</td>
												<td>${utente.gestore.gestEmail2}</td>
											</tr>
													</table>
										</div>
										
										<div id="divDettagliDistributore" style="display: none; position:relative; left:20px">
										</br> 
											<span class=titleTab> DETTAGLI DISTRIBUTORE </span> </br>
												</br> 
												<table id='tabs' height="320px">
														<tr valign=top>
												<td class=titleTab width=120px>Ragione Sociale:</td>
												<td>${utente.distributore.distRagSociale}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Username:</td>
												<td>${utente.distributore.credenzialiutente.userId}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Codice Fiscale:</td>
												<td>${utente.distributore.distCF}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Partita Iva:</td>
												<td>${utente.distributore.distPIVA}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Descrizione:</td>
												<td>${utente.distributore.distDescrizione}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Indirizzo:</td>
												<td>${utente.distributore.distIndirizzo}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Città:</td>
												<td>${utente.distributore.distCitta}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>CAP:</td>
												<td>${utente.distributore.distCap}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Telefono:</td>
												<td>${utente.distributore.distTel1}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Cellulare:</td>
												<td>${utente.distributore.distTel2}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Fax:</td>
												<td>${utente.distributore.distFax}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email primaria:</td>
												<td>${utente.distributore.distEmail}</td>
											</tr>
											<tr valign=top>
												<td class=titleTab width=120px>Email secondaria:</td>
												<td>${utente.distributore.distEmail2}</td>
											</tr>
													</table>
										</div>
										
									</div>
									<br />
									<div align="right">
										<input type="button" value="Torna indietro" name="back" class="button artBlockCorners" onclick=history.back() " />
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