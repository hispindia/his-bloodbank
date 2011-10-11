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

package org.openmrs.module.bloodbank.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.module.bloodbank.BloodBankService;
import org.openmrs.module.bloodbank.db.BloodbankDAO;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.openmrs.module.bloodbank.model.BloodbankForm;


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
}
