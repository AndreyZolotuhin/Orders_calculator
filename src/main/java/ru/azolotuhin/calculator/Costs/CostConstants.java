//Константы и перечисления. Справочник.
package ru.azolotuhin.calculator.Costs;

import ru.azolotuhin.calculator.Data.StockExchangeDB;

import java.util.Map;

public class CostConstants {

    private static final Map<String, Double> constantsMap = StockExchangeDB.getInstance().readConstants();

   public enum Unit{
        RubToKilo("руб/кг"),
        Percent("%");

        private String name;
        Unit(String name){
            this.name = name;
        }
        public String getName(){ return name;}
    }

    public enum CostType{
        Human("Трудозатраты"),
        Material("Материал"),
        Metal("Металл"),
        Paint("Краска"),
        Zinc("Цинк"),
        Delivery("Доставка"),
        OverHead("Накладные");

        private String name;
        CostType(String name){
         this.name = name;
        }
        public String getName(){ return name;}
    }

    public enum ConstructionType{
       Light ("Лёгкие"),
       Heavy("Тяжелые");

       private String name;
       ConstructionType(String name){
      this.name = name;
     }
     public String getName(){ return name;}

    }
    private static final double factorySquare = constantsMap.get("factorySquare");

    public static final double percentTax = constantsMap.get("percentTax");
    public static final double percentVAT = constantsMap.get("percentVAT");
    //Зарплата доп
    public static final double amountSecondaryWorkers = constantsMap.get("amountSecondaryWorkers"); //ЦЛК,ЦТК и склад
    public static final double amountEngineerSalary = constantsMap.get("amountEngineerSalary");
    private static final double amountMaintenanceSalary = constantsMap.get("amountMaintenanceSalary");//рабочие эксплуатация + РМУ

    //Вода, Элекчтричество, Отопление и тд.
    private static final double amountEnergy = constantsMap.get("amountEnergy");//никоноров
    private static final double amountWeldingCompound = constantsMap.get("amountWeldingCompound"); //никоноров
    private static final double amountFuel = constantsMap.get("amountFuel"); //никоноров
    private static final double amountHeating = constantsMap.get("amountHeating");//никоноров
    private static final double amountOxygen = constantsMap.get("amountOxygen");//никоноров
    private static final double amountWater = constantsMap.get("amountWater");
    private static final double amountGarbage = constantsMap.get("amountGarbage");
    private static final double amountPhone = constantsMap.get("amountPhone");

    //Всё для людей
    private static final double amountTransport = constantsMap.get("amountTransport");
    private static final double amountCoveralls = constantsMap.get("amountCoveralls");
    private static final double amountLearning = constantsMap.get("amountLearning");

    //Оборудование
    private static final double amountRepairing = constantsMap.get("amountRepairing");
    private static final double amountOfficeSupplies = constantsMap.get("amountOfficeSupplies");
    private static final double amountHandTools = constantsMap.get("amountHandTools");

    //Охрана, налоги
    private static final double amountProtection = constantsMap.get("amountProtection");

    // Доделать

    private static final double amountOther = constantsMap.get("amountOther");

    public static final double amountMaintenance = amountEnergy + amountWeldingCompound + amountFuel + amountHeating + amountOxygen + amountWater +
            amountGarbage + amountPhone + amountTransport + amountCoveralls + amountLearning + amountRepairing + amountOfficeSupplies + amountOther +
            amountMaintenanceSalary + amountMaintenanceSalary * (percentTax/100) + amountHandTools + amountProtection;

    public static final double amountRent = constantsMap.get("amountRentPrice") * factorySquare;

//обновил по ФОТ мая 2019
}
