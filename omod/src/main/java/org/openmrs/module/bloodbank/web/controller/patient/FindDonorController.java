package org.openmrs.module.bloodbank.web.controller.patient;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
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
	public String populatePatientList(@RequestParam("donorName") String donorName ,
									  @RequestParam("Counter") Integer Counter){
		List<Patient> patientsList = Context.getPatientService().getPatients( donorName );

		String patientIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.patientIdTypeId");
//		String donorIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.donorIdTypeId");
		
		Iterator<Patient> it = patientsList.iterator();
		Integer Max = patientsList.size();
		System.out.println("=========Received From Ajax"+Counter.toString());
		if (Counter%10 != 0){
			Counter = (Counter/10);
			Counter = Counter * 10;
		}
		if( Counter == 0)
			Counter = 20;
		
		int Clower = Counter - 20;
		Clower = (Clower<0)? 0:Clower ;
		System.out.println("=========Received From Ajax and changed "+Counter.toString()+" ,"+Clower+", "+Max);

		String patientsHtml="";
		Integer i = new Integer(0);
		while(it.hasNext()){
			Patient patient = (Patient) it.next();
			if(i >= Clower &&  i < Counter){
				BloodBankService bbs = (BloodBankService) Context.getService(BloodBankService.class);
	//			System.out.println("Sending Patient Id:"+patient.getPatientId());
				boolean existing  = bbs.isPatientDonor( patient.getPatientId() );
				patientsHtml +="<tr class='patientData'><td>"+ patient.getPersonName() +" &nbsp</td><td>"+
				(existing ? "<a href=\"showDonorEncounters.form?patientId=" + patient.getPatientId()+"\">"+patient.getPatientIdentifier(Integer.valueOf(patientIdientifier))+"</a></td>" : "Not A Donor &nbsp </td>");
			}
			if(i > Counter){
				break;
			}
			i++;
			
//			check if the patient is a donor
//			redirect to show donor encounter page else show that he not a donor
			/*
			PatientIdentifier pi = patient.getPatientIdentifier(Integer.valueOf(donorIdientifier));
			
			patientsHtml +="<tr class='patientData'><td>"+(pi==null ? patient.getPersonName() : patient.getPersonName()) +"</td><td>"+
			patient.getPatientIdentifier(Integer.valueOf(patientIdientifier))+"</td><td>"+
			(pi==null ? "Not a donor" : "<a href=\"showDonorEncounters.form?patientId=" + 
			patient.getPatientId()+"\">" +pi)+"</a></td><td>" +
			(pi!=null ? "N/A" : "<input  style='margin-top:12px;display: inline-table;' type='checkbox' name='useThisPat' id='useThisPat' onchange='confirmUse("+patient.getPatientId()+");' value='"+patient.getPatientId()+"' ><spring:message code='bloodbank.use.this'/></td></tr>");	
			*/
		}
		Counter = i -1;
		patientsHtml +="";
		patientsHtml = Counter.toString()+"?"+ Max.toString() +"&"+ patientsHtml;
		System.out.println("HTML:"+patientsHtml);
		if(patientsList.size()==0){
			return "";
		}else{
			return patientsHtml;
		}

	}
}