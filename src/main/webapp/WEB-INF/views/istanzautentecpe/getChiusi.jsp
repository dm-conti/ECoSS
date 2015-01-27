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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ecoss</title>

<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery/redmond/jqModal.css" />"
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
	src="<c:url value="/resources/js/jquery/jqModal.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>

<script type="text/javascript">
	var ajaxSubmitUrl = "<c:url value='/istanzautentecpe/getChiusi' />";

	var xmlHttpRequest = jq.ajax({
		url : ajaxSubmitUrl,
		type : "POST",
		async : false,
		success : function(data) {
			xmlHttpRequest = data;
			return xmlHttpRequest;
		},
		cache : false,
		dataType : "json"
	});

	function showDettagli(idDiv) {
		if (document.getElementById(idDiv).style.display == "none")
			document.getElementById(idDiv).style.display = "inline";

		else
			document.getElementById(idDiv).style.display = "none";
	}

	/*
	Effettuo il parsing dell'oggetto jqXHR ed ottengo
	un oggetto Json. In questo modo posso usare la 
	notazione 'dot' per poter accedere comodamente 
	alle informazioni inviate dal server 
	 */
	var jsonResponse = jq.parseJSON(xmlHttpRequest.responseText);

	//Recupero le righe da inserire in tabella
	var dataRows = jsonResponse.rows;

	jq(function() {
		/* ------------------------------------
		 * CARICAMENTO DELLA GRIGLIA PRINCIPALE
		 --------------------------------------*/
		jq("#grid")
				.jqGrid(
						{
							data : dataRows,
							datatype : "local",

Num : 10,
							rowList : [ 10, 20, 40 ],
							width : "100%",
							height : "100%",
							multiselect : true,
							colNames : [ 'idIstanzaUtenteCPE',
										'Numero Contratto', 
										'Stato',
										'Data inizio Contratto',
										'Data fine Contratto' ],
										
							colModel : [ {name : 'idIstanzaUtenteCPE', index : 'idIstanzaUtenteCPE', width : 55,hidden : true}, 
							             {name : 'numeroContratto',index : 'numeroContratto',width : 100,editable : true},
							             {name : 'tabellastati.stato',index : 'tabellastati.stato',width : 100,editable : true },           
							             {name : 'dataInizio',index : 'dataInizio',width : 100,editable : true}, 
							             {name : 'dataFine',index : 'dataFine',width : 100,editable : true} ],
							postData : {},
							autowidth : true,
							rownumbers : true,
							pager : '#pager',
							viewrecords : true,
							caption : '<fmt:message key="contrattoList"/>',
							emptyrecords : "Empty records",
							loadonce : false,

							//OnSelectRow
							onSelectRow : function(id) {
								if (id == null) {
									id = 0;
									if (jq("#details").jqGrid('getGridParam',
											'records') > 0) {
										//Devo costruire l'URL come quello usato quando edito un elemento
										jq("#details").jqGrid('setGridParam', {
											url : "Edit?" + id,
											page : 1
										});
										jq("#details").jqGrid('setCaption',
												"Invoice Detail: " + id)
												.trigger('reloadGrid');
									}
								} else {
									var nPage = jq("#grid").jqGrid('getGridParam', 'page');
									var nRow = jq("#grid").jqGrid('getGridParam', 'rowNum');
									var tmpId = parseInt(id) + (nPage*nRow) - nRow-1;
									//Devo costruire l'URL come quello usato quando edito un elemento
									jq("#details").jqGrid('setGridParam', {
										url : "Edit?" + id,
										page : 1
									});
									jq("#details").jqGrid('setCaption',
											"Invoice Detail: " + id).trigger(
											'reloadGrid');

									// Visualizzazione dettagli contratto
									document.getElementById('divDettagliContratto').style.display = "inline";
									
									// Dettagli contratto
									document.getElementById('dataAttivazioneContratto').innerHTML = dataRows[tmpId].dataFine;
									document.getElementById('dataCessazioneContratto').innerHTML = dataRows[tmpId].dataInizio;
									document.getElementById('numeroContratto').innerHTML = dataRows[tmpId].numeroContratto;
									document.getElementById('statoContratto').innerHTML = dataRows[tmpId].tabellastati.stato;
									
									// Dettagli utente
									document.getElementById('nomeUtente').innerHTML = dataRows[tmpId].utente.uteNome;
									document.getElementById('cognomeUtente').innerHTML = dataRows[tmpId].utente.uteCognome;
									document.getElementById('usernameUtente').innerHTML = dataRows[tmpId].utente.credenzialiutente.userId;
									document.getElementById('cfUtente').innerHTML = dataRows[tmpId].utente.uteCF;
									document.getElementById('pivaUtente').innerHTML = dataRows[tmpId].utente.utePIVA;
									document.getElementById('descrizioneUtente').innerHTML = dataRows[tmpId].utente.uteDescrizione;
									document.getElementById('gestoreUtente').innerHTML = dataRows[tmpId].utente.gestore.gestRagSociale;
									if(dataRows[tmpId].utente.distributore != null)
										document.getElementById('distributoreUtente').innerHTML = dataRows[tmpId].utente.distributore.distRagSociale;
									document.getElementById('indirizzoUtente').innerHTML = dataRows[tmpId].utente.uteIndirizzo;
									document.getElementById('cittaUtente').innerHTML = dataRows[tmpId].utente.uteCitta;
									document.getElementById('capUtente').innerHTML = dataRows[tmpId].utente.uteCap;
									document.getElementById('telefonoUtente').innerHTML = dataRows[tmpId].utente.uteTel1;
									document.getElementById('cellulareUtente').innerHTML = dataRows[tmpId].utente.uteTel2;
									document.getElementById('faxUtente').innerHTML = dataRows[tmpId].utente.uteFax;
									document.getElementById('email1Utente').innerHTML = dataRows[tmpId].utente.uteEmail;
									document.getElementById('email2Utente').innerHTML = dataRows[tmpId].utente.uteEmail2;
									
									// Dettagli licenza utente
									document.getElementById('dataAttivazioneLicenza').innerHTML = dataRows[tmpId].licenzautente.licUteDataAttivazione;
									document.getElementById('dataCessazioneLicenza').innerHTML = dataRows[tmpId].licenzautente.licUteDataCessazione;
									document.getElementById('descrizioneLicenza').innerHTML = dataRows[tmpId].licenzautente.licUteDescrizione;
									
									// Dettagli vsg
									document.getElementById("cpeNomeTab").innerHTML = dataRows[tmpId].cpe.cpeNome;
									document.getElementById('cpeModelloTab').innerHTML =  dataRows[tmpId].cpe.cpeModello;
									document.getElementById('cpeVersCoreTab').innerHTML =  dataRows[tmpId].cpe.cpeVersCore;
									document.getElementById('cpeVersCoreProgTab').innerHTML =  dataRows[tmpId].cpe.cpeVersCoreProg;
									document.getElementById('cpeDataAttivazioneTab').innerHTML =  dataRows[tmpId].cpe.cpeDataAttivazione;
									document.getElementById('cpeIpAddressTab').innerHTML =  dataRows[tmpId].cpe.cpeIpAddress;
									document.getElementById('cpeMacAddressTab').innerHTML =  dataRows[tmpId].cpe.cpeMacAddress;
									document.getElementById('cpeCompanyKey').innerHTML =  dataRows[tmpId].cpe.cpeCompanyKey;
									var cpeManagementKey="";
									for(i=0; i<dataRows[tmpId].cpe.cpeManagementKey.length;i++){
										cpeManagementKey += dataRows[tmpId].cpe.cpeManagementKey.slice(i,i+10)+" ";
										i=i+10;
									}
									document.getElementById('cpeManagementKeyTab').innerHTML =  cpeManagementKey;
								}
							}
						});

		jq("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : true
		}, {}, {}, {}, {
			sopt : [ 'eq', 'ne', 'lt', 'gt', 'cn', 'bw', 'ew' ],
			closeOnEscape : true,
			multipleSearch : true,
			closeAfterSearch : true
		});

		/*jq("#grid").navButtonAdd('#pager',
				{  	caption:"Add",
			     	buttonicon:"ui-icon-plus",
			     	onClickButton: addRow,
			     	position: "last",
			     	title:"",
			     	cursor: "pointer"
		});
		
		jq("#grid").navButtonAdd('#pager',
				{  	caption:"Edit",
					buttonicon:"ui-icon-pencil",
					onClickButton: editRow,
					position: "last",
					title:"",
					cursor: "pointer"
		});*/

		jq("#grid").navButtonAdd('#pager', {
			caption : "Delete",
			buttonicon : "ui-icon-trash",
			onClickButton : deleteRow,
			position : "last",
			title : "",
			cursor : "pointer"
		});

		jq("#btnFilter").click(function() {
			jq("#grid").jqGrid('searchGrid', {
				multipleSearch : false,
				sopt : [ 'eq' ]
			});
		});

	
});
</script>

