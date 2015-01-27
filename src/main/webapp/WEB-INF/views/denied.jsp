<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><fmt:message key="welcome.title"/></title>
	<link rel="stylesheet" href="<c:url value="/resources/css/screen.css" />" type="text/css" media="screen, projection"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/cupertino/jquery.ui.all.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/table/style.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/stile.css" />" type="text/css" />
	
	<!-- 
	<script type="text/javascript" src="/spring-mvc-jquery/resources/js/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script> -->
	
	<script type="text/javascript" src="/mvc-ajax/resources/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.accordion.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.slider.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/mvc-ajax/resources/js/cmxforms.js"></script>
	
	<!-- 
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
	<script type="text/javascript">
			$(function() {
				$("button, input:submit, a", ".buttonLogin").button();
				$("a", ".buttonLogin").click(function() { return false; });
			});
	
			$(function() {
				$("#accordionLogin").accordion({
					collapsible: true,
					autoHeight: false
				});
			});
			
			$().ready(function() {
				$("#formLogin").validate();
			});
		</script>
</head>
<body>
<div class="divBody" id="divBody" style="height:600px;">
			<div class="divHeadTitle">
                <br><br>&nbsp;&nbsp;<img src="/mvc-ajax/resources/images/logo.png" height="55" width="331"><br><br>
                <hr class="hrHead">
            </div>
				<div id="accordionLogin" class = "divLogin">
					<h3><a href="#">Member Login</a></h3>
					<div>
						<table border="0">
							<tr>
								<td width="60px"><img src="/mvc-ajax/resources/images/lock.png"></td>
								<td><h1>Accesso Negato!</h1></td>
								<td><a href="/mvc-ajax">effettua il login</a></td>
							</tr>
							<tr>
								<td><a href="/mvc-ajax">login</a></td>
							</tr>
						</table>
					</div>
	  </div>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		</div>
</html>