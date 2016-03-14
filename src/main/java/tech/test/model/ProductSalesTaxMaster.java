package tech.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_sales_tax_master")
public class ProductSalesTaxMaster {
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue
	private int id;
	
	@OneToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategoryMaster productCategoryMaster;
	
	@Column(name = "product_sales_tax_value", length = 10)
	private double productSalesTaxValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductCategoryMaster getProductCategoryMaster() {
		return productCategoryMaster;
	}

	public void setProductCategoryMaster(ProductCategoryMaster productCategoryMaster) {
		this.productCategoryMaster = productCategoryMaster;
	}

	public double getProductSalesTaxValue() {
		return productSalesTaxValue;
	}

	public void setProductSalesTaxValue(double productSalesTaxValue) {
		this.productSalesTaxValue = productSalesTaxValue;
	}
}
