
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

<script type="text/javascript">
	jq(document).ready(function() {

	});

	jq(function() {
		/* ---------------------------------------------------
		 * APPLICO STEPY AL FORM PER NUOVO GESTORE.
		 * ---------------------------------------------------*/
		jq('#newForm').stepy({
			backLabel : 'Indietro',
			block : true,
			errorImage : true,
			nextLabel : 'Avanti',
			titleClick : true,
			validate: true
		});
		
		/* ----------------------------------------
		*  VALIDAZIONE DELLA FORM NUOVO GESTORE:
		*  Definizione delle regole.
		*  ---------------------------------------- */
		jq.validator.addMethod("gestNome", function(value, element){
			return this.optional(element) || /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/i.test(value);
		}, 
		"Inserire solo caratteri e lettere accentate.");
		
		jq.validator.addMethod("gestCF", function(value, element){
			return this.optional(element) || /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/i.test(value);
		}, 
		"Inserisci un Codice Fiscale valido.");
		
		jq.validator.addMethod("gestPIVA", function(value, element){
			return this.optional(element) || /^[0-9]{11}$/i.test(value);
		}, 
		"Formato Partita IVA errato.");
		
		/* 
		jq.validator.addMethod("gestDescrizione", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
		}, 
		"Please enter a valid email address.");
		
		jq.validator.addMethod("gestDataCreazione", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$/i.test(value);
		}, 
		"Please enter a valid email address.");

		jq.validator.addMethod("gestIndirizzo",function(value,element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{3,16}$/i.test(value);
		},
		"Username are 3-15 characters"); 
		
		jq.validator.addMethod("gestCitta", function(value, element){
			return this.optional(element) || /^[0-9]{11}$/i.test(value);
		}, 
		"Formato Partita IVA errato.");
		*/
		
		jq.validator.addMethod("gestCap", function(value, element){
			return this.optional(element) || /^[0-9]{5}$/i.test(value);
		}, 
		"Formato CAP errato.");
		
		jq.validator.addMethod("gestTel", function(value, element){
			return this.optional(element) || /^[0-9]{10}$/i.test(value);
		}, 
		"Formato errato.");
		
		jq.validator.addMethod("gestEmail", function(value, element){
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
	
	

	function ShowHideUsers() {
		jq("#utentiSlidingDiv").animate({
			"height" : "toggle"
		}, {
			duration : 500
		});
	}

	function ShowHideVSG() {
		jq("#vsgSlidingDiv").animate({
			"height" : "toggle"
		}, {
			duration : 500
		});
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
										<a href="#" title="Permanent Link to this Post">Nuovo Gestore</a>
									</h2>

									<c:url var="saveUrl" value="Add" />
									<br />


									<div class='provideCSS-3 art-Block artBlockCorners'
										style='padding: 10px; margin: 0px; height:530px;'>
											<form:form id="newForm" modelAttribute="gestoreAttribute" commandName="gestoreAttribute" method="POST" action="${saveUrl}">
												<fieldset title="Dati Personali" class="step tabContents">
													<legend>1 di 2</legend>
													<p>
														<form:label path="gestRagSociale">Ragione sociale: <span class="errorSmall">*</span></form:label>
														<form:input path="gestRagSociale" class="required" /><form:errors path="gestRagSociale" class="error"/>
													</p>
													<p>
														<form:label path="gestDescrizione">Descrizione:</form:label>
														<form:input path="gestDescrizione" /><form:errors path="gestDescrizione" class="error"/>
													</p>
													<p>
														<label>Username: <span class="errorSmall">*</span></label>
														<input type="text" id="userId" name="userId" class="required"/>
													</p>
													<p>
														<form:label path="gestCF">Codice Fiscale: <span class="errorSmall">*</span></form:label>
														<form:input path="gestCF" class="required" /><form:errors path="gestCF" class="error"/>
													</p>
													<p>
														<form:label path="gestPIVA">Partita IVA: <span class="errorSmall">*</span></form:label>
														<form:input path="gestPIVA" class="required" /><form:errors path="gestPIVA" class="error"/>
													</p>
												</fieldset>
												<fieldset title="Contatti" class="step tabContents">
													<legend>2 di 2</legend>
													<p>
														<form:label path="gestIndirizzo">Indirizzo: <span class="errorSmall">*</span></form:label>
														<form:input path="gestIndirizzo" class="required" /><form:errors path="gestIndirizzo" class="error"/>
													</p>
													<p>
														<form:label path="gestCitta">Città: <span class="errorSmall">*</span></form:label>
														<form:input path="gestCitta" class="required" /><form:errors path="gestCitta" class="error"/>
													</p>
													<p>
														<form:label path="gestCap">CAP: <span class="errorSmall">*</span></form:label>
														<form:input path="gestCap" class="required" /><form:errors path="gestCap" class="error"/>
													</p>
													<p>
														<form:label path="gestTel1">Telefono: <span class="errorSmall">*</span></form:label>
														<form:input path="gestTel1" class="required" /><form:errors path="gestTel1" class="error"/>
													</p>
													<p>
														<form:label path="gestTel2">Cellulare:</form:label>
														<form:input path="gestTel2" /><form:errors path="gestTel2" class="error"/>
													</p>
													<p>
														<form:label path="gestFax">Fax:</form:label>
														<form:input path="gestFax" /><form:errors path="gestFax" class="error"/>
													</p>
													<p>
														<form:label path="gestEmail">Email primaria: <span class="errorSmall">*</span></form:label>
														<form:input path="gestEmail" class="required" /><form:errors path="gestEmail" class="error"/>
													</p>

													<p>
														<form:label path="gestEmail2">Email secondaria:</form:label>
														<form:input path="gestEmail2" /><form:errors path="gestEmail2" class="error"/>
													</p>
													<br />
												</fieldset>
												<input type="submit" class="finish" value="Salva gestore" />
										</form:form>
										<div id="gestSummary"></div>
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