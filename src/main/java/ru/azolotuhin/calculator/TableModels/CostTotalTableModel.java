package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Costs.TotalCost;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

public class CostTotalTableModel extends AbstractTableModel {

    private List<TotalCost> costList;

    public CostTotalTableModel(List<TotalCost> costList) {
        super();
        this.costList = costList;
    }

    @Override
    public int getRowCount() {
        return costList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return costList.get(rowIndex).getName();
            case 2:
                return new BigDecimal(costList.get(rowIndex).getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
            default:
                return "";
        }
    }
    //DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "№";
            case 1:
                return "Наименование затраты";
            case 2:
                return "Итого";
            default:
                return "Other Column";
        }
    }
}
