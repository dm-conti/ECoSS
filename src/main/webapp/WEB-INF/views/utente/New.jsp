<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<title>Ecoss</title>

	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/stepy/jquery.stepy.css" />" type="text/css" media="screen" />

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.5.1.min.js" />"></script>
	
	<script type="text/javascript"> 
		var jq = jQuery.noConflict();
	</script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
	
	<script type="text/javascript">

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
		jq.validator.addMethod("uteNome", function(value, element){
			return this.optional(element) || <fmt:message key="uteTest" />i.test(value);
		}, '<fmt:message key="uteNome.format" />');
		
		jq.validator.addMethod("uteCF", function(value, element){
			return this.optional(element) || /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$/i.test(value);
		}, '<fmt:message key="uteCF.format" />');
		
		jq.validator.addMethod("utePIVA", function(value, element){
			return this.optional(element) || /^[0-9]{11}$/i.test(value);
		}, '<fmt:message key="utePIVA.format" />');
		
		jq.validator.addMethod("uteCap", function(value, element){
			return this.optional(element) || /^[0-9]{5}$/i.test(value);
		}, '<fmt:message key="uteCAP.format" />' );
		
		jq.validator.addMethod("uteTel", function(value, element){
			return this.optional(element) || /^[0-9]{10}$/i.test(value);
		}, '<fmt:message key="uteTel.format" />' );
		
		jq.validator.addMethod("uteEmail", function(value, element){
			return this.optional(element) || /^[a-z0-9_]+@[a-z0-9\-]+\.[a-z0-9\-\.]+$/i.test(value);
		}, '<fmt:message key="uteEmail.format" />' );
		
		/* 	-----------------------------------
			INVOCO ALLA PRESSIONE DI pData_next 
			LA VALIDAZIONE. SE LA VALIDAZIONE 
			E' SUPERATA SI HA IL SUBMIT DEL 
			FORM.
		*/
		jq('#newForm').validate({
			rules: {
				'uteNome': "uteNome",
				'uteCognome': "uteNome",
				'uteCF': "uteCF",
				'utePIVA': "utePIVA",
				'uteCap': "uteCap",
				'uteTel1': "uteTel",
				'uteTel2': "uteTel",
				'uteFax': "uteTel",
				uteEmail: {
				      required: true,
				      email: true,
				},
				'uteEmail2': "uteEmail",
				userId: {
		            required: true,
		            minlength: 2,
		            remote: "<c:url value='/credenzialiutente/userIdIsExisted' />"
		        }
			},
			messages: {
				userId: {
		            required: '<fmt:message key="userId.required" />',
		            minlength: jQuery.format('<fmt:message key="userId.minlength" />'),
		            remote: jQuery.format('<fmt:message key="userId.unique" />')
				},
				uteEmail: {
	            	required: '<fmt:message key="uteEmail.required" />',
	            	email: '<fmt:message key="uteEmail.format" />'
				}
			}
	});

	function ShowHideUsers() {
		jq("#utentiSlidingDiv").animate({
			"height" : "toggle"
		}, {
			duration : 500
		});
	}

	function ShowHideVSG() {
		jq("#vsgSlidingDiv").animate(
				{ "height" : "toggle" }, 
				{ duration : 500 }
		);
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
										<a href="#" title="Permanent Link to this Post">Nuovo Utente</a>
									</h2>

									<c:url var="saveUrl" value="Add" />
									<br />


									<div class='provideCSS-3 art-Block artBlockCorners'
										style='padding: 10px; margin: 0px; height:530px; width: 99%'>
											<form:form id="newForm" modelAttribute="utenteAttribute" commandName="utenteAttribute" method="POST" action="${saveUrl}">
												<fieldset title="Dati Personali" class="step tabContents">
													<legend>1 di 2</legend>
													<p>
														
														<form:label path="uteNome">Nome: <span class="errorSmall">*</span></form:label>
														<form:input path="uteNome" class="required"/>
													</p>
													<p>
														<form:label path="uteCognome">Cognome: <span class="errorSmall">*</span></form:label>
														<form:input path="uteCognome" class="required" />
													</p>
													
													<p>
														<form:label path="credenzialiutente.userId">Username: <span class="errorSmall">*</span></form:label>
														<!-- <form:input type="hide" path="credenzialiutente.userId" class="required" name="userId"/> -->
														<input type="text" id="userId" name="userId" class="required"/>
													</p>
													
													<!--
													<p>
														<label>Username: <span class="errorSmall">*</span></label>
														<input type="text" id="userId" name="userId" class="required"/>
														<script>
														var usernameEsistente = ${usernameEsistente};
														if(usernameEsistente == true)
															document.write("<br />" +
																	"<label style=color:red>" +
																	"Username già esistente, inserire un altro Username" +
																	"</label>"
																	);
														</script>
													</p>
													 -->
													 
													<p>
														<form:label path="uteCF">Codice Fiscale: <span class="errorSmall">*</span></form:label>
														<form:input path="uteCF" class="required"/>
													</p>
													<p>
														<form:label path="utePIVA">Partita IVA: <span class="errorSmall">*</span></form:label>
														<form:input path="utePIVA" class="required" />
													</p>
													<p>
														<form:label path="uteDescrizione">Descrizione:</form:label>
														<form:input path="uteDescrizione" />
													</p>
													<p>
														<label>Gestore:</label>
														<form:select path="gestore.gestRagSociale">
															<c:forEach items="${listaGestori}" var="gestore">
																<option value="${gestore.gestRagSociale}">${gestore.gestRagSociale}</option>
															</c:forEach>
														</form:select>
													</p>
													<p>
														<label>Subgestore:</label> 
														<form:select path="distributore.distRagSociale">
															<c:forEach items="${listaDistributori}" var="distributore">
																<option value="${distributore.distRagSociale}">${distributore.distRagSociale}</option>
															</c:forEach>
														</form:select>
													</p>
												</fieldset>
												<fieldset title="Contatti" class="step tabContents">
													<legend>2 di 2</legend>
													<p>
														<form:label path="uteIndirizzo">Indirizzo: <span class="errorSmall">*</span></form:label>
														<form:input path="uteIndirizzo" class="required" />
													</p>
													<p>
														<form:label path="uteCitta">Città: <span class="errorSmall">*</span></form:label>
														<form:input path="uteCitta" class="required" />
													</p>
													<p>
														<form:label path="uteCap">CAP: <span class="errorSmall">*</span></form:label>
														<form:input path="uteCap" class="required" />
													</p>
													<p>
														<form:label path="uteTel1">Telefono: <span class="errorSmall">*</span></form:label>
														<form:input path="uteTel1" class="required" />
													</p>
													<p>
														<form:label path="uteTel2">Cellulare:</form:label>
														<form:input path="uteTel2" />
													</p>
													<p>
														<form:label path="uteFax">Fax:</form:label>
														<form:input path="uteFax" />
													</p>
													<p>
														<form:label path="uteEmail">Email primaria: <span class="errorSmall">*</span></form:label>
														<form:input path="uteEmail" class="required" />
													</p>

													<p>
														<form:label path="uteEmail2">Email secondaria:</form:label>
														<form:input path="uteEmail2" />
													</p>
													<br />
													
												</fieldset>
												<input type="submit" class="finish" value="Salva utente" />
												
										</form:form>
								
									</div>
									<br /><span id="fillAlert" class="errorSmall">* Campi obbligatori</span>
									
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