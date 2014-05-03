 
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<script type="text/javascript">

</script>
 <c:choose>
	<c:when test="${not empty bloodGroupStockCount}">
	<table class="box" width="10%" cellpadding="5" cellspacing="0">
	<th align="center">#</th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th align="center"><spring:message code="bloodbank.unitsAvailable"/></th>
	<c:forEach items="${bloodGroupStockCount}" var="bloodGroupStockCountItem" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
	<td align="center">${varStatus.index+1}</td>
	<td align="center">${bloodGroupStockCountItem.key}</td>
	<td align="center">${bloodGroupStockCountItem.value}</td>
	</tr>
	
	</c:forEach>	
	</table>
	</c:when>
	</c:choose>
	
