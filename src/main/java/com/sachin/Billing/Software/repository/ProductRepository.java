package com.sachin.Billing.Software.repository;

import com.sachin.Billing.Software.Entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> list = new ArrayList<>();
    //add product
    public Product addProduct(Product product){
        list.add(product);
        return product;
    }
    public Product getProductById(int id){
        return  list.stream().filter(p-> p.getId()==id).findFirst().orElse(null);
    }
    public Product updateProductById(int id, double price , int stock){
        Product currProduct = getProductById(id);
        if(currProduct!=null){
            currProduct.setPrice(price);
            currProduct.setStockQuantity(stock);
        }
        return currProduct;
    }
    public List<Product> getAllProductList(){
        return list;
    }
    public boolean deleteProductByID(int id){
        return list.removeIf(p->p.getId()==id);
    }
}
