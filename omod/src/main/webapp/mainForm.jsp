<%@ include file="mainMenu.jsp"%>
<%@ include file="includes/js_css.jsp" %>


<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<ul>
  <!-- <li><a href="newIds.form">Pre-generated IDs</a> </li>
  <openmrs:hasPrivilege privilege="Manage Global Properties">-->
  <li> <a onclick="showProp()">Edit properties</a>
<div id="properties" style="display:none">
<openmrs:portlet url="globalProperties" id="globalPropertyEditSection" parameters="propertyPrefix=bloodbank.|excludePrefix=bloodbank.started|hidePrefix=true" />
</div>
<script type="text/javascript" charset="utf-8">
	function showProp(){
		document.getElementById("properties").style.display="block";
	}
</script> </li>
	</openmrs:hasPrivilege>
</ul>


<%@ include file="/WEB-INF/template/footer.jsp"%>