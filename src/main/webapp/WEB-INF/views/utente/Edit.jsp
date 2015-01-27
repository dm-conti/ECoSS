<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import = "java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
<head>
    <!--
    Created by Artisteer v2.3.0.23326
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>Ecoss</title>

    <script type="text/javascript" src="../resources/newEcoss/script.js"></script>
	
	
	<link rel="stylesheet" href="../resources/css/wellcomeDiv.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="../resources/css/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="../resources/css/tableStyle.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="../resources/css/style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="../resources/css/style.ie7.css" type="text/css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="../resources/css/formToWizard.css" type="text/css" media="screen" />

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="../resources/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="../resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="../resources/js/formToWizard.js"></script>
	
	<script>

	$(document).ready(function() {
		var url = document.location.href;
		var tmpUrl = url.split("/");
		var urlHtc = "http://" + tmpUrl[2] + "/" + tmpUrl[3] + "/resources/css/PIE.htc";
		document.getElementById("wellcomeDiv").style.behavior = "url(" + urlHtc + ")";
		document.getElementById("divBenvenuto").style.behavior = "url(" + urlHtc + ")";
		document.getElementById("contentForm").style.behavior = "url(" + urlHtc + ")";		
        $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' });
	
		oTable = $('#mainTable').dataTable({
			"bJQueryUI": false,
			"oLanguage": {
				"sLengthMenu": "Visualizza _MENU_ righe per pagina",
				"sZeroRecords": "Nothing found - sorry",
				"sInfo": "Visualizzati _END_ records su _TOTAL_",
				"sInfoEmpty": "Showing 0 to 0 of 0 records",
				"sInfoFiltered": "(filtered from _MAX_ total records)"
			}
							
		});
	});
	
	</script>

</head>




