package DAO;

import Modelo.Grado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradoDAO {

    private Connection connection;

    public GradoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarGrado(Grado grado) throws SQLException {
        String sql = "INSERT INTO grados (codigoGrado, descripcion, seccion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, grado.getCodigoGrado());
            stmt.setString(2, grado.getDescripcion());
            stmt.setString(3, grado.getSeccion());
            stmt.executeUpdate();
        }
    }

    public Grado obtenerGrado(int codigoGrado) throws SQLException {
        String sql = "SELECT * FROM grados WHERE codigoGrado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoGrado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Grado(
                        rs.getInt("codigoGrado"),
                        rs.getString("descripcion"),
                        rs.getString("seccion")
                    );
                }
            }
        }
        return null;
    }

    public List<Grado> listarGrados() throws SQLException {
        List<Grado> grados = new ArrayList<>();
        String sql = "SELECT * FROM grados";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Grado grado = new Grado(
                    rs.getInt("codigoGrado"),
                    rs.getString("descripcion"),
                    rs.getString("seccion")
                );
                grados.add(grado);
            }
        }
        return grados;
    }

    public void actualizarGrado(Grado grado) throws SQLException {
        String sql = "UPDATE grados SET descripcion = ?, seccion = ? WHERE codigoGrado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, grado.getDescripcion());
            stmt.setString(2, grado.getSeccion());
            stmt.setInt(3, grado.getCodigoGrado());
            stmt.executeUpdate();
        }
    }

    public void eliminarGrado(int codigoGrado) throws SQLException {
        String sql = "DELETE FROM grados WHERE codigoGrado = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoGrado);
            stmt.executeUpdate();
        }
    }
}
