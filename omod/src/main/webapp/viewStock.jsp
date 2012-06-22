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
		window.location.reload();
	}
</script>

<openmrs:require privilege="All Blood Bank" otherwise="/login.htm" redirect="/module/bloodbank/main.form" />

<openmrs:globalProperty key="bloodbank.question.valid.count" defaultValue="0" var="qcount"/>
<openmrs:globalProperty key="bloodbank.test.valid.count" defaultValue="0" var="tcount"/>
<openmrs:globalProperty key="bloodbank.result.valid.count" defaultValue="0" var="rcount"/>
<p></p>
<div class="boxHeader">Available Blood Stock</div>
<div class="box">
<div id="tabs">
	<ul id="flowtabs">
		<c:set var="count1" value="0"/>
		<li><a id="#${count1}" href="#${count1}">SUMMARY</a></li> 
		<c:forEach items="${bloodGroupsNames}" var="bgname">
			<c:set var="count1" value="${count1+1}"/> 
			<li><a id="#${count1}" href="#${count1}">${bgname}</a></li>
		</c:forEach>
	</ul>
	

<div class="panes">

<c:set var="count1" value="0"/>
<c:set var="acount" value="0"/>
<c:set var="bcount" value="0"/>
<c:set var="abcount" value="0"/>
<c:set var="ocount" value="0"/>
<c:set var="ancount" value="0"/>
<c:set var="bncount" value="0"/>
<c:set var="abncount" value="0"/>
<c:set var="oncount" value="0"/>
<c:set var="bloodCount">${acount}</c:set>



<c:set var="count1" value="${count1}"/>
	<div id="${count1}">
	<table cellspacing="5" cellpadding="6" id="bloodSummary" class="stock">
		<thead><tr>
			<th style="">ID#</th>
			<th align="center">Blood Group</th>
			<th align="center">Units Available</th>
			</tr>
		</thead>	
		
		<tbody>
		<c:set var="blcount" value="0"/>
		<c:forEach items="${bloodGroupsNames}" var="bld">
			<c:set var="blcount" value="${blcount+1}"/>
			<tr class="<c:choose><c:when test="${count % 2 == 0}">evenRow</c:when><c:otherwise>oddRow</c:otherwise></c:choose>">
			<c:set var='bloodUnits' value='${bloodGroups[bld]}'/>
			<th align="center">${blcount}</th>
			<th align="center">${bld}</th>
			<c:set var='unitCount' value="0"/>
			<c:forEach items="${bloodUnits}" var="unit">
					<c:choose>
					<c:when test="${unit.expired}">
					</c:when>
					<c:otherwise>
						<c:set var='unitCount' value="${unitCount+1}"/>
					</c:otherwise>
					</c:choose>
			</c:forEach>	
			<th align="center">${unitCount}</th>
			</tr>
		</c:forEach>
		</tbody>
		
		
	</table>
	</div>





