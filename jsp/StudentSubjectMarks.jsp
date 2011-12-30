%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="iso3.pt.model.Alumno"%>
<%@page import="iso3.pt.model.Profesor"%>

<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="marks.title1"/> "<s:property value="selectedStudent.nombre"/>" <s:text name="marks.title2"/> "<s:property value="subject.nombre"/>"</h1>
		
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

		<table class="borderAll">
		    <tr>
		        <th>ID</th>
				<th><s:text name="mark.concept"/></th>
				<th><s:text name="mark.mark"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="evaluationList" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="concepto"/></td>
		            <td class="nowrap"><s:property value="nota"/></td>
		        </tr>
		    </s:iterator>
		</table>
		<% if (ActionContext.getContext().getSession().get("logged") instanceof Alumno) { %>
			<s:form action="studentShow" method="post">
				<s:submit value="%{getText('cancel')}" align="left"/>
			</s:form>
		<% } else if (ActionContext.getContext().getSession().get("logged") instanceof Profesor) { %>
			<s:form action="lecturerShow" method="post">
				<s:submit value="%{getText('cancel')}" align="left"/>
			</s:form>
		<% } %>
	</body>
</html>