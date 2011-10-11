<!-- Header & Include -->
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/js/jquery-1.4.2.min.js"></script>

<openmrs:require privilege="Add Donor" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

donor reg 

<div id="display" name="display"></div>
<h2>
	<spring:message code="bloodbank.add.donor" />
</h2>
<br/>
<form class="box" id="addDonorForm" method="post">
	<table >
		<tr>
			<td><spring:message code="bloodbank.donor.name"/></td>
			<td><input type="text" id="donorName" name="donorName" value=""/></td>
		</tr>
	</table>
</form>

<br/>
<span class="boxHeader">List of Donors</span>


<script>
jQuery(document).ready(function(){

	     $.ajaxSetup ({  
		         cache: false  
		     }); 
	
		jQuery("#donorName").focus();
});

     var ajax_load = "<img src='${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/css/images/ui-anim_basic_16x16.gif' alt='loading...' />";  
   
 //  load() functions  
     var loadUrl = "findDonorByNameOrId.form";  
//     $("#donorName").keyup(function(){  
//         if($("#donorName").val().length>=3)
//         $("#display").html(ajax_load).load(loadUrl, {donorName: $("#donorName").val()});  
//     }); 

     //  $.post()  
         $("#donorName").keyup(function(){
        	 if($("#donorName").val().length>=3){  
             $("#display").html(ajax_load);  
             $.post(  
                 loadUrl,  
                 {donorName: $("#donorName").val()},  
                 function(responseText){  
                     $("#display").html(responseText);  
                 },  
                 "html"  
             );  
        	 }
         });  
     
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>