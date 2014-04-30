<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="includes/js_css.jsp" %>
<%@ include file="includes/nav.jsp" %>

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<script type="text/javascript">
jQuery(document).ready(function() {

		// hover rows
		jQuery(".patientSearchRow").hover(
			function(event){					
				obj = event.target;
				while(obj.tagName!="TR"){
					obj = obj.parentNode;
				}
				PATIENTSEARCHRESULT.oldBackgroundColor = jQuery(obj).css("background-color");
				jQuery(obj).css("background-color", "#00FF99");									
			}, 
			function(event){
				obj = event.target;
				while(obj.tagName!="TR"){
					obj = obj.parentNode;
				}
				jQuery(obj).css("background-color", PATIENTSEARCHRESULT.oldBackgroundColor);				
			}
		);

SEARCH={
	search : function() {
		var nameOrId = jQuery('#nameOrId').val();
		ACT.go("showPatientSearchForm.form?nameOrId="+nameOrId);
	
		}
	}
	
PATIENTSEARCHRESULT = {
		oldBackgroundColor: "",
		
		/** Click to view patient info */
		visit: function(patientId,deadInfo){
		if(deadInfo=="true"){
        alert("This Patient is Dead");
        return false;
        }						
			window.location.href = openmrsContextPath + "/module/bloodbank/issueBloodToPatient.form?patientId=" + patientId;
		}
		
	};
	
});
</script>


<div id="searchDiv">
<spring:message code="bloodbank.search.nameOrId"/><input type="text" id="nameOrId"/>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.receiveBlood.search"/>" onclick="SEARCH.search();">

</div>

<div style="margin-left: 10px; ">
<b class="boxHeader">Patient Found</b> 
<c:choose>
	<c:when test="${not empty patients}" >		
	<table style="width:100%">
		<tr>
		
			<td><b><spring:message code="bloodbank.patientIdentifier"/></b></td>
			<td><b><spring:message code="bloodbank.patient.name"/></b></td>
			<td><b><spring:message code="bloodbank.patient.age"/></b></td>
			<td><b><spring:message code="bloodbank.patient.gender"/></b></td>			
	
			<td><b>Last day of visit</b></td>
	
		</tr>
		<c:forEach items="${patients}" var="patient" varStatus="varStatus">
			<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } patientSearchRow'>
				
				<td onclick="PATIENTSEARCHRESULT.visit(${patient.patientId},'${patient.dead}');">
					${patient.patientIdentifier.identifier}
				</td>
				<td onclick="PATIENTSEARCHRESULT.visit(${patient.patientId},'${patient.dead}');">${patient.givenName} ${patient.middleName} ${patient.familyName}</td>
				<td onclick="PATIENTSEARCHRESULT.visit(${patient.patientId},'${patient.dead}');"> 
                	<c:choose>
                		<c:when test="${patient.age == 0}"> &lt 1 </c:when>
                		<c:otherwise >${patient.age}</c:otherwise>
                	</c:choose>
                </td>
				<td onclick="PATIENTSEARCHRESULT.visit(${patient.patientId},'${patient.dead}');">
					<c:choose>
                		<c:when test="${patient.gender eq 'M'}">
							<img src="${pageContext.request.contextPath}/images/male.gif"/>
						</c:when>
                		<c:otherwise><img src="${pageContext.request.contextPath}/images/female.gif"/></c:otherwise>
                	</c:choose>
				</td>                
				
                <td onclick="PATIENTSEARCHRESULT.visit(${patient.patientId},'${patient.dead}');">
	                <openmrs:formatDate date="${lastVisitTime[patient.patientId]}"/>              	
                </td>
              
			</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
	
	</c:otherwise>
</c:choose>
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>