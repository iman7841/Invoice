package tech.test.service.serviceImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.test.dao.InvoiceDao;
import tech.test.model.InvoiceMaster;
import tech.test.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements  InvoiceService {
	
	@Autowired
	InvoiceDao invoiceDao;
	
	@Transactional
	public int intiateInvoice(InvoiceMaster invoiceMaster) {
		return invoiceDao.intiateInvoice(invoiceMaster);
	}
	
	@Transactional
	public InvoiceMaster addProductToBill(int invoiceId, int productId) {
		return invoiceDao.addProductToBill(invoiceId, productId);
	}
	
	@Transactional
	public InvoiceMaster finalizeBill(int invoiceId) {
		return invoiceDao.finalizeBill(invoiceId);
	}
}
