package org.openmrs.module.bloodbank.web.controller.editor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.BloodbankForm;
//import org.openmrs.module.bloodbank.model.BloodbankTest;
import org.openmrs.module.bloodbank.web.util.BloodbankUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankShowFormControllerWithDetails")
@RequestMapping("/module/bloodbank/showFormWithDetails.form")
public class ShowFormWithDetailsController {

	/**
	 * Show form value
	 * 
	 * @param id
	 * @param mode
	 * @param encounterId
	 * @param radiologyTestId
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showFormWithDetails(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "encounterId", required = false) Integer encounterId,
			@RequestParam(value = "conceptId", required = false) Integer conceptId,
			@RequestParam(value = "radiologyTestId", required = false) Integer radiologyTestId,
			@RequestParam(value = "type", required = false) String type,
			Model model) {
		BloodBankService bbs = (BloodBankService) Context.getService(BloodBankService.class);
		Encounter encounter = null;
		BloodbankForm form = null;
		System.out.println("Enterened New Class");
		
		form = bbs.getBloodbankFormById(id);
		if (encounterId != null){
			encounter = Context.getEncounterService().getEncounter(encounterId);
		}
		model.addAttribute("form", form);
		model.addAttribute("mode", mode);
		Patient patient = encounter.getPatient();
		Map<String, String> pdets = new HashMap<String, String>();
		java.util.List<PersonAttribute>  personattris = new java.util.ArrayList<PersonAttribute>();
		java.util.List<PatientIdentifier>  personidfier = new java.util.ArrayList<PatientIdentifier>();
		Set<PersonAddress> personaddress = patient.getAddresses();
		personattris = patient.getActiveAttributes();
		personidfier = patient.getActiveIdentifiers();
//		System.out.println("params"+personName+encDateTime+locationName);
		System.out.println("Enterened New Class 1");
//		pdets.put("Donor ID", personidfier.get(1).toString());
		System.out.println("Enterened New Class 2");
		pdets.put("Name", patient.getGivenName()+" "+patient.getMiddleName()+" "+patient.getFamilyName());
		String date = patient.getBirthdate().toString();
		date = date.substring(0,10);
		System.out.println("Entering New Class 3"+date);
		pdets.put("Date Of Birth", date);
		System.out.println("Enterened New Class 3");
		pdets.put("Age", patient.getAge().toString());
		System.out.println("Enterened New Class 4");
		pdets.put("Gender", patient.getGender());
		pdets.put("Father/Husband Name", personattris.get(0).toString());
//		pdets.put("Address for Communication", patient.getPersonAddress().getAddress1());
		System.out.println("Enterened New Class 5");
		for (Map.Entry<String, String> entry : pdets.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
		model.addAttribute("pdetsmap", pdets);
		if (encounter != null){
			model.addAttribute("encounterId", encounter.getEncounterId());
		}
		
		
		BloodbankUtil.generateDataFromEncounter(model, encounter, form);
		return "/module/bloodbank/editor/showDets";
	}

	/**
	 * Save form values into an existing encounter
	 * 
	 * @param request
	 * @param encounterId
	 *            Id of an existing encounter which should be filled necessary
	 *            information before such as patient, user...
	 * @param model
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveEncounter(HttpServletRequest request,
			@RequestParam("encounterId") Integer encounterId, Model model) {
		
		Map<String, String> parameters = buildParameterList(request);
		Encounter encounter = Context.getEncounterService().getEncounter(
				encounterId);
		if (encounter != null) {
			for (String key : parameters.keySet()) {
				Concept concept = BloodbankUtil.searchConcept(key);
				Obs obs = insertValue(encounter, concept, parameters.get(key));
				if (obs.getId() == null)
					encounter.addObs(obs);
			}
			Context.getEncounterService().saveEncounter(encounter);
			model.addAttribute("status", "success");
			return "/module/bloodbank/editor/enterForm";
		}
		model.addAttribute("status", "fail");
		return "/module/bloodbank/editor/enterForm";
	}
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> buildParameterList(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<String, String>();
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String parameterName = (String) e.nextElement();
			if (!parameterName.equalsIgnoreCase("id"))
				if (!parameterName.equalsIgnoreCase("mode"))
					if (!parameterName.equalsIgnoreCase("encounterId"))
						if (!parameterName.equalsIgnoreCase("redirectUrl"))
							parameters.put(parameterName,
									request.getParameter(parameterName));

		}
		return parameters;
	}
	
	@SuppressWarnings("deprecation")
	private Date ConverttoDate( String date )
	{
		if(date.contains("/")){
			String[] DATE = date.split("/");
			return new Date( Integer.parseInt(DATE[2])-1900 , Integer.parseInt(DATE[1])-1 , Integer.parseInt(DATE[0]) );
		}
		else{
			String[] DATE = date.split("-");
			return new Date( Integer.parseInt(DATE[0])-1900 , Integer.parseInt(DATE[1])-1 , Integer.parseInt(DATE[2]) );
		}
		
	}
	
	private Obs insertValue(Encounter encounter, Concept concept, String value) {

		Obs obs = getObs(encounter, concept);
		obs.setConcept(concept);
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) 
		{
			value = value.replace("\n", "\\n");
			obs.setValueText(value);
		}
		
		else if( concept.getDatatype().getName().equalsIgnoreCase("Date") )
		{
			obs.setValueDatetime(ConverttoDate(value));
		}
		
		else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric"))
		{
			obs.setValueNumeric(new Double(value));
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			Concept answerConcept = BloodbankUtil.searchConcept(value);
			obs.setValueCoded(answerConcept);
		}
		return obs;
	}
	
	private Obs getObs(Encounter encounter, Concept concept) {
		for (Obs obs : encounter.getAllObs()) {
			if (obs.getConcept().equals(concept))
				return obs;
		}
		return new Obs();
	}

}
