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
	@JsonProperty("bidderEmailId")
	private String bidderEmailId;
	@JsonProperty("bidderPhNo")
	private String bidderPhNo;
	@JsonProperty("bidderAdd")
	private String bidderAdd;
	@JsonProperty("bidderPincode")
	private String bidderPincode;
	@JsonProperty("bidderState")
	private String bidderState;
	
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
	public String getBidderEmailId() {
		return bidderEmailId;
	}
	public void setBidderEmailId(String bidderEmailId) {
		this.bidderEmailId = bidderEmailId;
	}
	public String getBidderPhNo() {
		return bidderPhNo;
	}
	public void setBidderPhNo(String bidderPhNo) {
		this.bidderPhNo = bidderPhNo;
	}
	public String getBidderAdd() {
		return bidderAdd;
	}
	public void setBidderAdd(String bidderAdd) {
		this.bidderAdd = bidderAdd;
	}
	public String getBidderPincode() {
		return bidderPincode;
	}
	public void setBidderPincode(String bidderPincode) {
		this.bidderPincode = bidderPincode;
	}
	public String getBidderState() {
		return bidderState;
	}
	public void setBidderState(String bidderState) {
		this.bidderState = bidderState;
	}
	public BidingDO(int id, String name, String bidderName, double bidAmount, String bidderEmailId, String bidderPhNo,
			String bidderAdd, String bidderPincode, String bidderState) {
		super();
		this.id = id;
		this.name = name;
		this.bidderName = bidderName;
		this.bidAmount = bidAmount;
		this.bidderEmailId = bidderEmailId;
		this.bidderPhNo = bidderPhNo;
		this.bidderAdd = bidderAdd;
		this.bidderPincode = bidderPincode;
		this.bidderState = bidderState;
	}
	public BidingDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BidingDO [id=" + id + ", name=" + name + ", bidderName=" + bidderName + ", bidAmount=" + bidAmount
				+ ", bidderEmailId=" + bidderEmailId + ", bidderPhNo=" + bidderPhNo + ", bidderAdd=" + bidderAdd
				+ ", bidderPincode=" + bidderPincode + ", bidderState=" + bidderState + "]";
	}	
}
