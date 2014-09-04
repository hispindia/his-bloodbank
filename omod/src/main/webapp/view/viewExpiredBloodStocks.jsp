
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<%@ include file="../includes/nav.jsp" %>


<openmrs:require privilege="Expired Blood Stock Balance" otherwise="/login.htm" redirect="/module/bloodbank/viewExpiredBloodStockBalance.form" />
<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery("#hyperlink1").toggleClass('');
	jQuery("#hyperlink2").toggleClass('');
	jQuery("#hyperlink3").toggleClass('');
	jQuery("#hyperlink4").toggleClass('');
	});

function warning(bloodStockId){
if(window.confirm("Are you sure?")){
	ACT.go("discard.form?bloodStockId="+bloodStockId);
	}else{ return false;}
}
</script>
<b class="boxHeader">#</b> 
 <table class="box" width="100%" cellpadding="5" cellspacing="0">
	<tr>
	<th align="center"></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.product"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.donorName"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.packNo"/></th>
	<th align="center"></th>
	</tr>
	<c:choose>
	<c:when test="${not empty bloodStocks}">
	<c:forEach items="${bloodStocks}" var="bloodStock" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td align="center"><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td align="center">${bloodStock.bloodGroupConcept.name} </td>	
		<td align="center">${bloodStock.product}</td>
		<td align="center"><openmrs:formatDate date="${bloodStock.receiptDate}" type="textbox"/></td>
		<td align="center"><openmrs:formatDate date="${bloodStock.expiryDate}" type="textbox"/></td>
		<td align="center">${bloodStock.donorName}</td>
		<td align="center">${bloodStock.packNo}</td>
		<td align="center"><a href="#" onclick="warning(${bloodStock.bloodStockId})"><spring:message code="bloodbank.discard" /></a></td>
		</tr>
	</c:forEach>
	
	</c:when>
	</c:choose>
</table>
