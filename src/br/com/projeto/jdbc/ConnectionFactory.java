package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    

    public Connection getConnection() { 
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_vendas", "user", "123");

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }      
}
