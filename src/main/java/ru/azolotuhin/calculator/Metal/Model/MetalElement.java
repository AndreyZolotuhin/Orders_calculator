package ru.azolotuhin.calculator.Metal.Model;

import ru.azolotuhin.calculator.Metal.MetalConstants;

public abstract class MetalElement {

    private int id;
    private MetalConstants.metalType metalType;
    private MetalConstants.steelType steelType;
    private double metalWeight;
    private double metalSquare;
    private String standart;

    public MetalConstants.metalType getMetalType() {
        return metalType;
    }

    public MetalConstants.steelType getSteelType() {
        return steelType;
    }

    public double getMetalWeight() {
        return metalWeight;
    }

    public double getMetalSquare() {
        return metalSquare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStandart() {
        return standart;
    }

    public void setStandart(String standart) {
        this.standart = standart;
    }

    MetalElement(int id, MetalConstants.metalType metalType, MetalConstants.steelType steelType, double metalWeight, double metalSquare, String standart) {
        this.id = id;
        this.metalType = metalType;
        this.steelType = steelType;
        this.metalWeight = metalWeight;
        this.metalSquare = metalSquare;
        this.standart = standart;
    }
}
