<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="lecturersubjects.title"/> <s:property value="lecturer.nombre"/> ( <s:property value="lecturer.dni"/> )</h1>
	
		<s:url id="logoutUrl" action="login!logout" />
		<s:a href="%{logoutUrl}"><s:text name="logout"/></s:a>
		
		<br/>
		<br/>
		<table class="borderAll">
		    <tr>
		        <th><s:text name="table.code"/></th>
				<th><s:text name="table.name"/></th>
				<th><s:text name="table.credits"/></th>
				<th><s:text name="table.lecturer"/></th>
				<th><s:text name="table.unitlist"/></th>
				<th><s:text name="table.numberstudents"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="subjectList" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="codigo"/></td>
		            <td class="nowrap"><s:property value="nombre"/></td>
		            <td class="nowrap"><s:property value="creditos"/></td>
		            <td class="nowrap"><s:property value="profesor.nombre"/></td>
		            <td class="nowrap">
		            	<s:url id="unitUrl" action="subjectShow!unitList">
							<s:param name="subjectId" value="%{id}" />
						</s:url>
						<s:a href="%{unitUrl}"><s:text name="%{unidades.size()}"/></s:a>
					</td>
		            <td class="nowrap"><s:property value="%{alumnos.size()}"/></td>
		            <td class="nowrap">
		            	<s:url id="studentUrl" action="lecturerShow!studentList">
							<s:param name="subjectId" value="%{id}" />
						</s:url>
						<s:a href="%{studentUrl}"><s:text name="table.studentURL"/></s:a>
					</td>
		        </tr>
		    </s:iterator>
		</table>
	</body>

</html>
