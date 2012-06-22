<%@ include file="../mainMenu.jsp"%>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/css/thickbox.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/jquery-1.4.2.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/hospitalcore/scripts/jquery/jquery.thickbox.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/CKEditor/ckeditor.js"></script>

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<div id="properties" style="display:none">
<openmrs:portlet url="globalProperties" id="globalPropertyEditSection" parameters="propertyPrefix=bloodbank.|excludePrefix=bloodbank.started|hidePrefix=true" />
</div>
<script type="text/javascript" charset="utf-8">

	var DUPLICATED_FORM = false;
	/*
	jQuery(document).ready(function(){		
		jQuery("#concept").autocomplete(getContextPath() + '/module/bloodbank/ajax/autocompleteConceptSearch.htm').result(function(event, item){
			checkExistingForm();
		}
		jQuery("#concept").focus();
		);;		
	});
	*/
	//insert obs from thickbox
	function insertObs(name, type, required){		
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/bloodbank/getHTMLObs.form",
			data : ({
				name			: name,
				type			: type,
				required		: required
			}),
			success : function(data) {
				CKEDITOR.instances.editor1.insertHtml(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});		
	}
	/*
	// check existing form with concept/type
	function checkExistingForm(item){		
		type = jQuery('#formType').val();
		conceptName = jQuery("#concept").val();
		jQuery.ajax({
			type : "GET",
			url : getContextPath() + "/module/bloodbank/ajax/checkExistingForm.htm",
			data : ({
				conceptName		: conceptName,
				type			: type,
				formId			: '${param.id}'
			}),
			success : function(data) {
				jQuery('#checkExistingFormStatus').html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("ERROR " + xhr);
			}
		});			
	}
	*/
	function submitForm(){		
		if(DUPLICATED_FORM){
			alert('Please check form type and concept and submit again!');			
		} else {
			jQuery("#bloodbankForm").submit();
		}
	}

</script>

<form id='bloodbankForm' method="post" enctype="multipart/form-data">		 
	<table>
		<tr>
			<spring:bind path="form.name">
				<td>Name</td>
				<td><input type="text" name="${status.expression}" value="${status.value}" style="width:350px;"/></td>
			</spring:bind>
		</tr>
		<tr>
			<spring:bind path="form.description">
				<td>Description:</td>
				<td><input type="text" name="${status.expression}" value="${status.value}" style="width:350px;"/></td>
			</spring:bind>
		</tr>		
		<tr>
			<spring:bind path="form.conceptName"> 
				<td>Concept:</td>
				<td><input id="concept" type="text" name="${status.expression}" value="${status.value}" style="width:350px;" disabled="disabled"/></td>
				<td id='checkExistingFormStatus'></td>
			</spring:bind>
		</tr>		
	</table>
	<spring:bind path="form.content">
		<textarea class="ckeditor" cols="80" id="editor1" name="${status.expression}" rows="10">
			${status.value}
		</textarea>
	</spring:bind>
	<input type="button" value="Save" onClick="submitForm();"/>	
	<input type="button" value="Cancel" onClick="javascript:window.location.href='list.form'"/>	
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>