<%@ include file="mainMenu.jsp"%>
<%@ include file="includes/js_css.jsp" %>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/moduleResources/bloodbank/styles/common.css" />

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />
<form action="newIds.form" method="post" accept-charset="utf-8">
	<p>Specify number of ids you want:</p><input type="text" name="numberofids" value="" id="numberofids">
	<p><input type="submit" value="Continue &rarr;"></p>
</form>
<form>
<input type="button" value="Print" onClick="window.print()">
</form>
<table>
  <tr>
    <th>List of New Identifiers</th>
  </tr>

    <c:forEach items="${ids}" var="preid">
        <tr>
             <td>${preid}</td>
        </tr>

    </c:forEach>
</table>



<%@ include file="/WEB-INF/template/footer.jsp"%>