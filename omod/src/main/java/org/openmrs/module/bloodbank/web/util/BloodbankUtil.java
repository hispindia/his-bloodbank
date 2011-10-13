/**
 *  Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
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
			return obs.getValueNumeric().toString();
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			return obs.getValueCoded().getName().getName();
		} else if(concept.getDatatype().getName().equalsIgnoreCase("Date")){
			return obs.getValueDatetime().toString().substring(0,10);
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
