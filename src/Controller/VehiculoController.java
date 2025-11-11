/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
import Model.*;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class VehiculoController {
    private List<Vehiculo> vehiculos;
    private ConectorDB conectorDB;
    
    public VehiculoController(){
        vehiculos = new ArrayList<>();
        conectorDB = new ConectorDB();
    }
    
    //Consulta de vehiculos
    public List<Vehiculo> getVehiculoFromDB(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        String query = "SELECT * FROM auto";
        try{
            Connection connection = conectorDB.getConnection();
            if(!connection.isClosed()){
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    double precio = resultSet.getDouble("precio");
                    String patente = resultSet.getString("patente");
                    int canPuertas = resultSet.getInt("canPuertas");
                    Auto auto = new Auto(canPuertas,marca,modelo,patente,precio);
                    vehiculos.add(auto);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al obtener los autos en la DB. " + e.getMessage());
        }
        
        return vehiculos;
    }
}
