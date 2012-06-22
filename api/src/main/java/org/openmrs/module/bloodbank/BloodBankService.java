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
package org.openmrs.module.bloodbank;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.openmrs.module.bloodbank.model.BloodbankForm;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */
@Transactional
public interface BloodBankService {


	String getUniqueId();

	void savePreparedId(PreparedDonorId pdi);

	PreparedDonorId getPrepDonorIdbyIdentifier(String identifier);
	
	List<PreparedDonorId> getUnusedPreparedId();

	List<BloodBank> getRecordsByPatient(Patient patient);
	
	void saveBloodBank(BloodBank bloodBank);

	List<BloodBank> getValidStockRecords();

	List<BloodBank> getTestableRecords();

	List<BloodBank> getValidStockByTypeConcept(Concept con);

	BloodBank getRecordById(int id);
	
	BloodBank getRecordByTest(Encounter encounter);

	BloodBank getRecordByResult(Encounter encounter);
	
	public List<Order> getOrders(Date date, String phrase);
	
	List<PreparedDonorId> getAllPreparedIds();
	
	/**
	 * Get all bloodbank forms
	 * 
	 * @return
	 */
	public List<BloodbankForm> getAllBloodbankForms();
	
	/**
	 * Get bloodbank forms
	 * 
	 * @param conceptName
	 * @return
	 */
	public List<BloodbankForm> getBloodbankForms(String conceptName);
	
	/**
	 * Get bloodbank test by id
	 * 
	 * @param id
	 * @return
	 */
	public BloodbankForm getBloodbankFormById(Integer id);
	
	/**
	 * Get radiology test by id
	 * 
	 * @param form
	 * @return
	 */
	public BloodbankForm saveBloodbankForm(BloodbankForm form);
	
	/**
	 * Given PatientId find if the patient has an existing encounter, is a donor or not.
	 */
	public boolean isPatientDonor(Integer PatientId);
	

}
