package com.ecommerce.microcommerce.model;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonFilter("myDynamicFilter") //bean accepte le filtre dynamique
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String name;
    private int price;

   // @JsonIgnore
    private int purchasingPrice;



    public Product(int id, String name, int price, int buyingPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.purchasingPrice = buyingPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(int purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj) return  true;
        Product p=(Product)obj;
        if( id == p.getId() )
            return true;
        return false;
    }
}
