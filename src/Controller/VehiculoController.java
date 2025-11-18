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
    
    //Buscar vehiculo
    public Vehiculo buscarVehiculoPorCodigo(int codigo)throws SQLException{
        String sql = "SELECT * FROM auto WHERE id = ?";
        Connection connection = conectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try{
            statement.setInt(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String patente = resultSet.getString("patente");
                double precio = resultSet.getDouble("precio");
                int canPuertas = resultSet.getInt("canPuertas");
                
                System.out.println("Auto encontraddo con exito.");
                return new Auto(canPuertas,marca,modelo,patente,precio);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al buscar el auto en la base de datos" + e.getMessage());
        }
        
        return null;
    }
    
    //Actualizar vehiculo en base a la busqueda
    public boolean actualizarVehiculo(int id, String marca, String modelo, String patente, double precio, int canPuertas)throws SQLException{
        String sql = "UPDATE auto SET marca = ?, modelo = ?, precio = ?, patente = ?, canPuertas = ? WHERE id = ?";
        Connection connection = conectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try{
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setDouble(3, precio);
            statement.setString(4, patente);
            statement.setInt(5, canPuertas);
            statement.setInt(6, id);
            
            int filasActualizadas = statement.executeUpdate();
            
            if(filasActualizadas > 0){
                System.out.println("Auto actualizado con exito.");
                return true;
            }else{
                System.out.println("Problemas al actualizar el auto");
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al actualizar el auto de la base de datos" + e.getMessage());
            return false;
        }
    }
    
    //Eliminar vehiculo
    public boolean borrarVehiculoPorCodigo(int codigo)throws SQLException{
        String sql = "DELETE FROM auto WHERE id = (?)";
        Connection connection = conectorDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        try{
            statement.setInt(1, codigo);
            statement.executeUpdate();
            
            System.out.println("Auto eliminado con exito.");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al eliminar el auto en la base de datos" + e.getMessage());
            return false;
        }
        
    }
}
