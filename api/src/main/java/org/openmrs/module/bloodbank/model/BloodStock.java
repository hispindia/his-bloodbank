/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/


package org.openmrs.module.bloodbank.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.User;


/**
 *
 */
public class BloodStock implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer bloodStockId;
    
    private Concept bloodGroupConcept;
    
    private String product;
    
    private String donorName;
    
    private String packNo;
    
    private boolean discarded;
    
    
    private Date expiryDate;
    
    private Date receiptDate;
 
	
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getPackNo() {
		return packNo;
	}

	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Concept getBloodGroupConcept() {
		return bloodGroupConcept;
	}

	public void setBloodGroupConcept(Concept bloodGroupConcept) {
		this.bloodGroupConcept = bloodGroupConcept;
	}

	public Integer getBloodStockId() {
		return bloodStockId;
	}

	public void setBloodStockId(Integer bloodStockId) {
		this.bloodStockId = bloodStockId;
	}
    
     
}
