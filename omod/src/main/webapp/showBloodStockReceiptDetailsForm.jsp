
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
	<th>#</th>
	<th><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th><spring:message code="bloodbank.receiveBlood.product"/></th>
	<th><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/></th>
	<th><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/></th>
	<th><spring:message code="bloodbank.receiveBlood.donorName"/></th>
	<th><spring:message code="bloodbank.receiveBlood.packNo"/></th>
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
	
		</tr>
	</c:forEach>
	
	</c:when>
	</c:choose>
</table>
<br/>
	
</div>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.print"/>" onClick="RECEIPT.printDiv();" />
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.back"/>" onclick="ACT.back();">

<!-- PRINT DIV 
<div id="printDiv" style="display: none; ">
<div style="width: 100%; float: right; margin-right: 4px; font-size: 1.0em;font-family:'Dot Matrix Normal',Arial,Helvetica,sans-serif;">        		
<br />
<br />      		
<center style="float:center;font-size: 2.2em">${store.name} - Receipt - Drugs</center>
<br/>
<br/>
<span style="float:right;font-size: 1.7em">Date: <openmrs:formatDate date="${date}" type="textbox"/></span>
<br />
<br />
<table border="1">
	<tr>
	<th>#</th>
	<th><spring:message code="inventory.drug.category"/></th>
	<th><spring:message code="inventory.drug.name"/></th>
	<th><spring:message code="inventory.drug.formulation"/></th>
	<th><spring:message code="inventory.receiptDrug.quantity"/></th>
	<th><spring:message code="inventory.receiptDrug.unitPrice"/></th>
	<th><spring:message code="inventory.receiptDrug.VAT"/></th>
	<th><spring:message code="inventory.receiptDrug.totalPrice"/></th>
	<th><spring:message code="inventory.receiptDrug.batchNo"/></th>
	<th><spring:message code="inventory.receiptDrug.companyName"/></th>
	<th><spring:message code="inventory.receiptDrug.dateManufacture"/></th>
	<th><spring:message code="inventory.receiptDrug.dateExpiry"/></th>
	<th><spring:message code="inventory.receiptDrug.receiptDate"/></th>
	</tr>
	<c:choose>
	<c:when test="${not empty listReceipt}">
	<c:forEach items="${listReceipt}" var="receipt" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td>${receipt.drug.category.name} </td>	
		<td>${receipt.drug.name}</td>
		<td>${receipt.formulation.name}-${receipt.formulation.dozage}</td>
		<td>${receipt.quantity}</td>
		<td>${receipt.unitPrice}</td>
		<td>${receipt.VAT}</td>
		<td>${receipt.totalPrice}</td>
		<td>${receipt.batchNo}</td>
		<td>${receipt.companyName}</td>
		<td><openmrs:formatDate date="${receipt.dateManufacture}" type="textbox"/></td>
		<td><openmrs:formatDate date="${receipt.dateExpiry}" type="textbox"/></td>
		<td><openmrs:formatDate date="${receipt.receiptDate}" type="textbox"/></td>
		<td>${receipt.receiptFrom}</td>
		</tr>
	</c:forEach>
	</c:when>
	</c:choose>
</table>
<br/><br/><br/><br/><br/><br/>
<span style="float:right;font-size: 1.5em">Signature of inventory clerk/ Stamp</span>
</div>
</div>
-->
<!-- END PRINT DIV -->   

 
<%@ include file="/WEB-INF/template/footer.jsp" %>