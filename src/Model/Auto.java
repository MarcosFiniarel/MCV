/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author marco
 */
public class Auto extends Vehiculo{
    private int canPuertas;

    public Auto(int canPuertas, String marca, String modelo, String patente, double precio) {
        super(marca, modelo, patente, precio);
        this.canPuertas = canPuertas;
    }

    /**
     * @return the cantPuertas
     */
    public int getCanPuertas() {
        return canPuertas;
    }

    /**
     * @param cantPuertas the cantPuertas to set
     */
    public void setCanPuertas(int canPuertas) {
        this.canPuertas = canPuertas;
    }
    
    public String getAtributosEspecificos(){
        return "Cantidad de Puertas: " + canPuertas;
    }
}
