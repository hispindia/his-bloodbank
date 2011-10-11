<%@ include file="/WEB-INF/template/include.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.validate.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/jquery.validate.min.js"></script>
<script type="text/javascript">
	var mode = "${mode}";
	
	$(document).ready(function(){
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
		$("#form_content input").each(function(index){
			input = $(this);
			if(input.attr('title')=='true'){
				//input.attr("title", "This field is required!");
			}
			input.removeClass("required");
		});
		
		$("#form_content select").each(function(index){
			select = $(this);			
			select.addClass("required");
			select.attr("title", "This field is required!");
		});
	}
	
	// Make all inputs for view (hide box, dropdown)
	function makeForView(){
		$("#form_content input").each(function(index){
			input = $(this);
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
		
		$("#form_content select").each(function(index){
			select = $(this);
			select.css("border", "0px");			
			selectedOption = $("option:selected", select);
			selectedOption.css("color", "blue");
			select.after("<span style='color:blue; font-size: medium;'>"+selectedOption.html()+"</span>");
			select.hide();
		});
		
		$("#form_content textarea").each(function(index){
			textarea = $(this);
			value = textarea.val().replace("\n", "<br/>");			
			textarea.after("<p style='color:blue; font-size: medium;'>" + value + "</p>");
			textarea.hide();
		});
	}
	
	// set input value
	function setInputValue(name, value){		
		
		$("#form_content input[name=" + name + "]").each(function(index){
			input = $(this);
			if(input.attr("type")=="radio"){
				if(input.attr("value")==value){
					input.attr("checked", "checked");
				};
			} else {
				input.val(value);
			}
		});
		
		$("#form_content select[name="+ name + "]").each(function(index){
			select = $(this);			
			$("option", select).each(function(index){
				option = $(this);				
				if(option.attr("value")==value){
					option.attr("selected", "selected");
				}
			});
		});
		
		$("#form_content textarea[name="+ name + "]").each(function(index){
			textarea = $(this);			
			$("textarea").html(value);
		});
	}
	
	// get the context path
	function getContextPath() {
		pn = location.pathname;
		len = pn.indexOf("/", 1);
		cp = pn.substring(0, len);
		return cp;
	}
	
	

	// Validate
	function isValidated(){
		validated = true;
		
		
		$("#form_content div").each(function(index){
			divs = $(this);
			//alert("came here " + input.attr('type') + " " + input.attr('class'));
			if(divs.attr('class')=='requirediv'){	
				//alert("got something ");
				var node = document.getElementById("sum");
				node.parentNode.removeChild(node);
			}
		});
		
		$("#form_content input").each(function(index){
			input = $(this);
			//alert("came here " + input.attr('type') + " " + input.attr('class'));
			if(input.attr('class')=='require'){	
				if(input.attr('type')=='text'){
					//alert("came here " + input.val().length);
					
					if(input.val().length<=0){
						validated = false;
						$('This is a required field').insertAfter(input);
						//alert("came here again " + input.val().length);
						input.after('<div id="sum" class="requirediv"><span style="background-color:#f8c100;">This is a required field<\span><\div>');
					}
				
				}
			}
			
			
			// make changes for radio buttons
			if(input.attr('type')=='radio'){
				if(input.attr('class')=='require'){
					if($("#form_content input[name="+ input.attr('name') + "]:checked").length<=0){
						validated = false;
						$('<div id="sum" class="requirediv"><span style="background-color:#f8c100;">Please select one of these<\span><\div>').insertBefore(input);
					}
				}
			}
			
			
		});
		
		
		
		$("#form_content select").each(function(index){
			select = $(this);
				$("option:selected", select).each(function(){
					option = $(this);
					if(option.attr('value')==''){
						if(option.attr('class')=='require'){
							validated = false;
							$('<div id="sum" class="requirediv"><span style="background-color:#f8c100;">This is a required select field<\span><\div>').insertAfter(select);
						}						
					}
			});
		});
		
		return validated;
	}


	
	// Submit the form
	function submitForm(){
		validated = isValidated();
		//$("#contentForm").valid();
		if(validated){
			var formContent = $("#contentForm").formSerialize();
			$.post(getContextPath() + "/module/bloodbank/showForm.form", formContent, function(data) {
				${param.script}
			});
			 tb_remove();
		}
	}
	
	
	
	// Fill data into all inputs
	function fillData(){
		<c:if test="${not empty inputNames}">
			<c:forEach var="i" begin="0" end="${inputLength-1}" step="1">
				setInputValue("${inputNames[i]}", "${inputValues[i]}");
			</c:forEach>
		</c:if>		
	}	
	
</script>
<form id="contentForm" method="post" action="showForm.form">
	<input type="hidden" name="encounterId" value="${encounterId}"/>	
	<div id="formPrintArea">		
		<div id="form_content">	
			${contents}
		</div>
	</div>
	
</form>

<c:if test="${mode eq 'edit'}">	 
	<input type="button" value="OK" onClick="submitForm();"/>
	<input type="button" value="Cancel" onClick="tb_remove();"/>
</c:if>

<c:if test="${mode eq 'view'}">	 
	<input type="button" value="Back" onClick="tb_remove();"/>
</c:if>