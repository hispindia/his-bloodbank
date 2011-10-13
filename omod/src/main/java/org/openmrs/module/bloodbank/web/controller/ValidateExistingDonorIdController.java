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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/validateDonorId.form")
public class ValidateExistingDonorIdController{

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	
	@RequestMapping(method = RequestMethod.GET)
	public String init(ModelMap map, @RequestParam("donorPrepId") String id){
		BloodBankService service = (BloodBankService)Context.getService(BloodBankService.class);
		PreparedDonorId predDonorId = service.getPrepDonorIdbyIdentifier( id );
		
		if(predDonorId!=null){
			map.addAttribute("donorId", "yes");
		}else{
			map.addAttribute("donorId", "");
		}
		
		return "/module/bloodbank/validateDonorId";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(ModelMap map, @RequestParam("donorPrepId") String id){
		BloodBankService service = (BloodBankService)Context.getService(BloodBankService.class);
		PreparedDonorId predDonorId = service.getPrepDonorIdbyIdentifier( id );
		
		if(predDonorId!=null){
			map.addAttribute("donorId", "yes");
		}else{
			map.addAttribute("donorId", "");
		}
		
		return "redirect:/module/bloodbank/validateDonorId.form?donorPrepId="+id;
	}

    
}
