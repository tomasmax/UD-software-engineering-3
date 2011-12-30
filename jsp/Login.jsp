<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		
		<s:form action="login!login" method="post">
			<h1><s:text name="login"/></h1>
			<tr>
				<td colspan="2" class="error">
					<s:actionerror />
				</td>
			</tr>
			<s:textfield name="username" label="%{getText('login.name')}"/>
			<s:password name="password" label="%{getText('login.password')}"/>
			<s:select name="selectedRole" list="roleList" label="%{getText('login.role.select')}"/>
			<s:submit value="%{getText('login.button')}" align="center"/>
		</s:form>
	</body>
</html>
