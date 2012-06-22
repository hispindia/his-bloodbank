<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="includes/js_css.jsp" %>

<openmrs:htmlInclude file="/openmrs.css" />
<openmrs:htmlInclude file="/style.css" />
<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/moduleResources/bloodbank/styles/common.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/css/start/jquery-ui-1.8.2.custom.css" />


<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
</head>
<body onload="window.print()">
<div id="printarea">
<div class="boxHeader">Tests requests:</div>
<div class="box">
<table>
	<thead><tr>
		<th class="count">#</th>
		<th class="encounterProviderHeader">Donor name:</th>
		<!-- <th class="donorId">Patient ID</th>-->
		<th class="donorId">Donor ID</th>
		<th>Blood Group:</th>
		<th>HCV test result</th>
		<th>VDRL test result</th>
		<th>HbSAg test result</th>
		<th>HIV test result</th>
		<th>Malaria test result</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${tests}" var="rec">
		<c:set var="count" value="${count+1}"/>
		<tr class="<c:choose><c:when test="${count % 2 == 0}">evenRow</c:when><c:otherwise>oddRow</c:otherwise></c:choose>">
			<td>${rec.bloodBankId}</td>
			<td class="patientName">${rec.patient.personName}</td>
			<c:forEach items="${rec.patient.identifiers}" var="id">
				<td>${id}</td>
			</c:forEach>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		</c:forEach>
</table>
</div>
</div>

</html>