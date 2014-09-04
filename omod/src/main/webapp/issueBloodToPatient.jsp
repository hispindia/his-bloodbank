<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="includes/js_css.jsp" %>
<%@ include file="includes/nav.jsp" %>

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery("#hyperlink1").toggleClass('');
	jQuery("#hyperlink2").toggleClass('');
	jQuery("#hyperlink3").toggleClass('');
	jQuery("#hyperlink4").toggleClass('');

});


</script>
	
	<b><spring:message code="bloodbank.issueBloodToPatient.patientName"/></b> 		${patient.givenName}  ${patient.familyName} ${fn:replace(patient.middleName,","," ")}<br/>
	<b><spring:message code="bloodbank.issueBloodToPatient.patientID"/>	</b>	${patient.patientIdentifier.identifier}<br/>
	<b><spring:message code="bloodbank.issueBloodToPatient.patientAge"/></b>		
					<c:choose>
                		<c:when test="${patient.age == 0}"> &lt 1 </c:when>
                		<c:otherwise >${patient.age}</c:otherwise>
                	</c:choose>
        	<br/>
	<b><spring:message code="bloodbank.issueBloodToPatient.patientGender"/>	</b>		
				
					<c:choose>
                		<c:when test="${patient.gender eq 'M'}">
							<img src="${pageContext.request.contextPath}/images/male.gif"/>
						</c:when>
                		<c:otherwise><img src="${pageContext.request.contextPath}/images/female.gif"/></c:otherwise>
                	</c:choose>
				             
			

<b class="boxHeader">
<spring:message code="bloodbank.issueBloodToPatient.patientBloodGroup"/>	<select name="bloodGroup" id="bloodGroup"  style="width: 30%;" onchange="BloodBank.getBloodStocks(this,${patient.id});">
				<option value=""><spring:message code="bloodbank.receiveblood.selectbloodgroup"/></option>
                <c:forEach items="${bloodGroups}" var="bloodGroup">
                    <option value="${bloodGroup.answerConcept.id}" title="${bloodGroup.answerConcept.id}">${bloodGroup.answerConcept.name}</option>
                </c:forEach>
  			</select>
 </b>  			
  			<div id="availableBloodStocks"></div>


<%@ include file="/WEB-INF/template/footer.jsp"%>