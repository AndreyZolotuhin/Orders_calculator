package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.TResultHandler;
import ru.azolotuhin.calculator.Metal.MetalConstants;
import ru.azolotuhin.calculator.Metal.Model.MetalBeam;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MetalIbeams extends BaseTable implements TableOperations {

    private List<MetalElement> iBeamList;
    private final static String SELECT = "SELECT * from steel.Ibeam";

    {
        iBeamList = new ArrayList<>();
    }

    public List<MetalElement> getIBeamList() {
        return iBeamList;
    }

    public MetalIbeams() throws SQLException {
        super("Ibeam");
    }


    @Override
    public void deleteAll() throws SQLException {

    }


    @Override
    public void read() throws SQLException {

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
    public void readAll() throws SQLException {
        iBeamList.clear();
        iBeamList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends MetalElement>>) resultSet -> {
            List<MetalBeam> list= new ArrayList<>();
            while (resultSet.next()){
                //TODO Написать что-то для исключения марки стали из конструктора...
                list.add(new MetalBeam(resultSet.getInt(1),resultSet.getString(2), MetalConstants.steelType.C345,
                        resultSet.getDouble(9),resultSet.getDouble(8),resultSet.getString(17)));
            }
            return list;
        }));
    }
}
