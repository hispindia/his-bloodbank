
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/headerMinimal.jsp" %>
<%@ include file="includes/js_css.jsp" %>
<form method="post" >
<table class="box" width="100%">
	<tr>
		<td>Description</td>
		<td><input type="text" name="description" id="description" size="35"/></td>
		
	</tr>
	<tr>
		<td></td>
		<td colspan="2"><input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>"></td>
	</tr>
</table>
</form>