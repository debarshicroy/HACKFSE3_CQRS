package com.command.service.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "product")
public class ProductDO {

	@Id
	@JsonProperty("id")
	private long id;
	@JsonProperty("product_name")
	private String name;
	@JsonProperty("short_desc")
	private String short_desc;
	@JsonProperty("detailed_desc")
	private String detailed_desc;
	@JsonProperty("category")
	private String category;
	@JsonProperty("price")
	private double price;
	@JsonProperty("bidEndDate")
	private Date bidEndDate;
	@JsonProperty
	private String action;
	
	
	// cts
	public ProductDO(long id, String name, String short_desc, String detailed_desc, String category, double price,
			Date bidEndDate, String action) {
		super();
		this.id = id;
		this.name = name;
		this.short_desc = short_desc;
		this.detailed_desc = detailed_desc;
		this.category = category;
		this.price = price;
		this.bidEndDate = bidEndDate;
		this.action = action;
	}





	
	public ProductDO() {
		super();
	}
	 


	@Override
	public String toString() {
		return "ProductDO [id=" + id + ", name=" + name + ", short_desc=" + short_desc + ", detailed_desc="
				+ detailed_desc + ", category=" + category + ", price=" + price + ", bidEndDate=" + bidEndDate
				+ ", action=" + action + "]";
	}


	public String getName() {
		return name;
	}


	public String getShort_desc() {
		return short_desc;
	}


	public String getDetailed_desc() {
		return detailed_desc;
	}


	public String getCategory() {
		return category;
	}


	public double getPrice() {
		return price;
	}


	public Date getBidEndDate() {
		return bidEndDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}
	
	
}
