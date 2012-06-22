<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="../includes/js_css.jsp" %>

<script type="text/javascript">	
	function getContextPath() {
		pn = location.pathname;
		len = pn.indexOf("/", 1);
		cp = pn.substring(0, len);
		return cp;
	}
	
	function getConceptRequired() {
		var form = document.getElementById('showPopup');
		var tester = form.elements["conceptRequired"].checked;
		//alert("This value shud probably be sent" + tester);
		return tester;
	}
	
	jQuery(document).ready(function(){		
		jQuery("#conceptPopup").autocomplete(getContextPath() + '/module/bloodbank/ajax/autocompleteConceptSearch.htm').result(function(event, item){window.parent.insertObs(jQuery('#conceptPopup').val(),'${type}',getConceptRequired()); tb_remove();});
		jQuery("#conceptPopup").focus();
	});
</script>
<form id="showPopup" name="showPopup">
	<center>
		<table cellspacing="20">
			<tr>
				<td>
					<b>Concept</b>
				</td>
				<td>
					<input id="conceptPopup" style="width:350px;"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" id="conceptRequired" name="conceptRequired" value="required" />
				</td>
				<td>
					<b>Required</b>
				</td>
			</tr>
		</table>
		<input type="button" onClick="javascript:window.parent.insertObs(jQuery('#conceptPopup').val(),'${type}', getConceptRequired()); tb_remove();" value="Insert"/>
		<input type="button" onClick="tb_remove();" value="Close"/>
	</center>
</form>