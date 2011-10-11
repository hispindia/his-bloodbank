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

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.db.BloodbankDAO;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.openmrs.module.bloodbank.model.BloodbankForm;

/**
 * Hibernate implementation of services
 */
public class HibernateBloodBankDAO implements BloodbankDAO {
	
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

	public String getUniqueId() {
		return UUID.randomUUID().toString();
	}

	public void savePreparedId(PreparedDonorId pdi) {
		this.sessionFactory.getCurrentSession().save(pdi);
	}
	
	@SuppressWarnings("unchecked")
    public List<PreparedDonorId> getUnusedPreparedId(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PreparedDonorId.class);
		criteria.add(Expression.eq("used", false));
		return (List<PreparedDonorId>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<PreparedDonorId> getAllPreparedIds(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PreparedDonorId.class);
		return (List<PreparedDonorId>) criteria.list();
	}

	public PreparedDonorId getPrepDonorIdbyIdentifier(String identifier) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PreparedDonorId.class);
		criteria.add(Expression.eq("identifier", identifier));
		return (PreparedDonorId) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<BloodBank> getRecordsByPatient(Patient patient) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BloodBank.class);
		criteria.add(Expression.eq("patient", patient));
		criteria.addOrder(Order.desc("bloodBankId"));
		return (List<BloodBank>) criteria.list();
	}
	
	public BloodBank getRecordById(int id){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BloodBank.class);
		criteria.add(Expression.eq("bloodBankId",id));
		return (BloodBank) criteria.uniqueResult();
	}

	public void saveBloodBank(BloodBank bloodBank) {
		sessionFactory.getCurrentSession().saveOrUpdate(bloodBank);
	}

	@SuppressWarnings("unchecked")
	public List<BloodBank> getValidStockRecords() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BloodBank.class);
		criteria.add(Expression.eq("voided", false));
		criteria.add(Expression.eq("disposed", false));
		criteria.add(Expression.isNotNull("questionnaire"));
		criteria.add(Expression.isNotNull("test"));
		criteria.add(Expression.eq("bloodResultComplete",false));
		criteria.add(Expression.eq("testComplete",true));
		criteria.addOrder(Order.asc("bloodBankId"));
		return (List<BloodBank>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<BloodBank> getValidStockByTypeConcept(Concept con) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BloodBank.class);
		criteria.add(Expression.eq("voided", false));
		criteria.add(Expression.eq("disposed", false));
		criteria.add(Expression.isNotNull("questionnaire"));
		criteria.add(Expression.isNotNull("test"));
		//criteria.add(Expression.isNotNull("bloodResult"));
		criteria.add(Expression.eq("questionnaireComplete",true));
		criteria.add(Expression.eq("testComplete",true));
		criteria.add(Expression.eq("bloodResultComplete",false));
		criteria.add(Expression.eq("bloodGroup",con));
		criteria.addOrder(Order.desc("expiryDate"));
		return (List<BloodBank>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<BloodBank> getTestableRecords() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BloodBank.class);
		criteria.add(Expression.eq("voided", false));
		criteria.add(Expression.isNotNull("questionnaire"));
		criteria.add(Expression.eq("questionnaireComplete",true));
		criteria.add(Expression.eq("bloodResultComplete",false));
		criteria.add(Expression.eq("testComplete", false));
		
		criteria.addOrder(Order.desc("bloodBankId"));
		return (List<BloodBank>) criteria.list();
		}
	
	@SuppressWarnings("unchecked")
	public List<BloodbankForm> getBloodbankForms(String conceptName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodbankForm.class);
		criteria.add(Restrictions.eq("conceptName", conceptName));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<BloodbankForm> getAllBloodbankForms() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodbankForm.class);
		return criteria.list();
	}
	
	public BloodbankForm getBloodbankFormById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				BloodbankForm.class);
		criteria.add(Restrictions.eq("id", id));
		return (BloodbankForm) criteria.uniqueResult();
	}
	
	public BloodbankForm saveBloodbankForm(BloodbankForm form) {
		return (BloodbankForm) sessionFactory.getCurrentSession().merge(form);
	}
	
}
