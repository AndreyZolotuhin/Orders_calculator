package ru.azolotuhin.calculator.Orders;

import ru.azolotuhin.calculator.Data.StockExchangeDB;

import java.util.ArrayList;
import java.util.List;

public class OrderList {

    private static OrderList instance;

    private List<Order> metalOrderList;

    private OrderList() {
        try {
            metalOrderList = StockExchangeDB.getInstance().readOrderList();
        }
        catch (Exception e){
            metalOrderList = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public static OrderList getInstance() {
        if (instance == null) {
            instance = new OrderList();
        }
        return instance;
    }

    public List<Order> getMetalOrderList() {
        return metalOrderList;
    }

    public void addOrder(Order order){
        if (order.getId() == 0){
            order.setId(generateOrderId());
        }
        this.metalOrderList.add(order);
        saveOrderListToDb();
    }

    public void deleteOrderBuId(int id){
        this.metalOrderList.remove(searchOrderById(id));
        saveOrderListToDb();
    }

    private Order searchOrderById(int id){
        return (this.metalOrderList.stream().anyMatch(p->p.getId() == id))?this.metalOrderList.stream().filter(p->p.getId() == id).findFirst().get():null;
    }

    public void saveOrderListToDb(){
        try{
            StockExchangeDB.getInstance().writeOrderList(this.metalOrderList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private int generateOrderId() {
        int orderId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(searchOrderById(orderId) != null) {
            orderId = (int)Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return orderId;
    }
}



