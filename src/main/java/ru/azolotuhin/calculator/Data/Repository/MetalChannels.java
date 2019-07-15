package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.TResultHandler;
import ru.azolotuhin.calculator.Metal.MetalConstants;
import ru.azolotuhin.calculator.Metal.Model.MetalChannel;
import ru.azolotuhin.calculator.Metal.Model.MetalElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MetalChannels extends BaseTable implements TableOperations {

    private List<MetalElement> chanelList;
    private final static String SELECT = "SELECT * from steel.channel";

    {
        chanelList = new ArrayList<>();
    }

    public List<MetalElement> getChanelList() {
        return chanelList;
    }

    public MetalChannels() throws SQLException {
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
        chanelList.clear();
        chanelList.addAll(executeSqlStatementQuery(SELECT, (TResultHandler<Collection<? extends MetalElement>>) resultSet -> {
            List<MetalChannel> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new MetalChannel(resultSet.getInt(1),resultSet.getString(2), MetalConstants.steelType.C345,
                        resultSet.getDouble(10),resultSet.getDouble(9),resultSet.getString(19)));
            }
            return list;
        }));
    }
}
