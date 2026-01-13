package com.sachin.Billing.Software.service;

import com.sachin.Billing.Software.Entity.Invoice;
import com.sachin.Billing.Software.dto.InvoiceRequestDTO;
import com.sachin.Billing.Software.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceRepository.createInvoice(invoiceRequestDTO);
    }

    @Override
    public Invoice getOrderById(long invoiceId) {
        return invoiceRepository.getInvoiceById(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.getAllInvoices();
    }

}
