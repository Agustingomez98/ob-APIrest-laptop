package com.example.obrestdatajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Laptops")
public class Laptop {

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private Integer precio;
    private Boolean stock;

    //constructores

    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, Integer precio, Boolean stock) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }
    //Getter & Setter

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }
}