<body>
<div id="art-page-background-simple-gradient">
    </div>
    <div id="art-main">
        <div class="art-Sheet">
            <div class="art-Sheet-tl"></div>
            <div class="art-Sheet-tr"></div>
            <div class="art-Sheet-bl"></div>
            <div class="art-Sheet-br"></div>
            <div class="art-Sheet-tc"></div>
            <div class="art-Sheet-bc"></div>
            <div class="art-Sheet-cl"></div>
            <div class="art-Sheet-cr"></div>
            <div class="art-Sheet-cc"></div>
            <div class="art-Sheet-body">
            
            <!--  Import dell'Header -->
            <%@include file='../template/header.jspf'%>
            <%@include file="../template/topNav.jspf" %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					<!-- 
					<div class="art-Post">
						<div class="art-Post-body">
							<div class="art-Post-inner art-article">                          
                        	</div>
						</div>
					</div> -->
					
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
                            <div class="art-Post-body">
                        <div class="art-Post-inner art-article">
                                        <h2 class="art-PostHeader">
                                            <a href="#" title="Permanent Link to this Post">Wizard nuovo Utente</a>
                                        </h2>
                                        <div class="art-PostContent">
                                        	<div class="formCorners formGradient formShadow" id="contentForm"> <!-- START contentForm-->
												
												<c:url var="saveUrl" value="Edit?idUtente=${utenteAttribute.idUtente}" />
												<form:form id="SignupForm" modelAttribute="utenteAttribute" method="POST" action="${saveUrl}">
													<hr id="hr"/>
													<fieldset>
														<legend>Dati Personali</legend>
														<form:label path="uteNome">Nome:</form:label>
														<form:input path="uteNome" value="${utenteAttribute.uteNome}"/>
														<form:label path="uteCognome">Cognome:</form:label>
														<form:input path="uteCognome" value="${utenteAttribute.uteCognome}"/>
													</fieldset>
													<fieldset>
														<legend>Dati Aziendali</legend>
														<form:label path="uteRagSociale">RagSociale:</form:label>
														<form:input path="uteRagSociale" value="${utenteAttribute.uteRagSociale}"/>
														<form:label path="uteDescrizione">Descrizione:</form:label>
														<form:input path="uteDescrizione" value="${utenteAttribute.uteDescrizione}"/>
														<form:label path="uteIndirizzo">Indirizzo:</form:label>
														<form:input path="uteIndirizzo" value="${utenteAttribute.uteIndirizzo}"/>
													</fieldset>
													<fieldset>
														<legend>Contatti</legend>
														<form:label path="uteCitta">Citta:</form:label>
														<form:input path="uteCitta" value="${utenteAttribute.uteCitta}"/>
														<form:label path="uteCap">CAP:</form:label>
														<form:input path="uteCap" value="${utenteAttribute.uteCap}"/>
												
														<form:label path="uteTel1">Telefono:</form:label>
														<form:input path="uteTel1" value="${utenteAttribute.uteTel1}"/>
														<form:label path="uteTel2">Cellulare:</form:label>
														<form:input path="uteTel2" value="${utenteAttribute.uteTel2}"/>
														<form:label path="uteFax">Fax:</form:label>
														<form:input path="uteFax" value="${utenteAttribute.uteFax}"/>
														<form:label path="uteEmail">Email primaria:</form:label>
														<form:input path="uteEmail" value="${utenteAttribute.uteEmail}"/>
														<form:label path="uteDataCreazione">Data creazione:</form:label>
														<form:label path="uteDataCreazione">${utenteAttribute.uteDataCreazione}</form:label>
														
														<form:label path="uteDataCessazione">Data cessazione:</form:label>
														<form:label path="uteDataCessazione">${utenteAttribute.uteDataCessazione}</form:label>
														
														<form:label path="uteCF">Codice Fiscale:</form:label>
														<form:input path="uteCF" value="${utenteAttribute.uteCF}"/>
														<form:label path="utePIVA">Partita IVA:</form:label>
														<form:input path="utePIVA" value="${utenteAttribute.utePIVA}"/>
														<form:label path="uteEmail2">Email secondaria:</form:label>
														<form:input path="uteEmail2" value="${utenteAttribute.uteEmail2}"/>
														
														<!--
														<form:label path="gestore">Gestore:</form:label>
														<select name="gestRagSociale">
														  
														<c:forEach items="${listaGestori}" var="gestore">
															<option value="${gestore.gestRagSociale}">${gestore.gestRagSociale}</option>
														</c:forEach>
														-->		
													
													</select>
													</fieldset>
													<input  id="SaveAccount" type="image" src="../resources/images/okButton.png" >
												</form:form>
											</div> <!--  END contentForm -->
                                               
                                        </div><!-- END art-PostContent -->
                                        <div class="cleared"></div>
                        </div>
                        
                        		<div class="cleared"></div>
                            </div>
                        </div>
                    </div>
                    <div class="art-sidebar1">
                        <div class="art-Block">
                            <div class="art-Block-tl"></div>
                            <div class="art-Block-tr"></div>
                            <div class="art-Block-bl"></div>
                            <div class="art-Block-br"></div>
                            <div class="art-Block-tc"></div>
                            <div class="art-Block-bc"></div>
                            <div class="art-Block-cl"></div>
                            <div class="art-Block-cr"></div>
                            <div class="art-Block-cc"></div>
                            <div class="art-Block-body">
                                        <div class="art-BlockHeader">
                                            <div class="l"></div>
                                            <div class="r"></div>
                                            <div class="art-header-tag-icon">
                                                <div class="t">Search</div>
                                            </div>
                                        </div><div class="art-BlockContent">
                                            <div class="art-BlockContent-body">
                                                
                                                        <style type="text/css">
                                                          form.gsc-search-box {
                                                            font-size:13px;
                                                            margin:0 0 4px;
                                                            width:100%;
                                                          }
                                                          table.gsc-search-box {
                                                            border-spacing:0;
                                                            border-style:none;
                                                            border-width:0;
                                                            margin-bottom:2px;
                                                            width:100%;
                                                          }
                                                          table.gsc-search-box td {
                                                            vertical-align:middle;
                                                          }
                                                          table.gsc-search-box td.gsc-input {
                                                            padding-right:2px;
                                                          }
                                                          td.gsc-search-button {
                                                            width:1%;
                                                          }
                                                          input.gsc-search-button {
                                                            margin-left : 2px;
                                                          }
                                                          input.gsc-input {
                                                            border:1px solid #BCCDF0;
                                                            padding-left:2px;
                                                            width:98%;
                                                          }
                                                          table.gsc-branding td, table.gsc-branding {
                                                            border:medium none;
                                                            margin:0;
                                                            padding:0;
                                                          }
                                                          table.gsc-branding {
                                                            border-spacing:0;
                                                            border-style:none;
                                                            border-width:0;
                                                            width:100%;
                                                          }
                                                          .gsc-branding-text {
                                                            color:#676767;
                                                          }
                                                          td.gsc-branding-text {
                                                            vertical-align:top;
                                                          }
                                                          td.gsc-branding-text div.gsc-branding-text {
                                                            font-size:11px;
                                                            margin-right:2px;
                                                            padding-bottom:2px;
                                                            text-align:right;
                                                          }
                                                          div.gsc-branding-youtube td.gsc-branding-text {
                                                            vertical-align:middle;
                                                          }
                                                          td.gsc-branding-img-noclear {
                                                            vertical-align:bottom;
                                                            width:51px;
                                                          }
                                                          td.gsc-branding-img {
                                                            vertical-align:bottom;
                                                            width:65px;
                                                          }
                                                          div.gsc-branding-youtube td.gsc-branding-img-noclear {
                                                            width:55px;
                                                          }
                                                          div.gsc-branding-youtube td.gsc-branding-img {
                                                            width:69px;
                                                          }
                                                          table.gsc-branding-vertical td.gsc-branding-text div.gsc-branding-text {
                                                            margin-right:0;
                                                            text-align:center;
                                                          }
                                                          table.gsc-branding-vertical td.gsc-branding-img-noclear {
                                                            text-align:center;
                                                          }
                                                          div.gsc-branding-img, div.gsc-branding-img-noclear, img.gsc-branding-img, img.gsc-branding-img-noclear {
                                                            padding-top:1px;
                                                          }
                                                          img.gsc-branding-img, img.gsc-branding-img-noclear {
                                                            border:medium none;
                                                            display:inline;
                                                            margin:0;
                                                            padding-bottom:0;
                                                            padding-left:0;
                                                            padding-right:0;
                                                          }
                                                        </style>
                                                        <div class="widget-content">
                                                          <div id="CustomSearch1_form">
                                                            <form class="gsc-search-box" accept-charset="utf-8">
                                                              <table cellspacing="0" cellpadding="0" class="gsc-search-box">
                                                                <tr>
                                                                  <td class="gsc-input">
                                                                    <input type="text" autocomplete="off" size="10" class="gsc-input" name="search" title="Search" />
                                                                  </td>
                                                                  <td class="gsc-search-button">
                                                                     <input type="submit" value="Search" class="gsc-search-button" title="Search" />
                                                                  </td>
                                                                </tr>
                                                              </table>
                                                              <table cellspacing="0" cellpadding="0" class="gsc-branding">
                                                                <tbody>
                                                                  <tr>
                                                                    <td class="gsc-branding-user-defined" />
                                                                    <td class="gsc-branding-text">
                                                                      <div class="gsc-branding-text">powered by</div>
                                                                    </td>
                                                                    <td class="gsc-branding-img-noclear">
                                                                      <img src="../resources/css/images/small-logo.png" class="gsc-branding-img-noclear" />
                                                                    </td>
                                                                  </tr>
                                                                </tbody>
                                                              </table>
                                                            </form>
                                                          </div>
                                                        </div>
                                                      
                                        		<div class="cleared"></div>
                                            </div>
                                        </div>
                        		<div class="cleared"></div>
                            </div>
                        </div>
                        <div class="art-Block">
                            <div class="art-Block-tl"></div>
                            <div class="art-Block-tr"></div>
                            <div class="art-Block-bl"></div>
                            <div class="art-Block-br"></div>
                            <div class="art-Block-tc"></div>
                            <div class="art-Block-bc"></div>
                            <div class="art-Block-cl"></div>
                            <div class="art-Block-cr"></div>
                            <div class="art-Block-cc"></div>
                            <div class="art-Block-body">
                                        <div class="art-BlockHeader">
                                            <div class="l"></div>
                                            <div class="r"></div>
                                            <div class="art-header-tag-icon">
                                                <div class="t">Archive</div>
                                            </div>
                                        </div><div class="art-BlockContent">
                                            <div class="art-BlockContent-body">
                                                
                                                        <style type="text/css">
                                                          #ArchiveList .toggle {
                                                            cursor:pointer;
                                                            font-family:Arial,sans-serif;
                                                          }
                                                          #ArchiveList .toggle-open {
                                                            line-height:0.6em;
                                                          }
                                                          #ArchiveList {
                                                            text-align:left;
                                                          }
                                                          #ArchiveList a.post-count-link, #ArchiveList a.post-count-link:link, #ArchiveList a.post-count-link:visited {
                                                            text-decoration:none;
                                                          }
                                                          #ArchiveList a.toggle, #ArchiveList a.toggle:link, #ArchiveList a.toggle:visited, #ArchiveList a.toggle:hover {
                                                            color:inherit;
                                                            text-decoration:none;
                                                          }
                                                          #ArchiveList ul li {
                                                            background:transparent none repeat scroll 0 0;
                                                            border-width:0;
                                                            list-style-image:none;
                                                            list-style-position:outside;
                                                            list-style-type:none;
                                                            margin-left:0;
                                                            padding:0 0 0 15px;
                                                            text-indent:-10px;
                                                          }
                                                          #ArchiveList ul ul li {
                                                            padding-left:1.2em;
                                                          }
                                                          #ArchiveList ul {
                                                            border-width:0;
                                                            list-style-image:none;
                                                            list-style-position:outside;
                                                            list-style-type:none;
                                                            margin:0;
                                                            padding:0;
                                                          }
                                                          #ArchiveList ul ul {
                                                            margin-top:5px;
                                                          }
                                                          #ArchiveList ul.posts li {
                                                            padding-left:1.3em;
                                                          }
                                                          #ArchiveList .collapsed ul {
                                                            display:none;
                                                          }
                                                        </style>
                                                        <div class='widget-content'>
                                                          <div id='ArchiveList'>
                                                            <div id='BlogArchive1_ArchiveList'>
                                                              <ul>
                                                                <li class='archivedate expanded'>
                                                                  <a class='toggle' href='#'>
                                                                    <span class='zippy toggle-open'>&#9660; </span>
                                                                  </a>
                                                                  <a class='post-count-link' href='#'>2009</a>
                                                                  <span class='post-count' dir='ltr'>(2)</span>
                                                                  <ul>
                                                                    <li class='archivedate expanded'>
                                                                      <a class='toggle' href='#'>
                                                                        <span class='zippy toggle-open'>&#9660; </span>
                                                                      </a>
                                                                      <a class='post-count-link' href='#'>June</a>
                                                                      <span class='post-count' dir='ltr'>(2)</span>
                                                                      <ul class='posts'>
                                                                        <li><a href='#'>Hyperlink</a></li>
                                                                        <li><a href='#' class="visited">Visited Link</a></li>
                                                                        <li><a href='#' class="hover">Hovered Link</a></li>
                                                                      </ul>
                                                                    </li>
                                                                  </ul>
                                                                </li>
                                                              </ul>
                                                            </div>
                                                          </div>
                                                        </div>
                                                      
                                        		<div class="cleared"></div>
                                            </div>
                                        </div>
                        		<div class="cleared"></div>
                            </div>
                        </div>
                        <div class="art-Block">
                            <div class="art-Block-tl"></div>
                            <div class="art-Block-tr"></div>
                            <div class="art-Block-bl"></div>
                            <div class="art-Block-br"></div>
                            <div class="art-Block-tc"></div>
                            <div class="art-Block-bc"></div>
                            <div class="art-Block-cl"></div>
                            <div class="art-Block-cr"></div>
                            <div class="art-Block-cc"></div>
                            <div class="art-Block-body">
                                        <div class="art-BlockHeader">
                                            <div class="l"></div>
                                            <div class="r"></div>
                                            <div class="art-header-tag-icon">
                                                <div class="t">About Me</div>
                                            </div>
                                        </div><div class="art-BlockContent">
                                            <div class="art-BlockContent-body">
                                                
                                                        <div class="widget-content">
                                                          <dl class="profile-datablock">
                                                            <dt class="profile-data">Username</dt>
                                                          </dl>
                                                          <a href="#">View my complete profile</a>
                                                      </div>
                                                      
                                        		<div class="cleared"></div>
                                            </div>
                                        </div>
                        		<div class="cleared"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cleared"></div><div class="art-Footer">
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
    </div>
    
</body>
</html>          