package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Calculations.Calculation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CalculationsTableModel extends AbstractTableModel {

    private List<Calculation> calculationList;

    public CalculationsTableModel(List<Calculation> calculationList) {
        super();
        this.calculationList = calculationList;
    }

    @Override
    public int getRowCount() {
        return calculationList.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return int.class;
            case 1:
                return Integer.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            case 6:
                return Double.class;
            case 7:
                return Double.class;
            case 8:
                return Double.class;
            case 9:
                return Double.class;
            case 10:
                return String.class;
            default:
                return null;
        }
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
                return false;
            case 4:
                return false;
            case 5:
                return false;
            case 6:
                return false;
            case 7:
                return false;
            case 8:
                return false;
            case 9:
                return false;
            case 10:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Calculation calculation = calculationList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                calculation.setOrderId((int)aValue);
                break;
            case 2:
                calculation.setMass((double)aValue);
                break;
            case 9:
                calculation.setNote((String)aValue);
                break;
        }
        this.fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return calculationList.get(rowIndex).getOrderId();
            case 2:
                return calculationList.get(rowIndex).getMass();
            case 3:
                return calculationList.get(rowIndex).getMetalPrice();
            case 4:
                return calculationList.get(rowIndex).getMaterialPrice();
            case 5:
                return calculationList.get(rowIndex).getPaintPrice();
            case 6:
                return calculationList.get(rowIndex).getZincPrice();
            case 7:
                return calculationList.get(rowIndex).getWorkPrice();
            case 8:
                return calculationList.get(rowIndex).getDeliveryPrice();
            case 9:
                return calculationList.get(rowIndex).getTotalPrice();
            case 10:
                return calculationList.get(rowIndex).getNote();
            case 11:
                return calculationList.get(rowIndex).getId();
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
                return "Номер КМД";
            case 2:
                return "Масса";
            case 3:
                return "Металл, без НДС";
            case 4:
                return "Материал, без НДС";
            case 5:
                return "Краска, без НДС";
            case 6:
                return "Цинк, без НДС";
            case 7:
                return "Работа, без НДС";
            case 8:
                return "Доставка, без НДС";
            case 9:
                return "Итого с НДС";
            case 10:
                return "Примечание";
            default:
                return "Other Column";
        }
    }
}
