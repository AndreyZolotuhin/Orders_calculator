package ru.azolotuhin.calculator.Metal;

import ru.azolotuhin.calculator.Data.StockExchangeDB;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;

import java.util.ArrayList;
import java.util.List;

public class MetalSortList{

    private static MetalSortList instance;

    private List<MetalElement> metalSortList;

    private MetalSortList() {

        try {
            metalSortList = StockExchangeDB.getInstance().readMetal();
        } catch (Exception e){
            e.printStackTrace();
            metalSortList = new ArrayList<>();
        }
    }

    public static MetalSortList getInstance() {
        if (instance == null) {
            instance = new MetalSortList();
        }
        return instance;
    }

    public List<MetalElement> getMetalSortList() {
        return metalSortList;
    }
    }