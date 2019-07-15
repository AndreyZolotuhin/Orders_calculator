//Основной класс для описания логики работы программы
package ru.azolotuhin.calculator.Calculations;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostList;
import ru.azolotuhin.calculator.Costs.TotalCost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.azolotuhin.calculator.Costs.CostConstants.*;

public class Calculator {
    //основной список для хранения текущих данных для расчета металла (две таблицы)
    private List<Cost> calculatorList;
    //основной список для хранения ткущих данных для расчета итогов (одна таблица)
    private List<TotalCost> totalCostCalculatorList;
    // Основной лист затрат (единственный экземпляр)
    private CostList costList;
    private ConstructionType constructionType;
    private double costHumanResult;
    private double costMaterialsResult;
    private double costTotalResultAfterVAT;
    private double costTotalResultBeforeVAT;
    private int constructionWeight;
    private int plantPlanPower;
    private boolean useRentAmount;

    {
        calculatorList = new ArrayList<>();
        totalCostCalculatorList = new ArrayList<>();
        costList = CostList.getInstance();
    }

    public Calculator(ConstructionType constructionType) {
        this.constructionType = constructionType;
        calculatorList.addAll(costList.getCostList(constructionType));
        updateTotalResult();
        System.out.println("Создался калькулятор!!!");
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
        calculatorList.clear();
        calculatorList.addAll(costList.getCostList(constructionType));
        updateTotalResult();
        System.out.println("Перезалился лист затрат");
    }

    public boolean isUseRentAmount() {
        return useRentAmount;
    }

    public void setUseRentAmount(boolean useRentAmount) {
        this.useRentAmount = useRentAmount;
    }

    public void setConstructionWeight(int constructionWeight) {
        this.constructionWeight = constructionWeight;
    }

    public int getConstructionWeight() {
        return constructionWeight;
    }

    public void setPlantPlanPower(int plantPlanPower) {
        this.plantPlanPower = plantPlanPower;
    }

    public List<Cost> getCalculatorList() {
        return calculatorList;
    }

    public List<TotalCost> getTotalCostCalculatorList() {
        return totalCostCalculatorList;
    }

    public void addToCostList(int index, Cost newCost){
        calculatorList.add(index,newCost);
        updateTotalResult();
    }

    public void deleteFromCostList(int id){
        calculatorList.removeAll(searchCostById(id));
        updateTotalResult();
    }

    private List<Cost> searchCostById(int id){
          return calculatorList.stream().filter(p->p.getId() == id).collect(Collectors.toList());
    }

    public void deleteAll(){
        calculatorList.clear();
        updateTotalResult();
    }

    private void updateTotalResult(){
        costHumanResult = 0;
        costMaterialsResult = 0;
        costTotalResultAfterVAT = 0;
        costTotalResultBeforeVAT = 0;
        totalCostCalculatorList.clear();

        for (Cost cost:calculatorList) {
            if (cost.getCostType() == CostType.Human){
                costHumanResult += cost.getPrice();
            }
        }
        for (Cost cost:calculatorList) {
            if (cost.getCostType() != CostType.Human){
                costMaterialsResult += cost.getPrice();
            }
        }
        totalCostCalculatorList.add(new TotalCost(CostType.Human.getName(),costHumanResult));
        totalCostCalculatorList.add(new TotalCost(CostType.Material.getName(), costMaterialsResult));

        totalCostCalculatorList.add(new TotalCost("ИТР", amountEngineerSalary /(plantPlanPower * 1000)));
        totalCostCalculatorList.add(new TotalCost("Вспомогательные рабочие", amountSecondaryWorkers/(plantPlanPower * 1000)));

        double maintenance = (amountMaintenance + (useRentAmount?amountRent:0))/(plantPlanPower * 1000);

        totalCostCalculatorList.add(new TotalCost("Эксплуатация цеха",maintenance));
        totalCostCalculatorList.add(new TotalCost("Отчисления",(costHumanResult + amountEngineerSalary /(plantPlanPower * 1000) + amountSecondaryWorkers/(plantPlanPower * 1000) )* percentTax/100));

        for (TotalCost totalCost : totalCostCalculatorList){
            costTotalResultBeforeVAT += totalCost.getAmount();
        }
        totalCostCalculatorList.add(new TotalCost("НДС 20 %",costTotalResultBeforeVAT * percentVAT/100));

        for (TotalCost totalCost : totalCostCalculatorList){
            costTotalResultAfterVAT += totalCost.getAmount();
        }
        totalCostCalculatorList.add(new TotalCost("Всего", costTotalResultAfterVAT));
    }

    public double getTotalResult(CostType costType){
        updateTotalResult();
        if (costType != CostType.Material){
            return costHumanResult;
        }
        else
            return costMaterialsResult;
    }

    public double getCostTotalResultAfterVAT() {
        return costTotalResultAfterVAT;
    }

    public double getResult(CostType costType){
        Stream<Cost> stream = calculatorList.stream().filter(p->p.getCostType() == costType);
        //первый аргумен - это тип значения. Потом бинарная операция, а потом уже операция суммирования вычислений!
        return stream.reduce(0.0,(x, y)-> x + y.getPrice(), Double::sum);
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "calculatorList=" + calculatorList +
                ", constructionType=" + constructionType +
                '}';
    }
}
