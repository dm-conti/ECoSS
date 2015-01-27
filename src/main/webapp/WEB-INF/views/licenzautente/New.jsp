
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
	href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/formToWizard.css" />"
	type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/stepy/jquery.stepy.css" />"
	type="text/css" media="screen" />

<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jquery.ui.all.css" />"
	type="text/css" />

<!--  IMPORT DI JQuery --SHARED-- -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.5.1.min.js" />"></script>
<script type="text/javascript">
	var jq = jQuery.noConflict();
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/jquery.ui.core.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/jquery.ui.widget.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/jquery.ui.datepicker.js" />"></script>

<script type="text/javascript">
	jq(document).ready(function() {
		
	});
	
	jq(function() {
		jq("#datepicker1").datepicker();
	});
	
	jq(function() {
		jq("#datepicker2").datepicker();
	});

	jq(function() {
		/* ---------------------------------------------------
		 * APPLICO STEPY AL FORM PER NUOVO UTENTE.
		 * ---------------------------------------------------*/
		jq('#newForm').stepy({
			backLabel : 'Indietro',
			block : true,
			errorImage : true,
			nextLabel : 'Avanti',
			titleClick : true,
			validate: true
		});
		document.getElementById("newForm-next-0").style.display="none";
		
		/* ----------------------------------------
		*  VALIDAZIONE DELLA FORM NUOVO UTENTE:
		*  Definizione delle regole.
		*  ---------------------------------------- */
		jq.validator.addMethod("uteNome", function(value, element){
			return this.optional(element) || /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/i.test(value);
		}, 
		"Inserire solo caratteri e lettere accentate.");
		
		jq.validator.addMethod("uteCF", function(value, element){
			return this.optional(element) || /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/i.test(value);
		}, 
		"Inserisci un Codice Fiscale valido.");
		
		jq.validator.addMethod("utePIVA", function(value, element){
			return this.optional(element) || /^[0-9]{11}$/i.test(value);
		}, 
		"Formato Partita IVA errato.");
		
		/* 
		jq.validator.addMethod("uteDescrizione", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
		}, 
		"Please enter a valid email address.");
		
		jq.validator.addMethod("uteDataCreazione", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
		}, 
		"Please enter a valid email address.");

		jq.validator.addMethod("uteIndirizzo",function(value,element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{3,16}$/i.test(value);
		},
		"Username are 3-15 characters"); 
		
		jq.validator.addMethod("uteCitta", function(value, element){
			return this.optional(element) || /^[0-9]{11}$/i.test(value);
		}, 
		"Formato Partita IVA errato.");
		*/
		
		jq.validator.addMethod("uteCap", function(value, element){
			return this.optional(element) || /^[0-9]{5}$/i.test(value);
		}, 
		"Formato CAP errato.");
		
		jq.validator.addMethod("uteTel", function(value, element){
			return this.optional(element) || /^[0-9]{10}$/i.test(value);
		}, 
		"Formato errato.");
		
		jq.validator.addMethod("uteEmail", function(value, element){
			return this.optional(element) || /^[a-z0-9_]+@[a-z0-9\-]+\.[a-z0-9\-\.]+$/i.test(value);
		}, 
		"Formato eMail errato.");
		
		/* 	-----------------------------------
			INVOCO ALLA PRESSIONE DI pData_next 
			LA VALIDAZIONE. SE LA VALIDAZIONE 
			E' SUPERATA SI HA IL SUBMIT DEL 
			FORM.
		*/
		
		jq('#newForm').validate({
			
		});
		
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
										<a href="#" title="Permanent Link to this Post">Nuova Licenza Utente</a>
									</h2>

									<c:url var="saveUrl" value="Add" />
									<br />


									<div class='provideCSS-3 art-Block artBlockCorners'
										style='padding: 10px; margin: 0px; height:390px;'>
											<form:form id="newForm" modelAttribute="licenzautenteAttribute" commandName="licenzautenteAttribute" method="POST" action="${saveUrl}">
												<fieldset title="Dati Licenza" class="step tabContents">
													<legend>1 di 1</legend>
													<p>
														
														<form:label path="licUteDescrizione">Descrizione: <span class="errorSmall">*</span></form:label>
														<form:input path="licUteDescrizione" class="required"/>
													</p>
													<p>
														<form:label path="dataAttivazione">Data Attivazione: <span class="errorSmall">*</span></form:label>
														<form:input path="dataAttivazione" class="required" id="datepicker1"/>
													</p>
													<p>
														<form:label path="dataCessazione">Data Cessazione: <span class="errorSmall">*</span></form:label>
														<form:input path="dataCessazione" class="required" id="datepicker2"/>
													</p>
													<p>
														<label>Stato:</label> 
														<form:select path="tabellastati.idstato">
															<c:forEach items="${tabellastati}" var="tabellastati">
																<option value="${tabellastati.idstato}">${tabellastati.stato}</option>
															</c:forEach>
														</form:select>
													</p>
													<p>
														<label>Licenza Commerciale:</label> 
														<form:select path="licenzecommerciali.idLicenzeCommerciali">
															<c:forEach items="${licenzecommerciali}" var="licenzecommerciali">
																<option value="${licenzecommerciali.idLicenzeCommerciali}">${licenzecommerciali.licomNomeLicenza}</option>
															</c:forEach>
														</form:select>
													</p>
													</br>
													<input type="submit" class="finish" value="Salva licenza" />
												</fieldset>								
												
												
										</form:form>
										
										<div id="uteSummary"></div>
									</div>
									</br><span class="errorSmall">* Campi obbligatori</span>
									<div class="art-PostContent"></div>
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