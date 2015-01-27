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
    <title>Ecoss</title>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery/redmond/jquery-ui-1.8.11.custom.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/wellcomeDiv.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jqgrid/ui.jqgrid.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/formToWizard.css" />" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/stepy/jquery.stepy.css" />" type="text/css" media="screen" />
	

	<!--  IMPORT DI JQuery --SHARED-- -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.4.min.js" />" ></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.8.10.min.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/i18n/grid.locale-it.js" />" ></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/jquery.jqGrid.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jqgrid/grid.subgrid.js" />"></script>
	
	<!-- <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.js" />" ></script> -->
	<script type="text/javascript" src="<c:url value="/resources/newEcoss/script.js" />" ></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery.stepy.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
	
	<script type="text/javascript">
	
	jq(function() {
		/* ---------------------------------------------------
		 * APPLICO STEPY AL FORM PER NUOVO UTENTE.
		 * ---------------------------------------------------*/
		jq('#newPasswordForm').stepy({
			backLabel : 'Indietro',
			block : true,
			errorImage : true,
			nextLabel : 'Avanti',
			titleClick : true,
			validate: true
		});
		

		jq.validator.addMethod("password",function(value,element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{6,32}$/i.test(value);
		},
		"Username are 6-32 characters"); 
		
		jq.validator.addMethod("repeatRassword",function(value,element){
			return this.optional(element) || /^[a-zA-Z0-9._-]{6,32}$/i.test(value);
		},
		"Username are 6-32 characters"); 
		
		jq('#newPasswordForm').validate({
			errorPlacement: function(error, element) {
				document.getElementById("divErroreDati").style.display="inline";
			},
			success: function(){
				document.getElementById("divErroreDati").style.display="none";
			},
			rules: {
				'password': "required password",
				'repeatRassword': "required repeatRassword"
			}
		});
	});
	
	function controlla(){
		var tmpPsw = document.getElementById("password").value;
		var tmpRptPsw = document.getElementById("repeatRassword").value;
		if(tmpPsw != tmpRptPsw){
			alert("Le due password non sono uguali");
			return false;
		}
		else
			return true;
		
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
            <%@include file='template/header.jspf'%>
            <%@include file='template/topNav.jspf' %>
			
			<div class="art-contentLayout">
				<div class="art-content" style="width:100%">
					<div class="art-Post"> <!--  TAG necessario per il mantenimento dei contenuti -->
						<div class="art-Post-body">
							<div class="art-Post-inner art-article">
								<h2 class="art-PostHeader">
									<a href="#" title="Permanent Link to this Post">Primo accesso</a>
								</h2>
								
								<c:url var="inviaPsw" value="setpassword" />
								
								<form id="newPasswordForm" method="post" action="${inviaPsw}">
									<div class="art-PostContent">
										</br>
										<div align="center">
										<div class='provideCSS-3 art-Block artBlockCorners'	style='padding: 10px; margin: 0px; height:190px; width:450px;'>
											</br>
											</br>
											<div class=titleTab align="center">
											Inserire nuova password <span class="errorSmall">*</span>
											</br></br>
											<input type="password" name="password" id="password" class="required" >
											</br></br>
											Ripetere password <span class="errorSmall">*</span>
											</br></br>
											<input type="password" name="repeatRassword" id="repeatRassword" class="required" >
											</br></br>
											<div align="right">
												<input type="submit" value="Salva" class="button artBlockCorners" onclick="return controlla()"/>
											</div>
											<label id="divErroreDati" class="error" style="position:relative; top:-15px; left:-320px; display:none">Inserire dati corretti</label>
											</div>
										</div>
										</br>
										<span class="errorSmall"  style="position:relative; top:-5px; left:-190px;">* Campi obbligatori</span>
										</div>
									</div><!-- END art-PostContent -->
								</form>
								
								<div class="cleared" ></div>
							</div><!-- END - art-Post-inner art-article - -->
                           	<div class="cleared" ></div>
						</div><!-- END -art-Post-body- -->
					</div><!-- END -art-Post- -->
				</div><!-- END -art-content- -->
			
				
			</div><!-- END -art-contentLayout- -->
			
			<div class="cleared"></div>
		</div><!-- END -art-Sheet-body- -->
		
		<div class="cleared" ></div>
		<%@include file="template/footer.jspf" %>
	</div><!-- END art-main -->
</div>
</body>
</html>