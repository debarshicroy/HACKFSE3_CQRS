package com.command.service.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="bid")
public class BidingDO {

	@Id
	@GeneratedValue
	private int id;
	@JsonProperty("productName")
	private String name;
	@JsonProperty("bidderName")
	private String bidderName;
	@JsonProperty("bidAmount")
	private double bidAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	@Override
	public String toString() {
		return "BidingDO [id=" + id + ", productName=" + name + ", bidderName=" + bidderName + ", bidAmount="
				+ bidAmount + "]";
	}
	public BidingDO(String productName, String bidderName, double bidAmount) {
		super();
		this.name = productName;
		this.bidderName = bidderName;
		this.bidAmount = bidAmount;
	}
	public BidingDO() {
		super();
	}
	
	
}
