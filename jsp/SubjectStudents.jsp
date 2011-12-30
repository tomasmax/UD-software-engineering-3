<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="subjectstudents.title1"/> "<s:property value="subject.profesor.nombre"/>" <s:text name="subjectstudents.title2"/> "<s:property value="subject.nombre"/>"</h1>
	
		<table class="borderAll">
	    <tr>
	        <th><s:text name="table.dni"/></th>
			<th><s:text name="table.name"/></th>
			<th><s:text name="table.phone"/></th>
	        <th>&nbsp;&nbsp;</th>
	    </tr>
	    <s:iterator value="studentList" status="status">
	        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
	            <td class="nowrap"><s:property value="dni"/></td>
	            <td class="nowrap"><s:property value="nombre"/></td>
	            <td class="nowrap"><s:property value="telefono"/></td>
	            <td class="nowrap"><s:url id="markUrl" action="subjectMarking">
								    	<s:param name="studentDni" value="%{dni}" />
								    	<s:param name="subjectId" value="%{subjectId}" />
									</s:url>
									<s:a href="%{markUrl}"><s:text name="table.addmark"/></s:a>
				</td>
	        </tr>
	    </s:iterator>
	</table>
	<s:form action="lecturerShow" method="post">
		<s:submit value="%{getText('cancel')}" align="left" />
	</s:form> 
	</body>

</html>