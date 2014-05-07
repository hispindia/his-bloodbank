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
import org.openmrs.Patient;
import org.openmrs.User;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 */
@Transactional
public class IssuedBloodStock implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
   private BloodStock bloodStock;
   
   private Patient patient;
   private boolean crossmatchingResult;


private String comment;
   
   private Date createdOn;
       
   private User createdBy;
   
   public BloodStock getBloodStock() {
	return bloodStock;
}

public void setBloodStock(BloodStock bloodStock) {
	this.bloodStock = bloodStock;
}

public Patient getPatient() {
	return patient;
}

public void setPatient(Patient patient) {
	this.patient = patient;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
}

public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

public User getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
}

public boolean isCrossmatchingResult() {
	return crossmatchingResult;
}

public void setCrossmatchingResult(boolean crossmatchingResult) {
	this.crossmatchingResult = crossmatchingResult;
}

}
