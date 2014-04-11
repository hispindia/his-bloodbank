 
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<%@ include file="../includes/nav.jsp" %>
<script type="text/javascript">
$(function() {
    $( "#tabs" ).tabs({
      beforeLoad: function( event, ui ) {
        ui.jqXHR.error(function() {
          ui.panel.html(
            "Couldn't load this tab." );
        });
      }
    });
  });
</script>
<div id="tabs">
  <ul>
    <li><a href="bloodStockSummary.form">Summary</a></li>
    <c:choose>
	<c:when test="${not empty bloodGroups}">
	
	<c:forEach items="${bloodGroups}" var="bloodGroup" varStatus="varStatus">
	<li><a href="bloodGroupStocks.form?bloodGroup=${bloodGroup.answerConcept.id}">${bloodGroup.answerConcept.name}</a></li>
	</c:forEach>	
	
	</c:when>
	</c:choose>
 
  </ul>
 
</div>
 
 
