 
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="../includes/js_css.jsp" %>

<script type="text/javascript">

</script>

 <table class="box" width="100%" cellpadding="5" cellspacing="0">
	<tr>
	<th align="center">#</th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.product"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.donorName"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.packNo"/></th>
	</tr>
	<c:choose>
	<c:when test="${not empty bloodStocks}">
	<c:forEach items="${bloodStocks}" var="bloodStock" varStatus="varStatus">
	<tr class='${ bloodStock.expiryDate <= todayPlusXDays && bloodStock.expiryDate >= today? "expired" : varStatus.index % 2 == 0 ? "oddRow" : "evenRow"  } '>
		<td align="center"><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td align="center">${bloodStock.bloodGroupConcept.name} </td>	
		<td align="center">${bloodStock.product}</td>
		<td align="center"><openmrs:formatDate date="${bloodStock.receiptDate}" type="textbox"/></td>
		<td align="center"><openmrs:formatDate date="${bloodStock.expiryDate}" type="textbox"/></td>
		<td align="center">${bloodStock.donorName}</td>
		<td align="center">${bloodStock.packNo}</td>
	
		</tr>
	</c:forEach>
	
	</c:when>
	</c:choose>
</table>
