
<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Add/Edit Bloodstock" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<spring:message var="pageTitle" code="bloodbank.title" scope="page"/>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="includes/js_css.jsp" %>
<%@ include file="includes/nav.jsp" %>

<script type="text/javascript">
jQuery(document).ready(function() {
	jQuery("#resultTable").hide();

	jQuery('#dateOfReceipt').datepicker({ maxDate: new Date() ,dateFormat: 'dd/mm/yy'});
	
	jQuery("#hyperlink1").toggleClass('');
	jQuery("#hyperlink2").toggleClass('');
	jQuery("#hyperlink3").toggleClass('');
	jQuery("#hyperlink4").toggleClass('');
	
	
	jQuery("#dateOfReceipt").change(function() {
		VALIDATION.checkRecieptDate();
	});
	jQuery("#dateOfExpiry").change(function() {
		VALIDATION.checkRecieptDate();
	});
	});
APPEND = {
		
		moveStock : function(){
		
			if(jQuery("#receiptBloodStockReceiptInput").valid() == true){
		var tableLength = jQuery('#receiptTable tr').length;
		var bloodGroup = 	jQuery('#bloodGroup').val();
		var bloodGroupName = jQuery('#bloodGroup').children(':selected').text();
		var product = 	jQuery('#product').val();
		var dateOfReceipt = 	jQuery('#dateOfReceipt').val();
		var dateOfExpiry = 	jQuery('#dateOfExpiry').val();
		var donorName = 	jQuery('#donorName').val();
		var packNo = 	jQuery('#packNo').val();
			if (tableLength%2==0){
			jQuery('#receiptTable').append('<tr class="evenRow" id=\''+bloodGroup+'\'><td align="center">'+tableLength+'</td><td align="center">'+bloodGroupName+'</td><td align="center">'+product+'</td><td align="center">'+dateOfReceipt+'</td><td align="center">'+dateOfExpiry+'</td><td align="center">'+donorName+'</td><td align="center">'+packNo+'</td></tr>');
			}else{
			jQuery('#receiptTable').append('<tr class="oddRow" id=\''+bloodGroup+'\'><td align="center">'+tableLength+'</td><td align="center">'+bloodGroupName+'</td><td align="center">'+product+'</td><td align="center">'+dateOfReceipt+'</td><td align="center">'+dateOfExpiry+'</td><td align="center">'+donorName+'</td><td align="center">'+packNo+'</td></tr>');
			}
			jQuery('#hiddenDiv').append('<input hidden type="text" id=bloodGroup_'+tableLength+' value='+bloodGroup+' />');
			product=product.replace(" ","_");
			jQuery('#hiddenDiv').append('<input hidden type="text" id=product_'+tableLength+' value='+product+' />');
			jQuery('#hiddenDiv').append('<input hidden type="text" id=dateOfReceipt_'+tableLength+' value='+dateOfReceipt+' />');
			jQuery('#hiddenDiv').append('<input hidden type="text" id=dateOfExpiry_'+tableLength+' value='+dateOfExpiry+' />');
			donorName=donorName.replace(" ","_");
			jQuery('#hiddenDiv').append('<input hidden type="text" id=donorName_'+tableLength+' value='+donorName+' />');
			packNo=packNo.replace(" ","_");
			jQuery('#hiddenDiv').append('<input hidden type="text" id=packNo_'+tableLength+' value='+packNo+' />');
			jQuery('#totalStocks').val(tableLength);
		
		jQuery('#bloodGroup').val(1);
		jQuery('#product').val("");
		jQuery('#dateOfReceipt').val("");
		jQuery('#dateOfExpiry').val("");
		jQuery('#donorName').val("");
		jQuery('#packNo').val("");
		
			jQuery("#resultTable").show();
			}
		}
}
VALIDATION={

	
	checkRecieptDate : function() {
		var recieptDate = new Date(STRING.convertDateFormat(jQuery('#dateOfReceipt').val()));
		var expiryDate = new Date(STRING.convertDateFormat(jQuery('#dateOfExpiry').val()));

		if (recieptDate >= expiryDate){
			jQuery('#dateOfReceipt').val("");
			jQuery('#dateOfExpiry').val("");
			alert("You can not receipt an expired stock");
		}
	}
}

