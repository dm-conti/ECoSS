<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import = "java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <!-- 
    <script type="text/javascript">
    	var jq = jQuery.noConflict();
 	</script>
    -->
    <title>Ecoss</title>

	<link href="<c:url value="/resources/css/wellcomeDiv.css" />" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/resources/css/tableStyle.css" />" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/resources/css/formToWizard.css" />" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value="/resources/css/spring.css" />" rel="stylesheet" type="text/css" media="screen" />
	
    <!-- Core files -->
	<link href="<c:url value="/resources/css/jquery-ui-1.8.10.custom.css" />" rel="stylesheet" type="text/css" media="screen" />
	
	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="../resources/js/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../resources/js/jquery/jquery-ui-1.8.10.min.js"></script>
	
	<script type="text/javascript" src="../resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="../resources/js/formToWizard.js"></script>
	<script type="text/javascript" src="../resources/newEcoss/script.js"></script>
	
	<script>

	var varLength = 0;

	$(document).ready(function() {

		oTable = $('#mainTable').dataTable({
			"bJQueryUI": false,
			"oLanguage": {
				"sLengthMenu": "<fmt:message key='tableHeader.lengthLabel' />",
				"sZeroRecords": "<fmt:message key='tableHeader.zeroRecors' />",
				"sInfo": "<fmt:message key='tableHeader.info' />",
				"sInfoEmpty": "<fmt:message key='tableHeader.infoEmpty' />",
				"sInfoFiltered": "<fmt:message key='tableHeader.infoFiltered' />",
				"sFilter": "Ricerca"	
			}			
		});

		$('#row1TableAdd').click(function(){
	        $('#licenzautentes option:selected').each( function() {
	                $('#boxServicesSelected').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
	            $(this).remove();
	        });
	    });
	    $('#row1TableRemove').click(function(){
	        $('#boxServicesSelected option:selected').each( function() {
	            $('#licenzautentes').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
	            $(this).remove();
	        });
	    });

		$("#emptyDialog").dialog({
			modal: true,
			autoOpen: false,
	        width: 300,
	        resizable: false,
			buttons: {
				Close: function() {
					$( this ).dialog( "close" );
				}
			}
	    });

		$("#detailServicesDialog").dialog({
			modal: false,
			autoOpen: false,
	        width: 300,
	        resizable: true,
			buttons: {
				Close: function() {
					$( this ).dialog( "close" );
				}
			}
	    });

		$("#dialog-message_2").dialog({
			modal: true,
			autoOpen: false,
	        width: 300,
	        resizable: false,
			buttons: {
				Ok: function() {
					elimina();
					$( this ).dialog( "close" );
				},
				Annulla: function(){
					$( this ).dialog( "close" );
				}
			}
	    });
	});/*END $(document).ready*/

	var sListServices = new Array();

	//RECUPERO I SERVIZI SELEZIONATI
	function getServicesSelected(){
		$("#boxServicesSelected option").each(
				function(index, option){
					sListServices[index] = option.value;
				});
		document.getElementById("servicesSelected").value = sListServices.toString();
	}
	

	function ShowHideUsers(){
		$("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
	}

	function ShowHideVSG(){
		$("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
	}

	function idChecked(listChecked){
		
		/*RECUPERO LA LUNGHEZZA DELLA LISTA*/
		var n = listChecked.split(",").length;
		listId = new Array();
		for (i=1; i <= n; i++){
			checkObj = document.getElementById("check_"+ i);
			if(checkObj == null)
					continue;
			if(checkObj.checked){
				listId.push(checkObj.value);
			}
		}
		varLength = listId.length;
		if(varLength > 0){
			var message = "Hai selezionato "+varLength+" utenti. \n Procedo con l'operazione ?";
			document.getElementById("dialog-message_2").innerHTML = message;
			$('#dialog-message_2').dialog('open');
		}else {
			message = "Selezionare almeno un utente!";
			document.getElementById("emptyDialog").innerHTML = message;
			$('#emptyDialog').dialog('open');
		}
	}

	function elimina(){
		$(function(){
			$.post("/springSecurityIntegration/licenzecommerciali/Delete",
				     {  arrayId:  listId.toString() },
				      function(data){
				       alert("Eliminazione Riuscita!");
				       location.reload();
			});
			});
	}

	function getInfoServices(idLicComm) {
		var message = "";
		$(function() {
			$.post("<c:url value='/licenzecommerciali/InfoServices' />",
					{ sIdLicCommerciale: idLicComm },
					function(data){
						for (i=0; i<data.length; i++) {
							message = message + data[i] + "<br />";
						}
						document.getElementById("detailServicesDialog").innerHTML = message;
						$('#detailServicesDialog').dialog('open');
				});
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
            <div id="emptyDialog" title="Operazione non consentita"></div>
            <div id="dialog-message_2" title="Operazione non consentita"></div>
            <div id="detailServicesDialog" title="Info Servizi"></div>
            <div id="modal" class="modal">
				<div id="textModal"></div>
			</div>
            
            <!--  Import dell'Header -->
            <%@include file='../template/header.jspf'%>
            <%@include file='../template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
                            <div class="art-Post-body">
                            	<div class="art-Post-inner art-article">
                            		<h2 class="art-PostHeader">
                            		</h2>
                            		<div class="art-PostContent">
                                    	<div class="formCorners formGradient formShadow" id="contentForm"> <!-- START contentForm-->
											<c:url var="saveUrl" value="Edit?idLicenzeCommerciali=${licCommerciale.idLicenzeCommerciali}" />
											<form:form id="SignupForm" modelAttribute="licCommerciale" method="POST"  action="${saveUrl}" >
												<hr id="hr"/>
												<fieldset>
													<legend><spring:message code="newLicComm.Title" /></legend>
													<div id="row1">
														<form:label path="licomNomeLicenza">Nome Licenza:</form:label>
														<form:input path="licomNomeLicenza"/>
													</div>
													<div id="row2">
														<table id="row1Table">
															<tr>
																<td>
																	<form:select path="licenzautentes">
																		<c:forEach items="${listServices}" var="voceServizio">
																			<option value="${voceServizio}">${voceServizio}</option>
																		</c:forEach>
																	</form:select>
																</td>
																<td id="center">
																	<input id="row1TableAdd" type="button" value=">>"/>
																	<input id="row1TableRemove" type="button" value="<<"/>
																</td>
																<td>
																	<select id="boxServicesSelected" multiple="yes">
																		<c:forEach items="${servicesAdded}" var="voiceService">
																			<option value="${voiceService}">${voiceService}</option>
																		</c:forEach>
																	</select>
																	<input type="hidden" id="servicesSelected" value="" name="servicesSelected"/>
																</td>							
															</tr>
														</table>
													</div>
												</fieldset>
												<input  id="SaveAccount" onclick="getServicesSelected()" type="image" src="../resources/images/okButton.png" />
											</form:form>
										</div> <!--  END contentForm -->
									</div> <!-- END - art-PostContent - -->
                                        <div class="cleared"></div>
                        </div>
                        
                        		<div class="cleared"></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="art-sidebar1">
                        
                        <br />
                        <div id="art-UserMenu" class="art-Block artBlockCorners">
                            <div class="art-Block-body">
                                        <div id="art-UserMenuHeader" class="art-BlockHeader artBlockGradient artBlockCorners">
                                           
                                            <div class="art-header-tag-icon">
                                                <div class="t"><a onclick="ShowHideUsers(); return false;" href="#"><fmt:message key="usersMenu.label.title" /></a></div>
                                            </div>
                                        </div><div id="utentiSlidingDiv" class="art-BlockContent">
                                            <div class="art-BlockContent-body">
                                                        <div class="widget-content">
                                                        	<ul>
									                   	  		<li><img src="../resources/images/iconContractEnable.png" /><a href="#"><fmt:message key="usersMenu.label.item1" /></a></li>
									                      		<li><img src="../resources/images/iconContractExpiring.png" /><a href="#"><fmt:message key="usersMenu.label.item2" /></a></li>
									                      		<li><img src="../resources/images/iconContractDisabled.png" /><a href="#"><fmt:message key="usersMenu.label.item3" /></a></li>
															</ul>
                                                        </div>
                                        		<div class="cleared"></div>
                                            </div>
                                        </div>
                        		<div class="cleared"></div>
                            </div>
                        </div>
                       
                        <div id="art-SVGMenu" class="art-Block artBlockCorners">
                            <div class="art-Block-body">
                            <div id="art-SVGMenuHeader" class="art-BlockHeader artBlockGradient artBlockCorners">
                                           
                                            <div class="art-header-tag-icon">
                                                <div class="t"><a onclick="ShowHideVSG(); return false;" href="#"><fmt:message key="vsgMenu.label.title" /></a></div>
                                            </div>
                                        </div>
                            	<div id="vsgSlidingDiv" class="art-BlockContent">
                            		<div class="art-BlockContent-body">
                            			<div class="widget-content">
                                        	<ul>
									                   	  		<li><img src="../resources/images/iconVSGEnable.png" /><a href="#"><fmt:message key="vsgMenu.label.item1" /></a></li>
									                      		<li><img src="../resources/images/iconVSGExpiring.png" /><a href="#"><fmt:message key="vsgMenu.label.item2" /></a></li>
									                      		<li><img src="../resources/images/iconVSGDisabled.png" /><a href="#"><fmt:message key="vsgMenu.label.item3" /></a></li>
															</ul>
                                        </div>
                                    	<div class="cleared"></div>
									</div>
								</div>
                        		<div class="cleared"></div>
                            </div>
                        </div>
                        
                    </div><!-- END art-sidebar1 -->
                </div>
                <div class="cleared"></div>
                <div id="art-Footer" class="art-Footer artBlockCorners">
                    <div class="art-Footer-inner">
                        <a href="#" class="art-rss-tag-icon" title="RSS"></a>
                        <div class="art-Footer-text">
                            <p><a href="#">Contattaci</a> | <a href="#">Condizioni di utilizzo</a> | <a href="#">Trademarks</a>
                                | <a href="#">Privacy Statement</a><br />
                                Copyright &copy; 2011 ---. All Rights Reserved.</p>
                        </div>
                    </div>
                    <div class="art-Footer-background"></div>
                </div>
        		<div class="cleared"></div>
            </div>
        </div>
        <div class="cleared"></div>
        <p class="art-page-footer"><a href="#">Ecoss Template</a> by Obiectivo Technology </p>
    </div><!-- END aet-main -->
</body>
</html>