<script type="text/javascript">
	function addRow() {

		// Get the currently selected row
		jq("#grid").jqGrid('editGridRow', 'new', {
			url : '<c:url value="/utente/add" />',
			editData : {},
			recreateForm : true,
			beforeShowForm : function(form) {
			},
			closeAfterAdd : true,
			reloadAfterSubmit : false,
			afterSubmit : function(response, postdata) {
				var result = eval('(' + response.responseText + ')');
				var errors = "";

				if (result.success == false) {
					for ( var i = 0; i < result.message.length; i++) {
						errors += result.message[i] + "<br/>";
					}
				} else {
					jq("#dialog").text('Entry has been added successfully');
					jq("#dialog").dialog({
						title : 'Success',
						modal : true,
						buttons : {
							"Ok" : function() {
								jq(this).dialog("close");
							}
						}
					});
				}
				// only used for adding new records
				var new_id = null;

				return [ result.success, errors, new_id ];
			}
		});

	}

	function editRow() {
		// Get the currently selected row
		var row = jq("#grid").jqGrid('getGridParam', 'selrow');

		if (row != null)
			jq("#grid")
					.jqGrid(
							'editGridRow',
							row,
							{
								url : '<c:url value="/utente/edit" />',
								editData : {},
								recreateForm : true,
								beforeShowForm : function(form) {
								},
								closeAfterEdit : true,
								reloadAfterSubmit : false,
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i]
													+ "<br/>";
										}
									} else {
										jq("#dialog")
												.text(
														'Entry has been edited successfully');
										jq("#dialog").dialog({
											title : 'Success',
											modal : true,
											buttons : {
												"Ok" : function() {
													jq(this).dialog("close");
												}
											}
										});
									}

									return [ result.success, errors, null ];
								}
							});
		else
			jq("#dialogSelectRow").dialog('open');
	}

	jq(function() {
		jq("#dialogSelectRow").dialog({
			autoOpen : false,
			modal : true,
			width : 220,
			height : 130,
			resizable : false,
			buttons : {
				'Ok' : function() {
					jq("#dialogSelectRow").dialog('close');
				}
			}
		});
	});

	function deleteRow() {
		/*Seleziona l'insieme di righe selezionate*/
		var rows = jq('#grid').jqGrid('getGridParam', 'selarrrow');

		var ids = new Array();

		/* Per ogni riga selezionata recupero l'idUtente 
		 * E COSTRUISCO IL NUOVO VETTORE CON GLI id DEGLI
		 * ELEMENTI DA ELIMINARE                          */
		if (rows.length) {
			for ( var i = 0; i < rows.length; i++) {
				var idTmp = jq('#grid').jqGrid('getCell', rows[i],
						'idIstanzaUtenteCPE');
				ids.push(idTmp);
			}
		}
		var leftValue = screen.width / 2 - 125;
		// A pop-up dialog will appear to confirm the selected action
		if (ids.length > 0)
			jq("#grid")
					.jqGrid(
							'delGridRow',
							ids,
							{
								url : '<c:url value="/istanzautentecpe/ajaxDelete" />',
								delData : {
									arrayOfIds : ids.toString()
								},
								caption : "Cancellazione",
								msg : "Confermare eliminazione?",
								bSubmit : "Ok",
								bCancel : "Annulla",
								width : 250,
								height : 130,
								dataheight : 50,
								top : 300,
								left : leftValue,
								resize : false,
								modal : true,
								/*beforeShowForm: function(form) {
								  //change title
								  jq(".delmsg").replaceWith(
										  '<span style="white-space: pre;">' +
										  'Cancellare il contratto selezionato?' + 
											'</span>');
								  	//hide arrows
								  	jq('#pData').hide();  
								  	jq('#nData').hide();  
								},*/
								reloadAfterSubmit : true,
								closeAfterDelete : true,
								afterSubmit : function(response, postdata) {
									var result = eval('('
											+ response.responseText + ')');
									var errors = "";

									if (result.success == false) {
										for ( var i = 0; i < result.message.length; i++) {
											errors += result.message[i]
													+ "<br/>";
										}
									} else {
										jq("#dialog")
												.text(
														'Eliminazione effettuata correttamente');
										jq("#dialog")
												.dialog(
														{
															title : 'Eliminazione',
															modal : true,
															buttons : {
																"Ok" : function() {
																	jq(this)
																			.dialog(
																					"close");
																	window.location.href = window.location.pathname;
																}

															}
														});

									}
									// only used for adding new records
									var new_id = null;

									return [ result.success, errors, new_id ];
								}
							});
		else
			jq("#dialogSelectRow").dialog('open');
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
										<a href="#" title="Permanent Link to this Post">
										Lista Contratti Chiusi
										</a>
									</h2>
									<div class="art-PostContent">
										</br>
										<div id="jqgrid">
											<table id="grid"></table>
											<div id="pager"></div>
										</div>
										</br>
										</br>
										<div id="divDettagliContratto" style="display: none;">
											<div class='provideCSS-3 art-Block artBlockCorners'
												style='padding: 10px; margin: 0px;'>
												<span class=titleTab> RIEPILOGO CONTRATTO </span> </br>
												</br> 
												<table id='tabs'>
													<tr valign=top>
															<td class=titleTab>Numero Contratto:</td>
															<td><div id="numeroContratto"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Stato:</td>
															<td><div id="statoContratto"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab width=150px>Data attivazione:</td>
															<td><div id="dataAttivazioneContratto"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab width=150px>Data cessazione:</td>
															<td><div id="dataCessazioneContratto"></div></td>
														</tr>
														
													</table>
												</br><span class=titleTab> <a href="#"
													onclick="showDettagli('dettagliVsgRiepilogo');">
														DETTAGLI VSG</a> </span> </br>
												<div id="dettagliVsgRiepilogo" style="display: inline">
													<table id='tabs'>
														<tr valign=top>
													<td class=titleTab width=150px>Nome:</td>
													<td><div id="cpeNomeTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Modello:</td>
													<td><div id="cpeModelloTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Versione Core:</td>
													<td><div id="cpeVersCoreTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Programma:</td>
													<td><div id="cpeVersCoreProgTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Data attivazione:</td>
													<td><div id="cpeDataAttivazioneTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Indirizzo IP:</td>
													<td><div id="cpeIpAddressTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Mac Address:</td>
													<td><div id="cpeMacAddressTab"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Company Key:</td>
													<td><div id="cpeCompanyKey"></div></td>
												</tr>
												<tr valign=top>
													<td class=titleTab width=150px>Management Key:</td>
													<td><div id="cpeManagementKeyTab"></div></td>
												</tr></table>
												</div>
												</br> <span class=titleTab> <a href="#"
													onclick="showDettagli('dettagliUtenteRiepilogo');">
														DETTAGLI UTENTE</a> </span></br>
												<div id="dettagliUtenteRiepilogo" style="display: inline">
													<table id='tabs'>
														<tr valign=top>
															<td class=titleTab width=150px>Nome:</td>
															<td><div id="nomeUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Cognome:</td>
															<td><div id="cognomeUtente"></div></td>
														</tr>
														<tr valign=top >
															<td class=titleTab>Username:</td>
															<td><div id="usernameUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Codice fiscale:</td>
															<td><div id="cfUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Partiva Iva:</td>
															<td><div id="pivaUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab >Descrizione:</td>
															<td><div id="descrizioneUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Gestore:</td>
															<td><div id="gestoreUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Distributore:</td>
															<td><div id="distributoreUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Indirizzo:</td>
															<td><div id="indirizzoUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Città:</td>
															<td><div id="cittaUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Cap:</td>
															<td><div id="capUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Telefono:</td>
															<td><div id="telefonoUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Cellulare:</td>
															<td><div id="cellulareUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Fax:</td>
															<td><div id="faxUtente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Email primaria:</td>
															<td><div id="email1Utente"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Email secondaria:</td>
															<td><div id="email2Utente"></div></td>
														</tr>
													</table>
												</div>
												</br> <span class=titleTab> <a href="#"
													onclick="showDettagli('dettagliLicenzaRiepilogo');">
														DETTAGLI LICENZA</a> </span></br>
												<div id="dettagliLicenzaRiepilogo" style="display: inline">
													<table id='tabs'>
														<tr valign=top>
															<td class=titleTab width=150px>Data attivazione:</td>
															<td><div id="dataAttivazioneLicenza"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab width=150px>Data cessazione:</td>
															<td><div id="dataCessazioneLicenza"></div></td>
														</tr>
														<tr valign=top>
															<td class=titleTab>Descrizione:</td>
															<td><div id="descrizioneLicenza"></div></td>
														</tr>
													</table>
												</div>
											</div>
										</div>
										<div id="dialog" title="Feature not supported"
											style="display: none">
											<p>That feature is not supported.</p>
										</div>
										<div id="dialogSelectRow" title=""
											style="display: none; font-size: 11px;">Selezionare un
											Contratto</div>
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