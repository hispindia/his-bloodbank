<!-- Header & Include -->
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="includes/js_css.jsp" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery-ui-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/agesimplifier.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/css/start/jquery-ui-1.8.2.custom.css" />

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

<openmrs:globalProperty key="bloodbank.question.valid.count" defaultValue="0" var="qcount"/>
<openmrs:globalProperty key="bloodbank.test.valid.count" defaultValue="0" var="tcount"/>
<openmrs:globalProperty key="bloodbank.result.valid.count" defaultValue="0" var="rcount"/>

<h2>Blood Orders:</h2>

<table>
	<thead><tr>
		<th class="encounterView" align="center">Order Id</th>
		<th class="encounterView" align="center">Patient Name</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${orders}" var="order">	
	<tr>
	<td>${order.orderId}</td>
	<td>${order.patient.names}</td>
	</tr>

		</c:forEach>
		</tbody>

</table>
<%@ include file="/WEB-INF/template/footer.jsp"%>