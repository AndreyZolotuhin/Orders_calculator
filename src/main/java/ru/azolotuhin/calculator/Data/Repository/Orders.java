package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.TResultHandler;
import ru.azolotuhin.calculator.Orders.Customer;
import ru.azolotuhin.calculator.Orders.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Orders extends BaseTable implements TableOperations{

    private final static String SELECT = "SELECT * FROM steel.orders ORDER BY order_number";
    private static final String SELECT_ONE = "SELECT * FROM steel.orders WHERE id=? ORDER BY order_number";
    private static final String INSERT = "INSERT INTO steel.orders VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE steel.orders SET Customer_id=?,Order_Number=?,Contract=?," +
            "Mass=?,Date=?,Details_Number=?, metal_price=?, paint_price=?, zinc_price=?, work_price=?, delivery_price=?, total_price=?," +
            "note=?, Tolling=? WHERE id =?";
    private static final String DELETE = "DELETE FROM steel.orders WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM steel.orders";


    private List<Order> orderList;

    {
        orderList = new ArrayList<>();
    }


    public List<Order> getOrderList() {
        return orderList;
    }

    public Orders() throws SQLException {
        super("Orders");
    }

    @Override
    public int update(Object objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(UPDATE);
        Order orderToUpdate = (Order)objectToUpdate;
        int customerId = 0;
        try {
            Customer customer = orderToUpdate.getCustomer();
            customerId = customer.getId();
        } catch (Exception e){
            e.printStackTrace();
        }
        preparedStatement.setInt(1,customerId);
        preparedStatement.setString(2,orderToUpdate.getOrderNumber());
        preparedStatement.setString(3,orderToUpdate.getContract());
        preparedStatement.setDouble(4,orderToUpdate.getMass());
        preparedStatement.setDate(5,(java.sql.Date)orderToUpdate.getDate());
        preparedStatement.setString(6,orderToUpdate.getDetailsNumber());
        preparedStatement.setDouble(7,orderToUpdate.getMetalPrice());
        preparedStatement.setDouble(8,orderToUpdate.getPaintPrice());
        preparedStatement.setDouble(9,orderToUpdate.getZincPrice());
        preparedStatement.setDouble(10,orderToUpdate.getWorkPrice());
        preparedStatement.setDouble(11,orderToUpdate.getDeliveryPrice());
        preparedStatement.setDouble(12,orderToUpdate.getTotalPrice());
        preparedStatement.setString(13,orderToUpdate.getNote());
        preparedStatement.setBoolean(14,orderToUpdate.isTolling());
        preparedStatement.setInt(15,orderToUpdate.getId());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(DELETE);
        preparedStatement.setInt(1,((Order)objectToDelete).getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void insert(Object objectToInsert) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(INSERT);
        Order order = (Order)objectToInsert;

        int customerId = 0;
        try {
            Customer customer = order.getCustomer();
            customerId = customer.getId();
        } catch (Exception e){
            e.printStackTrace();
        }
        preparedStatement.setInt(1,customerId); //ИД КЛИЕНТА!!!
        preparedStatement.setString(2,order.getOrderNumber());
        preparedStatement.setString(3,order.getContract());
        preparedStatement.setDouble(4,order.getMass());
        preparedStatement.setDate(5,(java.sql.Date)order.getDate());
        preparedStatement.setString(6,order.getDetailsNumber());
        preparedStatement.setDouble(7,order.getMetalPrice());
        preparedStatement.setDouble(8,order.getPaintPrice());
        preparedStatement.setDouble(9,order.getZincPrice());
        preparedStatement.setDouble(10,order.getWorkPrice());
        preparedStatement.setDouble(11,order.getDeliveryPrice());
        preparedStatement.setDouble(12,order.getTotalPrice());
        preparedStatement.setString(13,order.getNote());
        preparedStatement.setBoolean(14,order.isTolling());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public void readAll() throws SQLException {
        orderList.clear();
        Customers customers = new Customers();
        customers.readAll();

        orderList.addAll(executeSqlStatementQuery(SELECT, new TResultHandler<Collection<? extends Order>>() {
            @Override
            public Collection<? extends Order> handle(ResultSet resultSet) throws SQLException {
                List<Order> list= new ArrayList<>();
                while (resultSet.next()){
                    list.add(new Order(
                            resultSet.getInt("Id"),
                            resultSet.getString("Order_Number"),
                            customers.findCustomer(resultSet.getInt("Customer_id")),
                            resultSet.getString("Contract"),
                            resultSet.getDouble("Mass"),
                            resultSet.getString("Details_Number"),
                            resultSet.getDouble("Metal_Price"),
                            resultSet.getDouble("Paint_Price"),
                            resultSet.getDouble("zinc_price"),
                            resultSet.getDouble("Work_Price"),
                            resultSet.getDouble("Delivery_Price"),
                            resultSet.getDouble("Total_Price"),
                            resultSet.getString("Note"),
                            resultSet.getBoolean("Tolling")));
                }
                return list;
            }
        }));
    }

    public void deleteAll() throws SQLException {
        executeSqlStatementUpdate(DELETE_ALL);
    }
}

