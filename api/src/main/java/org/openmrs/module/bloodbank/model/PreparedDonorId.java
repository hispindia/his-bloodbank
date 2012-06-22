package org.openmrs.module.bloodbank.model;

import java.util.Date;

import org.openmrs.User;

public class PreparedDonorId {

	private Integer id;
	
	private String identifier;
	
	private Boolean used;
	
	//general properties
	
	protected User creator;
	
	private Date dateCreated;
	
	private User changedBy;
	
	private Date dateChanged;
	
	private Boolean voided = Boolean.FALSE;
	
	private Date dateVoided;
	
	private User voidedBy;
	
	private String voidReason;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the used
	 */
	public Boolean getUsed() {
		return used;
	}

	/**
	 * @param used the used to set
	 */
	public void setUsed(Boolean used) {
		this.used = used;
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
