package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exceptions.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(description="Gestion des produits en stocks")
@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @ApiOperation(value = "Affiche tous les produits")
    @RequestMapping( value = "/Produits", method = RequestMethod.GET)
    public List<Product> productsList( ) {
        return productDao.findAll();
    }

    @ApiOperation(value = "Récupère un produit à partir de son id")
    @GetMapping("/Produits/{id}")
    public Product afficherUnPorduit( @PathVariable("id") int id )  {
       // Optional<Product> product = productDao.findById(id);
        Optional<Product> product=productDao.findById(id);
        if ( product.isEmpty() )
           throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE.");
        return product.get();

    }

    @ApiOperation(value = "Ajout  d'un produit")
    @PostMapping(value = "/Produits")
    public ResponseEntity<Product> saveProduct( @Valid @RequestBody Product product ){
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

    @ApiOperation(value = "Edition d'un produit")
    @PutMapping(value = "/Produits/{id}")
    public void updateProduct( @PathVariable int id, @RequestBody Product product ) {
      //  int productUpdate = productDao.updateProduct( id, product );
        productDao.save(product);

    }

    @ApiOperation(value = "Suppression un produit")
    @DeleteMapping (value = "/Produits/{id}")
    public void delProduct(@PathVariable int id) {
        productDao.deleteById(id);
    }

    @ApiOperation(value = "Récupère les produits dont les prix sont superieurs à")
    @GetMapping(value = "/Produits/price/{price}")
    public List<Product> getProductsPriceGt(@PathVariable int price) {
        return productDao.findByPriceGreaterThan(price);
    }

    @ApiOperation(value = "Affiche la marge de chaque produit")
    @GetMapping("/teste/marge")
    public Map<Product, Integer> calculerMargeProduit(){
        List<Product> products=productDao.findAll();
        Map<Product, Integer> map=new HashMap<>();
        for ( Product product: products ){
            Integer marge = product.getPrice() - product.getPurchasingPrice();
            map.put( product, marge);
        }
        return map;
    }


    @ApiOperation(value = "Tri des produits par ordre alphabétique")
    @RequestMapping( value = "/teste/tri")
    public List<Product> trierProduitsParOrdreAlphabetique( ) {
        return productDao.findByOrderByNameAsc();
    }


}
