package org.openmrs.module.bloodbank.web.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptWord;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
/*
import org.openmrs.module.hospitalcore.util.PatientUtil;
import org.openmrs.module.radiology.RadiologyService;
import org.openmrs.module.radiology.model.RadiologyForm;
import org.openmrs.module.radiology.model.RadiologyTest;
import org.openmrs.module.radiology.web.util.RadiologyUtil;
*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("BloodbankAjaxController")
public class AjaxController {
//
//	/**
//	 * Accept a test
//	 * 
//	 * @param orderId
//	 * @param model
//	 * @return id of accepted radiology test
//	 */
//	@RequestMapping(value = "/module/radiology/ajax/acceptTest.htm", method = RequestMethod.GET)
//	public String acceptTest(@RequestParam("orderId") Integer orderId,
//			@RequestParam("date") String dateStr, Model model) {
//		Order order = Context.getOrderService().getOrder(orderId);
//		if (order != null) {
//			try {
//				RadiologyService rs = (RadiologyService) Context
//						.getService(RadiologyService.class);
//				Integer acceptedTestId = rs.acceptTest(order);
//				model.addAttribute("acceptedTestId", acceptedTestId);
//			} catch (Exception e) {
//				model.addAttribute("acceptedTestId", "0");
//			}
//		}
//		return "/module/radiology/ajax/acceptTest";
//	}
//
//	/**
//	 * Unaccept a test
//	 * 
//	 * @param testId
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/module/radiology/ajax/unacceptTest.htm", method = RequestMethod.GET)
//	public String unacceptTest(@RequestParam("testId") Integer testId,
//			Model model) {
//		RadiologyService rs = (RadiologyService) Context
//				.getService(RadiologyService.class);
//		RadiologyTest test = rs.getRadiologyTestById(testId);
//		String unacceptStatus = rs.unacceptTest(test);
//		model.addAttribute("unacceptStatus", unacceptStatus);
//		return "/module/radiology/ajax/unacceptTest";
//	}
//
//	/**
//	 * Complete a test
//	 * 
//	 * @param testId
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/module/radiology/ajax/completeTest.htm", method = RequestMethod.GET)
//	public String completeTest(@RequestParam("testId") Integer testId,
//			Model model) {
//		RadiologyService rs = (RadiologyService) Context
//				.getService(RadiologyService.class);
//		RadiologyTest test = rs.getRadiologyTestById(testId);
//		String completeStatus = rs.completeTest(test);
//		model.addAttribute("completeStatus", completeStatus);
//		return "/module/radiology/ajax/completeTest";
//	}

	/**
	 * Concept search autocomplete for form
	 * 
	 * @param name
	 * @param model
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/module/bloodbank/ajax/autocompleteConceptSearch.htm", method = RequestMethod.GET)
	public String autocompleteConceptSearch(
			@RequestParam(value = "q", required = false) String name,
			Model model) {
		List<ConceptWord> cws = Context.getConceptService().findConcepts(name,
				new Locale("en"), false);
		Set<String> conceptNames = new HashSet<String>();
		for (ConceptWord word : cws) {
			String conceptName = word.getConcept().getName().getName();
			conceptNames.add(conceptName);
		}
		List<String> concepts = new ArrayList<String>();
		concepts.addAll(conceptNames);
		Collections.sort(concepts, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		model.addAttribute("conceptNames", concepts);
		return "/module/bloodbank/ajax/autocompleteConceptSearch";
	}

//	/**
//	 * Show patient/test information when showing on patient report page
//	 * 
//	 * @param patientIdentifier
//	 * @param orderId
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/module/radiology/ajax/showTestInfo.htm", method = RequestMethod.GET)
//	public String showTestInfo(
//			@RequestParam("patientIdentifier") String patientIdentifier,
//			@RequestParam(value = "orderId", required = false) Integer orderId,
//			Model model) {
//		List<Patient> patients = Context.getPatientService().getPatients(
//				patientIdentifier);
//		if (!patients.isEmpty()) {
//			Patient patient = patients.get(0);
//			model.addAttribute("patient_identifier", patient
//					.getPatientIdentifier().getIdentifier());
//			model.addAttribute("patient_age", patient.getAge());
//			model.addAttribute("patient_gender", patient.getGender());
//			model.addAttribute("patient_name", PatientUtil.getFullName(patient));
//		}
//		if (orderId != null) {
//			Order order = Context.getOrderService().getOrder(orderId);
//			if (order != null) {
//				model.addAttribute("test_orderDate",
//						RadiologyUtil.formatDate(order.getDateCreated()));
//				model.addAttribute("test_name", order.getConcept().getName()
//						.getName());
//			}
//		}
//		return "/module/radiology/ajax/showTestInfo";
//	}
//
//	@RequestMapping(value = "/module/radiology/ajax/checkExistingForm.htm", method = RequestMethod.GET)
//	public String checkExistingForm(
//			@RequestParam("conceptName") String conceptName,			
//			@RequestParam(value = "formId", required = false) Integer formId,
//			Model model) {
//		Concept concept = RadiologyUtil.searchConcept(conceptName);
//		boolean duplicatedFormFound = false;
//		if (concept != null) {
//			RadiologyService rs = (RadiologyService) Context
//					.getService(RadiologyService.class);
//			List<RadiologyForm> forms = rs.getRadiologyForms(conceptName);
//			if (!CollectionUtils.isEmpty(forms)) {
//				if (formId != null) {
//					RadiologyForm form = rs.getRadiologyFormById(formId);
//					if ((forms.size() == 1) && (forms.contains(form))) {
//
//					} else {
//						duplicatedFormFound = true;
//					}
//					if (forms.contains(form)) {
//						forms.remove(form);
//					}
//				} else {					
//					duplicatedFormFound = true;
//				}
//
//			}
//			model.addAttribute("duplicatedFormFound", duplicatedFormFound);
//			model.addAttribute("duplicatedForms", forms);
//		}
//		return "/module/radiology/ajax/checkExistingForm";
//	}
//	
//	@RequestMapping(value = "/module/radiology/ajax/validateRescheduleDate.htm", method = RequestMethod.GET)	
//	public void validateRescheduleDate(@RequestParam("rescheduleDate") String rescheduleDateStr, HttpServletResponse response) throws IOException, ParseException{
//		
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer = response.getWriter();
//		
//		Date rescheduleDate = RadiologyUtil.parseDate(rescheduleDateStr + " 00:00:00");
//		Date now = new Date();
//		String currentDateStr = RadiologyUtil.formatDate(now) + " 00:00:00";
//		Date currentDate = RadiologyUtil.parseDate(currentDateStr);
//		if(rescheduleDate.after(currentDate))
//			writer.print("success");
//		else
//			writer.print("fail");
//	}
}
