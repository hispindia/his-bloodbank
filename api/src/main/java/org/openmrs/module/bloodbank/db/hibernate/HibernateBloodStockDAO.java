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
import org.openmrs.Concept;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;

/**
 * Hibernate implementation of services
 */
public class HibernateBloodStockDAO implements BloodStockDAO {
	
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

	public List<BloodStock> listAllBloodStocks() {
		// TODO Auto-generated method stub
		return null;
	}

	public BloodStock saveBloodstock(BloodStock bloodStock) {
		// TODO Auto-generated method stub
		return (BloodStock) sessionFactory.getCurrentSession().merge(bloodStock);
	}

	public Collection<BloodStock> getBloodStocksByBloodGroup(Concept bloodGroup) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStock.class);		
		criteria.add(Restrictions.eq("bloodGroupConcept", bloodGroup));
		criteria.add(Restrictions.ge("expiryDate", new Date()));
		criteria.add(Restrictions.eq("discarded", Boolean.FALSE));
		return criteria.list();
	}

	public Collection<BloodStock> getNonDiscardedExpiredBloodStocks() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStock.class);	
		criteria.add(Restrictions.lt("expiryDate", new Date()));
		criteria.add(Restrictions.eq("discarded", Boolean.FALSE));
		return criteria.list();
	}

	public BloodStock getBloodStockById(int bloodStockId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodStock.class);	
		criteria.add(Restrictions.eq("bloodStockId", bloodStockId));
		return (BloodStock) criteria.uniqueResult();
	}

	

	public void deleteBloodStocks(Collection<BloodStock> bloodStocks) {
		for(BloodStock bloodStock : bloodStocks){
			sessionFactory.getCurrentSession().delete(bloodStock);
		}
		
	}
	
	
}
