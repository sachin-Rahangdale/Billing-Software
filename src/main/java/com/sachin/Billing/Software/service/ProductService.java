package com.sachin.Billing.Software.service;

import com.sachin.Billing.Software.Entity.Product;
import com.sachin.Billing.Software.exception.ResourceNotFoundException;
import com.sachin.Billing.Software.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.addProduct(product);
    }
    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }
    public Product updateProductById(int id, double price, int stock){
        return productRepository.updateProductById(id,price,stock);
    }
    public List<Product> getAllProducts(){
        return productRepository.getAllProductList();
    }
    public void deleteProductById(int id){
        productRepository.deleteProductByID(id);
    }

}
