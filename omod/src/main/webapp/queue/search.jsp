<%@ include file="/WEB-INF/template/include.jsp" %>
<script type="text/javascript">
	testNo = ${testNo};
</script>
<table id="myTable" class="tablesorter">
	<thead>
		<tr> 
			<th>No</th>
			<th>Date</th>
			<th>Patient ID</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Age</th>
			<th>Accept</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="test" items="${tests}" varStatus="index">
			<c:choose>
				<c:when test="${index.count mod 2 == 0}">
					<c:set var="klass" value="odd"/>
				</c:when>					
				<c:otherwise>
					<c:set var="klass" value="even"/>
				</c:otherwise>
			</c:choose>
			<tr class="${klass}">
				<td>${index.count}</td>
				<td>
					${test.startDate}
				</td>
				<td>
					${test.patientIdentifier}
				</td>
				<td>
					${test.patientName}
				</td>
				<td>
					${test.gender}
				</td>
				<td>
					${test.age}
				</td>
				<td id="acceptBox_${test.orderId}">					
						<form method="post" accept-charset="utf-8">
						<input type="hidden" name="orderId" value="${test.orderId}" id="newEncounter">
						<input type="submit" value="Accept &rarr;">
						</form>
				</td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<div id='paging'>
	<a style="text-decoration:none" href='javascript:getTests(1);'>&laquo;&laquo;</a>
	<a style="text-decoration:none" href="javascript:getTests(${pagingUtil.prev});">&laquo;</a>		
	${pagingUtil.currentPage} / <b>${pagingUtil.numberOfPages}</b>	
	<a style="text-decoration:none" href="javascript:getTests(${pagingUtil.next});">&raquo;</a>
	<a style="text-decoration:none" href='javascript:getTests(${pagingUtil.numberOfPages});'>&raquo;&raquo;</a>
</div>