package org.openmrs.module.bloodbank.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.APIException;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.model.BloodStock;


public class BloodStockServiceImpl implements BloodStockService{
	private final Log log = LogFactory.getLog(getClass());
	private BloodStockDAO dao;
	
	public void setDao(BloodStockDAO dao) {
		this.dao = dao;
	}

	public List<BloodStock> listAllBloodStocks() throws APIException {
		// TODO Auto-generated method stub
		return dao.listAllBloodStocks();
	}

	public BloodStock saveBloodStock(BloodStock bloodStock) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveBloodstock(bloodStock);
	}

	public Collection<BloodStock> getBloodStocksByBloodGroup(Concept bloodGroup) {
		// TODO Auto-generated method stub
		return dao.getBloodStocksByBloodGroup(bloodGroup);
	}

	public Collection<BloodStock> getNonDiscardedExpiredBloodStocks() {
		// TODO Auto-generated method stub
		return dao.getNonDiscardedExpiredBloodStocks();
	}

	public BloodStock getBloodStockById(int bloodStockId) {
		// TODO Auto-generated method stub
		return dao.getBloodStockById(bloodStockId);
	}


	public void deleteBloodStocks(Collection<BloodStock> bloodStocks) {
	dao.deleteBloodStocks(bloodStocks);
	}
}
