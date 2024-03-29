package factory;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sys");
        dataSource.setUsername("root");
        dataSource.setPassword("rootpassword");
        dataSource.setMaxTotal(100);
        dataSource.setDefaultAutoCommit(false);
    }

    public static Connection getConnection() throws SQLException {
        try {

            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void rollback()  {
        try {
            dataSource.getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}