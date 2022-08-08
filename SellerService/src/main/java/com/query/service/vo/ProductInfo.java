package com.query.service.vo;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "product_info")
public class ProductInfo {
	private String id;
	private long productId;
	private String name;
	private String short_desc;
	private String detailed_desc;
	private String category;
	private double price;
	private Date bidEndDate;
	
    @DynamoDBAutoGeneratedKey
	public String getId() {
		return id;
	}
    @DynamoDBHashKey
	@DynamoDBAttribute
	public String getName() {
		return name;
	}
	@DynamoDBAttribute
	public String getShort_desc() {
		return short_desc;
	}
	@DynamoDBAttribute
	public String getDetailed_desc() {
		return detailed_desc;
	}
	@DynamoDBAttribute
	public String getCategory() {
		return category;
	}
	@DynamoDBAttribute
	public double getPrice() {
		return price;
	}
	@DynamoDBAttribute
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public void setDetailed_desc(String detailed_desc) {
		this.detailed_desc = detailed_desc;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	@DynamoDBAttribute
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public ProductInfo(long productId, String name, String short_desc, String detailed_desc, String category,
			double price, Date bidEndDate) {
		super();
		this.productId = productId;
		this.name = name;
		this.short_desc = short_desc;
		this.detailed_desc = detailed_desc;
		this.category = category;
		this.price = price;
		this.bidEndDate = bidEndDate;
	}
	
	  public ProductInfo() { super(); }
	 
	
	
	
}
