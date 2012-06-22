package org.openmrs.module.bloodbank.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.db.BloodbankDAO;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.openmrs.module.bloodbank.model.BloodbankForm;
import org.openmrs.module.hospitalcore.BillingConstants;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;


public class BloodbankServiceImpl implements BloodBankService{
	private final Log log = LogFactory.getLog(getClass());
	private BloodbankDAO dao;
	
	public void setDao(BloodbankDAO dao) {
		this.dao = dao;
	}
	
    public String getUniqueId() {
	    return getDao().getUniqueId();
    }

    public void savePreparedId(PreparedDonorId pdi) {
    	getDao().savePreparedId(pdi);
    }

    public PreparedDonorId getPrepDonorIdbyIdentifier(String identifier) {
		return getDao().getPrepDonorIdbyIdentifier(identifier);
    }
    
    public List<PreparedDonorId> getAllPreparedIds(){
    	return getDao().getAllPreparedIds();
    }

    public List<PreparedDonorId> getUnusedPreparedId() {
	    return getDao().getUnusedPreparedId();
    }

    public List<BloodBank> getRecordsByPatient(Patient patient) {
	    return getDao().getRecordsByPatient(patient);
    }

    public void saveBloodBank(BloodBank bloodBank) {
    	getDao().saveBloodBank(bloodBank);
    }

    public List<BloodBank> getValidStockRecords() {
    	return getDao().getValidStockRecords();
    }

    public List<BloodBank> getTestableRecords() {
	    return getDao().getTestableRecords();
    }

    public List<BloodBank> getValidStockByTypeConcept(Concept con) {
	    return getDao().getValidStockByTypeConcept(con);
    }

    public BloodBank getRecordById(int id) {
	   return getDao().getRecordById(id);
    }
    
	public BloodBank getRecordByTest(Encounter encounter){
		return getDao().getRecordByTest(encounter);
	}

    
	public BloodbankDAO getDao() {
		return dao;
	}
	
	public List<BloodbankForm> getAllBloodbankForms() {
		return dao.getAllBloodbankForms();
	}
	
	public List<BloodbankForm> getBloodbankForms(String conceptName) {
		return dao.getBloodbankForms(conceptName);
	}
	
	public BloodbankForm getBloodbankFormById(Integer id) {
		return dao.getBloodbankFormById(id);
	}
	
	public BloodbankForm saveBloodbankForm(BloodbankForm form) {
		return dao.saveBloodbankForm(form);
	}

	public BloodBank getRecordByResult(Encounter encounter) {
		return dao.getRecordByResult(encounter);
	}

	public List<Order> getOrders(Date date, String phrase) {
		System.out.println("Orders created");
		return dao.getOrders(date, phrase);

	}

	public boolean isPatientDonor(Integer PatientId) {
		System.out.println("Reached ServiceImpl");
		return dao.isPatientDonor(PatientId);
	}
}
