package org.openmrs.module.bloodbank.db;

import java.util.Collection;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.IssuedBloodStock;

public interface IssuedBloodStockDAO {

	List<IssuedBloodStock> listAllIssuedBloodStocks();

	IssuedBloodStock saveIssuedBloodStock(IssuedBloodStock issuedBloodstock);

}
