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
import org.openmrs.Patient;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.db.IssuedBloodStockDAO;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;
import org.openmrs.module.bloodbank.model.IssuedBloodStock;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate implementation of services
 */
public class HibernateIssuedBloodStockDAO implements IssuedBloodStockDAO {
	
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

	public List<IssuedBloodStock> listAllIssuedBloodStocks() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IssuedBloodStock.class);
		return criteria.list();
	}
	@Transactional
	public IssuedBloodStock saveIssuedBloodStock(
			IssuedBloodStock issuedBloodstock) {
		
		return (IssuedBloodStock) sessionFactory.getCurrentSession().save(issuedBloodstock);
	}

	public Collection<IssuedBloodStock> getBloodStockByPatient(Patient patient) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IssuedBloodStock.class);
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}

	
	
}
