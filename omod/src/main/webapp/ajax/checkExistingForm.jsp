<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:choose>
	<c:when test='${duplicatedFormFound}'>
		<script type='text/javascript'>
			DUPLICATED_FORM = true;
		</script>
		<span style='color:red;'>Duplicated form with the same concept was found!</span>
		<c:forEach var='form' items='${duplicatedForms}'>
			<a href='editForm.form?id=${form.id}'>
				${form.name}
			</a>
		</c:forEach>
	</c:when>
	<c:otherwise>
		Fine!
		<script type='text/javascript'>
			DUPLICATED_FORM = false;
		</script>
	</c:otherwise>
</c:choose>