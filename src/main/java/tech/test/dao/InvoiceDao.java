package tech.test.dao;

import tech.test.model.InvoiceMaster;

public interface InvoiceDao {

	public int intiateInvoice(InvoiceMaster invoiceMaster);

	public InvoiceMaster addProductToBill(int invoiceId, int productId);
	
	public InvoiceMaster finalizeBill(int invoiceId);
}
