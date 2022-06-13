package com.command.service.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class ProductVO {

	//Product Name Ø Short Description Ø Detailed Description Ø Category Ø Starting Price Ø Bid end date
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String short_desc;
	private String detailed_desc;
	private String category;
	private double price;
	private Date bidEndDate;
	
	public ProductVO() {
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", product_name=" + name + ", short_desc=" + short_desc
				+ ", detailed_desc=" + detailed_desc + ", category=" + category + ", price=" + price + ", bidEndDate="
				+ bidEndDate + "]";
	}
	
	public ProductVO(String name, String short_desc, String detailed_desc, String category,
			double price, Date bidEndDate) {
		super();
		this.name = name;
		this.short_desc = short_desc;
		this.detailed_desc = detailed_desc;
		this.category = category;
		this.price = price;
		this.bidEndDate = bidEndDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public String getDetailed_desc() {
		return detailed_desc;
	}
	public void setDetailed_desc(String detailed_desc) {
		this.detailed_desc = detailed_desc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	
}
