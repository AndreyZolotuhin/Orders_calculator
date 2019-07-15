package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.TResultHandler;
import ru.azolotuhin.calculator.Orders.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Customers extends BaseTable implements TableOperations{

    private final static String SELECT = "SELECT * FROM steel.customers ORDER BY name";
    private static final String SELECT_ONE = "SELECT id, name FROM steel.customers WHERE id=?";
    private static final String INSERT = "INSERT INTO steel.customers VALUES (?)";
    private static final String UPDATE = "UPDATE steel.customers SET name =? WHERE id =?";
    private static final String DELETE = "DELETE FROM steel.customers WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM steel.customers";

    private List<Customer> customerList;

    {
        customerList = new ArrayList<>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Customers() throws SQLException {
        super("Customers");
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(DELETE);
        preparedStatement.setInt(1,((Customer)objectToDelete).getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public int update(Object objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(UPDATE);
        preparedStatement.setString(1,((Customer)objectToUpdate).getName());
        preparedStatement.setInt(2,((Customer)objectToUpdate).getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void readAll() throws SQLException {
        customerList.clear();
        customerList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends Customer>>) resultSet -> {
            List<Customer> list= new ArrayList<>();
            while (resultSet.next()){
                list.add(new Customer(resultSet.getInt("id"),resultSet.getString("name")));
            }
            return list;
        }));
    }

    @Override
    public void insert(Object objectToInsert) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(INSERT);
        preparedStatement.setString(1,((Customer)objectToInsert).getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteAll() throws SQLException {
        executeSqlStatementUpdate(DELETE_ALL);
    }

    public Customer findCustomer(int idx){
        //return customerList.stream().filter(e->e.getId() == idx).findFirst().get();
        for (Customer customer: customerList){
            if (customer.getId() == idx){
                return customer;
            }
        }
        return new Customer("Unknown customer");
    }
}

