package com.sachin.Billing.Software.service;

import com.sachin.Billing.Software.Entity.Customer;
import com.sachin.Billing.Software.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.addCustomer(customer);
    }
    public Customer getCustomerById(int id){

        return customerRepository.getCustomerById(id);
    }
    public Customer updateCustomerById(int id, Customer customer){
        return customerRepository.updateCustomerById(id,customer);
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.getAllCustomerList();
    }
}
