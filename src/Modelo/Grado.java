/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author DELL 5590
 */
public class Grado {
    private int codigoGrado;
    private String descripcion;
    private String seccion;

    public Grado(int codigoGrado, String descripcion, String seccion) {
        this.codigoGrado = codigoGrado;
        this.descripcion = descripcion;
        this.seccion = seccion;
    }

    public Grado() {
    }

    public int getCodigoGrado() {
        return codigoGrado;
    }

    public void setCodigoGrado(int codigoGrado) {
        this.codigoGrado = codigoGrado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    
}
