package ru.azolotuhin.calculator.Costs;

public class TotalCost {
    private String name;
    private double amount;

    public TotalCost(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}
