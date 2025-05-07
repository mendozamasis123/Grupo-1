package DAO;

import Modelo.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO estudiantes (codigoEstudiante, nombre1, nombre2, apellido1, apellido2, nombreTutor, telefonoTutor, direccion, telefono, sexo, fechaNac) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estudiante.getCodigoEstudiante());
            stmt.setString(2, estudiante.getNombre1());
            stmt.setString(3, estudiante.getNombre2());
            stmt.setString(4, estudiante.getApellido1());
            stmt.setString(5, estudiante.getApellido2());
            stmt.setString(6, estudiante.getNombreTutor());
            stmt.setString(7, estudiante.getTelefonoTutor());
            stmt.setString(8, estudiante.getDireccion());
            stmt.setString(9, estudiante.getTelefono());
            stmt.setString(10, estudiante.getSexo());
            stmt.setDate(11, new Date(estudiante.getFechaNac().getTime()));
            stmt.executeUpdate();
        }
    }

    public Estudiante obtenerEstudiante(int codigoEstudiante) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE codigoEstudiante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoEstudiante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                            rs.getInt("codigoEstudiante"),
                            rs.getString("nombre1"),
                            rs.getString("nombre2"),
                            rs.getString("apellido1"),
                            rs.getString("apellido2"),
                            rs.getString("nombreTutor"),
                            rs.getString("telefonoTutor"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("sexo"),
                            rs.getDate("fechaNac")
                    );
                }
            }
        }
        return null;
    }

    public List<Estudiante> listarEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getInt("codigoEstudiante"),
                        rs.getString("nombre1"),
                        rs.getString("nombre2"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("nombreTutor"),
                        rs.getString("telefonoTutor"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("sexo"),
                        rs.getDate("fechaNac")
                );
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

    public void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        String sql = "UPDATE estudiantes SET nombre1 = ?, nombre2 = ?, apellido1 = ?, apellido2 = ?, nombreTutor = ?, telefonoTutor = ?, direccion = ?, telefono = ?, sexo = ?, fechaNac = ? WHERE codigoEstudiante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre1());
            stmt.setString(2, estudiante.getNombre2());
            stmt.setString(3, estudiante.getApellido1());
            stmt.setString(4, estudiante.getApellido2());
            stmt.setString(5, estudiante.getNombreTutor());
            stmt.setString(6, estudiante.getTelefonoTutor());
            stmt.setString(7, estudiante.getDireccion());
            stmt.setString(8, estudiante.getTelefono());
            stmt.setString(9, estudiante.getSexo());
            stmt.setDate(10, new Date(estudiante.getFechaNac().getTime()));
            stmt.setInt(11, estudiante.getCodigoEstudiante());
            stmt.executeUpdate();
        }
    }

    public void eliminarEstudiante(int codigoEstudiante) throws SQLException {
        String sql = "DELETE FROM estudiantes WHERE codigoEstudiante = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoEstudiante);
            stmt.executeUpdate();
        }
    }
} 