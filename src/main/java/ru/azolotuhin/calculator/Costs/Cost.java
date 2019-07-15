//Класс для создания самих затрат
package ru.azolotuhin.calculator.Costs;

import ru.azolotuhin.calculator.Costs.CostConstants.*;

public class Cost implements Cloneable {

    private int id;
    private ConstructionType constructionType;
    private String costName;
    private Unit costUnit;
    private CostType costType;
    private double price;
    private boolean defaultUse;

    public Cost(int id, ConstructionType constructionType, String costName, Unit costUnit, CostType costType, double price, boolean defaultUse) {
        this.id = id;
        this.constructionType = constructionType;
        this.costName = costName;
        this.costUnit = costUnit;
        this.costType = costType;
        this.price = price;
        this.defaultUse = defaultUse;
    }

    public Cost(){
        this.id = 0;
        this.constructionType = ConstructionType.Heavy;
        this.costName = " ";
        this.costUnit = Unit.RubToKilo;
        this.costType = CostType.Human;
        this.price = 0.0;
        this.defaultUse = false;
    }

    //ГЕТТЕРЫ
    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public boolean isDefaultUse() {
        return defaultUse;
    }

    public Unit getCostUnit() {
        return costUnit;
    }

    public CostType getCostType() {
        return costType;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getCostName() {
        return costName;
    }

    //СЕТТЕРЫ
    public void setDefaultUse(boolean defaultUse) {
        this.defaultUse = defaultUse;
    }

    public void setCostName(String costName){
        this.costName = costName;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public void setCostUnit(Unit costUnit) {
        this.costUnit = costUnit;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Методы
    @Override
    public String toString() {
        return  costType.getName() + " | " +  costName+ " | " + price + " | " + costUnit.getName();
    }

    @Override
    public Cost clone() throws CloneNotSupportedException {
        return (Cost)super.clone();
    }
}

