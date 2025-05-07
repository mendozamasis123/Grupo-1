package DAO;

import Modelo.Estudiante;
import Modelo.Profesor;
import Modelo.Turno;
import Modelo.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    private Connection connection;

    public MatriculaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarMatricula(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO matriculas (codigoMatricula, estudiante_id, profesor_id, turno_id, fechaMat) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula.getCodigoMatricula());
            statement.setInt(2, matricula.getEstudiante().getCodigoEstudiante());
            statement.setInt(3, matricula.getProfesor().getCodigoProf());
            statement.setInt(4, matricula.getTurno().getCodigoTurno());
            statement.setDate(5, new java.sql.Date(matricula.getFechaMat().getTime()));
            statement.executeUpdate();
        }
    }

    public Matricula obtenerMatricula(int codigoMatricula) throws SQLException {
        String sql = "SELECT * FROM matriculas WHERE codigoMatricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoMatricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Matricula(
                        resultSet.getInt("codigoMatricula"),
                        obtenerEstudiante(resultSet.getInt("estudiante_id")),
                        obtenerProfesor(resultSet.getInt("profesor_id")),
                        obtenerTurno(resultSet.getInt("turno_id")),
                        resultSet.getDate("fechaMat")
                    );
                }
            }
        }
        return null;
    }

    public List<Matricula> listarMatriculas() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM matriculas";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Matricula matricula = new Matricula(
                    resultSet.getInt("codigoMatricula"),
                    obtenerEstudiante(resultSet.getInt("estudiante_id")),
                    obtenerProfesor(resultSet.getInt("profesor_id")),
                    obtenerTurno(resultSet.getInt("turno_id")),
                    resultSet.getDate("fechaMat")
                );
                matriculas.add(matricula);
            }
        }
        return matriculas;
    }

    public void actualizarMatricula(Matricula matricula) throws SQLException {
        String sql = "UPDATE matriculas SET estudiante_id = ?, profesor_id = ?, turno_id = ?, fechaMat = ? WHERE codigoMatricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matricula.getEstudiante().getCodigoEstudiante());
            statement.setInt(2, matricula.getProfesor().getCodigoProf());
            statement.setInt(3, matricula.getTurno().getCodigoTurno());
            statement.setDate(4, new java.sql.Date(matricula.getFechaMat().getTime()));
            statement.setInt(5, matricula.getCodigoMatricula());
            statement.executeUpdate();
        }
    }

    public void eliminarMatricula(int codigoMatricula) throws SQLException {
        String sql = "DELETE FROM matriculas WHERE codigoMatricula = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoMatricula);
            statement.executeUpdate();
        }
    }

    // Métodos auxiliares

    private Estudiante obtenerEstudiante(int codigoEstudiante) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE codigoEstudiante = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoEstudiante);
            try (ResultSet rs = statement.executeQuery()) {
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

    private Profesor obtenerProfesor(int codigoProf) throws SQLException {
        String sql = "SELECT * FROM profesores WHERE codigoProf = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoProf);
            try (ResultSet rs = statement.executeQuery()) {
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

    private Turno obtenerTurno(int codigoTurno) throws SQLException {
        String sql = "SELECT * FROM turnos WHERE codigoTurno = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoTurno);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Turno(
                        rs.getInt("codigoTurno"),
                        rs.getString("descripcion")
                    );
                }
            }
        }
        return null;
    }
}
