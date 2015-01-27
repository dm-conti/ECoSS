
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
		jq("#datepicker").datepicker();
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
		
		/* ----------------------------------------
		*  VALIDAZIONE DELLA FORM NUOVO UTENTE:
		*  Definizione delle regole.
		*  ---------------------------------------- */
		jq.validator.addMethod("cpeNome", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{6,20}$/i.test(value);
		}, "Inserire solo caratteri e lettere accentate, min 5 max 40.");

		jq.validator.addMethod("cpeModello", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{6,20}$/i.test(value);
		}, "Inserire solo caratteri e lettere accentate, min 5 max 45.");

		jq.validator.addMethod("cpeVersCore", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{0,20}$/i.test(value);
		}, "Inserire solo caratteri e lettere accentate, max 20.");

		jq.validator.addMethod("cpeVersCoreProg", function(value, element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{0,20}$/i.test(value);
		}, "Inserire solo caratteri e lettere accentate, max 20.");

		jq.validator.addMethod("cpeIpAddress", function(value, element){
			return this.optional(element) || /^([0-9]{1,3}([.]|$)){4}$/i.test(value);
		}, "Formato indirizzo ip errato 'XXX.XXX.XXX.XXX' .");

		jq.validator.addMethod("cpeMacAddress", function(value, element){
			return this.optional(element) || /^([0-9a-f]{2}([:-]|$)){6}$/i.test(value);
		}, "Formato indirizzo ip errato 'XX:XX:XX:XX:XX:XX' .");

		jq.validator.addMethod("cpeManagementKey", function(value, element){
			return this.optional(element) || /^([a-zA-Z0-9\xE0\xE8\xE9\xF9\xF2\xEC\x27]{128}\s?)+$/i.test(value);
		}, "Inserire solo caratteri e lettere accentate, max 128.");

		
		/* 	-----------------------------------
			INVOCO ALLA PRESSIONE DI pData_next 
			LA VALIDAZIONE. SE LA VALIDAZIONE 
			E' SUPERATA SI HA IL SUBMIT DEL 
			FORM.
		*/
		
		jq('#newForm').validate({
			errorPlacement: function(error, element) {
				document.getElementById("divErroreDati").style.display="inline";
			},
			success: function(){
				document.getElementById("divErroreDati").style.display="none";
			},
			/*submitHandler: function(form) {
				jq("#newForm").submit();
			}, */
			rules: {
				'cpeNome': "required cpeNome",
				'cpeModello': "required cpeModello",
				'cpeVersCore': "cpeVersCore",
				'cpeVersCoreProg': "cpeVersCoreProg",
				'cpeIpAddress': "required cpeIpAddress",
				'cpeMacAddress': "required cpeMacAddress",
				'cpeDataAttivazione': "required",
				'cpeManagementKey': "required cpeManagementKey"
				//'cpeStato': "required",
			}	
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
										<a href="#" title="Permanent Link to this Post">Nuovo Vsg</a>
									</h2>

									<c:url var="saveUrl" value="Add" />
									<br />


									<div class='provideCSS-3 art-Block artBlockCorners'
										style='padding: 10px; margin: 0px; height:430px;'>
											<form:form id="newForm" modelAttribute="cpeAttribute" commandName="cpeAttribute" method="POST" action="${saveUrl}">
												<fieldset title="Dati Vsg" class="step tabContents">
													<legend>1 di 2</legend>
													<p>
														
														<form:label path="cpeNome">Nome Cpe: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeNome" class="required"/>
													</p>
													<p>
														<form:label path="cpeModello">Modello: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeModello" class="required" />
													</p>
													<p>
														<form:label path="cpeVersCore">Versione Core: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeVersCore" class="required"/>
													</p>
													<p>
														<form:label path="cpeVersCoreProg">Programma: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeVersCoreProg" class="required" />
													</p>
													<p>
														<form:label path="cpeIpAddress">IP Address: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeIpAddress" class="required"/>
													</p>
													<p>
														<label>Gestore:</label>
														<form:select path="gestore.gestRagSociale">
															<c:forEach items="${listaGestori}" var="gestore">
																<option value="${gestore.gestRagSociale}">${gestore.gestRagSociale}</option>
															</c:forEach>
														</form:select>
													</p>
												</fieldset>
												<fieldset title="Dati Vsg" class="step tabContents">
													<legend>2 di 2</legend>
													<p>
														<form:label path="cpeMacAddress">MAC Address: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeMacAddress" class="required" />
													</p>
													<p>
														<form:label path="cpeManagementKey">Management Key: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeManagementKey" class="required" />
													</p>
													<p>
														<form:label path="cpeCompanyKey">Company Key: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeCompanyKey"  />
													</p>
													<p>
														<form:label path="cpeRegistrationPort">Registration Port: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeRegistrationPort" class="required" />
													</p>
													<p>
														<form:label path="cpeDataAttivazione">Data Attivazione: <span class="errorSmall">*</span></form:label>
														<form:input path="cpeDataAttivazione" class="required" id="datepicker"/>
													</p>
													
													<br />
													
												</fieldset>
												<input type="submit" class="finish" value="Salva vsg" />
												
										</form:form>
										<label id="divErroreDati" class="error" style="position:relative; top:450px; left:-560px; display:none">Inserire dati corretti</label>
										
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