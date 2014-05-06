<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="includes/nav.jsp" %>

<openmrs:require privilege="Issue Blood" otherwise="/login.htm" redirect="/module/bloodbank/viewExpiredBloodStockBalance.form" />

<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery("#hyperlink1").toggleClass('');
	jQuery("#hyperlink2").toggleClass('');
	jQuery("#hyperlink3").toggleClass('');
	jQuery("#hyperlink4").toggleClass('highlighted');
});
SEARCH={
	search : function() {
		var fromDate = jQuery('#fromDate').val();
		var toDate = jQuery('#toDate').val();
		var description = jQuery('#description').val();
		ACT.go("receiveBlood.form?fromDate="+fromDate+"&toDate="+toDate+"&description="+description);
	
	}
}
</script>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='bloodbank.issueBlood'/>" onclick="ACT.go('showPatientSearchForm.form');"/>
<br></br>


<div style="margin-left: 10px; ">
<b class="boxHeader">Issued Blood Stocks</b> 
<table class="box"  cellpadding="0" cellspacing="5" >
	<tr>
	<th align="center">#</th>
	<th align="center"><spring:message code="bloodbank.patientIdentifier"/></th>
	<th align="center"><spring:message code="bloodbank.patient.name"/></th>
	<th align="center"><spring:message code="bloodbank.patient.age"/></th>
	<th align="center"><spring:message code="bloodbank.patient.gender"/></th>
	<th align="center"><spring:message code="bloodbank.issuedon"/></th>
	<th align="center"><spring:message code="bloodbank.issuedby"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.packNo"/></th>
	<th align="center"></th>
	</tr>
	<c:choose>
	<c:when test="${not empty issuedBloodStocks}">
	<c:forEach items="${issuedBloodStocks}" var="issuedStock" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td align="center"><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td align="center">${issuedStock.patient.patientIdentifier.identifier} </td>	
		<td align="center">${issuedStock.patient.givenName} ${issuedStock.patient.middleName} ${issuedStock.patient.familyName}</td>	
		<td align="center">	<c:choose>
                		<c:when test="${issuedStock.patient.age == 0}"> &lt 1 </c:when>
                		<c:otherwise >${issuedStock.patient.age}</c:otherwise>
                	</c:choose>
		</td>
		<td align="center">
					<c:choose>
                		<c:when test="${issuedStock.patient.gender eq 'M'}">
							<img src="${pageContext.request.contextPath}/images/male.gif"/>
						</c:when>
                		<c:otherwise><img src="${pageContext.request.contextPath}/images/female.gif"/></c:otherwise>
                	</c:choose>
				</td>                
				
		<td align="center"><openmrs:formatDate date="${issuedStock.createdOn}" type="textbox"/></td>
		<td align="center">${issuedStock.createdBy}</td>
		<td align="center">${issuedStock.bloodStock.bloodGroupConcept.displayString}</td>
		<td align="center">${issuedStock.bloodStock.packNo}</td>
		</tr>
	</c:forEach>
	
	</c:when>
	</c:choose>
</table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>