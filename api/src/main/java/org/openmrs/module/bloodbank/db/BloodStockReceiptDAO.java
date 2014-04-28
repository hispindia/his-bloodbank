package org.openmrs.module.bloodbank.db;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.module.bloodbank.model.BloodStock;
import org.openmrs.module.bloodbank.model.BloodStockReceipt;

public interface BloodStockReceiptDAO {

	BloodStockReceipt saveBloodStockReceipt(BloodStockReceipt bloodStockReceipt);

	BloodStockReceipt getBloodStockReceiptFromId(int receiptId);

	Collection<BloodStockReceipt> listAll();

	Collection<BloodStockReceipt> searchBloodStockReceipt(String description,
			Date fromDate, Date toDate);

	void deleteReceipt(BloodStockReceipt receipt);

	
}
