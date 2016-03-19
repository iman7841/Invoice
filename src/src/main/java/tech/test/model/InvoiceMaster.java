package tech.test.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "invoice_master")
public class InvoiceMaster {
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue
	private int id;
	
	@Column(name = "invoice_code", length = 20)
	private String invoiceCode;
	
	@Column(name = "invoice_remarks", length = 255)
	private String invoiceRemarks;
	
	@Column(name = "invoice_status", length = 1)
	private int invoiceStatus;
	
	@Column(name = "invoice_total", length = 10)
	private double invoiceTotal;
	
	@Column(name = "invoice_total_sales_tax", length = 10)
	private double invoiceTotalSalesTax;
	
	@OneToMany(mappedBy="invoiceMaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<InvoiceItemMaster> invoiceItemMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceRemarks() {
		return invoiceRemarks;
	}

	public void setInvoiceRemarks(String invoiceRemarks) {
		this.invoiceRemarks = invoiceRemarks;
	}

	public int getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(int invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public double getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public Set<InvoiceItemMaster> getInvoiceItemMaster() {
		return invoiceItemMaster;
	}

	public void setInvoiceItemMaster(Set<InvoiceItemMaster> invoiceItemMaster) {
		this.invoiceItemMaster = invoiceItemMaster;
	}

	public double getInvoiceTotalSalesTax() {
		return invoiceTotalSalesTax;
	}

	public void setInvoiceTotalSalesTax(double invoiceTotalSalesTax) {
		this.invoiceTotalSalesTax = invoiceTotalSalesTax;
	}
}
