<!-- Header & Include -->
<%@ include file="mainMenu.jsp"%>
<%@ include file="includes/js_css.jsp" %>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/bloodbank/scripts/jquery/css/start/jquery-ui-1.8.2.custom.css" />

<script type="text/javascript">
	function showPopup( id , encounterId , mode )
	{
		if( mode == 'view' ){
			tb_show( "testing", "showFormWithDetails.form?modal=true&height=600&width=800&id=" + id + "&encounterId=" + encounterId + "&mode=" + mode );
		}
		else{
			tb_show( "testing", "showForm.form?script=refresh();&modal=true&height=600&width=800&id=" + id + "&encounterId=" + encounterId + "&mode=" + mode );
		}
		
	}
	
	function refresh(){
		window.location.href = window.location.href;
	}
		
</script>
<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

<openmrs:globalProperty key="bloodbank.question.valid.count" defaultValue="0" var="qcount"/>
<openmrs:globalProperty key="bloodbank.test.valid.count" defaultValue="0" var="tcount"/>
<openmrs:globalProperty key="bloodbank.result.valid.count" defaultValue="0" var="rcount"/>


<p><a href="printTests.form" target="none"><img src="${pageContext.request.contextPath}/images/printer.gif" title="print worklist" align="top" border="0">Print worklist</a></p>

<div id="printarea">
<div class="boxHeader">Test requests:</div>
<div class="box">
<table class="testlist">
	<thead><tr>
		<th class="testlist">#</th>
		<th class="testlist"><spring:message code="Person.name"/></th>
		<th class="testlist">Questionnaire</th>
		<th class="testlist">Edit</th>
		<!-- <th class="testlist">Donor ID</th> -->
		<th class="testlist">Patient ID</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${records}" var="rec">
		<c:set var="count" value="${count+1}"/>
		<tr class="<c:choose><c:when test="${count % 2 == 0}">evenRow</c:when><c:otherwise>oddRow</c:otherwise></c:choose>">
			<td class="testlist">${rec.bloodBankId}</td>
			<td class="testlist">${rec.patient.personName}</td>
			<td class="testlist">
				<!-- <a href="javascript:void(0)" onclick="loadUrlIntoEncounterPopup('${rec.questionnaire.encounterType.name} | ${rec.questionnaire.location.name} | <openmrs:formatDate date='${rec.questionnaire.encounterDatetime}'/> | ${rec.patient.personName}', '${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${rec.questionnaire.encounterId}&mode=VIEW&returnUrl=${pageContext.request.contextPath}/module/bloodbank/viewStock.form&amp;inPopup=true'); return false;"> -->
				<a href="javascript: showPopup( 2 , ${rec.questionnaire.encounterId} , 'view' );">
					<img src="${pageContext.request.contextPath}/images/file.gif" alt="view">view
				</a>
			</td>
			<td class="testlist" align="center">
			<c:if test="${ rec.test != null }">
				<openmrs:hasPrivilege privilege="Edit Encounters">
				<!-- <a href="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${rec.test.encounterId}&mode=EDIT&returnUrl=${pageContext.request.contextPath}/module/bloodbank/viewEditTests.form">-->
					<a href="javascript: showPopup( 3 , ${rec.test.encounterId} , 'edit' );">
						<img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit" align="top" border="0"> Add test data
					</a>
				</openmrs:hasPrivilege>
				</c:if>
			</td>
			<td class="testlist" align="center">
				<openmrs:hasPrivilege privilege="Edit Encounters">
					<a href="${pageContext.request.contextPath}/module/bloodbank/showDonorEncounters.form?patientId=${rec.patient.patientId}">
						${rec.patient.patientIdentifier}
					</a>
				</openmrs:hasPrivilege>
			</td>
			<!-- <td class="testlist" align="center">
				<openmrs:hasPrivilege privilege="Edit Encounters">
					${donorId[rec.patient]}
				</openmrs:hasPrivilege>
			</td> -->
		</tr>
		</c:forEach>
</table>
</div>
</div>

<div id="displayEncounterPopup">
	<iframe id="displayEncounterPopupIframe" width="100%" height="100%" marginWidth="0" marginHeight="0" frameBorder="0" scrolling="auto"></iframe>
</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>

<script type="text/javascript">
function loadUrlIntoEncounterPopup(title, urlToLoad) {
        jQuery("#displayEncounterPopupIframe").attr("src", urlToLoad);
        jQuery('#displayEncounterPopup')
            .dialog('option', 'title', title)
            .dialog('option', 'height', jQuery(window).height() - 50) 
            .dialog('open');
}

jQuery(document).ready(function() {
	jQuery('#displayEncounterPopup').dialog({
					title: 'dynamic',
					autoOpen: false,
					draggable: true,
					resizable: false,
					width: '60%',
					height: '70%',
					modal: true,
					open: function(a, b) {}
			});
	
});
</script>