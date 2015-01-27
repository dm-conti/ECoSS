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
		<jsp:forward page='/' />
		<% response.sendRedirect("j_spring_security_logout"); %>
		<% request.getSession().invalidate(); %>
	</div>
</body>
</html>