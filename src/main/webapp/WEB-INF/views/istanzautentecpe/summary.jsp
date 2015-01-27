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

<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Ecoss</title>
	<script language="javascript">
		if(history.length>0)
			history.forward();
	</script>
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	
	<!-- 
	<link rel="stylesheet" href="<c:url value="/resources/css/dialogs.css" />" type="text/css" media="screen" /> 
	<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css"/> -->
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
		
	function showDettagli(idDiv, idVariabile){
		if(document.getElementById(idDiv).style.display == "none")
			document.getElementById(idDiv).style.display = "inline";
			
		else
			document.getElementById(idDiv).style.display = "none";
	}
	
	/* -------------------------------
	 * SERVE A SETTARE CORRETTAMENTE
	 * IL COLORE DEI PASSI
	 -------------------------------*/
	function setCurrentStep(idStep){
		if(idStep > 0){
			var idPreviousStep = idStep-4; 
			jq("#callback-title-"+idPreviousStep).removeClass("current-step");
			jq("#callback-title-"+idStep).addClass("current-step");
		}
	}

	jq(function() {
		
		//SETTO LO STEP CORRENTE
		setCurrentStep(4);

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
            
            <!--  Import dell'Header -->
            <%@include file='../template/header.jspf'%>
            <%@include file='../template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
						<h2 class="art-PostHeader">
						<a href="#" title="Permanent Link to this Post"><spring:message code="usersList" /></a>
					</h2>
					
					<div class="art-PostContent">
						
						<ul class="stepy-titles">
							<li id="callback-title-0" class="current-step">
							Step 1
							<span>Nuovo Contratto</span>
							</li>
							<li id="callback-title-1" >
							Step 2
							<span>Utente</span>
							</li>
							<li id="callback-title-2" >
							Step 3
							<span>Licenza Utente</span>
							</li>
							<li id="callback-title-3" >
							Step 4
							<span>Licenza Commerciali</span>
							</li>
							<li id="callback-title-4" >
							Finish
							<span>Summary</span>
							</li>
						</ul>
						<div id="uteSummary" class='provideCSS-3 art-Block artBlockCorners' style='padding:10px; margin:0px;'>
						<span class=titleTab>
						RIEPILOGO CONTRATTO
						</span>
						<br /><br />
							<span class=titleTab>
							<a href="#" onclick="showDettagli('dettagliVsgRiepilogo');"> DETTAGLI VSG</a>
							</span>
							<br />
							<div id="dettagliVsgRiepilogo" style="display:inline">
								<c:set var="vsg" value="${newContratto.cpe}" />
								<table id='tabs' height="240px">
												<tr valign=top>
													<td class=titleTab width=150px>Nome Cpe:</td>
													<td>${vsg.cpeNome}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Modello:</td>
													<td>${vsg.cpeModello}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Versione Core:</td>
													<td>${vsg.cpeVersCore}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Programma:</td>
													<td>${vsg.cpeVersCoreProg}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>IP Address:</td>
													<td>${vsg.cpeIpAddress}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>MAC Address:</td>
													<td>${vsg.cpeMacAddress}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Management Key:</td>
													<td>
													<script>
													var cpeManagementKey = "${vsg.cpeManagementKey}";
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
													<td class=titleTab width=150px>Company Key:</td>
													<td>${vsg.cpeCompanyKey}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Registration Port:</td>
													<td>${vsg.cpeRegistrationPort}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Data Attivazione:</td>
													<td>
													<fmt:formatDate value="${vsg.cpeDataAttivazione}" type="date" pattern="dd MMM yyyy"/>
													</td>
												</tr>
												
											</table>
							</div>
							<br />
							<span class=titleTab>
							<a href="#" onclick="showDettagli('dettagliUtenteRiepilogo');"> DETTAGLI UTENTE</a>
							</span><br />
							<div id="dettagliUtenteRiepilogo" style="display:inline">
								<c:set var="utente" value="${newContratto.utente}" />
								<table id='tabs' height="300px">
												<tr valign=top>
													<td class=titleTab width=150px>Nome:</td>
													<td>${utente.uteNome}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Cognome:</td>
													<td>${utente.uteCognome}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Username:</td>
													<td>${utente.credenzialiutente.userId}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Codice Fiscale:</td>
													<td>${utente.uteCF}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Partita Iva:</td>
													<td>${utente.utePIVA}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Descrizione:</td>
													<td>${utente.uteDescrizione}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Gestore:</td>
													<td>${utente.gestore.gestRagSociale}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Distributore:</td>
													<td>${utente.distributore.distRagSociale}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Indirizzo:</td>
													<td>${utente.uteIndirizzo}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Città:</td>
													<td>${utente.uteCitta}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>CAP:</td>
													<td>${utente.uteCap}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Telefono:</td>
													<td>${utente.uteTel1}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Cellulare:</td>
													<td>${utente.uteTel2}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Fax:</td>
													<td>${utente.uteFax}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Email primaria:</td>
													<td>${utente.uteEmail}</td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Email secondaria:</td>
													<td>${utente.uteEmail2}</td>
												</tr>
											</table>
							</div>
							<br />
							<span class=titleTab>
							<a href="#" onclick="showDettagli('dettagliLicenzaRiepilogo');"> DETTAGLI LICENZA</a>
							</span><br />
							<div id="dettagliLicenzaRiepilogo" style="display:inline">
								<c:set var="licComm" value="${newContratto.licenzautente.licenzecommerciali}" />
								<table id='tabs' height="110px">
												<tr valign=top>
													<td class=titleTab width=150px>Licenza Commerciale:</td>
													<td>${licComm.licomNomeLicenza}</td>
												</tr>
											</table>
							</div>
						</div>
						<form:form method="POST"  modelAttribute="newContratto" id="callback" >
							<input type="hidden" value="4" name="_page" />
							<br />
							<input type="submit" value="Indietro" name="_target3" class="button artBlockCorners" />
							<input type="submit" value="Salva"  name="_finish" class="button artBlockCorners" />
						</form:form>
				</div><!-- END -art-Post- -->
				</div><!-- END -art-content- -->
			</div>
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