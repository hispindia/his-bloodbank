<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="../includes/js_css.jsp" %>


<link type="text/css" rel="stylesheet"	href="${pageContext.request.contextPath}/moduleResources/bloodbank/styles/common.css" />
<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

<div style="border-bottom: 1px solid black;">
    <ul id="menu">
        <li class="first">
            <a href="main.form" style="font-size:large; font-weight:bold; text-decoration:none;">Blood Bank System</a>
        </li>
        
        <openmrs:hasPrivilege privilege="Edit Encounters">
            <li <c:if test='<%= request.getRequestURI().contains("queue") %>'>class="active"</c:if>>
            <a href="queue.form">Queue</a>
            </li>
        </openmrs:hasPrivilege>

        <openmrs:hasPrivilege privilege="Add patients">
            <li <c:if test='<%= request.getRequestURI().contains("addOrUpdate") %>'>class="active"</c:if>>
            <a href="addOrUpdate.form">Find Donor</a>
            </li>
        </openmrs:hasPrivilege>

		<openmrs:hasPrivilege privilege="Edit Encounters">
            <li <c:if test='<%= request.getRequestURI().contains("viewEditTests") %>'>class="active"</c:if>>
            <a href="viewEditTests.form">Blood Test Lab</a>
            </li>
        </openmrs:hasPrivilege>
		
		<openmrs:hasPrivilege privilege="Edit Encounters">
            <li <c:if test='<%= request.getRequestURI().contains("viewStock") %>'>class="active"</c:if>>
            <a href="viewStock.form">View Stock</a>
            </li>
        </openmrs:hasPrivilege>
    </ul>
</div>