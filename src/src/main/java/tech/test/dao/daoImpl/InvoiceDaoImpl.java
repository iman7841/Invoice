package tech.test.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import tech.test.dao.InvoiceDao;
import tech.test.model.InvoiceItemMaster;
import tech.test.model.InvoiceMaster;
import tech.test.model.ProductCategoryMaster;
import tech.test.model.ProductMaster;
import tech.test.model.ProductSalesTaxMaster;

public class InvoiceDaoImpl implements InvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int intiateInvoice(InvoiceMaster invoiceMaster) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(invoiceMaster);
		return id.intValue();
	}
	
	public InvoiceMaster addProductToBill(int invoiceId, int productId) {
		int productCategoryId, productCount = 0;
		double productFinalCost, productSalesTaxValue, productCost, invoiceTotal, invoiceTotalSalesTax, prodSalesTaxValue;
		InvoiceMaster invoiceMaster = null;
		ProductMaster productMaster = null;
		InvoiceItemMaster invoiceItemMaster = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invoiceId);
			
			if (invoiceMaster != null && invoiceMaster.getInvoiceStatus() != 2) {				
				productMaster = (ProductMaster) session.get(ProductMaster.class, productId);
				
				Criteria criteriaInvoiceItemMaster = session.createCriteria(InvoiceItemMaster.class);
				criteriaInvoiceItemMaster.add(Restrictions.eq("productMaster", productMaster));
				List<InvoiceItemMaster> invoiceItems = (ArrayList<InvoiceItemMaster>) criteriaInvoiceItemMaster.list();
				
				productCategoryId = productMaster.getProductCategoryMaster().getId();
								
				ProductSalesTaxMaster productSalesTaxMaster = (ProductSalesTaxMaster) session.get(ProductSalesTaxMaster.class, productCategoryId);
				
				productSalesTaxValue = productSalesTaxMaster.getProductSalesTaxValue();
				
				productCost = productMaster.getProductCost();
				
				productFinalCost = productCost + (productCost * (productSalesTaxValue / 100));
				
				invoiceTotal = invoiceMaster.getInvoiceTotal();
				
				invoiceTotal = invoiceTotal + productFinalCost;
				
				invoiceTotalSalesTax = invoiceMaster.getInvoiceTotalSalesTax();
				
				invoiceTotalSalesTax = invoiceTotalSalesTax + productSalesTaxValue; 
				
				invoiceMaster.setInvoiceTotal(invoiceTotal);
				invoiceMaster.setInvoiceTotalSalesTax(invoiceTotalSalesTax);
				invoiceMaster.setInvoiceStatus(1);
	
				if (invoiceItems.size() != 0) {
					invoiceItemMaster = invoiceItems.get(0);
					productCount = invoiceItemMaster.getProductCount();
					invoiceItemMaster.setProductCount(productCount + 1);
					prodSalesTaxValue = invoiceItemMaster.getProductSalesTax();
					invoiceItemMaster.setProductSalesTax(prodSalesTaxValue + productSalesTaxValue);
				} else {			
					invoiceItemMaster = new InvoiceItemMaster();
					invoiceItemMaster.setProductCount(1);
					invoiceItemMaster.setProductSalesTax(productSalesTaxValue);
				}
				
				invoiceItemMaster.setInvoiceMaster(invoiceMaster);
				invoiceItemMaster.setProductMaster(productMaster);
				
				invoiceMaster.getInvoiceItemMaster().add(invoiceItemMaster);
				session.save(invoiceMaster);
				
				invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invoiceId);
			} else {
				invoiceMaster = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return invoiceMaster;
	}
	
	public InvoiceMaster finalizeBill(int invoiceId) {
		InvoiceMaster invoiceMaster = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invoiceId);

			if (invoiceMaster != null) {
				invoiceMaster.setInvoiceStatus(2);
				session.save(invoiceMaster);
				
				invoiceMaster = (InvoiceMaster) session.get(InvoiceMaster.class, invoiceId);
			} else {
				invoiceMaster = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return invoiceMaster;
	}
}
