<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:choose>
	<c:when test="${type eq 'textbox'}">
		<input type="text" name="${obsName}" class="${classic}" value="" title="${obsName}" />
	</c:when>
	<c:when test="${type eq 'selection'}">
		<select name="${obsName}" title="${obsName}" class="${classic}">
			<option value='' class="${classic}">Please select</option>
			<c:forEach var="option" items="${options}">
				<option value="${option}">${option}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:when test="${type eq 'radio'}">
		<c:forEach var="option" items="${options}">
			<span><input type="radio" name="${obsName}"  class="${classic}" value="${option}" title="${obsName}">${option}</span>
		</c:forEach>
	</c:when>
	<c:when test="${type eq 'date'}">
		<input type="text" name="${obsName}"  class="${classic}" value="" title="DATE/ESTIMATED DATE" onFocus="showCalendar(this)"/>
	</c:when>
</c:choose>