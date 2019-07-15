package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Metal.MetalConstants;
import ru.azolotuhin.calculator.Metal.Model.MetalBeam;
import ru.azolotuhin.calculator.Metal.Model.MetalChannel;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;
import ru.azolotuhin.calculator.Metal.Model.MetalSheet;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

class CostMetalTableModel extends AbstractTableModel {

    private List<MetalElement> metalList = new ArrayList<>();

    public CostMetalTableModel(List<MetalElement> metalList) {
        super();
        this.metalList.addAll(metalList);
    }

    @Override
    public int getRowCount() {
        return metalList.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return MetalConstants.metalType.class;
            case 2:
                return MetalConstants.steelType.class;
            case 3:
                return Integer.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            case 6:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        MetalElement metalElement = metalList.get(rowIndex);

        //Оповещает слушателей. Обязательно нужно!
        this.fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return metalList.get(rowIndex).getId();
            case 1:
                return metalList.get(rowIndex).getMetalType();
            case 2:
                return metalList.get(rowIndex).getSteelType();
            case 3:{
                if (metalList.get(rowIndex) instanceof MetalSheet)
                    return ((MetalSheet)metalList.get(rowIndex)).getSheetThickness();
                else if (metalList.get(rowIndex) instanceof MetalBeam)
                    return ((MetalBeam)metalList.get(rowIndex)).getBeamNumber();
                else if (metalList.get(rowIndex) instanceof MetalChannel)
                    return ((MetalChannel)metalList.get(rowIndex)).getChannelNumber();
            }
            case 4:
                return metalList.get(rowIndex).getMetalSquare();
            case 5:
                return metalList.get(rowIndex).getMetalWeight();
            case 6:
                return metalList.get(rowIndex).getStandart();
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
                return "Тип проката";
            case 2:
                return "Марка стали";
            case 3:
                return "Размер";
            case 4:
                return "Единичная площадь";
            case 5:
                return "Единичная масса";
            case 6:
                return "Стандарт";
            default:
                return "Other Column";
        }
    }

    public void updateData(List<Cost> costList){
        this.metalList.clear();
        /*for (Cost cost: costList) {
            if (cost.getCostType() == CostConstants.CostType.Material) {
                this.metalList.add();
            }
        }*/
    }
}
