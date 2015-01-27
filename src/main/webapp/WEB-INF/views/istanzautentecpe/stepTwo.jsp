<%@page import="java.util.Date"%>
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

<% 
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", 0); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" ></meta>
    <meta http-equiv="Cache-Control" content="no-cache" ></meta>
    <meta http-equiv="pragma" content="no-cache" ></meta>
    
    <c:if test=" ${pageContext.session} == null}">
    	<c:redirect url="/login"/>
	</c:if>
<title>Ecoss</title>
<script language="javascript">
		if(history.length>0)
			history.forward();
	</script>
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />"
	type="text/css" media="screen" />
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
	src="<c:url value="/resources/js/jquery/jquery-ui-1.8.11.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>

<script type="text/javascript">

	<c:if test='${utente == "Seleziona" }'>
		var dataRows = ${listaUtenti} ;
	</c:if>

	var newFormValues = null;
	
	function controlla(){
		if(jq("#_idUtente").val()==""){
			alert('<fmt:message key="alertUser" />');
			return false;
		}
		else
			return true;
	}
	
	/* -------------------------------
	 * SERVE A SETTARE CORRETTAMENTE
	 * IL COLORE DEI PASSI
	 -------------------------------*/
	function setCurrentStep(idStep){
		if(idStep > 0){
			var idPreviousStep = idStep-1; 
			jq("#callback-title-"+idPreviousStep).removeClass("current-step");
			jq("#callback-title-"+idStep).addClass("current-step");
		}
	}
	
	jq(function() {
		
		//SETTO LO STEP CORRENTE
		setCurrentStep(1);

		if("${utente}" == "Nuovo"){
			
			/* ---------------------------------------------------
			 * INIZIALMENTE NASCONDO I BOTTONI PER LA NAVIGAZIONE
			 * DEL WIZARD. 
			 ----------------------------------------------------*/
			jq("#avantiButton").hide();
			
			/* ---------------------------------------------------
			 * APPLICO STEPY AL FORM PER NUOVO UTENTE.
			 * ---------------------------------------------------*/
			jq('#newForm').stepy({
				backLabel:	'Indietro',
				block:		true,
				errorImage:	true,
				nextLabel:	'Avanti',
				titleClick:	true,
				validate:	true
			});
		}

		<c:if test='${utente == "Seleziona" }'>
				/* ------------------------------------
				 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
				 --------------------------------------*/ 
				jq("#grid").jqGrid({
					data: dataRows,
					datatype: "local",
					rowNum:10,
				   	rowList:[10,20,40],
				   	width: "100%",
				   	height: "100%",
				   	colNames:['idUtente', '<fmt:message key="name" />',
				   	       		'<fmt:message key="lastName" />',	'<fmt:message key="taxCode" />',
				   	       		'<fmt:message key="VAT" />',		'<fmt:message key="description" />',	
				   	       		'<fmt:message key="address" />',	'<fmt:message key="city" />',			
								'<fmt:message key="zipCode" />',	'<fmt:message key="phone" />',			
								'<fmt:message key="cellPhone" />',	'<fmt:message key="fax" />',			
								'<fmt:message key="eMail" />',		'<fmt:message key="eMail2" />',
								'<fmt:message key="userId" />' /*,	'<fmt:message key="nContracts" />'*/],
					colModel:[
						   		{name:'idUtente',index:'idUtente', width:55, hidden:true, editable:true},
						   		{name:'uteNome',index:'uteNome', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteCognome',index:'uteCognome', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteCF',index:'uteCF', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'utePIVA',index:'utePIVA', width:100, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteDescrizione',index:'uteDescrizione', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'uteIndirizzo',index:'uteIndirizzo', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'uteCitta',index:'uteCitta', width:100, hidden:false, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteCap',index:'uteCap', width:100, editrules:{edithidden:true, required:true}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'uteTel1',index:'uteTel1', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteTel2',index:'uteTel2', width:100, editrules:{edithidden:true, required:false}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'uteFax',index:'uteFax', width:100, editable:true, editoptions: { size: 35}},
						   		{name:'uteEmail',index:'uteEmail', width:100, editable:true, editoptions: { size: 35}, editrules:{edithidden:true, required:true}},
						   		{name:'uteEmail2',index:'uteEmail2', width:100, editrules:{edithidden:true, required:false}, editable:true, hidden:true, editoptions: { size: 35}},
						   		{name:'credenzialiutente.userId',index:'credenzialiutente.userId', width:100, editrules:{edithidden:true, required:false}, editable:true, hidden:true, editoptions: { size: 35}},
						   		//{name:'contratti', index:'contratti', width:90, editoptions: { size: 90}, formatter:haveContracts}   		
					],
				   	postData: { },
				   	autowidth: true,
					rownumbers: true,
				   	pager: '#pager',
				    viewrecords: true,
				    caption:'<fmt:message key="usersList" />',
				    emptyrecords: "Empty records",
				    loadonce: false,	
				    
					onSelectRow : function(id, isChecked) {
						var grid = jq("#grid");
		
						var nPage = grid.jqGrid('getGridParam', 'page');
						var nRow = grid.jqGrid('getGridParam', 'rowNum');
						var idUtente = grid.jqGrid('getCell', id,'idUtente');
						var uteEamil = grid.jqGrid('getCell', id, 'uteEmail');
						var selectedRow;
						var arrayIds = grid.jqGrid('getGridParam', 'selarrrow');
		
						jq("#_idUtente").val(idUtente);
						selectedRow = grid.jqGrid('getRowData', id);
							
						// Setto variabile nascosta id utente per invio email
						jq("#uteEmail").val(idUtente);
							
						//Devo costruire l'URL come quello usato quando edito un elemento
						grid.jqGrid('setGridParam', { url : "Edit?" + idUtente, page : 1 });
							
						// Visualizzazione dettagli utente
						document.getElementById('divDettagliUtente').style.display = "inline";
		
						for(var key in selectedRow){
							if(selectedRow[key] != null){
								var tmpKey = key.replace(/\./g,"_");
								var tmpHtml = selectedRow[key];
								jq("#d"+tmpKey).html(tmpHtml);
							}
						}				
					}//END - onSelectRow
				});
		
				function haveContracts(cellvalue, options, rowObject) {
					var link = "<c:url value='/utente/Details?idUtente="+rowObject.idUtente+"' />"; 
					return "<a href='"+link+"' >"+cellvalue+"</a>";
			}		
		</c:if>

		function ShowHideUsers(){
			jq("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}

		function ShowHideVSG(){
			jq("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}		
		
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
				'uteNome': "uteNome required",
				'uteCognome': "uteNome required",
				'uteCF': "uteCF",
				'utePIVA': "utePIVA",
				'uteCap': "uteCap",
				'uteTel1': "uteTel",
				'uteTel2': "uteTel",
				'uteFax': "uteTel",
				gestRagSociale: "required",
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
			},
			submitHandler: function(form) {
				newFormValues = jq("#newForm").serializeArray();
				
				jq.post(
						"<c:url value='/istanzautentecpe/makeTmpUser' />",
						newFormValues,
						function(data){
							setSummary(newFormValues);
							jq("#avantiButton").fadeIn(1000);
							jq("#_idUtente").show();
							jq("#_idUtente").val(data);
						}
				);
			}
		});


		function setSummary(dataArray){
			jq("#wizard").hide();
			jq("#fillAlert").hide();

			// Visualizzazione dettagli utente
			jq("#divDettagliUtente").show();
			
			jq.each(dataArray, function(i, field) {
			    jq("#d"+field.name).html(field.value);

			});
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
				<%@include file='../template/topNav.jspf'%>

				<div class="art-contentLayout">
					<div class="art-content">
						<div class="art-Post">
							<!--  TAG necessario per il mantenimento dei contenuti -->
							<h2 class="art-PostHeader">
								<a href="#" title="Permanent Link to this Post"><spring:message
										code="usersList" />
								</a>
							</h2>

							<div class="art-PostContent">

								<ul class="stepy-titles">
									<li id="callback-title-0" class="current-step">Step 1 <span>Nuovo Contratto</span></li>
									<li id="callback-title-1">Step 2 <span>Utente</span></li>
									<li id="callback-title-2">Step 3 <span>VSG</span></li>
									<li id="callback-title-2">Step 4 <span>Licenza Utente</span></li>
									<li id="callback-title-3">Finish <span>Summary</span></li>
								</ul>

								<c:if test='${utente == "Seleziona" }'>
									<table id="grid"></table>
									<div id="pager"></div>
								</c:if>

								<c:if test='${utente == "Nuovo" }'>
									<div id="wizard" class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px; height: 530px;'>
										<form:form id="newForm" method="POST" modelAttribute="newUtente" >
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
														
														<div id="divUsernameEsistente" style="display:none">
														<label style=color:red>
														Username già esistente, inserire un altro Username
														</label>
														</div>
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
														<form:select id="gestRagSociale" path="gestore.gestRagSociale">
															<c:forEach items="${listaGestori}" var="gestore">
																<option value="${gestore.gestRagSociale}">${gestore.gestRagSociale}</option>
															</c:forEach>
														</form:select>
													</p>
													<p>
														<label>Subgestore:</label> 
														<form:select path="distributore.distRagSociale">
															<option value=""></option>
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
											<br /> <input type="submit" class="finish" value="Salva utente" />
										</form:form>	
									</div>
									<br /><span id="fillAlert" class="errorSmall">* Campi obbligatori</span>
								</c:if>
								<br />
								
								<div id="divDettagliUtente" style="display: none;">
										<div class='provideCSS-3 art-Block artBlockCorners' style='padding: 10px; margin: 0px;'>
											<span id='titleTab' class="titleTab" style="float: left;"> DETTAGLI UTENTE </span> <br />
											<br /> 
											<table id='tabs' class="linkDetails" height="350px">
												<tr valign=top>
													<td class=titleTab>Nome:</td>
													<td><div id="duteNome"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Cognome:</td>
													<td><div id="duteCognome"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Codice fiscale:</td>
													<td><div id="duteCF"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Partiva Iva:</td>
													<td><div id="dutePIVA"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Descrizione:</td>
													<td><div id="duteDescrizione"></div></td>
												</tr>														
												<tr valign=top>
													<td class=titleTab>Indirizzo:</td>
													<td><div id="duteIndirizzo"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Città:</td>
													<td><div id="duteCitta"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Cap:</td>
													<td><div id="duteCap"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Telefono:</td>
													<td><div id="duteTel1"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Cellulare:</td>
													<td><div id="duteTel2"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Fax:</td>
													<td><div id="duteFax"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Email primaria:</td>
													<td><div id="duteEmail"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab>Email secondaria:</td>
													<td><div id="duteEmail2"></div></td>
												</tr>
											</table>
											<br /><br /> 
										</div>
									</div>

								<form:form method="POST" modelAttribute="newContratto" >
									<input type="hidden" value="" name="_idUtente" id="_idUtente" required="required" />
									<input type="hidden" value="1" name="_page" />
									<br />
									<input type="submit" value="Indietro" name="_target0" class="button artBlockCorners" />
									<input type="submit" value="Avanti" name="_target2" class="button artBlockCorners" id="avantiButton" onclick="return controlla()" />
								</form:form>
								<br />
								<br />
							</div>
							<!-- END -art-Post- -->
						</div>
						<!-- END -art-content- -->
					</div>
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