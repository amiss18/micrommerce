package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class ProductDaoImpl {//implements  ProductDao {

    public static List<Product>products=new ArrayList<>();

    static {
        products.add(new Product(1, new String("Ordinateur portable"), 350, 250));
        products.add(new Product(2, new String("Aspirateur Robot"), 500, 400));
        products.add(new Product(3, new String("Table de Ping Pong"), 750, 700));

    }
/*
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id )
                .findFirst().orElse(null);
    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public int updateProduct(int id, Product product) {
        if( findById( id ) == null ) return  0;
        int index = products.indexOf(product);
        products.set(index, product);
        return 1;

    }
*/

}
