package tech.test.service;

import org.springframework.stereotype.Service;

import tech.test.model.InvoiceMaster;

@Service
public interface InvoiceService {
	public int intiateInvoice(InvoiceMaster invoiceMaster);
	
	public InvoiceMaster addProductToBill(int invoiceId, int productId);
	
	public InvoiceMaster finalizeBill(int invoiceId);
}
