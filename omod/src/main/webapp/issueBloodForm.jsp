
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/headerMinimal.jsp" %>
<%@ include file="includes/js_css.jsp" %>

<script type="text/javascript">
function issuePrompt(){

var result = jQuery('#result:checked').val();

	if (result == 'Negative'){
		alert("Result-Negative : Blood will NOT be issued to the patient.");
	}else if (result == 'Positive'){
		
	}
}
</script>
<form method="post" id="issueBloodForm">
<table class="box" width="100%">
	
	<tr>
		<td><spring:message code="bloodbank.issueBloodToPatient.crossmatchingResult"/> </td>
		<td><input type="radio" name="result" id="result" value="Positive"/>Positive</td>
		<td><input type="radio" name="result" id="result" value="Negative"/>Negative</td>
		
	</tr>
		<tr>
		<td><spring:message code="bloodbank.issueBloodToPatient.comment"/> </td>
		<td><input type="text" name="comment" id="comment" value=""/></td>
			
	</tr>
	<tr>
		<td colspan="2"><input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" onclick = "issuePrompt();" value="<spring:message code="bloodbank.submit"/>" </td>
	</tr>
</table>
</form>