package ru.azolotuhin.calculator.Calculations;

import ru.azolotuhin.calculator.Data.StockExchangeDB;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

    private static Calculations instance;
    private List<Calculation> metalCalculationList;

    private Calculations() {
        try{
            metalCalculationList = StockExchangeDB.getInstance().readCalculationList();
            System.out.println("Создался лист калькуляций");
        }
        catch (Exception e){
            metalCalculationList = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public static Calculations getInstance() {
        if (instance == null) {
            instance = new Calculations();
        }
        return instance;
    }

    public List<Calculation> getMetalCalculationList() {
        return metalCalculationList;
    }

    public void addCalculation (Calculation calculation){
        if (calculation.getId() == 0){
            calculation.setId(generateCalculationId());
        }
        this.metalCalculationList.add(calculation);
        saveCalculationListToDb();
    }

    public void deleteCalculationById(int id){
        this.metalCalculationList.remove(searchCalculationById(id));
        saveCalculationListToDb();
    }

    private Calculation searchCalculationById(int id){
        return (this.metalCalculationList.stream().anyMatch(p->p.getId() == id))?this.metalCalculationList.stream().filter(p->p.getId() == id).findFirst().get():null;
    }

    public void saveCalculationListToDb(){
        try{
            StockExchangeDB.getInstance().writeCalculationList(this.metalCalculationList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private int generateCalculationId() {
        int calculationId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(searchCalculationById(calculationId) != null) {
            calculationId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return calculationId;
    }
}
