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
    <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
	<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
	<META NAME="ROBOTS" CONTENT="NONE"> 
	<META NAME="GOOGLEBOT" CONTENT="NOARCHIVE">
    
    <c:if test=" ${pageContext.session} == null}">
    	<c:redirect url="/login"/>
	</c:if>
    <title>Ecoss</title>
	<script language="javascript">
		if(history.length>0)
			history.forward();
	</script>
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	
	<link rel="stylesheet" href="<c:url value="/resources/css/dialogs.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/stepy/jquery.stepy.css" />" type="text/css" media="screen" />

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />" ></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.locale-en.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
	
	<script type="text/javascript">
			// This code does NOT belong the plugin. See the example code at the bottom of this page.
			var currentStep = false;
			var _gaq = _gaq || [];
			_gaq.push(['_setAccount', 'UA-194992347-4']);
			_gaq.push(['_trackPageview']);

			(function() {
				var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
				ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
				var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
			})();
	</script>
	
	<script type="text/javascript">
	

	jq(function() {
		/*Definisco una variabile per conservare l''id dell'Utente selezionato*/
		var idSelectedUtente;
		
		setCurrentStep(0);
		
		jq(".tabContents").hide(); // Hide all tab conten divs by default
		jq(".tabContents:first").show(); // Show the first div of tab content by default
			
		jq("#steps ul li a").click(function(){ //Fire the click event
			
			var activeTab = jq(this).attr("href"); // Catch the click link
			jq("#steps ul li a").removeClass("active"); // Remove pre-highlighted link
			jq(this).addClass("active"); // set clicked link to highlight state
			jq(".tabContents").hide(); // hide currently visible tab content div
			jq(activeTab).fadeIn(); // show the target tab content div by matching clicked link.
		});

		jq("#newUtente").button().click(function() {
			jq( "#dialog-grid" ).dialog( "open" );
		});

		function ShowHideUsers(){
			jq("#utentiSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}

		function ShowHideVSG(){
			jq("#vsgSlidingDiv").animate({"height": "toggle"}, { duration: 1000 });
		}
	
	function setCurrentStep(idStep){
		if(idStep > 0){
			var idPreviousStep = idStep-1; 
			jq("#callback-title-"+idPreviousStep).removeClass("current-step");
			jq("#callback-title-"+idStep).addClass("current-step");
		}
	}
});
	</script>

</head>
<body>
	<c:if test=" ${newContratto} == null}">
    	<c:redirect url="/login"/>
	</c:if>
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
            <%@include file='../template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content">
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
						<div class="art-Post-body">
							<div class="art-Post-inner art-article">
								<h2 class="art-PostHeader">
									<a href="#" title="Permanent Link to this Post">Nuovo Contratto</a>
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
											<span>VSG</span>
											</li>
											<li id="callback-title-2" >
											Step 4
											<span>Licenza Utente</span>
											</li>
											<li id="callback-title-3" >
											Finish
											<span>Summary</span>
											</li>
										</ul>
										<form:form method="POST"  modelAttribute="newContratto" >
											<div class='provideCSS-3 art-Block artBlockCorners' style='padding:10px; margin:0px;'>
												<span class="titleTab">Nuovo Contratto</span>
												</br></br>
												<p> Per creare un nuovo contratto hai bisogno
													di un Utente. Puoi associare il contratto ad un Utente
													gia' registrato altrimenti crea un nuovo Utente:</p><br />
													<input type="radio" name="choice" value="Nuovo"> Crea un nuovo Utente						
													<br /><br />
													<input type="radio" name="choice" value="Seleziona" checked> Seleziona un Utente registrato
													<br /><br />
											</div>	
											</br>
											<input type="hidden" value="0" name="_page" />
											<input type="submit" value="Avanti" name="_target1" class="button artBlockCorners" />
										</form:form>     
									</div><!-- END art-PostContent -->
								<div class="cleared" ></div>
							</div><!-- END - art-Post-inner art-article - -->
                           	<div class="cleared" ></div>
						</div><!-- END -art-Post-body- -->
					</div><!-- END -art-Post- -->
				</div><!-- END -art-content- -->
			
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