/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Date;
/**
 *
 * @author DELL 5590
 */
public class Matricula {
    
    private int codigoMatricula;
    private Estudiante estudiante;
    private Profesor profesor;
    private Turno turno;
    private Date fechaMat;

    public Matricula(int codigoMatricula, Estudiante estudiante, Profesor profesor, Turno turno, Date fechaMat) {
        this.codigoMatricula = codigoMatricula;
        this.estudiante = estudiante;
        this.profesor = profesor;
        this.turno = turno;
        this.fechaMat = fechaMat;
    }

    public Matricula() {
    }

    public int getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(int codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Date getFechaMat() {
        return fechaMat;
    }

    public void setFechaMat(Date fechaMat) {
        this.fechaMat = fechaMat;
    }

}


