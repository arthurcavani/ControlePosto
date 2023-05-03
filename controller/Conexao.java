package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String DATABASE="posto";
    private static final String HOST="localhost";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://"+HOST+"/"+DATABASE;
    private static final String USR="root";
    private static final String PWD="passwd";
    
    public static Connection Conectar(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
    
    public static void Desconectar(Connection con){
        try {
            if ( con != null){
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
}
