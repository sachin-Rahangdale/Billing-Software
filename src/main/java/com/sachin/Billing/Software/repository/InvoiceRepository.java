package com.sachin.Billing.Software.repository;

import com.sachin.Billing.Software.Entity.Customer;
import com.sachin.Billing.Software.Entity.Invoice;
import com.sachin.Billing.Software.Entity.InvoiceItem;
import com.sachin.Billing.Software.Entity.Product;
import com.sachin.Billing.Software.dto.InvoiceRequestDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InvoiceRepository {

    private final JdbcTemplate jdbcTemplate;

    public InvoiceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Invoice createInvoice(InvoiceRequestDTO dto) {

        double totalAmount = 0;
        double totalTax = 0;
        double totalDiscount = 0;

        // 1️⃣ Insert empty invoice
        String invoiceSql = "INSERT INTO invoice (invoice_date, customer_id, total_amount, total_tax, discount, final_amount) VALUES (CURDATE(), ?, 0, 0, 0, 0)";
        jdbcTemplate.update(invoiceSql, dto.getCustomer().getCustomerId());

        Long invoiceId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        // 2️⃣ Process items
        for (InvoiceItem item : dto.getItems()) {

            int productId = item.getProduct().getId();

            Product dbProduct = getProductById(productId);
            double price = dbProduct.getPrice();
            int qty = item.getQuantity();

            double lineAmount = price * qty;
            double tax = lineAmount * 0.18;      // 18% GST
            double discount = lineAmount * 0.05; // 5% discount
            double total = lineAmount + tax - discount;

            totalAmount += lineAmount;
            totalTax += tax;
            totalDiscount += discount;

            String itemSql = "INSERT INTO invoice_item (invoice_id, product_id, quantity, price, tax_amount, discount, total) VALUES (?,?,?,?,?,?,?)";

            jdbcTemplate.update(itemSql,
                    invoiceId,
                    productId,
                    qty,
                    price,
                    tax,
                    discount,
                    total
            );
        }

        double finalAmount = totalAmount + totalTax - totalDiscount;

        // 3️⃣ Update invoice
        String updateSql = "UPDATE invoice SET total_amount=?, total_tax=?, discount=?, final_amount=? WHERE invoice_id=?";
        jdbcTemplate.update(updateSql,
                totalAmount,
                totalTax,
                totalDiscount,
                finalAmount,
                invoiceId
        );

        return getInvoiceById(invoiceId);
    }

    // Fetch invoice
    public Invoice getInvoiceById(Long invoiceId) {
        String sql = "SELECT * FROM invoice WHERE invoice_id=?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Invoice i = new Invoice();
            i.setInvoiceId(rs.getLong("invoice_id"));
            i.setInvoiceDate(rs.getDate("invoice_date").toLocalDate());

            Customer c = new Customer();
            c.setCustomerId(rs.getInt("customer_id"));
            i.setCustomer(c);

            i.setTotalAmount(rs.getDouble("total_amount"));
            i.setTotalTax(rs.getDouble("total_tax"));
            i.setDiscount(rs.getDouble("discount"));
            i.setFinalAmount(rs.getDouble("final_amount"));

            return i;
        }, invoiceId);
    }

    // Fetch product
    private Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setPrice(rs.getDouble("price"));
            return p;
        }, id);
    }
    public List<Invoice> getAllInvoices() {

        String sql = "SELECT * FROM invoice";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Invoice i = new Invoice();

            i.setInvoiceId(rs.getLong("invoice_id"));
            i.setInvoiceDate(rs.getDate("invoice_date").toLocalDate());
            Customer c = new Customer();
            c.setCustomerId(rs.getInt("customer_id"));
            i.setCustomer(c);
            i.setTotalAmount(rs.getDouble("total_amount"));
            i.setTotalTax(rs.getDouble("total_tax"));
            i.setDiscount(rs.getDouble("discount"));
            i.setFinalAmount(rs.getDouble("final_amount"));
            return i;
        });
    }

}
