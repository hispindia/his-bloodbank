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
