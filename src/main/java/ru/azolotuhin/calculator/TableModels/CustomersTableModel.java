package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Orders.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CustomersTableModel extends AbstractTableModel {

    private List<Customer> customersList;

    public CustomersTableModel(List<Customer> customersList) {
        super();
        this.customersList = customersList;
    }

    @Override
    public int getRowCount() {
        return customersList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
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
        Customer customer = customersList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                customer.setName((String)aValue);
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
                return customersList.get(rowIndex).getName();
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
                return "Наименование";
            default:
                return "Other Column";
        }
    }
}
