package tech.test.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategoryMaster {
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue
	private int id;
	
	@Column(name = "product_category", length = 5)
	private String productCategory;
	
	@Column(name = "product_category_description", length = 255)
	private String productCategoryDescription;
	
	@OneToMany(mappedBy="productCategoryMaster")
	private Set<ProductMaster> productMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductCategoryDescription() {
		return productCategoryDescription;
	}

	public void setProductCategoryDescription(String productCategoryDescription) {
		this.productCategoryDescription = productCategoryDescription;
	}

	public Set<ProductMaster> getProductMaster() {
		return productMaster;
	}

	public void setProductMaster(Set<ProductMaster> productMaster) {
		this.productMaster = productMaster;
	}
}
