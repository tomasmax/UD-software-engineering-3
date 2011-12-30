<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="marks.title1"/> "<s:property value="selectedStudent.nombre"/>"</h1>
		
		<table class="borderAll">
		    <tr>
		        <th><s:text name="table.dni"/></th>
				<th><s:text name="table.name"/></th>
				<th><s:text name="table.phone"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <tr>
	            <td class="nowrap"><s:property value="selectedStudent.dni"/></td>
	            <td class="nowrap"><s:property value="selectedStudent.nombre"/></td>
	            <td class="nowrap"><s:property value="selectedStudent.telefono"/></td>
			</tr>
		</table>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="table.subject"/></th>
				<th><s:text name="table.id"/></th>
				<th><s:text name="mark.concept"/></th>
				<th><s:text name="mark.mark"/></th>
		    </tr>
		    <s:iterator value="evaluationList" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="asignatura.nombre"/></td>
		            <td class="nowrap"><s:property value="asignatura.id"/></td>
		            <td class="nowrap"><s:property value="concepto"/></td>
		            <td class="nowrap"><s:property value="nota"/></td>
		        </tr>
		    </s:iterator>
		</table>
		<br/>

		<s:form action="studentShow" method="post">
			<s:submit value="%{getText('cancel')}" align="left"/>
		</s:form>
	</body>
</html>