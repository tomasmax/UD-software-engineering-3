<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
	    <title><s:text name="application.title"/></title>
	    <link href="<s:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<br />
		<a class="error"><s:text name="error.logging"/></a>
		<br />
		<br />
		<s:text name="error.logging.message1"/>
		<br />
		<br />
		<s:url id="loginUrl" action="login" includeParams="none"/>
		<s:text name="error.logging.message2"/> <s:a href="%{loginUrl}"><s:text name="error.logging.message3"/></s:a>.
	</body>
</html>