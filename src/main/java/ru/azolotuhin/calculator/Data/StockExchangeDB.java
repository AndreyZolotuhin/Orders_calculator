package ru.azolotuhin.calculator.Data;

import ru.azolotuhin.calculator.Calculations.Calculation;
import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Data.Repository.*;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;
import ru.azolotuhin.calculator.Orders.Customer;
import ru.azolotuhin.calculator.Orders.Order;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class StockExchangeDB {

    private static StockExchangeDB ourInstance;

    private MetalIbeams metalIbeams;
    private MetalChannels metalChannels;
    private MetalSheets metalSheets;
    private Costs costs;
    private Orders metalOrders;
    private Calculations metalCalculations;
    private Customers customers;
    private Constants constants;

    private static final String PATH_TO_PROPERTIES = "src\\main\\resources\\database.properties";
    private static final String DB_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection = null;

    public static StockExchangeDB getInstance() {
        if (ourInstance == null) {
            ourInstance = new StockExchangeDB();
        }
        return ourInstance;
    }

    private StockExchangeDB()  {
        try {
            //на самом деле работает и без конструктора и вызова экземпляра. Драйвер видимо тоже регестрировать необязательно
            Class.forName(DB_CLASS_NAME).getDeclaredConstructor().newInstance();
            connection = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            customers = new Customers();
            metalIbeams = new MetalIbeams();
            metalChannels = new MetalChannels();
            metalSheets = new MetalSheets();
            costs = new Costs();
            metalOrders = new Orders();
            metalCalculations = new Calculations();
            constants = new Constants();
        } catch (SQLException e) {
            System.out.println("SQL Exception " + e.getMessage());
        }
        try {
            fillTables();
            System.out.println("Tables filled");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        //дописал, чтобы каждый раз не создавать соединение.
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            try (InputStream in = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES))) {
                props.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            // на всякий случай задаём ещё со свойствами юзернейм и паролем
            System.out.println("Создалось новое соединиение");
            return DriverManager.getConnection(url, username, password);
        }else {
            return connection;
        }
    }

    private void fillTables() throws SQLException {
        metalIbeams.readAll();
        metalChannels.readAll();
        metalSheets.readAll();
        customers.readAll();
        costs.readAll();
        metalOrders.readAll();
        metalCalculations.readAll();
        constants.readAll();
    }

    //Тут работа с самими таблицами базы
    //Лист затрат
    public List<Cost> readCostList () {
        return new ArrayList<>(costs.getCostList());
    }

    public void writeCostList (List<Cost> list) throws SQLException{
        costs.readAll();
        for (Cost cost : list) {
            if (costs.update(cost) == 0){
                costs.insert(cost);
            }
        }
        //Убираем все удалённые из базы
        List<Cost> removedCosts = compareCostLists(list,costs.getCostList());
        for (Cost cost : removedCosts) {
            costs.delete(cost);
        }
    }
    //Лист калькуляций заказов
    public List<Calculation> readCalculationList(){
        return new ArrayList<>(metalCalculations.getMetalCalculationList());
    }

    public void writeCalculationList (List<Calculation> list) throws SQLException{
        metalCalculations.readAll();
        for (Calculation calculation : list) {
            if (metalCalculations.update(calculation) == 0){
               metalCalculations.insert(calculation);
            }
        }
        //Убираем все удалённые из базы
        List<Calculation> removedCalculations = compareCalculationLists(list,metalCalculations.getMetalCalculationList());
        for (Calculation calculation : removedCalculations) {
            metalCalculations.delete(calculation);
        }
    }
    //Лист заказов
    public List<Order> readOrderList() {
        return new ArrayList<>(metalOrders.getOrderList());
    }

    public void writeOrderList(List<Order> orderList) throws SQLException{
        metalOrders.readAll();
        for (Order order : orderList) {
            if (metalOrders.update(order) == 0){
                metalOrders.insert(order);
            }
        }
        //Убираем все удалённые из базы
        List<Order> removedOrders = compareOrderLists(orderList,metalOrders.getOrderList());
        System.out.println(removedOrders);
        for (Order order : removedOrders) {
            metalOrders.delete(order);
        }
    }
    //Лист клиентов
    public List<Customer> readCustomersList(){
        return new ArrayList<>(customers.getCustomerList());
    }

    public void writeCustomerList (List<Customer> list) throws SQLException {
        customers.readAll();
        for (Customer customer : list) {
            if (customers.update(customer) == 0){
                customers.insert(customer);
            }
        }
        //Убираем все удалённые из базы
        List<Customer> removedCustomers = compareCustomerLists(list,customers.getCustomerList());
        for (Customer customer : removedCustomers) {
            customers.delete(customer);
        }
    }
    //Лист сортамент
    public List<MetalElement> readMetal() {
        List<MetalElement> metalList = new ArrayList<>();
        metalList.addAll(metalIbeams.getIBeamList());
        metalList.addAll(metalChannels.getChanelList());
        metalList.addAll(metalSheets.getSheetList());
        return metalList;
    }

    //Константы
    public Map<String, Double> readConstants(){
        return new HashMap<>(constants.getConstantsMap());
    }

    //методы для сравнения списков
    private List<Customer> compareCustomerLists( List<Customer> listUser, List<Customer> listDb){
        List<Customer> removed = new ArrayList<>();
        for (Customer dbList: listDb) {
            boolean flag = false;
            for (Customer user: listUser) {
                if (dbList.getId() == user.getId()){
                    flag = true;
                }
            }
            if(!flag) {
                removed.add(dbList);
            }
        }
        return removed;
    }

    private  List<Calculation> compareCalculationLists( List<Calculation> listUser, List<Calculation> listDb){
        List<Calculation> removed = new ArrayList<>();
        for (Calculation dbList: listDb) {
            boolean flag = false;
            for (Calculation user: listUser) {
                if (dbList.getId() == user.getId()){
                    flag = true;
                }
            }
            if(!flag) {
                removed.add(dbList);
            }
        }
        return removed;
    }

    private  List<Order> compareOrderLists( List<Order> listUser, List<Order> listDb){
        List<Order> removed = new ArrayList<>();
        for (Order dbList: listDb) {
            boolean flag = false;
            for (Order user: listUser) {
                if (dbList.getId() == user.getId()){
                    flag = true;
                }
            }
            if(!flag) {
                removed.add(dbList);
            }
        }
        return removed;
    }

    private  List<Cost> compareCostLists( List<Cost> listUser, List<Cost> listDb){
        List<Cost> removed = new ArrayList<>();
        for (Cost dbList: listDb) {
            boolean flag = false;
            for (Cost cost: listUser) {
                if (dbList.getId() == cost.getId()){
                    flag = true;
                }
            }
            if(!flag) {
                removed.add(dbList);
            }
        }
        return removed;
    }
}
