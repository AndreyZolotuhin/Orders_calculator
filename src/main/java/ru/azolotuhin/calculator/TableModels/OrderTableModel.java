package ru.azolotuhin.calculator.TableModels;

import ru.azolotuhin.calculator.Orders.Customer;
import ru.azolotuhin.calculator.Orders.Order;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

    private List<Order> orderList;

    public OrderTableModel(List<Order> orderList) {
        super();
        this.orderList = orderList;
    }

    @Override
    public int getRowCount() {
        return orderList.size();
    }

    @Override
    public int getColumnCount() {
        return 15;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Customer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Double.class;
            case 5:
                return Date.class;
            case 6:
                return String.class;
            case 7:
                return Double.class;
            case 8:
                return Double.class;
            case 9:
                return Double.class;
            case 10:
                return Double.class;
            case 11:
                return Double.class;
            case 12:
                return Double.class;
            case 13:
                return String.class;
            case 14:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
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
            case 7:
                return true;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return true;
            case 12:
                return false;
            case 13:
                return true;
            case 14:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Order order = orderList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                order.setCustomer((Customer)aValue);
                break;
            case 2:
                order.setOrderNumber((String)aValue);
                break;
            case 3:
                order.setContract((String)aValue);
                break;
            case 4:
                order.setMass((Double)aValue);
                break;
            case 5:
                order.setDate((Date)aValue);
                break;
            case 6:
                order.setDetailsNumber((String)(aValue));
                break;
            case 7:
                order.setMetalPrice((Double)(aValue));
                order.setTotalPrice(countSum(rowIndex));
                break;
            case 8:
                order.setPaintPrice((Double)(aValue));
                order.setTotalPrice(countSum(rowIndex));
                break;
            case 9:
                order.setZincPrice((Double)(aValue));
                order.setTotalPrice(countSum(rowIndex));
                break;
            case 10:
                order.setWorkPrice((Double)(aValue));
                order.setTotalPrice(countSum(rowIndex));
                break;
            case 11:
                order.setDeliveryPrice((Double)(aValue));
                order.setTotalPrice(countSum(rowIndex));
                break;
            case 13:
                order.setNote((String)(aValue));
                break;
            case 14:
                order.setTolling((Boolean)(aValue));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + columnIndex);
        }
        this.fireTableCellUpdated(rowIndex,columnIndex);
        this.fireTableCellUpdated(rowIndex,12);

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return orderList.get(rowIndex).getCustomer();
            case 2:
                return orderList.get(rowIndex).getOrderNumber();
            case 3:
                return orderList.get(rowIndex).getContract();
            case 4:
                return orderList.get(rowIndex).getMass();
            case 5:
                return orderList.get(rowIndex).getDate();
            case 6:
                return orderList.get(rowIndex).getDetailsNumber();
            case 7:
                return orderList.get(rowIndex).getMetalPrice();
            case 8:
                return orderList.get(rowIndex).getPaintPrice();
            case 9:
                return orderList.get(rowIndex).getZincPrice();
            case 10:
                return orderList.get(rowIndex).getWorkPrice();
            case 11:
                return orderList.get(rowIndex).getDeliveryPrice();
            case 12:
                return orderList.get(rowIndex).getTotalPrice();
            case 13:
                return orderList.get(rowIndex).getNote();
            case 14:
                return orderList.get(rowIndex).isTolling();
            case 15:
                return orderList.get(rowIndex).getId();
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
                return "Заказчик";
            case 2:
                return "Распоряжение";
            case 3:
                return "Договор";
            case 4:
                return "Масса";
            case 5:
                return "Дата";
            case 6:
                return "Номер КМД";
            case 7:
                return "Металл";
            case 8:
                return "Краска";
            case 9:
                return "Цинк";
            case 10:
                return "Работа";
            case 11:
                return "Доставка";
            case 12:
                return "Итого с НДС";
            case 13:
                return "Примечание";
            case 14:
                return "Давальческий";
            default:
                return "Other Column";
        }
    }
// Вычисляем сумму согласно введённых значений (в целом можно сделать и при добавлении в лист)
    private Double countSum(int rowIndex){
        return  (Double)(getValueAt(rowIndex,7)) + (Double)(getValueAt(rowIndex,8))
                + (Double)(getValueAt(rowIndex,9))
                + (Double)(getValueAt(rowIndex,10))
                + (Double)(getValueAt(rowIndex,11));
    }
}
