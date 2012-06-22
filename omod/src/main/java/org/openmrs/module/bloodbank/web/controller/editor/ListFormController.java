package org.openmrs.module.bloodbank.web.controller.editor;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodbankForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("sdfsdgdsf")
@RequestMapping("/module/bloodbank/list.form")
public class ListFormController {
		
	@ModelAttribute("forms")
	public List<BloodbankForm> getAllForms(){
		BloodBankService bbs = (BloodBankService) Context.getService(BloodBankService.class);
		return bbs.getAllBloodbankForms();
	}
	

	@RequestMapping(method = RequestMethod.GET)
	public String listForms(
			Model model) {
		return "/module/bloodbank/editor/list";
	}
}
