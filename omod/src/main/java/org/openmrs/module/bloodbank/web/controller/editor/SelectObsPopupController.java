package org.openmrs.module.bloodbank.web.controller.editor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankSelectObsPopupController")
@RequestMapping("/module/bloodbank/selectObsPopup.form")
public class SelectObsPopupController {

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(@RequestParam("type") String type, Model model) {
		model.addAttribute("type", type);
		return "/module/bloodbank/editor/selectObsPopup";
	}
}