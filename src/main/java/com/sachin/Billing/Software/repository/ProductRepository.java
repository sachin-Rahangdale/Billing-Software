package com.sachin.Billing.Software.repository;

import com.mysql.cj.result.Row;
import com.sachin.Billing.Software.Entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs,rowNum)->{
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setGstPercentage(rs.getDouble("gst_percentage"));
        p.setStockQuantity(rs.getInt("stock_quantity"));
        return p;
    };

    //add product
    public Product addProduct(Product product){
       String sql = " Insert into Product (id, name,price,gst_percentage,stock_quantity) values (?,?,?,?,?)";
       jdbcTemplate.update(
               sql,
               product.getId(),
               product.getName(),
               product.getPrice(),
               product.getGstPercentage(),
               product.getStockQuantity()
       );
       return product;


    }
    public Product getProductById(int id){
        return
    }
    public Product updateProductById(int id, double price , int stock){

        return
    }
    public List<Product> getAllProductList(){
        return
    }
    public boolean deleteProductByID(int id){
        return
    }
}
