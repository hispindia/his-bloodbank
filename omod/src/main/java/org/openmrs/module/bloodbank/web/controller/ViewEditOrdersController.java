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

package org.openmrs.module.bloodbank.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.api.OrderService;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class ViewEditOrdersController extends SimpleFormController{

	Log log = LogFactory.getLog(getClass());

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		log.debug("Entering formBackingObject");
		
		return new Object();
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request, Object obj, Errors err) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		BloodBankService service = Context.getService(BloodBankService.class);
		
		List<Concept> concepts = new ArrayList<Concept>();
		
		String conceptIds = Context.getAdministrationService().getGlobalProperty("bloodbank.order.concepts");
		
		String[] conceptIdArray = conceptIds.split(",");
		
		for (String id : conceptIdArray) {
			Concept concept = Context.getConceptService().getConcept(Integer.valueOf(id));
			concepts.add(concept);
		}
		
		List<OrderType> orderTypes = new ArrayList<OrderType>();
		
		orderTypes.add(Context.getOrderService().getOrderType(
				Integer.valueOf(Context.getAdministrationService().getGlobalProperty("billing.orderTypeId"))));
		
		Context.getOrderService();
		List<Order> orders = Context.getOrderService()
		.getOrders(Order.class, null, concepts, OrderService.ORDER_STATUS.CURRENT, null, null, orderTypes);
		
		List<BloodBank> records = service.getValidStockRecords();

		map.put("records", records);
		map.put("orders", orders);
		return map;
	}
}
