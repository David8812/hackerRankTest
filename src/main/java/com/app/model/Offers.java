package com.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "offers")
public class Offers implements Serializable {

	/**
	 * version number for deserialization only
	 * 
	 */
	private static final long serialVersionUID = 413404668369006185L;

	/**
	 * Auto-generated id of the offer. For database persistence only
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * brief description of the offer
	 * 
	 */
	@Column
	private String description;

	/**
	 * this field holds the currency in what the offer is represented
	 * 
	 */
	@Column
	private String currency;

	/**
	 * this field holds the price of the offer
	 * 
	 */
	@Column
	private double price;

	/**
	 * this field holds the beginning date of the offer
	 * 
	 */
	@Column
	private Date beginningTime;

	/**
	 * this field represents for how long this offer will last in milliseconds
	 * 
	 */
	@Column(name = "offer_time")
	private long offerTime;

	/**
	 * this field holds the status of the offer, true if it is active, false if it
	 * has been cancelled
	 * 
	 */
	@Column
	private boolean status;

	/**
	 * this field represents a readable state of the status, is a calculable field
	 * no need to be store, but depending on the volume to handle we can avoid the
	 * calculus and directly save the object in the database to improve performance
	 * 
	 */
	@Transient
	private StatusDescription statusDescription;

	public Offers() {
	}

	public Offers(String description, String currency, double price, Date beginningTime, long offerTime,
			boolean status) {
		this.description = description;
		this.currency = currency;
		this.price = price;
		this.beginningTime = beginningTime;
		this.offerTime = offerTime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getBeginningTime() {
		return beginningTime;
	}

	public void setBeginningTime(Date beginningTime) {
		this.beginningTime = beginningTime;
	}

	public long getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(long offerTime) {
		this.offerTime = offerTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return String.format(
				"[id: %s, description: %s, currency: %s, price: %s, beginning time: %s, offerTime: %s, status: %s]",
				String.valueOf(getId()), getDescription(), getCurrency(), String.valueOf(getPrice()),
				getBeginningTime().toString(), String.valueOf(getOfferTime()), String.valueOf(isStatus()));
	}

	@Override
	public boolean equals(Object _other) {
		if (_other instanceof Offers) {
			Offers offer = (Offers) _other;
			return this.id == offer.getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _hashCode = 0;

		if (getId() != 0)
			_hashCode += 29 * getId().hashCode();

		if (getDescription() != null)
			_hashCode += 29 * getDescription().hashCode();

		if (getCurrency() != null)
			_hashCode += 29 * getCurrency().hashCode();

		_hashCode += 29 * getPrice();

		if (getBeginningTime() != null)
			_hashCode += 29 * getBeginningTime().hashCode();

		_hashCode += 29 * getOfferTime();

		if (isStatus())
			_hashCode += 29;

		return _hashCode;
	}
}
