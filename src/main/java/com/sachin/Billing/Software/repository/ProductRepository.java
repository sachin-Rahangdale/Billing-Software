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
        String sql = "select (id,name,price,gst_percentage,stock_quantity) from Product where id = ?";
        return jdbcTemplate.query(sql, productRowMapper, id)
                .stream()
                .findFirst()
                .orElse(null);
    }
    public Product updateProductById(int id, double price, int stock){
        String sql = "UPDATE product SET price=?, stock_quantity=? WHERE id=?";

        int rows = jdbcTemplate.update(sql, price, stock, id);

        if (rows == 0) {
            return null;
        }
        return getProductById(id);
    }

    public List<Product> getAllProductList(){
        String sql = "SELECT id, name, price, gst_percentage, stock_quantity FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public boolean deleteProductByID(int id){
        String sql = "DELETE FROM product WHERE id=?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}
