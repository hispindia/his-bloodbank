package org.openmrs.module.bloodbank.web.controller.patient;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/patient/findDonorByNameOrId.form")
public class FindDonorController{

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(ModelMap map, @RequestParam("donorName") String donorName){
		return "/module/bloodbank/patient/findDonorByNameOrId.form?donorName="+donorName;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap map){
		return "/module/bloodbank/patient/findDonor";
	}
	                            
	@ModelAttribute("html")
	public String populatePatientList(@RequestParam("donorName") String donorName){
		List<Patient> patientsList = Context.getPatientService().getPatients( donorName );

		String patientIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.patientIdTypeId");
		String donorIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.donorIdTypeId");
		
		Iterator<Patient> it = patientsList.iterator();
		
		String patientsHtml="";
		
		while(it.hasNext()){
			Patient patient = (Patient) it.next();
			
			PatientIdentifier pi = patient.getPatientIdentifier(Integer.valueOf(donorIdientifier));
			
			patientsHtml +="<tr class='patientData'><td>"+(pi==null ? patient.getPersonName() : patient.getPersonName()) +"</td><td>"+
			patient.getPatientIdentifier(Integer.valueOf(patientIdientifier))+"</td><td>"+
			(pi==null ? "Not a donor" : "<a href=\"showDonorEncounters.form?patientId=" + 
			patient.getPatientId()+"\">" +pi)+"</a></td><td>" +
			(pi!=null ? "N/A" : "<input  style='margin-top:12px;display: inline-table;' type='checkbox' name='useThisPat' id='useThisPat' onchange='confirmUse("+patient.getPatientId()+");' value='"+patient.getPatientId()+"' ><spring:message code='bloodbank.use.this'/></td></tr>");	
		}
		patientsHtml +="";
		 
		
		if(patientsList.size()==0){
			return "";
		}else{
			return patientsHtml;
		}

	}
}