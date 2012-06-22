package org.openmrs.module.bloodbank.web.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/showDonorEncounters.form")
public class DonorEncountersController {
	
	public static void newEncounter(int patientId){
		PatientService ps = Context.getPatientService();
		BloodBankService bbs = Context.getService(BloodBankService.class);
		Patient patient = ps.getPatient(patientId);
		
		Encounter enc = new Encounter();
		enc.setCreator(Context.getAuthenticatedUser());
		enc.setDateCreated(new Date());
        Location loc = Context.getLocationService().getLocation(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.location.id")));
        enc.setLocation(loc);
		enc.setPatient(patient);
		enc.setPatientId(patient.getPatientId());
		EncounterType encounterType =  Context.getEncounterService().getEncounterType(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.question.enctype.id")));
		enc.setEncounterType(encounterType);
		Form form = Context.getFormService().getForm(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.question.formId")));
		enc.setVoided(false);
		enc.setProvider(Context.getAuthenticatedUser().getPerson());
		enc.setUuid(UUID.randomUUID().toString());
		enc.setForm(form);
		enc.setEncounterDatetime(new Date());
		Context.getEncounterService().saveEncounter(enc);
		
		BloodBank bb = new BloodBank();
		bb.setQuestionnaire(enc);
		bb.setDateCreated(new Date());
		bb.setQuestionnaireProvider(Context.getAuthenticatedUser());
		bb.setPatient(patient);
		bb.setQuestionnaireComplete(false);
		bb.setTestComplete(false);
		bb.setBloodResultComplete(false);
		bb.setExpired(false);
		bb.setDisposed(false);
		bbs.saveBloodBank(bb);
	}
	
	public BloodBank newTest(BloodBank encounter){
		if (encounter.getTest() == null){
			Encounter test = new Encounter();
			test.setCreator(Context.getAuthenticatedUser());
			test.setDateCreated(new Date());
			test.setLocation(Context.getLocationService().getLocation(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.location.id"))));
			test.setPatient(encounter.getPatient());
			test.setPatientId(encounter.getPatient().getPatientId());
			test.setEncounterType(Context.getEncounterService().getEncounterType(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.test.enctype.id"))));
			test.setVoided(false);
			test.setProvider(Context.getAuthenticatedUser().getPerson());
			test.setUuid(UUID.randomUUID().toString());
			test.setForm(Context.getFormService().getForm(Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.test.formId"))));
			test.setEncounterDatetime(new Date());
			Context.getEncounterService().saveEncounter(test);
			encounter.setTest(test);
		}
		return encounter;
	}
	
	@ModelAttribute("patient")
	public Patient getPatient(@RequestParam("patientId") int patientId){
		PatientService ps = Context.getPatientService();
		return ps.getPatient(patientId);
	}
	
	@ModelAttribute("encounters")
	public List<BloodBank> getEncounters(@RequestParam("patientId") int patientId){
		BloodBankService bbs = Context.getService(BloodBankService.class);
		
		List<BloodBank> encounters = bbs.getRecordsByPatient(getPatient(patientId));
		for (BloodBank enc : encounters) {
			if((enc.getQuestionnaire() != null
    				&& enc.getQuestionnaire().getObs() != null ? enc.getQuestionnaire().getObs().size() : 0)>=
    				Integer.valueOf(Context.getAdministrationService().getGlobalProperty("bloodbank.question.valid.count"))
    			){
				enc.setQuestionnaireComplete(true);
				bbs.saveBloodBank(newTest(enc));
			}
        }
		
		return encounters;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewList(@RequestParam("patientId") int patientId, ModelMap model) {
		String donorIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.donorIdTypeId");
		PatientIdentifier pi = getPatient(patientId).getPatientIdentifier(Integer.valueOf(donorIdientifier));
		model.addAttribute("donorId", pi);
		
		return "/module/bloodbank/patient/showDonorEnc";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(ModelMap model, 
	                       @RequestParam("patientId") int patientId,
	                       @RequestParam("newEncounter") String newEncounter){
		if(newEncounter.equals("true")){
			newEncounter(patientId);
		}
		return "redirect:/module/bloodbank/showDonorEncounters.form?patientId=" + patientId;
	}
	
}
