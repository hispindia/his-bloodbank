package org.openmrs.module.bloodbank.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.module.bloodbank.BloodStockReceiptService;
import org.openmrs.module.bloodbank.BloodStockService;
import org.openmrs.module.bloodbank.db.BloodStockDAO;
import org.openmrs.module.bloodbank.db.BloodStockReceiptDAO;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;


public class BloodStockReceiptServiceImpl implements BloodStockReceiptService{
	private final Log log = LogFactory.getLog(getClass());
	private BloodStockReceiptDAO dao;
	
	public void setDao(BloodStockReceiptDAO dao) {
		this.dao = dao;
	}

	
	public BloodStockReceipt saveBloodStockreceipt(BloodStockReceipt bloodStockReceipt)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveBloodStockReceipt(bloodStockReceipt);
	}


	public BloodStockReceipt getBloodStockReceiptFromId(int receiptId)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getBloodStockReceiptFromId(receiptId);
	}


	public Collection<BloodStockReceipt> listAll() {
		// TODO Auto-generated method stub
		return dao.listAll();
	}


	public Collection<BloodStockReceipt> searchBloodStockReceipt(
			String description, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return dao.searchBloodStockReceipt(description,fromDate,toDate);
	}


}
