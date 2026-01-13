package com.sachin.Billing.Software.service;

import com.sachin.Billing.Software.Entity.Invoice;
import com.sachin.Billing.Software.dto.InvoiceRequestDTO;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO);
    Invoice getOrderById(long invoiceId);
    List<Invoice> getAllInvoice();
}
