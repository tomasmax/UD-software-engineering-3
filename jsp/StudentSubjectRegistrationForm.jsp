<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="registration.title"/> <s:property value="selectedStudent.nombre"/> ( <s:property value="selectedStudent.dni"/> )</h1>
		<s:if test="%{subjectList.isEmpty()}">
			<s:text name="registration.emptylist"/>
		</s:if>
		<s:else>
			<s:form action="subjectEnrolling!enroll" method="post">
				<s:select name="subjectId" list="subjectList" listKey="id" listValue="nombre" label="%{getText('registration.selection')}"/>
				<s:submit value="%{getText('submit')}" align="left"/>
			</s:form>
		</s:else>
		<s:form action="studentShow" method="post">
			<s:submit value="%{getText('cancel')}" align="left"/>
		</s:form>
	</body>
</html>