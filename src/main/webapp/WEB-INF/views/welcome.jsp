<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><fmt:message key="welcome.title"/></title>
	
	<link href="<c:url value="/resources/css/loginStyle.css" />" rel="stylesheet" type="text/css" media="screen" />

</head>
<body>
	<div id="mainLoginDiv">
		<form:form method="post" action="j_spring_security_check">
			<div id="divLogin" class="provideCSS-3 loginCorners loginGradient loginShadow">
				<div id="wellcomeFirstBlock">
					<div id="imageLock"></div>
					<h2>Login</h2>
				</div>
				<div id="wellcomeSecondBlock">
					<table>
						<tr>
							<td><label id="textUserId" class="FrmLabel" for="j_username">Username:</label></td>
							<td><input id="j_username" class="required textFields" name="j_username" type="text" ></td>
						</tr>
						<tr>
							<td><label id="textPassword" class="FrmLabel" for="j_password">Password:</label></td>
							<td><input id="j_password" class="required textFields" name="j_password" type="password" /></td>
						</tr>
					</table>
					<input id="submitButton" type="image" src="<c:url value="/resources/images/loginButton.png" />" value="" />
				</div>
				<div id="errorBlock">${error}</div>				      	
			</div>
		</form:form>
	</div>
</body>
</html>