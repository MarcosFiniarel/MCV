/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class ConectorDB {
    private static final String URL = "jdbc:mysql://localhost:3306/automotora";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private Connection connection;
    
    public ConectorDB(){
        conectar();
    }
    
    private void conectar(){
        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexion establecida con exito.");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al establecer la conexion con la DB"+e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}
