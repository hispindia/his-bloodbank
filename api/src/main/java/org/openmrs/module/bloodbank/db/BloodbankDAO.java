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

package org.openmrs.module.bloodbank.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.bloodbank.model.BloodBank;
import org.openmrs.module.bloodbank.model.PreparedDonorId;
import org.openmrs.module.bloodbank.model.BloodbankForm;

public interface BloodbankDAO {
	public void setSessionFactory(SessionFactory sessionFactory);

	public String getUniqueId();

	public void savePreparedId(PreparedDonorId pdi);
	
    public List<PreparedDonorId> getUnusedPreparedId();

	public PreparedDonorId getPrepDonorIdbyIdentifier(String identifier);

	public List<BloodBank> getRecordsByPatient(Patient patient);
	
	public BloodBank getRecordById(int id);

	public void saveBloodBank(BloodBank bloodBank);
	
	public List<BloodBank> getValidStockRecords();

	public List<BloodBank> getTestableRecords();

	public List<BloodBank> getValidStockByTypeConcept(Concept con);

	public List<PreparedDonorId> getAllPreparedIds();
	
	/**
	 * Get radiology form be concept name
	 * 
	 * @param concept
	 * @return
	 */
	public List<BloodbankForm> getBloodbankForms(String conceptName);

	/**
	 * Get all radiology form
	 * 
	 * @return
	 */
	public List<BloodbankForm> getAllBloodbankForms();
	
	/**
	 * Get radiology form by id
	 * 
	 * @param id
	 * @return
	 */
	public BloodbankForm getBloodbankFormById(Integer id);
	
	/**
	 * Save radiology form
	 * 
	 * @param form
	 * @return
	 */
	public BloodbankForm saveBloodbankForm(BloodbankForm form);

}
