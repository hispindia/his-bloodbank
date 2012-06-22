<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="../includes/js_css.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.validate.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
	var mode = "${mode}";
	jQuery(document).ready(function(){
		if(mode=="view"){
			fillData();
			makeForView();			
		} else if (mode=="edit"){
			fillData();
		} else if (mode=="preview"){
			
		}
		addValidations();		
	});
	
	// Add required class
	function addValidations(){
		jQuery("#form_content input").each(function(index){
			input = jQuery(this);
			input.addClass("required");
			input.attr("title", "This field is required!");
		});
		
		jQuery("#form_content select").each(function(index){
			select = jQuery(this);			
			select.addClass("required");
			select.attr("title", "This field is required!");
		});
	}
	
	// Make all inputs for view (hide box, dropdown)
	function makeForView(){
		jQuery("#form_content input").each(function(index){
			input = jQuery(this);
			input.css("border", "0px");
			input.css("color", "blue");
			input.css("font-size", "medium");
			input.attr("disabled", "disabled");
			input.css("background-color", "white");
			
			// make changes for radio buttons
			if(input.attr('type')=='radio'){
				if(input.attr('checked')==false){
					input.parent().hide();
				} else {
					input.hide();
					input.parent().css("color", "blue");
					input.parent().css("font-size", "medium");					
				}
			}
		});
		
		jQuery("#form_content select").each(function(index){
			select = jQuery(this);
			select.css("border", "0px");			
			selectedOption = jQuery("option:selected", select);
			selectedOption.css("color", "blue");
			select.after("<span style='color:blue; font-size: medium;'>"+selectedOption.html()+"</span>");
			select.hide();
		});
		
		jQuery("#form_content textarea").each(function(index){
			textarea = jQuery(this);
			value = textarea.val().replace("\n", "<br/>");			
			textarea.after("<p style='color:blue; font-size: medium;'>" + value + "</p>");
			textarea.hide();
		});
	}
	
	// set input value
	function setInputValue(name, value){		
		
		jQuery("#form_content input[name=" + name + "]").each(function(index){
			input = jQuery(this);
			if(input.attr("type")=="radio"){
				if(input.attr("value")==value){
					input.attr("checked", "checked");
				};
			} else {
				input.val(value);
			}
		});
		
		jQuery("#form_content select[name="+ name + "]").each(function(index){
			select = jQuery(this);			
			jQuery("option", select).each(function(index){
				option = jQuery(this);				
				if(option.attr("value")==value){
					option.attr("selected", "selected");
				}
			});
		});
		
		jQuery("#form_content textarea[name="+ name + "]").each(function(index){
			textarea = jQuery(this);			
			jQuery("textarea").html(value);
		});
	}
	
	// get the context path
	function getContextPath() {
		pn = location.pathname;
		len = pn.indexOf("/", 1);
		cp = pn.substring(0, len);
		return cp;
	}
	
	// Submit the form
	function submitForm(){
		validated = jQuery("#contentForm").valid();
		if(validated){
			var formContent = jQuery("#contentForm").formSerialize();
			jQuery.post(getContextPath() + "/module/bloodbank/showForm.form", formContent, function(data) {
				${param.script}
			});
			 tb_remove();
		}
	}
	
	// Validate
	function isValidated(){
		validated = true;
		jQuery("#form_content input").each(function(index){
			input = jQuery(this);
			
			if(input.attr('type')=='text'){
				if(input.val().length<=0){
					validated = false;
				}
			}
			
			// make changes for radio buttons
			if(input.attr('type')=='radio'){
				if(jQuery("#form_content input[name="+ input.attr('name') + "]:checked").length<=0)
					validated = false;
			}
		});
		
		jQuery("#form_content select").each(function(index){
			select = jQuery(this);			
			jQuery("option:selected", select).each(function(){
				option = jQuery(this);
				if(option.attr('value')=='Please select')
					validated = false;
			});
		});
		return validated;
	}
	
	// Fill data into all inputs
	function fillData(){
		<c:if test="${not empty inputNames}">
			<c:forEach var="i" begin="0" end="${inputLength-1}" step="1">
				setInputValue("${inputNames[i]}", "${inputValues[i]}");
			</c:forEach>
		</c:if>		
	}	
	
	function printPage(){
		$("#contentForm").printArea({
			mode : "popup",
			popClose : true
		});
	}
</script>

<form id="contentForm" method="post" action="showForm.form">
	
	<p style="border: 0px none; color: red; font-size: large; background-color: white;" >Donor Details</p>
	<c:forEach var='item' items='${pdetsmap}'>
			<!--<c:out value='${item.key}: ${item.value}'/><br>-->
			<p><c:out value='${item.key}: '/>
			<input type="text" value="${item.value}" name='${item.key}' style="border: 0px none; 	color: blue; font-size: small; background-color: white;" disabled=""></p>
	</c:forEach>
	<br>
	<hr>
	<br>
	<input type="hidden" name="encounterId" value="${encounterId}"/>
	<div id = patientDetails>
	
	<p style="border: 0px none; color: red; font-size: large; background-color: white;" >Donor Questionaire Details</p>
	</div>
	<div id="formPrintArea">		
		<div id="form_content">	
			${form.content}
		</div>
	</div>
	</br>
	
</form>
<c:if test="${mode eq 'edit'}">	 
	<input type="button" value="OK" onClick="submitForm();"/>
	<input type="button" value="Cancel" onClick="tb_remove();"/>
</c:if>

<c:if test="${mode eq 'view'}">	 
	<input type="button" value="Back" onClick="tb_remove();"/>
	<input type="button" value="Print" onClick="printPage();"/>
</c:if>