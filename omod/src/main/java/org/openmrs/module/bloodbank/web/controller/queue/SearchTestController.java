package org.openmrs.module.bloodbank.web.controller.queue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.web.controller.DonorEncountersController;
import org.openmrs.module.bloodbank.web.util.QueuePatient;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodBankSearchTestController")
@RequestMapping("/module/bloodbank/searchTest.form")
public class SearchTestController {

	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String searchTest(
			@RequestParam(value = "date", required = false) String dateStr,
			@RequestParam(value = "phrase", required = false) String phrase,			
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			HttpServletRequest request, Model model) {
		BloodBankService bbs = (BloodBankService) Context.getService(BloodBankService.class);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			System.out.println(date);
			List<Order> queueOrders = bbs.getOrders(date,phrase);
			System.out.println("Orders created");
			List<QueuePatient> tests = QueuePatient.ListQueuePatient(queueOrders);
			System.out.println("Tests created");
//			Map<Concept, Set<Concept>> testTreeMap = (Map<Concept, Set<Concept>>) request
//					.getSession().getAttribute(
//							RadiologyConstants.SESSION_TEST_TREE_MAP);
//			Set<Concept> allowableTests = new HashSet<Concept>();
//			if (currentPage == null)
//				currentPage = 1;
//			List<Order> orders = bbs.getOrders(date, phrase, currentPage);
//			List<TestModel> tests = BloodbankUtil.generateModelsFromOrders(orders, testTreeMap);
//			int total = bbs.countOrders(date, phrase);
//			PagingUtil pagingUtil = new PagingUtil(GlobalPropertyUtil.getInteger(RadiologyConstants.PROPERTY_PAGESIZE, 20), currentPage,total);
//			model.addAttribute("pagingUtil", pagingUtil);
			model.addAttribute("tests", tests);
//			model.addAttribute("testNo", tests.size());
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error when parsing order date!");
			return null;
		}		

		return "/module/bloodbank/queue/search";
	}
	
	
}
