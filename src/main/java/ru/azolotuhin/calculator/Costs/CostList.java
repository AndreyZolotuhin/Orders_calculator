//Лист затрат, который используется при текущей работе приложения. Изменяется по ходу работы программы. Базовый кост лист изменяется только с БД.
package ru.azolotuhin.calculator.Costs;

import ru.azolotuhin.calculator.Costs.CostConstants.*;
import ru.azolotuhin.calculator.Data.StockExchangeDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class CostList {
    // "текущий" лист затрат. В него клонируем объекты из Базового листа
    private final List<Cost> costList;
    //базовый костлист делаем для того, чтобы при изменение объектов КОСТ, они не менялись везде. Используем в редакторе.
    private List<Cost> baseCostList;
    private static CostList instance;

    {
        costList = new ArrayList<>();
        baseCostList = new ArrayList<>();
    }

    public static CostList getInstance() {
        if (instance == null) {
            instance = new CostList();
        }
        return instance;
    }

    private CostList() {
        try {
            baseCostList = readBaseCostListFromDB();
        } catch (Exception e){
            e.printStackTrace();
        }
        cloneCostList();
    }

    public List<Cost> getCostList(ConstructionType constructionType) {
        cloneCostList();
        //Пробуем Стрим АПИ. Создаём поток, потом фильруем и закидываем в результат
        return costList.stream().filter(s->(s.getConstructionType() == constructionType) && (s.isDefaultUse())).collect(Collectors.toList());
    }

    public List<Cost> getBaseCostList(ConstructionType constructionType, CostType costType) {
        //костыль
        if (costType == CostType.Human) {
            return baseCostList.stream().filter(s -> (s.getConstructionType() == constructionType) && (s.getCostType() == costType)).collect(Collectors.toList());
        }
        else {
            return baseCostList.stream().filter(s -> (s.getConstructionType() == constructionType) && (s.getCostType() != CostType.Human)).collect(Collectors.toList());
        }
    }

    public List<Cost> getBaseCostList(ConstructionType constructionType) {
        //Пробуем Стрим АПИ. Создаём поток, потом фильруем и закидываем в результат
        return baseCostList.stream().filter(s->s.getConstructionType() == constructionType).collect(Collectors.toList());
    }

    public List<Cost> getBaseCostList() {
        return baseCostList;
    }

    public void addToBaseCostList(int index,Cost costToAdd){
        if (costToAdd.getId() == 0){
            costToAdd.setId(generateCostId());
        }
        baseCostList.add(index,costToAdd);
    }

    public void deleteFromBaseCostList(int index){
        baseCostList.remove(index);
    }

    private void cloneCostList(){
        costList.clear();
        for (Cost cost : baseCostList) {
            try {
                costList.add(cost.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveBaseCostListToDB(){
        try {
            StockExchangeDB.getInstance().writeCostList(baseCostList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Cost> readBaseCostListFromDB(){
        return StockExchangeDB.getInstance().readCostList();
    }

    private Cost searchCostById(int id){
        return (this.baseCostList.stream().anyMatch(p->p.getId() == id))?this.baseCostList.stream().filter(p->p.getId() == id).findFirst().get():null;
    }

    private int generateCostId() {
        int costId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(searchCostById(costId) != null) {
            costId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return costId;
    }
}
