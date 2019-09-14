package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> findAll();

    Product findById( int id );
   // Optional<Product> findById(int id );

    Product save( Product product );

    /**
     * mise à jour d'un produit
     * @param id
     * @param product
     * @return
     */
    int updateProduct( int id, Product product );
}
