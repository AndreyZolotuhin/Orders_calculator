package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.TResultHandler;
import ru.azolotuhin.calculator.Metal.MetalConstants;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;
import ru.azolotuhin.calculator.Metal.Model.MetalSheet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MetalSheets extends BaseTable implements TableOperations {

    private List<MetalElement> sheetList;
    private final static String SELECT = "SELECT * from steel.sheet";

    {
        sheetList = new ArrayList<>();
    }

    public List<MetalElement> getSheetList() {
        return sheetList;
    }

    public MetalSheets() throws SQLException {
        super("List");
    }


    @Override
    public void deleteAll() throws SQLException {

    }

    @Override
    public int update(Object objectToUpdate) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Object objectToDelete) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Object objectToInsert) throws SQLException {

    }

    @Override
    public void read() throws SQLException {

    }

    @Override
    public void readAll() throws SQLException {
        sheetList.clear();
        sheetList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends MetalElement>>) resultSet -> {
            List<MetalSheet> list= new ArrayList<>();
            while (resultSet.next()){
                //TODO Написать что-то для исключения марки стали из конструктора...
                list.add(new MetalSheet(resultSet.getInt("Id"),resultSet.getString("Number"), MetalConstants.steelType.C345,
                        resultSet.getDouble("Density"),resultSet.getString("Standard")));
            }
            return list;
        }));
    }
}
