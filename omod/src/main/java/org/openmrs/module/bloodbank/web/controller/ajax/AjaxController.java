package org.openmrs.module.bloodbank.web.controller.ajax;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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

@Controller("AjaxGlobalController")
public class AjaxController {
	@RequestMapping("/module/bloodbank/checkSession.form")
	public String checkSession(HttpServletRequest request,Model model) {
		 if( Context.getAuthenticatedUser() != null &&  Context.getAuthenticatedUser().getId() != null){
			 model.addAttribute("session","1");
		 }else{
			 model.addAttribute("session","0");
		 }
		
		return "/module/bloodbank/session/checkSession";
	}
	@RequestMapping(value="/module/bloodbank/addBloodStockReceiptDescription.form", method = RequestMethod.GET)
	public String addBloodStockReceiptDescriptionForm(@RequestParam(value="receiptId",required=false) String receiptId,Model model) {
		model.addAttribute("receiptId", receiptId);	
		return "/module/bloodbank/addBloodStockReceiptDescriptionForm";
	}
	
	@RequestMapping(value="/module/bloodbank/discard.form", method = RequestMethod.GET)
	public String discardBloodstock(@RequestParam(value="bloodStockId",required=false) String bloodStockId,Model model) {
		BloodStockService bloodStockService = Context.getService(BloodStockService.class);
		BloodStock bloodStock = bloodStockService.getBloodStockById(Integer.parseInt(bloodStockId));
		bloodStock.setDiscarded(true);
		bloodStock = bloodStockService.saveBloodStock(bloodStock);
				
		return "redirect:/module/bloodbank/viewExpiredBloodStockBalance.form";
	}
	
	@RequestMapping(value="/module/bloodbank/addBloodStockReceiptDescription.form", method = RequestMethod.POST)
	public String addBloodStockReceiptDescription(@RequestParam(value="receiptId",required=false) String receiptId,
			@RequestParam(value="description",required=false) String description,Model model) {
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=->>>"+receiptId);
		BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);;
		BloodStockReceipt bloodStockReceipt =bloodStockReceiptService.getBloodStockReceiptFromId(Integer.parseInt(receiptId));
		bloodStockReceipt.setDescription(description);
		bloodStockReceiptService.saveBloodStockreceipt(bloodStockReceipt);
		return "/module/bloodbank/thickbox/success";
	}
	
	@RequestMapping(value="/module/bloodbank/showDetailBloodStock.form",method=RequestMethod.GET)
	public String showDetailBloodStock(@RequestParam(value="receiptId",required=false)  Integer receiptId,
			Model model){
		
			BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);;
			BloodStockReceipt bloodStockReceipt =bloodStockReceiptService.getBloodStockReceiptFromId(receiptId);
				 System.out.println("================"+bloodStockReceipt);
		 Set<BloodStock> bloodStocks =  bloodStockReceipt.getBloodStocks();	
	
		model.addAttribute("bloodStocks", bloodStocks);
		model.addAttribute("receiptId", receiptId);
		return "/module/bloodbank/showBloodStockReceiptDetailsForm";
	}
}
