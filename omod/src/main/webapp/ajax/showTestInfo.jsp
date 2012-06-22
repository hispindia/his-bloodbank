<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<table border='0'>
	<tr>
		<td style="text-align:right;">ID. No:</td>
		<td><b>${patient_identifier}</b></td>
		<td style="text-align:right;">Age:</td>
		<td><b>${patient_age}</b></td>
	</tr>
	<tr>
		<td style="text-align:right;">Gender:</td>
		<td><b><c:choose>
				<c:when test="${patient_gender eq 'M'}">Male</c:when>
				<c:otherwise>Female</c:otherwise>
			</c:choose>
			</b>
		</td>
		<td style="text-align:right;">Name:</td>
		<td><b>${patient_name}</b></td>
	</tr>
	<c:if test='${not empty test_orderDate}'>
	<tr>								
		<td style="text-align:right;">Order date:</td>
		<td><b>${test_orderDate}</b></td>
		<td style="text-align:right;">Test name:</td>
		<td><b>${test_name}</b></td>
	</tr>
	</c:if>
</table>