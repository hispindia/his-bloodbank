package org.openmrs.module.bloodbank.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.db.BloodbankDAO;


public class BloodbankServiceImpl implements BloodBankService{
	private final Log log = LogFactory.getLog(getClass());
	private BloodbankDAO dao;
	
	public void setDao(BloodbankDAO dao) {
		this.dao = dao;
	}
}
