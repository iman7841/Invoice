package tech.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_master")
public class ProductMaster {
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue
	private int id;
	
	@Column(name = "product_name", length = 255)
	private String productName;
	
	@Column(name = "product_cost", length = 10)
	private double productCost;
	
	@Column(name = "product_code", length = 20)
	private String productCode;
	
	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategoryMaster productCategoryMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public ProductCategoryMaster getProductCategoryMaster() {
		return productCategoryMaster;
	}

	public void setProductCategoryMaster(ProductCategoryMaster productCategoryMaster) {
		this.productCategoryMaster = productCategoryMaster;
	}
}
