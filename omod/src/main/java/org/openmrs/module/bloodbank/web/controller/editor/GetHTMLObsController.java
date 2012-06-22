package org.openmrs.module.bloodbank.web.controller.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.module.bloodbank.web.util.BloodbankUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankGetHTMLObsController")
@RequestMapping("/module/bloodbank/getHTMLObs.form")
public class GetHTMLObsController {

	@RequestMapping(method = RequestMethod.GET)
	public String getHTMLObs(@RequestParam("name") String name,
			@RequestParam("type") String type,@RequestParam("required") String required, Model model) {
		if(required.equalsIgnoreCase("true")){
			model.addAttribute("classic", "require");
		}
		else if(required.equalsIgnoreCase("false")){
			model.addAttribute("classic", "optional");
		}
		Concept concept = BloodbankUtil.searchConcept(name);
		if (concept != null) {
			model.addAttribute("obsName", name);
			if ((concept.getDatatype().getName().equalsIgnoreCase("text"))
					|| (concept.getDatatype().getName().equalsIgnoreCase("numeric"))) 
			{
				model.addAttribute("type", "textbox");
			}
			
			else if ( concept.getDatatype().getName().equalsIgnoreCase("date") )
			{
				model.addAttribute("type", "date");
			}
			
			else if (concept.getDatatype().getName().equalsIgnoreCase("coded")) 
			{
				Set<String> options = new HashSet<String>();
				for (ConceptAnswer ca : concept.getAnswers()) 
				{
					Concept c = ca.getAnswerConcept();
					options.add(c.getName().getName());
				}

				for (ConceptSet cs : concept.getConceptSets()) 
				{
					Concept c = cs.getConcept();
					options.add(c.getName().getName());
				}
				
				model.addAttribute("options", getSortedOptionNames(options));
				if ( !type.equalsIgnoreCase("textbox") && !type.equalsIgnoreCase("date") ) 
				{
					model.addAttribute("type", type);
				} 
				else 
				{
					model.addAttribute("type", "selection");
				}
			}
		}

		return "/module/bloodbank/editor/getHTMLObs";
	}
	
	private List<String> getSortedOptionNames(Set<String> options){
		List<String> names = new ArrayList<String>();
		names.addAll(options);
		Collections.sort(names);		
		return names;
	}
}
