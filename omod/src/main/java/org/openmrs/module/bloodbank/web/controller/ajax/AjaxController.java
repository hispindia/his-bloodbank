package org.openmrs.module.bloodbank.web.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.PatientSearchCriteria;
import org.openmrs.module.bloodbank.BloodStockReceiptService;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.BloodbankConstants;
import org.openmrs.module.bloodbank.IssuedBloodStockService;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;
import org.openmrs.module.bloodbank.model.IssuedBloodStock;
import org.openmrs.reporting.PatientSearch;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;

@Controller("AjaxGlobalControllerBB")
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
		
		BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);;
		BloodStockReceipt bloodStockReceipt =bloodStockReceiptService.getBloodStockReceiptFromId(Integer.parseInt(receiptId));
		bloodStockReceipt.setDescription(description);
		bloodStockReceiptService.saveBloodStockreceipt(bloodStockReceipt);
		model.addAttribute("urlAjax", "/module/bloodbank/bloodStockReceipts.form");
		
		return "/module/bloodbank/thickbox/success";
	}
	
	@RequestMapping(value="/module/bloodbank/showDetailBloodStock.form",method=RequestMethod.GET)
	public String showDetailBloodStock(@RequestParam(value="receiptId",required=false)  Integer receiptId,
			Model model){
		
			BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);
			BloodStockReceipt bloodStockReceipt =bloodStockReceiptService.getBloodStockReceiptFromId(receiptId);
		
		 Set<BloodStock> bloodStocks =  bloodStockReceipt.getBloodStocks();	
	
		model.addAttribute("bloodStocks", bloodStocks);
		model.addAttribute("receiptId", receiptId);
		return "/module/bloodbank/showBloodStockReceiptDetailsForm";
	}
	
	
	@RequestMapping(value="/module/bloodbank/showPatientSearchForm.form",method=RequestMethod.GET)
	public String showPatientSearchForm(@RequestParam(value="nameOrId",required=false)  String nameOrId,
			Model model){
		PatientService patientService = (PatientService) Context.getService(PatientService.class);
		Collection<Patient> patients = patientService.getPatients(nameOrId);
		System.out.println("patientssize============="+patients.size());
		model.addAttribute("patients", patients);
		return "/module/bloodbank/showPatientSearchForm";
	}
	
	@RequestMapping(value="/module/bloodbank/issueBloodToPatient.form",method=RequestMethod.GET)
	public String issueBloodToPatient(@RequestParam(value="patientId",required=false)  String patientId,
			Model model){
		PatientService patientService = (PatientService) Context.getService(PatientService.class);
		Patient patient = patientService.getPatient(Integer.parseInt(patientId));
		Integer bloodGroupContainerConceptId = Integer.valueOf(Context.getAdministrationService().getGlobalProperty(
				BloodbankConstants.BLOODGROUPS_CONCEPT_ID));
			
		 Concept bloodGroupContainerConcept = Context.getConceptService().getConcept(bloodGroupContainerConceptId);
		 Collection<ConceptAnswer> bloodGroups =  bloodGroupContainerConcept.getAnswers();
		model.addAttribute("bloodGroups", bloodGroups);
	
		model.addAttribute("patient", patient);
		return "/module/bloodbank/issueBloodToPatient";
	}
	@RequestMapping(value="/module/bloodbank/issueBlood.form",method=RequestMethod.GET)
	public String issueBlood(@RequestParam(value="patientId",required=false)  String patientId,
			@RequestParam(value="bloodStockId",required=false)  String bloodStockId,
			Model model){
		
		
		model.addAttribute("bloodStockId", bloodStockId);
		model.addAttribute("patientId", patientId);
		return "/module/bloodbank/issueBloodForm";
	}
	@Transactional
	@RequestMapping(value="/module/bloodbank/issueBlood.form",method=RequestMethod.POST)
	public String issueBloodPost(@RequestParam(value="patientId",required=false)  String patientId,
								@RequestParam(value="bloodStockId",required=false)  String bloodStockId,
								HttpServletRequest request,Model model){
		
		String comment = request.getParameter("comment");
		String crossmatchingResult = request.getParameter("result");
		IssuedBloodStockService issueBloodStockService = (IssuedBloodStockService) Context.getService(IssuedBloodStockService.class);
		PatientService patientService = (PatientService) Context.getService(PatientService.class);
		BloodStockService bloodStockService =(BloodStockService)  Context.getService(BloodStockService.class);
		BloodStock bloodStock = bloodStockService.getBloodStockById(Integer.parseInt(bloodStockId));
		
		IssuedBloodStock issuedBloodStock = new IssuedBloodStock();
		
		issuedBloodStock.setBloodStock(bloodStock);
		issuedBloodStock.setPatient(patientService.getPatient(Integer.parseInt(patientId)));
		issuedBloodStock.setCreatedBy(Context.getAuthenticatedUser());
		issuedBloodStock.setComment(comment);
		issuedBloodStock.setCreatedOn(new Date());
		
		if (crossmatchingResult.equalsIgnoreCase("Positive"))
			issuedBloodStock.setCrossmatchingResult(true);
		else issuedBloodStock.setCrossmatchingResult(false);
		
		issueBloodStockService.saveIssuedBloodStock(issuedBloodStock);
		if (crossmatchingResult.equalsIgnoreCase("Positive")){
		bloodStock.setDiscarded(true);
		}
		model.addAttribute("urlAjax", "/module/bloodbank/viewIssueBloodToPatient.form");
		
		return "/module/bloodbank/thickbox/success";
	}
	@RequestMapping(value="/module/bloodbank/getBloodStocksByBloodGroup.form",method=RequestMethod.GET)
	public String getBloodStocksByBloodGroup(@RequestParam(value="bloodGroupId",required=false)  String bloodGroupId,
			@RequestParam(value="patientId",required=false)  String patientId,
			Model model){
		PatientService patientService = Context.getService(PatientService.class);
		BloodStockService bloodStockService = Context.getService(BloodStockService.class);
		IssuedBloodStockService issuedBloodStockService = Context.getService(IssuedBloodStockService.class);
		Collection<BloodStock> alreadyTestedBloodStockList = new ArrayList<BloodStock>();
		Patient patient = patientService.getPatient(Integer.parseInt(patientId));
		 Collection<IssuedBloodStock> alreadyTestedIssuedBloodStocks = issuedBloodStockService.getBloodStockByPatient(patient);
		 for (IssuedBloodStock issuedBloodStock : alreadyTestedIssuedBloodStocks){
			 alreadyTestedBloodStockList.add(issuedBloodStock.getBloodStock());
			}
			 
		Concept bloodGroup = Context.getConceptService().getConcept(bloodGroupId);			
		 Collection<BloodStock> bloodStocks =bloodStockService.getBloodStocksByBloodGroup(bloodGroup);
		 bloodStocks.removeAll(alreadyTestedBloodStockList);
		
		 System.out.println("already tested="+alreadyTestedIssuedBloodStocks.size());
		 Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, BloodbankConstants.SOON_EXPIRING_DAYS_LIMIT);
			model.addAttribute("today", new Date());
			model.addAttribute("todayPlusXDays", calendar.getTime());
		
		 model.addAttribute("bloodStocks", bloodStocks);
			model.addAttribute("patientId", patientId);
			
		 return "/module/bloodbank/availableBloodStockListAjax";
	}

	@RequestMapping(value="/module/bloodbank/clearBloodStockReceipt.form",method=RequestMethod.GET)
	public String clearBloodStockReceipt(@RequestParam(value="receiptId",required=false)  String receiptId,
			Model model){
		if (!receiptId.equalsIgnoreCase("-1")){
		BloodStockReceiptService bloodReceiptService = Context.getService(BloodStockReceiptService.class);
		BloodStockService bloodStockService = Context.getService(BloodStockService.class);
	BloodStockReceipt bloodStockReceipt = bloodReceiptService.getBloodStockReceiptFromId(Integer.parseInt(receiptId));
	
	Collection<BloodStock> bloodStocks = bloodStockReceipt.getBloodStocks();
	bloodStockService.deleteBloodStocks(bloodStocks);
	bloodReceiptService.deleteReceipt(bloodStockReceipt);
		}
		 return "redirect:/module/bloodbank/receiveBlood.form?";
	}
}
