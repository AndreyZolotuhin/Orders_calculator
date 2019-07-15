package ru.azolotuhin.calculator.Data.Repository;

import java.sql.SQLException;

interface TableOperations {

    void read() throws SQLException;
    int update(Object objectToUpdate) throws SQLException;
    void readAll() throws SQLException;
    int delete(Object objectToDelete) throws SQLException;
    void deleteAll() throws SQLException;
    void insert(Object objectToInsert) throws SQLException;
}
