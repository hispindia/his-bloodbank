/**
 *  Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
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

package org.openmrs.module.bloodbank;

import java.util.List;

import org.openmrs.Concept;
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

}
