package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CostMaterialsTableModel extends AbstractTableModel {

    private List<Cost> costList = new ArrayList<>();

    public CostMaterialsTableModel(List<Cost> costList) {
        super();
        for (Cost cost: costList) {
            if (cost.getCostType() != CostConstants.CostType.Human) {
                this.costList.add(cost);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            return true;
        }
        return false;
    }

    @Override
    public int getRowCount() {
        return costList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
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
        switch (column) {
            case 0:
                return "№";
            case 1:
                return "Наименование затраты";
            case 2:
                return "Тип затраты";
            case 4:
                return "Единица измерения";
            case 3:
                return "Расценка без НДС";
            case 5:
                return "Итого без НДС";
            default:
                return "Other Column";
        }
    }

    public void updateData(List<Cost> costList){
        this.costList.clear();
        for (Cost cost: costList) {
            if (cost.getCostType() != CostConstants.CostType.Human) {
                this.costList.add(cost);
            }
        }
    }
}
