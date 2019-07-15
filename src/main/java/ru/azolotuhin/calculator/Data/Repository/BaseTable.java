package ru.azolotuhin.calculator.Data.Repository;

import ru.azolotuhin.calculator.Data.StockExchangeDB;
import ru.azolotuhin.calculator.Data.TResultHandler;

import java.io.Closeable;
import java.sql.*;

class BaseTable implements Closeable{
    private Connection connection;  // JDBC-соединение для работы с таблицей
    private String tableName;       // Имя таблицы

    BaseTable(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.connection = StockExchangeDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }

    public String getTableName() {
        return tableName;
    }

    // Активизация соединения с СУБД, если оно не активно.
    private void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = StockExchangeDB.getConnection();
        }
    }

    // Закрытие
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

// Выполнить SQL команду на выборку без параметров в СУБД
//для корректного закрытия стейтментов раализовано вот такое решение
    <T> T executeSqlStatementQuery(String sqlCommand, TResultHandler<T> handler) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        Statement statement = connection.createStatement();  // Создаем statement для выполнения sql-команд
        ResultSet resultSet = statement.executeQuery(sqlCommand); // Выполняем statement - sql команду
        T value = handler.handle(resultSet);
        //важно закрыть!!!
        resultSet.close();
        statement.close();
        return value;
    }

    // Выполнить SQL команду без параметров в СУБД
    int executeSqlStatementUpdate(String sqlCommand) throws SQLException{
        reopenConnection();
        Statement statement = connection.createStatement();
        int updated = statement.executeUpdate(sqlCommand);
        //важно закрыть!!!
        statement.close();
        return updated;
    }

    // Выполнить SQL команду на выборку с параметрами в СУБД
    PreparedStatement getSqlStatementWithParameter(String sqlCommand) throws SQLException {
        reopenConnection(); // переоткрываем (если оно неактивно) соединение с СУБД
        return connection.prepareStatement(sqlCommand);
    }

    // Выполнить список команд в транзакции
    void executeSqlStatementUpdateTransaction(String[] sqlCommands) throws SQLException{
        reopenConnection();
        try {
            connection.setAutoCommit(false);
            for (String sqlCommand:sqlCommands) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlCommand);
                statement.close();
            }
            connection.commit();
        }catch (Exception e){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
