/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.bloodbank.web.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodStockReceiptService;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.BloodbankConstants;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;
import org.openmrs.module.bloodbank.util.DateUtils;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/module/bloodbank/bloodStockReceipts.form")
public class BloodStockReceiptsController {
	
	private static final String CONCEPT_NAME_BLOOD_GROUPS = "BLOOD GROUPS";
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String main(@RequestParam(value="receiptId",required=false)  Integer receiptId,
			Model model){
		System.out.println("||||---->"+receiptId);
		if (receiptId ==null){
			receiptId = -1;
		}else{
			BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);;
			BloodStockReceipt bloodStockReceipt =bloodStockReceiptService.getBloodStockReceiptFromId(receiptId);
				 System.out.println("================"+bloodStockReceipt);
		 Set<BloodStock> bloodStocks =  bloodStockReceipt.getBloodStocks();	
	
		model.addAttribute("bloodStocks", bloodStocks);
		
		}	
		 Concept bloodGroupContainerConcept = Context.getConceptService().getConcept(BloodbankConstants.BLOODGROUPCONCEPTID);
		 Collection<ConceptAnswer> bloodGroups =  bloodGroupContainerConcept.getAnswers();
		model.addAttribute("bloodGroups", bloodGroups);
		model.addAttribute("receiptId", receiptId);
		return "/module/bloodbank/addBloodStockReceipt";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String addBloodStock(HttpServletRequest request,Model model){
		Integer bloodGroupConceptId = Integer.parseInt(request.getParameter("bloodGroup"));
			// get concept from id
			Concept bloodGroupConcept =Context.getConceptService().getConcept(bloodGroupConceptId);
		String product = request.getParameter("product");
		String dateOfReceipt = request.getParameter("dateOfReceipt");
		String dateOfExpiry = request.getParameter("dateOfExpiry");
		String donorName = request.getParameter("donorName");
		String packNo = request.getParameter("packNo");
		
		String description = request.getParameter("description");
		Integer receiptId = Integer.parseInt(request.getParameter("receiptId"));
		BloodStockReceipt bloodStockReceipt=null;
		BloodStockReceiptService bloodStockReceiptService = (BloodStockReceiptService) Context.getService(BloodStockReceiptService.class);
		
		
		if (receiptId == null || receiptId == -1){
			 bloodStockReceipt = new BloodStockReceipt();
				bloodStockReceipt.setCreatedBy(Context.getAuthenticatedUser());
				bloodStockReceipt.setCreatedOn(new Date());
			
				}else{
					bloodStockReceipt = bloodStockReceiptService.getBloodStockReceiptFromId(receiptId);

					}
	
		System.out.println(""+bloodGroupConceptId+","+product+","+ dateOfReceipt+","+dateOfExpiry+","+donorName+","+packNo+","+receiptId);
		
		BloodStockService bloodStockService = (BloodStockService) Context.getService(BloodStockService.class);
		BloodStock bloodStock = new BloodStock();
		bloodStock.setBloodGroupConcept(bloodGroupConcept);
		bloodStock.setPackNo(packNo);
		bloodStock.setProduct(product);
		bloodStock.setDonorName(donorName);
		bloodStock.setReceiptDate(DateUtils.getDateFromStr(dateOfReceipt));
		bloodStock.setExpiryDate(DateUtils.getDateFromStr(dateOfExpiry));
		bloodStock.setDiscarded(false);
		System.out.println(bloodStockReceipt);
		
			
		// saving bloodstock + getting the saved instance back
		bloodStock = bloodStockService.saveBloodStock(bloodStock);
		
		
		bloodStockReceipt.getBloodStocks().add(bloodStock);
		bloodStockReceipt.setDescription(description);
		System.out.println("bloodstockId="+bloodStock.getBloodStockId());
		
		// saving receipt + getting saved instance back
		bloodStockReceipt = bloodStockReceiptService.saveBloodStockreceipt(bloodStockReceipt);
		
		return "redirect:/module/bloodbank/bloodStockReceipts.form?receiptId="+bloodStockReceipt.getReceiptId();
	}
	}
