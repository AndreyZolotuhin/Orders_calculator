package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Costs.Cost;
import ru.azolotuhin.calculator.Costs.CostConstants.CostType;
import ru.azolotuhin.calculator.Data.TResultHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.azolotuhin.calculator.Costs.CostConstants.*;

public class Costs extends BaseTable implements TableOperations {

    private final static String SELECT = "SELECT * from steel.costs ORDER BY constructionType,costType,defaultUse";
    private static final String SELECT_ONE = "SELECT * FROM from steel.costs WHERE id=?";
    private static final String INSERT = "INSERT INTO steel.costs VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE steel.costs SET constructionType=?, costName=?, costUnit=?, costType=?, price=?, defaultUse=?  WHERE id =?";
    private static final String DELETE = "DELETE FROM steel.costs WHERE id=?";
    private static final String DELETE_ALL = "DELETE FROM steel.costs";

    private List<Cost> costList;

    {
        costList = new ArrayList<>();
    }

    public List<Cost> getCostList() {
        return costList;
    }

    public Costs() throws SQLException {
        super("Затраты");
    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public int update(Object objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(UPDATE);
        Cost cost = (Cost)objectToUpdate;

        preparedStatement.setString(1,cost.getConstructionType().toString());
        preparedStatement.setString(2,cost.getCostName());
        preparedStatement.setString(3,cost.getCostUnit().toString());
        preparedStatement.setString(4,cost.getCostType().toString());
        preparedStatement.setDouble(5,cost.getPrice());
        preparedStatement.setBoolean(6,cost.isDefaultUse());

        preparedStatement.setInt(7,cost.getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(DELETE);
        preparedStatement.setInt(1,((Cost)objectToDelete).getId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public void insert(Object objectToInsert) throws SQLException {
        PreparedStatement preparedStatement = getSqlStatementWithParameter(INSERT);
        Cost cost = (Cost)objectToInsert;

        preparedStatement.setString(1,cost.getConstructionType().toString());
        preparedStatement.setString(2,cost.getCostName());
        preparedStatement.setString(3,cost.getCostUnit().toString());
        preparedStatement.setString(4,cost.getCostType().toString());
        preparedStatement.setDouble(5,cost.getPrice());
        preparedStatement.setBoolean(6,cost.isDefaultUse());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

     @Override
    public void deleteAll() throws SQLException {
        executeSqlStatementUpdate(DELETE_ALL);
    }

    @Override
    public void readAll() throws SQLException {
        costList.clear();
        costList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends Cost>>) resultSet -> {
            List<Cost> list= new ArrayList<>();
            while (resultSet.next()){
                CostType costType = returnCostType(resultSet.getString(5));
                Cost costToAdd = new Cost(resultSet.getInt(1),(resultSet.getString(2).equals("Heavy"))? ConstructionType.Heavy: ConstructionType.Light,
                        resultSet.getString(3),(resultSet.getString(4).equals("RubToKilo"))? Unit.RubToKilo: Unit.Percent,
                        costType,resultSet.getDouble(6),
                        resultSet.getBoolean(7));
                list.add(costToAdd);
            }
            return list;
        }));
    }
    CostType returnCostType(String string){
            switch (string){
                case "Human": return CostType.Human;
                case "Material": return CostType.Material;
                case "Metal": return CostType.Metal;
                case "Delivery": return CostType.Delivery;
                case "Zinc": return CostType.Zinc;
                case "Paint": return CostType.Paint;
                default: return CostType.Human;
            }
        }
}