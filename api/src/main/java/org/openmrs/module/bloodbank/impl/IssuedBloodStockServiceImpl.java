package org.openmrs.module.bloodbank.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.IssuedBloodStockService;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.db.IssuedBloodStockDAO;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.IssuedBloodStock;


public class IssuedBloodStockServiceImpl implements IssuedBloodStockService{
	private final Log log = LogFactory.getLog(getClass());
	private IssuedBloodStockDAO dao;
	public void setDao(IssuedBloodStockDAO dao) {
		this.dao = dao;
	}
	public List<IssuedBloodStock> listAllIssuedBloodStocks()
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listAllIssuedBloodStocks();
	}
	public IssuedBloodStock saveIssuedBloodStock(
			IssuedBloodStock issuedBloodstock) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveIssuedBloodStock(issuedBloodstock);
	}
	public Collection<IssuedBloodStock> getBloodStockByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return dao.getBloodStockByPatient(patient);
	}
	
	
}