<c:forEach items="${bloodGroupsNames}" var="name">
	<c:set var="count1" value="${count1+1}"/>
	<div id="${count1}">
	<table cellspacing="0" cellpadding="2" id="donorData" class="stock">
		<thead><tr>
			<th style="">ID#</th>
			<th class="encounterView" align="center">Issue blood</th>
			<th class="encounterDonorIdHeader" align="center">Donor Name</th>
			<th class="encounterTypeHeader">Questionnaire</th>
			<th class="encounterProviderHeader">Blood tests</th>
			<th class="encounterTypeHeader">Blood tag</th>
			<th class="encounterProviderHeader">Patient ID</th>
			<th class="encounterProviderHeader">Storage Date</th>
			<th class="encounterProviderHeader">Expiry Date</th>
		</tr>
		</thead>
		
		<tbody>
		
			<c:set var='bloodUnits' value='${bloodGroups[name]}'/> 
			<c:forEach items="${bloodUnits}" var="rec">
				<c:set var="count" value="${count+1}"/>
				<c:set var="expired" value="false"/>
				<c:choose>
					<c:when test="${rec.expired}">
						<tr style="background-color:#FCC">
					</c:when>
					<c:otherwise>
						<tr class="<c:choose><c:when test="${count % 2 == 0}">evenRow</c:when><c:otherwise>oddRow</c:otherwise></c:choose>">
					</c:otherwise>
				</c:choose>
					<td style="">${rec.bloodBankId}</td>
					<td class="encounterEdit">
						<c:if test="${ rec.bloodResult != null }">
						<openmrs:hasPrivilege privilege="Edit Encounters">
						<c:choose>
							<c:when test="${!rec.expired}">
								<!-- <a href="${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${rec.bloodResult.encounterId}&mode=EDIT&returnUrl=${pageContext.request.contextPath}/module/bloodbank/viewStock.form#${count1}">-->
								<a href="javascript: showPopup( 1 , ${rec.bloodResult.encounterId} , 'edit' );">
										<img src="${pageContext.request.contextPath}/images/edit.gif" title="Edit" align="top" border="0"> Issue
								</a>
							</c:when>
							<c:otherwise>
								<form action="viewStock.form#${count1}" id="disposeForm" method="post" accept-charset="utf-8">
									<input type="hidden" name="dispose" id="dispose" value="">
									<button type="submit" onclick="confirmDispose(${rec.bloodBankId})"><img src="${pageContext.request.contextPath}/images/trash.gif" title="Edit" align="top" border="0">Dispose</button>
								</form>
							</c:otherwise>
						</c:choose>
						</openmrs:hasPrivilege>
						</c:if>
					</td>
					<td class="encounterProvider">${rec.patient.personName}</td>
				
					<!-- view questionnaire -->
					<td class="encounterEdit">
						<openmrs:hasPrivilege privilege="Edit Encounters">
						<!-- <a href="javascript:void(0)" onclick="loadUrlIntoEncounterPopup('${rec.questionnaire.encounterType.name} | ${rec.questionnaire.location.name} | <openmrs:formatDate date='${rec.questionnaire.encounterDatetime}'/> | ${rec.patient.personName}', '${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${rec.questionnaire.encounterId}&mode=VIEW&returnUrl=${pageContext.request.contextPath}/module/bloodbank/viewStock.form&amp;inPopup=true'); return false;">-->
						<a href="javascript: showPopup( 2 , ${rec.questionnaire.encounterId} , 'view' );">
							<img src="${pageContext.request.contextPath}/images/file.gif" alt="view">view
						</a>
						</openmrs:hasPrivilege>
					</td>
				
					<!-- view blood tests -->
					<td class="encounterEdit">
					<c:if test="${ rec.test != null }">
						<openmrs:hasPrivilege privilege="Edit Encounters">
						<!-- <a href="javascript:void(0)" onclick="loadUrlIntoEncounterPopup('${rec.test.encounterType.name} | ${rec.test.location.name} | <openmrs:formatDate date='${rec.test.encounterDatetime}'/> | ${rec.patient.personName}', '${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?encounterId=${rec.test.encounterId}&mode=VIEW&returnUrl=${pageContext.request.contextPath}/module/bloodbank/viewStock.form&amp;inPopup=true'); return false;">-->
						<a href="javascript: showPopup( 3 , ${rec.test.encounterId} , 'view' );">
							<img src="${pageContext.request.contextPath}/images/file.gif" alt="view">view
						</a>
						</openmrs:hasPrivilege>
					</c:if>
					</td>
					<td class="encounterType">
						<a href="printBloodTag.form?bbId=${rec.bloodBankId}" target="none"><img src="${pageContext.request.contextPath}/images/printer.gif" title="print bloodtag" align="top" border="0"/>Print</a>
					</td>
					
					<td class="donorId">
						<openmrs:hasPrivilege privilege="Edit Encounters">
							<a href="${pageContext.request.contextPath}/module/bloodbank/showDonorEncounters.form?patientId=${rec.patient.patientId}">${donorId[rec.patient]}</a>
						</openmrs:hasPrivilege>
					</td>
					<td class="encounterProvider" align="center"><openmrs:formatDate date="${rec.storageDate}"/></td>
					<td class="encounterProvider" align="center"><openmrs:formatDate date="${rec.expiryDate}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</c:forEach>




</div>
</div>
</div>

<div id="displayEncounterPopup">
	<iframe id="displayEncounterPopupIframe" width="100%" height="100%" marginWidth="0" marginHeight="0" frameBorder="0" scrolling="auto"></iframe>
</div>



<script type="text/javascript">
function loadUrlIntoEncounterPopup(title, urlToLoad) {
        jQuery("#displayEncounterPopupIframe").attr("src", urlToLoad);
        jQuery('#displayEncounterPopup')
            .dialog('option', 'title', title)
            .dialog('option', 'height', jQuery(window).height() - 50) 
            .dialog('open');
}


jQuery(document).ready(function() {
	jQuery('#tabs ul li a').click(function () {location.hash = jQuery(this).attr('href');});
	jQuery('#tabs').tabs({
	    
	});
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

function confirmDispose(id){  
	if(confirm('Are you sure you want to dispose this blood unit?')){
		jQuery('#dispose').val(id);
		jQuery('#disposeForm').submit(); 
	}
}; 


</script>


<%@ include file="/WEB-INF/template/footer.jsp"%>