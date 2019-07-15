package ru.azolotuhin.calculator.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

//Для корректного закрытия сейтментов и резалтсетов реализовано вот такое решение...
public interface TResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
