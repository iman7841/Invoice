package tech.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "invoice_item_master")
public class InvoiceItemMaster {
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue
	private int id;
	
	@Column(name = "product_count", length = 10)
	private int productCount;
	 
	@Column(name = "product_sales_tax", length = 10)
	private double productSalesTax;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	@JsonIgnore
	private InvoiceMaster invoiceMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private ProductMaster productMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InvoiceMaster getInvoiceMaster() {
		return invoiceMaster;
	}

	public void setInvoiceMaster(InvoiceMaster invoiceMaster) {
		this.invoiceMaster = invoiceMaster;
	}

	public ProductMaster getProductMaster() {
		return productMaster;
	}

	public void setProductMaster(ProductMaster productMaster) {
		this.productMaster = productMaster;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public double getProductSalesTax() {
		return productSalesTax;
	}

	public void setProductSalesTax(double productSalesTax) {
		this.productSalesTax = productSalesTax;
	}
	
}
