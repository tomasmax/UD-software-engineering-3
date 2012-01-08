<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="addmark.title"/></h1>
		
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
		        <th><s:text name="table.code"/></th>
				<th><s:text name="table.name"/></th>
				<th><s:text name="table.credits"/></th>
				<th><s:text name="table.lecturer"/></th>
				<th><s:text name="table.numberstudents"/></th>
		    </tr>
	        <tr>
	            <td class="nowrap"><s:property value="subject.codigo"/></td>
	            <td class="nowrap"><s:property value="subject.nombre"/></td>
	            <td class="nowrap"><s:property value="subject.creditos"/></td>
	            <td class="nowrap"><s:property value="subject.profesor.nombre"/></td>
	            <td class="nowrap"><s:property value="%{subject.alumnos.size()}"/></td>
	        </tr>
		</table>
		<br/>
		<s:form action="subjectMarking!submitMark" method="post">
			<s:textfield name="concept" label="%{getText('mark.concept')}"/>
			<s:textfield name="mark" label="%{getText('mark.mark')}"/>
			<s:hidden name="subjectId" value="%{subject.id}" />
			<s:hidden name="studentDni" value="%{selectedStudent.dni}" />
			<s:submit value="%{getText('submit')}" align="left"/>
		</s:form>
		<s:form action="lecturerShow" method="post">
			<s:submit value="%{getText('cancel')}" align="left"/>
		</s:form>
	</body>
</html>


