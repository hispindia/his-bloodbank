<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="includes/js_css.jsp" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/css/thickbox.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/jquery.thickbox.js"></script>

<br/>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/CKEditor/ckeditor.js"></script>

<script type="text/javascript">		
	
</script>
		<textarea class="ckeditor" cols="80" id="editor1" name="${status.expression}" rows="10">
			${status.value}
		</textarea>	
	<input type="button" value="Save" onClick="submitForm();"/>	
	<input type="button" value="Cancel" onClick="javascript:window.location.href='listForm.form'"/>	
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>  