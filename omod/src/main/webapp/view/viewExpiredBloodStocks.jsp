
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<%@ include file="../includes/nav.jsp" %>

<script type="text/javascript">
function warning(bloodStockId){
if(window.confirm("Are you sure?")){
	ACT.go("discard.form?bloodStockId="+bloodStockId);
	}else{ return false;}
}
</script>
<b class="boxHeader">#</b> 
 <table class="box" width="100%" cellpadding="5" cellspacing="0">
	<tr>
	<th></th>
	<th><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th><spring:message code="bloodbank.receiveBlood.product"/></th>
	<th><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/></th>
	<th><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/></th>
	<th><spring:message code="bloodbank.receiveBlood.donorName"/></th>
	<th><spring:message code="bloodbank.receiveBlood.packNo"/></th>
	<th></th>
	</tr>
	<c:choose>
	<c:when test="${not empty bloodStocks}">
	<c:forEach items="${bloodStocks}" var="bloodStock" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td>${bloodStock.bloodGroupConcept.name} </td>	
		<td>${bloodStock.product}</td>
		<td><openmrs:formatDate date="${bloodStock.receiptDate}" type="textbox"/></td>
		<td><openmrs:formatDate date="${bloodStock.expiryDate}" type="textbox"/></td>
		<td>${bloodStock.donorName}</td>
		<td>${bloodStock.packNo}</td>
		<td><a href="#" onclick="warning(${bloodStock.bloodStockId})"><spring:message code="bloodbank.discard" /></a></td>
		</tr>
	</c:forEach>
	
	</c:when>
	</c:choose>
</table>
