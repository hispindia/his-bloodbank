<!-- Header & Include -->
<%@ include file="mainMenu.jsp"%>
<%@ include file="../includes/js_css.jsp" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/css/start/jquery-ui-1.8.2.custom.css" />

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

<openmrs:globalProperty key="bloodbank.question.valid.count" defaultValue="0" var="qcount"/>
<openmrs:globalProperty key="bloodbank.test.valid.count" defaultValue="0" var="tcount"/>
<openmrs:globalProperty key="bloodbank.result.valid.count" defaultValue="0" var="rcount"/>

<h2>Encounters of donor ${patient.personName}</h2>
<h3>Patient ID: ${patient.patientIdentifier}</h3>
<!--  <h3>Donor ID: ${donorId}</h3>--><br>
<h3>Blood bank encounters:</h3>

<form method="post" accept-charset="utf-8">
	<input type="hidden" name="newEncounter" value="true" id="newEncounter">

	<p><input type="submit" value="New donation &rarr;"></p>
</form>

<script type="text/javascript">
	function showPopup( id , encounterId , mode )
	{
		tb_show( "testing", "showForm.form?script=refresh();&modal=true&height=600&width=800&id=" + id + "&encounterId=" + encounterId + "&mode=" + mode );
	}
	
	function refresh(){
		window.location.href = window.location.href;
	}
</script>

<table cellspacing="0" cellpadding="2" id="donorData">
	<thead><tr>
		<th style="display:none">ID#</th>
		<th class="bloodbankID">Donation#</th>
		<th class="encounterTypeHeader"> <spring:message code="Encounter.type"/></th>
		<th class="encounterView" align="center">Edit</th>
		<th class="encounterView" align="center">Correct</th>
		<th class="encounterProviderHeader"><spring:message code="Encounter.provider"/></th>
		<th class="encounterTypeHeader"> <spring:message code="Encounter.type"/></th>
		<th class="encounterView" align="center">View</th>
		<th class="encounterView" align="center">Correct</th>
		<th class="encounterProviderHeader"> <spring:message code="Encounter.provider"/> </th>
		<th class="encounterTypeHeader"><spring:message code="Encounter.type"/>  </th>
		<th class="encounterView" align="center">View</th>
		<th class="encounterView" align="center">Correct</th>
		<th class="encounterProviderHeader"> <spring:message code="Encounter.provider"/> </th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${encounters}" var="enc">
		<c:set var="count" value="${count+1}"/>
		<tr class="<c:choose><c:when test="${count % 2 == 0}">evenRow</c:when><c:otherwise>oddRow</c:otherwise></c:choose>">
			<td style="display:none">${enc.bloodBankId}</td>
			<td class="encounterDonorId">${enc.bloodBankId}</td>
			<td class="encounterType">${enc.questionnaire.encounterType.name}</td>
			<td class="encounterEdit" align="center">
				<openmrs:hasPrivilege privilege="Edit Encounters">
				<!--  
				<a href="${pageContext.request.contextPath}/module/bloodbank/showForm.form?id=2&encounterId=${enc.questionnaire.encounterId}&mode=EDIT&returnUrl=${pageContext.request.contextPath}/module/bloodbank/showDonorEncounters.form">
				-->
				<a href="javascript: showPopup( 2 , ${enc.questionnaire.encounterId} , 'edit' );">
						<img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit" align="top" border="0">
					</a>
				</openmrs:hasPrivilege>
			</td>
			<td class="encounterProvider">
			
			<c:set var="innercount" value="0"/> 
			<c:forEach items="${enc.questionnaire.obs}">
			<c:set var="innercount" value="${innercount+1}"/>  
			</c:forEach>
				<c:choose>
                    <c:when test="${innercount > qcount == 'true'}">
                    <img src='${pageContext.request.contextPath}/images/checkmark.png' alt='correct' />
                    </c:when>
                    <c:otherwise>
						<img src='${pageContext.request.contextPath}/images/error.gif' alt='wrong' />
                    </c:otherwise>
                </c:choose>
			</td>
			
			<td class="encounterProvider">${enc.questionnaire.provider.personName}</td>
			<td class="encounterType">${enc.test.encounterType.name}</td>
			<td class="encounterEdit" align="center">
			<c:if test="${ enc.test != null }">
				<openmrs:hasPrivilege privilege="Edit Encounters">
				<!-- 
				<a href="${pageContext.request.contextPath}/module/bloodbank/showForm.form?encounterId=${enc.test.encounterId}&mode=VIEW&returnUrl=${pageContext.request.contextPath}/module/bloodbank/showDonorEncounters.form">
				-->
				<a href="javascript: showPopup( 3 , ${enc.test.encounterId} , 'view' );">
						<img src="${pageContext.request.contextPath}/images/info.gif" title="Edit" align="top" border="0">
				</a>
				</openmrs:hasPrivilege>
				</c:if>
			</td>
			<td class="encounterProvider">
				<c:choose>
					<c:when test="${enc.bloodResult != null}">
						<img src='${pageContext.request.contextPath}/images/checkmark.png' alt='correct' />
					</c:when>
					<c:otherwise>
						<c:if test="${enc.voided}">
							<b style="color:red">VOIDED!</b>
						</c:if>
            	    </c:otherwise>
				</c:choose>
			</td>
						
			<td class="encounterProvider">${enc.test.provider.personName}</td>
			<td class="encounterType">${enc.bloodResult.encounterType.name}</td>
			<c:choose>
				<c:when test="${ enc.bloodResult != null }">
				<td class="encounterEdit" align="center">
				<openmrs:hasPrivilege privilege="Edit Encounters">
				<!-- 
				<a href="${pageContext.request.contextPath}/module/bloodbank/showForm.form?encounterId=${enc.bloodResult.encounterId}&mode=VIEW&returnUrl=${pageContext.request.contextPath}/module/bloodbank/showDonorEncounters.form">
				-->
				<a href="javascript: showPopup( 1 , ${enc.bloodResult.encounterId} , 'view' );">
						<img src="${pageContext.request.contextPath}/images/info.gif" title="Edit" align="top" border="0">
					</a>
				</openmrs:hasPrivilege>
				<td class="encounterProvider">
				<c:set var="innercount" value="0"/> 
				<c:forEach items="${enc.bloodResult.obs}">
				<c:set var="innercount" value="${innercount+1}"/>  
				</c:forEach>
					<c:choose>
	                    <c:when test="${innercount > rcount == 'true'}">
	                    <img src='${pageContext.request.contextPath}/images/checkmark.png' alt='correct' />
	                    </c:when>
	                    <c:otherwise>
							<img src='${pageContext.request.contextPath}/images/error.gif' alt='wrong' />
	                    </c:otherwise>
	                </c:choose>
				</td>
				</c:when>
				<c:otherwise>
					<td class="encounterprovider"></td>
					<td class="encounterprovider"></td>
				</c:otherwise>
			</c:choose>
							
			<td class="encounterProvider">${enc.bloodResult.provider.personName}</td>
		</tr>
		</c:forEach>
</table>


<%@ include file="/WEB-INF/template/footer.jsp"%>