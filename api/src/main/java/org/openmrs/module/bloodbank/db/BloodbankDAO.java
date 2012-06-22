package org.openmrs.module.bloodbank.db;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
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
	
	public BloodBank getRecordByTest(Encounter encounter);

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

	public BloodBank getRecordByResult(Encounter encounter);
	
	public List<Order> getOrders(Date date, String phrase);

	public boolean isPatientDonor(Integer patientId);

}
