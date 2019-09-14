package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping( value = "/Produits")
    public MappingJacksonValue productsList( ) {

        List<Product> products =productDao.findAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("purchasingPrice");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("myDynamicFilter", filter);

        MappingJacksonValue productsFilter = new MappingJacksonValue(products);

        productsFilter.setFilters(listDeNosFiltres);
        return productsFilter;
    }

    @GetMapping("/Produits/{id}")
    public Product afficherUnPorduit( @PathVariable("id") int id ){
        return  productDao.findById(id);
    }

    @PostMapping(value = "/Produits")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product ){
      Product productAdded =  productDao.save( product );



        if( productAdded == null )
            return ResponseEntity.noContent().build();
         // return ResponseEntity.noContent().build();


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created( location ).build();
      //  return new ResponseEntity<Product>(productAdded, HttpStatus.CREATED);
    }

    @PutMapping(value = "/Produits/{id}")
    public void updateProduct( @PathVariable int id, @RequestBody Product product ) {
        int productUpdate = productDao.updateProduct( id, product );

    }
}
