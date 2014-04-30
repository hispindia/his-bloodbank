
<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Add/Edit Bloodstock" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<spring:message var="pageTitle" code="bloodbank.title" scope="page"/>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="includes/js_css.jsp" %>
<%@ include file="includes/nav.jsp" %>

<script type="text/javascript">
jQuery(document).ready(function() {
	
	
}

</script>
<!-- Receipt list -->
<div id="printDiv" style="width: 79%;  ">
<b class="boxHeader">Receipt Slip-${receiptId}</b> 

<table class="box" width="100%" border="1" cellpadding="5" cellspacing="0">
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
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
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
<br/>
	
</div>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.print"/>" onClick="RECEIPT.printDiv();" />
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.back"/>" onclick="ACT.back();">
 
<%@ include file="/WEB-INF/template/footer.jsp" %>