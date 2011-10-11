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
