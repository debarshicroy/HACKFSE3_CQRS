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
	

	public ProductDO(long id, String product_name, String short_desc, String detailed_desc, String category,
			double price, Date bidEndDate) {
		super();
		this.id = id;
		this.name = product_name;
		this.short_desc = short_desc;
		this.detailed_desc = detailed_desc;
		this.category = category;
		this.price = price;
		this.bidEndDate = bidEndDate;
	}


	public ProductDO() {
		super();
	}


	@Override
	public String toString() {
		return "ProductDO [id=" + id + ", product_name=" + name + ", short_desc=" + short_desc
				+ ", detailed_desc=" + detailed_desc + ", category=" + category + ", price=" + price + ", bidEndDate="
				+ bidEndDate + "]";
	}
	
	
}
