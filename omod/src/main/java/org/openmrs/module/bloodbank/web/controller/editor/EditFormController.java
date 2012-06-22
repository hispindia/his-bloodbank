package org.openmrs.module.bloodbank.web.controller.editor;

import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodbankForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankEditFormController")
@RequestMapping("/module/bloodbank/editForm.form")
public class EditFormController {

	@ModelAttribute("form")
	public BloodbankForm getForm(
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			BloodBankService bbs = (BloodBankService) Context
					.getService(BloodBankService.class);
			BloodbankForm form = bbs.getBloodbankFormById(id);
			if (form != null)
				return form;
		}
		return new BloodbankForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return "/module/bloodbank/editor/CKEditor";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("form") BloodbankForm form,
			Model model) {
		BloodBankService bbs = (BloodBankService) Context
				.getService(BloodBankService.class);
		bbs.saveBloodbankForm(form);
		return "redirect:/module/bloodbank/list.form";
	}
}
