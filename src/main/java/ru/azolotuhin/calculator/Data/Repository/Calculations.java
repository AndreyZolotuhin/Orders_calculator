package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Calculations.Calculation;
import ru.azolotuhin.calculator.Data.TResultHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Calculations extends BaseTable implements TableOperations{

    private final static String SELECT = "SELECT * FROM steel.calculations ORDER BY order_id";
    private static final String SELECT_ONE = "SELECT * FROM steel.calculations WHERE id=? ORDER BY order_id";
    private static final String INSERT = "INSERT INTO steel.calculations VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE steel.calculations SET order_id =?, mass=?, metal_price=?, paint_price=?, zinc_price=?, work_price=?, delivery_price=?, total_price=?, note=?, material_price=?  WHERE id =?";
    private static final String DELETE = "DELETE FROM steel.calculations WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM steel.calculations";

    private List<Calculation> metalCalculationList;

    {
        metalCalculationList = new ArrayList<>();
    }

    public List<Calculation> getMetalCalculationList() {
        return metalCalculationList;
    }

    public Calculations() throws SQLException {
        super("Calculations");
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(DELETE);
        preparedStatement.setInt(1,((Calculation)objectToDelete).getId());
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
        Calculation calculation = (Calculation)objectToUpdate;

        preparedStatement.setInt(1,calculation.getOrderId());
        preparedStatement.setDouble(2,calculation.getMass());
        preparedStatement.setDouble(3,calculation.getMetalPrice());
        preparedStatement.setDouble(4,calculation.getPaintPrice());
        preparedStatement.setDouble(5,calculation.getZincPrice());
        preparedStatement.setDouble(6,calculation.getWorkPrice());
        preparedStatement.setDouble(7,calculation.getDeliveryPrice());
        preparedStatement.setDouble(8,calculation.getTotalPrice());
        preparedStatement.setString(9,calculation.getNote());
        preparedStatement.setDouble(10,calculation.getMaterialPrice());
        preparedStatement.setInt(11,calculation.getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void deleteAll() throws SQLException {
        executeSqlStatementUpdate(DELETE_ALL);
    }

    @Override
    public void insert(Object objectToInsert) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(INSERT);
        Calculation calculation = (Calculation)objectToInsert;

        preparedStatement.setInt(1,calculation.getOrderId());
        preparedStatement.setDouble(2,calculation.getMass());
        preparedStatement.setDouble(3,calculation.getMetalPrice());
        preparedStatement.setDouble(4,calculation.getPaintPrice());
        preparedStatement.setDouble(5,calculation.getZincPrice());
        preparedStatement.setDouble(6,calculation.getWorkPrice());
        preparedStatement.setDouble(7,calculation.getDeliveryPrice());
        preparedStatement.setDouble(8,calculation.getTotalPrice());
        preparedStatement.setString(9,calculation.getNote());
        preparedStatement.setDouble(10,calculation.getMaterialPrice());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void readAll() throws SQLException {
        metalCalculationList.clear();
        metalCalculationList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends Calculation>>) resultSet1 -> {
            List<Calculation> list= new ArrayList<>();
            while (resultSet1.next()){
                list.add(new Calculation(
                        resultSet1.getInt("id"),
                        resultSet1.getInt("Order_id"),
                        resultSet1.getDouble("Mass"),
                        resultSet1.getDouble("Metal_Price"),
                        resultSet1.getDouble("Paint_Price"),
                        resultSet1.getDouble("zinc_price"),
                        resultSet1.getDouble("Work_Price"),
                        resultSet1.getDouble("Delivery_Price"),
                        resultSet1.getDouble("Total_Price"),
                        resultSet1.getString("Note"),
                        resultSet1.getDouble("material_price")));
            }
            return list;
        }));
    }
}

