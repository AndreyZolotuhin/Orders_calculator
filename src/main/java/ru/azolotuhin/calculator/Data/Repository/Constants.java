package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Calculations.Calculation;
import ru.azolotuhin.calculator.Data.TResultHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Constants extends BaseTable implements TableOperations{

    private final static String SELECT = "SELECT * FROM steel.constants";
    private static final String SELECT_ONE = "SELECT * FROM steel.constants WHERE id=?";
    private static final String INSERT = "INSERT INTO steel.constants VALUES (?,?)";
    private static final String UPDATE = "UPDATE steel.constants SET type =?, value=?  WHERE type =?";
    private static final String DELETE = "DELETE FROM steel.constants WHERE value=?";
    private static final String DELETE_ALL = "";

    private Map<String, Double> constantsMap;

    {
        constantsMap = new HashMap<>();
    }

    public Map<String, Double> getConstantsMap(){
        return constantsMap;
    }

    public Constants() throws SQLException {
        super("Constants");
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(DELETE);
        Map.Entry<String,Double> pair = (Map.Entry<String,Double>)objectToDelete;
        preparedStatement.setString(1, pair.getKey());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public int update(Object objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(INSERT);
        Map.Entry<String,Double> pair = (Map.Entry<String,Double>)objectToUpdate;

        preparedStatement.setString(1,pair.getKey());
        preparedStatement.setDouble(2,pair.getValue());
        preparedStatement.setString(3,pair.getKey());
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
        Map.Entry<String,Double> pair = (Map.Entry<String,Double>)objectToInsert;

        preparedStatement.setString(1,pair.getKey());
        preparedStatement.setDouble(2,pair.getValue());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void readAll() throws SQLException {
        constantsMap.clear();
        constantsMap.putAll(executeSqlStatementQuery(SELECT, resultSet -> {
            Map<String, Double> map = new HashMap<>();
            while (resultSet.next()){
                map.put(resultSet.getString("type"),resultSet.getDouble("value"));
            }
            return map;
        }));
    }
}

