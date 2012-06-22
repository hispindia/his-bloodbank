<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
	<c:when test="${not empty test}">
		${test.id}
	</c:when>
	<c:otherwise>
		error
	</c:otherwise>
</c:choose>