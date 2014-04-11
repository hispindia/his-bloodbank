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
package org.openmrs.module.bloodbank.db.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.db.BloodStockReceiptDAO;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;


/**
 * Hibernate implementation of services
 */
public class HibernateBloodStockReceiptDAO implements BloodStockReceiptDAO {
	
protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BloodStockReceipt saveBloodStockReceipt(BloodStockReceipt bloodStockReceipt) {
		// TODO Auto-generated method stub
		return (BloodStockReceipt) sessionFactory.getCurrentSession().merge(bloodStockReceipt);
	}

	public BloodStockReceipt getBloodStockReceiptFromId(int receiptId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStockReceipt.class);		
		criteria.add(Restrictions.eq("receiptId", receiptId));
	
		return (BloodStockReceipt) criteria.uniqueResult();
	}

	public Collection<BloodStockReceipt> listAll() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStockReceipt.class);		
		
		return (Collection<BloodStockReceipt>) criteria.list();
	}

	public Collection<BloodStockReceipt> searchBloodStockReceipt(
			String description, Date fromDate, Date toDate) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStockReceipt.class);		
		criteria.add(Restrictions.like("description", "%"+description+"%"));
		if (fromDate!=null){
			criteria.add(Restrictions.ge("createdOn", fromDate));
		}
		if (toDate!=null){
			criteria.add(Restrictions.le("createdOn", toDate));
		}
		return criteria.list();
	}


	
	
}
