package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CostHumanTableModel extends AbstractTableModel {

    private List<Cost> costList = new ArrayList<>();
    private int columnCount = 5;

    private final String[] columnNames = {"№", "Наименование затраты", "Тип затраты", "Расценка","Единица измерения", "Итого"};

    public CostHumanTableModel(List<Cost> costList) {
        super();

        /* TODO сделать нормальный фильтр и отдельную функцию! */
            for (Cost cost: costList) {
                if (cost.getCostType() == CostConstants.CostType.Human) {
                    this.costList.add(cost);
                }
            }
    }

    @Override
    public int getRowCount() {
        return costList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //return super.getColumnClass(columnIndex);
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return CostConstants.CostType.class;
            case 4:
                return CostConstants.Unit.class;
            case 3:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 3:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cost cost = costList.get(rowIndex);
        if (columnIndex == 3) {
            cost.setPrice((double) aValue);
        }
        //Оповещает слушателей. Обязательно нужно!
        this.fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return costList.get(rowIndex).getCostName();
            case 2:
                return costList.get(rowIndex).getCostType().getName();
            case 4:
                return costList.get(rowIndex).getCostUnit().getName();
            case 3:
                return costList.get(rowIndex).getPrice();
            case 5:
                return costList.get(rowIndex).getId();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
            return columnNames[column];
    }

    @Override
    public String toString() {
        return "CostHumanTableModel{" +
                "costList=" + costList +
                ", columnCount=" + columnCount +
                '}';
    }

    public void updateData(List<Cost> costList){
        this.costList.clear();
        for (Cost cost: costList) {
            if (cost.getCostType() == CostConstants.CostType.Human) {
                this.costList.add(cost);
            }
        }
    }
}
