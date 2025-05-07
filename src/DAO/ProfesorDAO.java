/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    private Connection connection;

    public ProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarProfesor(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO profesores (codigoProf, cedula, nombre1, nombre2, apellido1, apellido2, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, profesor.getCodigoProf());
            stmt.setString(2, profesor.getCedula());
            stmt.setString(3, profesor.getNombre1());
            stmt.setString(4, profesor.getNombre2());
            stmt.setString(5, profesor.getApellido1());
            stmt.setString(6, profesor.getApellido2());
            stmt.setString(7, profesor.getDireccion());
            stmt.setString(8, profesor.getTelefono());
            stmt.executeUpdate();
        }
    }

    public Profesor obtenerProfesor(int codigoProf) throws SQLException {
        String sql = "SELECT * FROM profesores WHERE codigoProf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoProf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Profesor(
                        rs.getInt("codigoProf"),
                        rs.getString("cedula"),
                        rs.getString("nombre1"),
                        rs.getString("nombre2"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                    );
                }
            }
        }
        return null;
    }

    public List<Profesor> listarProfesores() throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Profesor profesor = new Profesor(
                    rs.getInt("codigoProf"),
                    rs.getString("cedula"),
                    rs.getString("nombre1"),
                    rs.getString("nombre2"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getString("direccion"),
                    rs.getString("telefono")
                );
                profesores.add(profesor);
            }
        }
        return profesores;
    }

    public void actualizarProfesor(Profesor profesor) throws SQLException {
        String sql = "UPDATE profesores SET cedula = ?, nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, direccion = ?, telefono = ? WHERE codigoProf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profesor.getCedula());
            stmt.setString(2, profesor.getNombre1());
            stmt.setString(3, profesor.getNombre2());
            stmt.setString(4, profesor.getApellido1());
            stmt.setString(5, profesor.getApellido2());
            stmt.setString(6, profesor.getDireccion());
            stmt.setString(7, profesor.getTelefono());
            stmt.setInt(8, profesor.getCodigoProf());
            stmt.executeUpdate();
        }
    }

    public void eliminarProfesor(int codigoProf) throws SQLException {
        String sql = "DELETE FROM profesores WHERE codigoProf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoProf);
            stmt.executeUpdate();
        }
    }
} 
