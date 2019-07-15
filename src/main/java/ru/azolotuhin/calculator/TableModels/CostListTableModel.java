package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CostListTableModel extends AbstractTableModel {

    private List<Cost> costList;
    private int columnCount;

    {
        columnCount = 7;
        costList = new ArrayList<>();
    }

    public CostListTableModel(List<Cost> costList) {
        super();
        this.costList.addAll(costList);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                return true;
            default:
                return false;
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
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return CostConstants.ConstructionType.class;
            case 2:
                return String.class;
            case 3:
                return CostConstants.CostType.class;
            case 4:
                return CostConstants.Unit.class;
            case 5:
                return Double.class;
            case 6:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Cost cost = costList.get(rowIndex);
        switch(columnIndex)
        {
            case 1: cost.setConstructionType((CostConstants.ConstructionType)aValue);
                break;
            case 2: cost.setCostName((String)aValue);
                break;
            case 3:
                cost.setCostType((CostConstants.CostType)aValue);
                break;
            case 4:
                cost.setCostUnit((CostConstants.Unit)aValue);
                break;
            case 5: cost.setPrice((double)aValue);
                break;
            case 6: cost.setDefaultUse((boolean)aValue);
                break;
        }
        //Оповещает слушателей. Обязательно нужно!
        System.out.println("Обновлено значение ячейки cost");
        this.fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return  rowIndex + 1;
            case 1:
                return costList.get(rowIndex).getConstructionType().getName();
            case 2:
                return costList.get(rowIndex).getCostName();
            case 3:
                return costList.get(rowIndex).getCostType().getName();
            case 4:
                return costList.get(rowIndex).getCostUnit().getName();
            case 5:
                return costList.get(rowIndex).getPrice();
            case 6:
                return costList.get(rowIndex).isDefaultUse();
            case 7:
                return costList.get(rowIndex).getId();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Номер";
            case 1:
                return "Тип конструкций";
            case 2:
                return "Наименование затраты";
            case 3:
                return "Тип затраты";
            case 4:
                return "Ед.изм";
            case 5:
                return "Расценка";
            case 6:
                return "По-умолчанию";
            default:
                return "Other Column";
        }
    }

    public void updateData(List<Cost> costList){
        this.costList.clear();
        this.costList.addAll(costList);
    }
}

