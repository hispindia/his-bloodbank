package org.openmrs.module.bloodbank.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openmrs.Concept;
import org.openmrs.ConceptWord;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.model.BloodbankForm;
import org.springframework.ui.Model;

public class BloodbankUtil 
{
	@SuppressWarnings("deprecation")
	public static Concept searchConcept(String name) {
		Concept concept = Context.getConceptService().getConcept(name);
		if (concept != null) {
			return concept;
		} else {
			List<ConceptWord> cws = Context.getConceptService().findConcepts(
					name, new Locale("en"), false);
			if (!cws.isEmpty())
				return cws.get(0).getConcept();
		}
		return null;
	}
	
	private static String getObsValue(Obs obs) {
		Concept concept = obs.getConcept();
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) {
			return obs.getValueText();
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric")) {
			if(obs.getValueDatetime() != null){
				return obs.getValueNumeric().toString();
			}
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			return obs.getValueCoded().getName().getName();
		} else if(concept.getDatatype().getName().equalsIgnoreCase("Date")){
			if(obs.getValueDatetime() != null){
				return obs.getValueDatetime().toString().substring(0,10);
			}
			return "";
		}
		return null;
	}
	
	/**
	 * generate data for form from an existing encounter
	 * 
	 * @param model
	 * @param encounter
	 */
	public static void generateDataFromEncounter(Model model,
			Encounter encounter, BloodbankForm form) {
		if (encounter != null) {
			List<String> inputNames = new ArrayList<String>();
			List<String> inputValues = new ArrayList<String>();
			for (Obs obs : encounter.getAllObs()) {
				inputNames.add(obs.getConcept().getName().getName());
				inputValues.add(getObsValue(obs));
			}
			model.addAttribute("inputNames", inputNames);
			model.addAttribute("inputValues", inputValues);
			model.addAttribute("inputLength", inputValues.size());
		}
	}
	
}
