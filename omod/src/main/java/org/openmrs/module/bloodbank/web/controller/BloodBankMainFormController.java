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


import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodStockReceiptService;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;
import org.openmrs.module.bloodbank.util.DateUtils;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/module/bloodbank/receiveBlood.form")
public class BloodBankMainFormController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String main(@RequestParam(value="description",required=false)  String description,
			@RequestParam(value="fromDate",required=false)  String fromDateStr,
			@RequestParam(value="toDate",required=false)  String toDateStr,
			Model model){
		BloodStockReceiptService bloodStockReceiptService = Context.getService(BloodStockReceiptService.class);
		Collection<BloodStockReceipt> bloodStockReceipts = null;
	System.out.println(description+"---"+fromDateStr);
	if (description==null){
		 bloodStockReceipts = bloodStockReceiptService.listAll();
	}else {
		Date fromDate =null;
		Date toDate = null;
		
		if (!fromDateStr.isEmpty()){
		fromDate = DateUtils.getDateFromStr(fromDateStr);
		}
		if (!toDateStr.isEmpty()){
		toDate = DateUtils.getDateFromStr(toDateStr);
		}
		
		bloodStockReceipts = bloodStockReceiptService.searchBloodStockReceipt(description,fromDate,toDate);
	}
		
		model.addAttribute("receipts", bloodStockReceipts);
		return "/module/bloodbank/receiveBlood";
	}
	
}
