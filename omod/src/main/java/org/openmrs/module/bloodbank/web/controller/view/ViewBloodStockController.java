package org.openmrs.module.bloodbank.web.controller.view;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodStockReceiptService;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.BloodbankConstants;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("viewBloodStockController")
public class ViewBloodStockController {

	@RequestMapping(value="/module/bloodbank/viewBloodStockBalance.form", method = RequestMethod.GET)
	public String addBloodStockReceiptDescriptionForm(Model model) {
		Integer bloodGroupContainerConceptId = Integer.valueOf(Context.getAdministrationService().getGlobalProperty(
				BloodbankConstants.BLOODGROUPS_CONCEPT_ID));
		
		 Concept bloodGroupContainerConcept = Context.getConceptService().getConcept(bloodGroupContainerConceptId);
			
		 Collection<ConceptAnswer> bloodGroups =  bloodGroupContainerConcept.getAnswers();
			
		 model.addAttribute("bloodGroups", bloodGroups);
		return "/module/bloodbank/view/viewBloodStockForm";
	}
	
	
	@RequestMapping(value = "/module/bloodbank/bloodStockSummary.form", method = RequestMethod.GET)
	public String viewBloodStockSummary(HttpServletRequest request,Model model) {
		Map<String,Integer> bloodGroupStockCount = new HashMap<String,Integer>();
		Integer bloodGroupContainerConceptId = Integer.valueOf(Context.getAdministrationService().getGlobalProperty(
				BloodbankConstants.BLOODGROUPS_CONCEPT_ID));
		
		 Concept bloodGroupContainerConcept = Context.getConceptService().getConcept(bloodGroupContainerConceptId);		
		 Collection<ConceptAnswer> bloodGroups =  bloodGroupContainerConcept.getAnswers();
			
		 BloodStockService bloodStockService = Context.getService(BloodStockService.class);
		 Collection<BloodStock> bloodStocks = null;
		for (ConceptAnswer bloodGroupAnswerConcept : bloodGroups){
		 
			bloodStocks = bloodStockService.getBloodStocksByBloodGroup(bloodGroupAnswerConcept.getAnswerConcept());
			if (bloodStocks!=null){
				bloodGroupStockCount.put(bloodGroupAnswerConcept.getAnswerConcept().getDisplayString(), bloodStocks.size());
			
			}
		}
		
		model.addAttribute("bloodGroupStockCount", bloodGroupStockCount);
		return "/module/bloodbank/view/viewBloodStockSummary";
	}
	
	@RequestMapping(value = "/module/bloodbank/bloodGroupStocks.form", method = RequestMethod.GET)
	public String viewBloodGroupStock(HttpServletRequest request,Model model) {
		Integer bloodGroupConceptId = Integer.parseInt(request.getParameter("bloodGroup"));
		// get concept from id
		Concept bloodGroupConcept =Context.getConceptService().getConcept(bloodGroupConceptId);

		 BloodStockService bloodStockService = Context.getService(BloodStockService.class);
		 Collection<BloodStock> bloodStocks = bloodStockService.getBloodStocksByBloodGroup(bloodGroupConcept);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		
		 model.addAttribute("bloodStocks", bloodStocks);
		model.addAttribute("today", new Date());
		model.addAttribute("todayPlus3Days", calendar.getTime());
		return "/module/bloodbank/view/viewBloodGroupStock";
	}
	@RequestMapping(value = "/module/bloodbank/viewExpiredBloodStockBalance.form", method = RequestMethod.GET)
	public String getExpiredBloodStock(HttpServletRequest request,Model model) {
		
		 BloodStockService bloodStockService = Context.getService(BloodStockService.class);
		 Collection<BloodStock> bloodStocks = bloodStockService.getNonDiscardedExpiredBloodStocks();
		model.addAttribute("bloodStocks", bloodStocks);
		return "/module/bloodbank/view/viewExpiredBloodStocks";
	}


}
