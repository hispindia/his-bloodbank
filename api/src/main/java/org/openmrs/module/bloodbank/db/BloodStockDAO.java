package org.openmrs.module.bloodbank.db;

import java.util.Collection;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.module.bloodbank.model.BloodStock;

public interface BloodStockDAO {

	List<BloodStock> listAllBloodStocks();

	BloodStock saveBloodstock(BloodStock bloodStock);

	Collection<BloodStock> getBloodStocksByBloodGroup(Concept bloodGroup);

	Collection<BloodStock> getNonDiscardedExpiredBloodStocks();

	BloodStock getBloodStockById(int bloodStockId);

}
