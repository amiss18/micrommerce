package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {


    @OrderBy(value = "id")
    List<Product> findByPriceGreaterThan(int price);


    @Query("SELECT id, name, price FROM Product p WHERE p.price > :prix")
    List<Product>  chercherUnProduitCher(@Param("prix") int prix);

    List<Product>  findByOrderByNameAsc(); //findByOrderByNameNameAsc();

    /*
    List<Product> findAll();

    Product findById( int id );

    Product save( Product product );

    int updateProduct( int id, Product product );
    */
}