</script>
<form method="post" id="receiptBloodStockReceiptInput">
<div style="width: 20%; float: left; margin-left: 4px; ">
<b class="boxHeader"><spring:message code="bloodbank.receiveBlood.add"/></b>
<div class="box">
<input hidden type="numeric" id="receiptId" name="receiptId" value = "${receiptId}"  />

<br/>
<table width="100%" cellpadding="5" cellspacing="0" >
 <tr>
	<td><spring:message code="bloodbank.receiveBlood.bloodgroup"/><em>*</em></td>
		<td>
			<select name="bloodGroup" id="bloodGroup"  style="width: 80%;">
				<option value=""><spring:message code="bloodbank.receiveblood.selectbloodgroup"/></option>
                <c:forEach items="${bloodGroups}" var="bloodGroup">
                    <option name="${bloodGroup.answerConcept.name}" value="${bloodGroup.answerConcept.id}" title="${bloodGroup.answerConcept.id}">${bloodGroup.answerConcept.name}</option>
                </c:forEach>
  			</select>
	</td>	
</tr>

	<tr>
		<td><spring:message code="bloodbank.receiveBlood.product"/><em>*</em></td>
		<td>
			<input type="text" id="product" name="product" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/><em>*</em></td>
		<td>
			<input type="text" id="dateOfReceipt" name="dateOfReceipt" readonly="readonly"  ondblclick="this.value='';"/>
		</td>
	</tr>
	
	<tr>
		<td><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/><em>*</em></td>
		<td>
			<input type="text" id="dateOfExpiry" name="dateOfExpiry" class="date-pick left" readonly="readonly"  ondblclick="this.value='';"/>
		</td>
	</tr>
	
	<tr>
		<td><spring:message code="bloodbank.receiveBlood.donorName"/><em>*</em></td>
		<td>
			<input type="text" id="donorName" name="donorName" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="bloodbank.receiveBlood.packNo"/><em>*</em></td>
		<td>
			<input type="text" id="packNo" name="packNo" />
		</td>
	</tr>

	
</table>
<br/>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.receiveblood.addToSlip"/>" onclick = "APPEND.moveStock();">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.back"/>" onclick="BloodBank.clearBloodReceipt(${receiptId});">

</div>
</div>
</form>
<!-- Receipt list -->
<form method="post" id="receiptBloodStockReceipt">
<div style="width: 79%; float: right; margin-right: 4px; ">
<b class="boxHeader">Receipt Slip</b> 


<table id="receiptTable" class="box" width="100%"  cellpadding="5" cellspacing="0">
	<tr>
	<th align="center">#</th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.bloodgroup"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.product"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfReceipt"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.dateOfExpiry"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.donorName"/></th>
	<th align="center"><spring:message code="bloodbank.receiveBlood.packNo"/></th>
	</tr>
</table>
<div id="hiddenDiv">
<input hidden type="text" id="totalStocks" name="totalStocks"  />
</div>
<br/>
		<table id="resultTable" class="box" width="100%" cellpadding="5" cellspacing="0">
		<td>
				<input  type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.receiveblood.finish"/>" onclick="RECEIPT.receiptSlip(${receiptId});" />
			<!-- 	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.receiveblood.clear"/>"  onclick="RECEIPT.receiptSlip('1');"/>
				<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="bloodbank.receiveblood.print"/>" onClick="RECEIPT.printDiv();" />
		-->	</td>
		</tr>
		</table>
</div>
 </form>
<%@ include file="/WEB-INF/template/footer.jsp" %>