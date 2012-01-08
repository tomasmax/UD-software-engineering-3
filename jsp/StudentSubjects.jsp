<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		<h1><s:text name="studentsubjects.title"/> <s:property value="selectedStudent.nombre"/> ( <s:property value="selectedStudent.dni"/> )</h1>
			
		<s:url id="enrollUrl" action="subjectEnrolling"></s:url>
		<s:a href="%{enrollUrl}"><s:text name="enroll"/></s:a>&nbsp;&nbsp;
		
		<s:url id="showallUrl" action="studentShow!listAllMarks"></s:url>
		<s:a href="%{showallUrl}"><s:text name="mark.showall"/></s:a>&nbsp;&nbsp;
		
		<s:url id="logoutUrl" action="login!logout"/>
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
		            	<s:url id="unit" action="subjectShow!unitList">
							<s:param name="subjectId" value="%{id}" />
						</s:url>
						<s:a href="%{unit}"><s:text name="%{unidades.size()}"/></s:a>
					</td>
		            <td class="nowrap"><s:property value="%{alumnos.size()}"/></td>
		            <td class="nowrap">&nbsp;&nbsp;
		            	<s:url id="unenrollUrl" action="subjectEnrolling!unenroll">
							<s:param name="subjectId" value="%{id}" />
						</s:url>
						<s:a href="%{unenrollUrl}"><s:text name="unenroll"/></s:a>
						
						<s:url id="marklistUrl" action="studentShow!markList">
							<s:param name="subjectId" value="%{id}" />
						</s:url>
						<s:a href="%{marklistUrl}"> <s:text name="mark.show"/></s:a>
					</td>
		        </tr>
		    </s:iterator>
		</table>
	</body>

</html>


