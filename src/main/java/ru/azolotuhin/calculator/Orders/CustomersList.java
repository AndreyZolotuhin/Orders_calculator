package ru.azolotuhin.calculator.Orders;

import ru.azolotuhin.calculator.Data.StockExchangeDB;

import java.util.ArrayList;
import java.util.List;

public class CustomersList {

    private List<Customer> customerList;

    private static CustomersList instance;

    public static CustomersList getInstance() {
        if (instance == null) {
            instance = new CustomersList();
        }
        return instance;
    }

    private CustomersList() {
        customerList = new ArrayList<>();
        customerList.addAll(StockExchangeDB.getInstance().readCustomersList());
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void addCustomer(Customer customer){
        this.customerList.add(customer);
        saveCustomerListToDb();
    }

    public void deleteCustomer(int idx){
        this.customerList.remove(idx);
        saveCustomerListToDb();
    }

    public void saveCustomerListToDb(){
        try{
            StockExchangeDB.getInstance().writeCustomerList(this.customerList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
