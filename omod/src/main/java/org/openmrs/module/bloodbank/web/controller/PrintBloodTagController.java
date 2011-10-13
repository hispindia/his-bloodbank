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

package org.openmrs.module.bloodbank.web.controller;

import java.util.ArrayList;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/printBloodTag.form")
public class PrintBloodTagController {
	

	@RequestMapping(method = RequestMethod.GET)
	public String printBloodTag(ModelMap model, @RequestParam("bbId") int bbId){
		
		
		BloodBankService bbs = Context.getService(BloodBankService.class);
		String donorIdientifier = Context.getAdministrationService().getGlobalProperty("bloodbank.donorIdTypeId");
		BloodBank encounter = bbs.getRecordById(bbId);
		
		model.addAttribute("donorId", bbs.getRecordById(bbId).getPatient().getPatientIdentifier(Integer.valueOf(donorIdientifier)));
		model.addAttribute("bb", encounter);
		
		String bg = encounter.getBloodGroup().getName().toString();
		bg = bg.replace("NEGATIVE", "-");
		bg = bg.replace("POSITIVE", "+");
		model.addAttribute("bloodType", bg);
		
		ArrayList<String> tests =  new ArrayList<String>();
		for (Obs obs : encounter.getTest().getAllObs()) {
				if (obs.getConcept().getDatatype().isBoolean() && obs.getValueAsBoolean() == false){
		        String s = obs.getConcept().getName().toString();
		        tests.add(s);
			}
        }
		model.addAttribute("br", tests);
		
		return "/module/bloodbank/printBloodTag";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(){
		return "/module/bloodbank/printBloodTag";
	}

}
