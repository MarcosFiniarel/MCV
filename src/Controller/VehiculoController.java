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
    
    //Insertar vehiculo
    public void addVehiculo(Vehiculo vehiculo)throws SQLException{
        vehiculos.add(vehiculo);
        insertarAuto((Auto) vehiculo);
    }
            
    public void insertarAuto(Auto auto)throws SQLException{
        String sql = "INSERT INTO auto(marca,modelo,precio,patente,canPuertas) VALUES (?,?,?,?,?)";
        Connection connection = conectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try{
            statement.setString(1, auto.getMarca());
            statement.setString(2, auto.getModelo());
            statement.setDouble(3, auto.getPrecio());
            statement.setString(4, auto.getPatente());
            statement.setInt(5, auto.getCanPuertas());
            statement.executeUpdate();
            System.out.println("Auto ingresado con exito.");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al obtener los autos de la base de datos" + e.getMessage());
        }
    }
}
