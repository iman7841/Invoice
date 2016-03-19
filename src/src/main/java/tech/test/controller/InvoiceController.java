package tech.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.test.model.InvoiceItemMaster;
import tech.test.model.InvoiceMaster;
import tech.test.service.InvoiceService;

@Controller
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping(value = "/rest/initiateBill", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> initiateBill(@RequestBody Map<String, Object> requestMap) {
		InvoiceMaster invoiceMaster = new InvoiceMaster();
		HashMap hashMap = new HashMap<String, Object>();
		invoiceMaster.setInvoiceCode(String.valueOf(Math.random()));
		int invoiceId = invoiceService.intiateInvoice(invoiceMaster);
		
		hashMap.put("invoiceId", invoiceId);
		return hashMap;
	}
	
	@RequestMapping(value = "/rest/addProductToBill", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addProductToBill(@RequestBody Map<String, Object> requestMap) {
		HashMap hashMap = new HashMap<String, Object>();
		int invoiceId = Integer.parseInt((String) requestMap.get("invoiceId"));
		int productId = Integer.parseInt((String) requestMap.get("productId"));
		
		InvoiceMaster invoiceMaster = invoiceService.addProductToBill(invoiceId, productId);
		
		hashMap.put("invoice", invoiceMaster);
		return hashMap;
	}
	
	@RequestMapping(value = "/rest/finalizeBill", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> finalizeBill(@RequestBody Map<String, Object> requestMap) {
		HashMap hashMap = new HashMap<String, Object>();
		int invoiceId = Integer.parseInt((String) requestMap.get("invoiceId"));
		
		InvoiceMaster invoiceMaster = invoiceService.finalizeBill(invoiceId);
		
		hashMap.put("invoice", invoiceMaster);
		return hashMap;
	}
} 
