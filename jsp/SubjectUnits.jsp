<%@ taglib prefix="s" uri="/struts-tags" %>
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
		<h1><s:text name="subjectunits.title"/> "<s:property value="subject.nombre"/>"</h1>
	
		<table class="borderAll">
		    <tr>
		        <th><s:text name="table.id"/></th>
				<th><s:text name="table.title"/></th>
				<th><s:text name="table.acronim"/></th>
		        <th><s:text name="table.content"/></th>
		    </tr>
		    <s:iterator value="unitList" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="id"/></td>
		            <td class="nowrap"><s:property value="titulo"/></td>
		            <td class="nowrap"><s:property value="acronimo"/></td>
		            <td class="nowrap"><s:property value="contenido"/></td>
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