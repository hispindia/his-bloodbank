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
package org.openmrs.module.bloodbank.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.User;


/**
 *
 */
public class BloodBank implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	private Integer bloodBankId;
	
	private Patient patient;
	
	private Encounter questionnaire;
	
	private Boolean questionnaireComplete;
	
	private User questionnaireProvider;
	
	private Encounter test;
	
	private Boolean testComplete;
	
	private User testProvider;
	
	private Encounter bloodResult;
	
	private Boolean bloodResultComplete;

	private User bloodResultProvider;
	
	private Concept bloodGroup;
	
	private Date storageDate;
	
	private Date expiryDate;
	
	private Concept issuedTo;
	
	private Boolean disposed;
	
	private Boolean expired;
	
	//general props
	
	protected User creator;
	
	private Date dateCreated;
	
	private User changedBy;
	
	private Date dateChanged;
	
	private Boolean voided = Boolean.FALSE;
	
	private Date dateVoided;
	
	private User voidedBy;
	
	private String voidReason;

	
    /**
	 * @return the bloodBankId
	 */
	public Integer getBloodBankId() {
		return bloodBankId;
	}

	/**
	 * @param bloodBankId the bloodBankId to set
	 */
	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	/**
     * @return the storageDate
     */
    public Date getStorageDate() {
    	return storageDate;
    }

    /**
     * @param storageDate the storageDate to set
     */
    public void setStorageDate(Date storageDate) {
    	this.storageDate = storageDate;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
    	return expiryDate;
    }

    /**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the questionnaireComplete
	 */
	public Boolean getQuestionnaireComplete() {
		return questionnaireComplete;
	}

	/**
	 * @param questionnaireComplete the questionnaireComplete to set
	 */
	public void setQuestionnaireComplete(Boolean questionnaireComplete) {
		this.questionnaireComplete = questionnaireComplete;
	}

	/**
	 * @return the bloodGroup
	 */
	public Concept getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(Concept bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * @return the testComplete
	 */
	public Boolean getTestComplete() {
		return testComplete;
	}

	/**
	 * @param testComplete the testComplete to set
	 */
	public void setTestComplete(Boolean testComplete) {
		this.testComplete = testComplete;
	}

	/**
	 * @return the bloodResultComplete
	 */
	public Boolean getBloodResultComplete() {
		return bloodResultComplete;
	}

	/**
	 * @param bloodResultComplete the bloodResultComplete to set
	 */
	public void setBloodResultComplete(Boolean bloodResultComplete) {
		this.bloodResultComplete = bloodResultComplete;
	}

	/**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
    	this.expiryDate = expiryDate;
    }

    /**
	 * @return the questionnaire
	 */
	public Encounter getQuestionnaire() {
		return questionnaire;
	}

	/**
	 * @param questionnaire the questionnaire to set
	 */
	public void setQuestionnaire(Encounter questionnaire) {
		this.questionnaire = questionnaire;
	}

	/**
	 * @return the questionnaireProvider
	 */
	public User getQuestionnaireProvider() {
		return questionnaireProvider;
	}

	/**
	 * @param questionnaireProvider the questionnaireProvider to set
	 */
	public void setQuestionnaireProvider(User questionnaireProvider) {
		this.questionnaireProvider = questionnaireProvider;
	}

	/**
	 * @return the test
	 */
	public Encounter getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Encounter test) {
		this.test = test;
	}

	/**
	 * @return the testProvider
	 */
	public User getTestProvider() {
		return testProvider;
	}

	/**
	 * @param testProvider the testProvider to set
	 */
	public void setTestProvider(User testProvider) {
		this.testProvider = testProvider;
	}

	/**
	 * @return the bloodResult
	 */
	public Encounter getBloodResult() {
		return bloodResult;
	}

	/**
	 * @param bloodResult the bloodResult to set
	 */
	public void setBloodResult(Encounter bloodResult) {
		this.bloodResult = bloodResult;
	}

	/**
	 * @return the bloodResultProvider
	 */
	public User getBloodResultProvider() {
		return bloodResultProvider;
	}

	/**
	 * @param bloodResultProvider the bloodResultProvider to set
	 */
	public void setBloodResultProvider(User bloodResultProvider) {
		this.bloodResultProvider = bloodResultProvider;
	}

	/**
     * @return the issuedTo
     */
    public Concept getIssuedTo() {
    	return issuedTo;
    }

    /**
     * @param issuedTo the issuedTo to set
     */
    public void setIssuedTo(Concept issuedTo) {
    	this.issuedTo = issuedTo;
    }

    /**
	 * @return the disposed
	 */
	public Boolean getDisposed() {
		return disposed;
	}

	/**
	 * @param disposed the disposed to set
	 */
	public void setDisposed(Boolean disposed) {
		this.disposed = disposed;
	}

	/**
     * @return the expired
     */
    public Boolean getExpired() {
    	return expired;
    }

    /**
     * @param expired the expired to set
     */
    public void setExpired(Boolean expired) {
    	this.expired = expired;
    }

    /**
     * @return the creator
     */
    public User getCreator() {
    	return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(User creator) {
    	this.creator = creator;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
    	return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
    	this.dateCreated = dateCreated;
    }

    /**
     * @return the changedBy
     */
    public User getChangedBy() {
    	return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(User changedBy) {
    	this.changedBy = changedBy;
    }

    /**
     * @return the dateChanged
     */
    public Date getDateChanged() {
    	return dateChanged;
    }

    /**
     * @param dateChanged the dateChanged to set
     */
    public void setDateChanged(Date dateChanged) {
    	this.dateChanged = dateChanged;
    }

    /**
     * @return the voided
     */
    public Boolean getVoided() {
    	return voided;
    }

    /**
     * @param voided the voided to set
     */
    public void setVoided(Boolean voided) {
    	this.voided = voided;
    }

    /**
     * @return the dateVoided
     */
    public Date getDateVoided() {
    	return dateVoided;
    }

    /**
     * @param dateVoided the dateVoided to set
     */
    public void setDateVoided(Date dateVoided) {
    	this.dateVoided = dateVoided;
    }

    /**
     * @return the voidedBy
     */
    public User getVoidedBy() {
    	return voidedBy;
    }

    /**
     * @param voidedBy the voidedBy to set
     */
    public void setVoidedBy(User voidedBy) {
    	this.voidedBy = voidedBy;
    }

    /**
     * @return the voidReason
     */
    public String getVoidReason() {
    	return voidReason;
    }

    /**
     * @param voidReason the voidReason to set
     */
    public void setVoidReason(String voidReason) {
    	this.voidReason = voidReason;
    }


}
