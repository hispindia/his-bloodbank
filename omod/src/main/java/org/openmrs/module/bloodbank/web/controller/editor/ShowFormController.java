/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of Bloodbank module.
 *
 *  Bloodbank module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Bloodbank module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Bloodbank module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.bloodbank.web.controller.editor;

import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodbankForm;
//import org.openmrs.module.bloodbank.model.BloodbankTest;
import org.openmrs.module.bloodbank.web.util.BloodbankUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankShowFormController")
@RequestMapping("/module/bloodbank/showForm.form")
public class ShowFormController {

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
	public String showForm(
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
		/*
		if (radiologyTestId != null) {
			RadiologyTest test = rs.getRadiologyTestById(radiologyTestId);
			form = test.getForm();
			encounter = test.getEncounter();

			model.addAttribute("patientIdentifier", test.getPatient()
					.getPatientIdentifier().getIdentifier());
			model.addAttribute("orderId", test.getOrder().getOrderId());
		} else {
			if (id == 0) { // generate default form

				form = rs.getDefaultForm();
			} else { // get the existing form

				form = rs.getRadiologyFormById(id);
			}

			if (encounterId != null) {
				encounter = Context.getEncounterService().getEncounter(
						encounterId);
			}
		}
		*/
		form = bbs.getBloodbankFormById(id);
		if (encounterId != null)
			encounter = Context.getEncounterService().getEncounter(encounterId);
		
		model.addAttribute("form", form);
		model.addAttribute("mode", mode);
		if (encounter != null)
			model.addAttribute("encounterId", encounter.getEncounterId());
		
		BloodbankUtil.generateDataFromEncounter(model, encounter, form);
		model.addAttribute("contents", form.getContent());
		return "/module/bloodbank/editor/show";
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
