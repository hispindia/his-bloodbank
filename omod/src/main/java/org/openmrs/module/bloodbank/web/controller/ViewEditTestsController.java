package org.openmrs.module.bloodbank.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/bloodbank/viewEditTests.form")
public class ViewEditTestsController{

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(ModelMap model){
		return "/module/bloodbank/viewEditTests";
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String initList(ModelMap model) {

		BloodBankService service = Context.getService(BloodBankService.class);
		Map<Patient, PatientIdentifier> donorId = new HashMap<Patient, PatientIdentifier>();
		
		//Lists all bloodbank encounters with filled out questionaire but not filled out Tests and have not been issued
		List<BloodBank> records = service.getTestableRecords();
		List<BloodBank> mapRecords = service.getTestableRecords();
		
		String donorIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.donorIdTypeId");
		
		for(BloodBank bloodBank : records){
			donorId.put(bloodBank.getPatient(), bloodBank.getPatient().getPatientIdentifier(Integer.valueOf(donorIdientifier)));
			
			if((bloodBank.getTest()!=null
				&& bloodBank.getTest().getObs()!=null ? bloodBank.getTest().getObs().size() : 0)>=
				Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.test.valid.count"))
			){
				
				//Checking the results for the blood tests
                Set<Obs> testObs = bloodBank.getTest().getObs();
                boolean hasAids = false;
                String voidReason = "";
				for (Obs obs : testObs) {
	                if(obs.getConcept().getDatatype().isBoolean() ?  obs.getValueAsBoolean() : false == true){
	                	hasAids = true;
	                	voidReason += obs.getConcept().getName();
	                	break;
	                }	
                }
				
				if(hasAids){
					bloodBank.setTestComplete(true);
					bloodBank.setVoided(true);
					bloodBank.setVoidedBy(Context.getAuthenticatedUser());
					bloodBank.setVoidReason(voidReason);
					service.saveBloodBank(bloodBank);
					mapRecords.remove(bloodBank);
					//The whole set of encounters should probably be voided
				}else{
					//Adds a new issue encounter to the set
					Encounter enc = new Encounter();
					if(bloodBank.getBloodResult()!=null){
						enc = bloodBank.getBloodResult();
					}
						
					enc.setCreator(Context.getAuthenticatedUser());
					enc.setDateCreated(new Date());
					Location loc = Context.getLocationService().getLocation(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.location.id")));
					enc.setLocation(loc);
					enc.setPatient(bloodBank.getPatient());
					enc.setPatientId(bloodBank.getPatient().getPatientId());
					EncounterType encounterType =  Context.getEncounterService().getEncounterType(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.result.enctype.id")));
					enc.setEncounterType(encounterType);
					Form form = Context.getFormService().getForm(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.result.formId")));
					enc.setVoided(false);
					enc.setProvider(Context.getAuthenticatedUser().getPerson());
					enc.setUuid(UUID.randomUUID().toString());
					enc.setForm(form);
					enc.setEncounterDatetime(new Date());
					Context.getEncounterService().saveEncounter(enc);
					bloodBank.setBloodResult(enc);
					// Set correct blood type in bb db
					Encounter encounter = bloodBank.getTest();
					List<Encounter> encList = new ArrayList<Encounter>();
					encList.add(encounter);
					
					Concept concept = Context.getConceptService().getConcept(Integer.valueOf(
						Context.getAdministrationService().getGlobalProperty("bloodbank.blood.group.concept")));
					List<Concept> conList = new ArrayList<Concept>();
					conList.add(concept);
					
					List<Obs> obs = Context.getObsService().getObservations(null, encList, conList, null, null, null, null, null, null, null, null, false);
					
					Concept answer = obs.get(0).getValueCoded();
					
					bloodBank.setBloodGroup(answer);
					
					//Set date of storage
					bloodBank.setStorageDate(new Date());
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.expiry.period")));
					bloodBank.setExpiryDate(calendar.getTime());
					
					bloodBank.setTestComplete(true);
					
					//records.remove(records.indexOf(bloodBank));
					service.saveBloodBank(bloodBank);
					//Updates recordlist sent to the view:
					mapRecords.remove(bloodBank);
				}
			}
		}
		
		model.addAttribute("records", mapRecords);
		model.addAttribute("donorId", donorId);
		return "/module/bloodbank/viewEditTests";
	}
}