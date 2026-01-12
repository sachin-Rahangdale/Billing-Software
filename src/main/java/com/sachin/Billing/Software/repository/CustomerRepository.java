package com.sachin.Billing.Software.repository;

import com.sachin.Billing.Software.Entity.Customer;
import com.sachin.Billing.Software.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final List<Customer> custlist = new ArrayList<>();

    public Customer  addCustomer(Customer customer){
        custlist.add(customer);
        return customer;
    }
    public Customer getCustomerById(int id){
        return  custlist.stream().filter(c-> c.getId()==id).findFirst().orElse(null);
    }
    public Customer updateCustomerById(int id, Customer customer){
        Customer currCustomer = getCustomerById(id);
        if(currCustomer!=null){
            currCustomer.setId(customer.getId());
            currCustomer.setName(customer.getName());
            currCustomer.setAddress(customer.getAddress());
            currCustomer.setEmail(customer.getEmail());
            currCustomer.setPhone(customer.getPhone());
        }
        return currCustomer;
    }
    public List<Customer> getAllCustomerList(){
        return custlist;
    }

}
