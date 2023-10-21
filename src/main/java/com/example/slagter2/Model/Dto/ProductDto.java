package com.example.slagter2.Model.Dto;

import com.example.slagter2.Model.Animal;

public class ProductDto {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    private String name;
    private double price;

    private Animal animal;

}
