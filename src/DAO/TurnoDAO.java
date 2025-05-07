/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {

    private Connection connection;

    public TurnoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarTurno(Turno turno) throws SQLException {
        String sql = "INSERT INTO turnos (codigoTurno, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, turno.getCodigoTurno());
            stmt.setString(2, turno.getDescripcion());
            stmt.executeUpdate();
        }
    }

    public Turno obtenerTurno(int codigoTurno) throws SQLException {
        String sql = "SELECT * FROM turnos WHERE codigoTurno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoTurno);
            try (ResultSet rs = stmt.executeQuery()) {
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

    public List<Turno> listarTurnos() throws SQLException {
        List<Turno> turnos = new ArrayList<>();
        String sql = "SELECT * FROM turnos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Turno turno = new Turno(
                    rs.getInt("codigoTurno"),
                    rs.getString("descripcion")
                );
                turnos.add(turno);
            }
        }
        return turnos;
    }

    public void actualizarTurno(Turno turno) throws SQLException {
        String sql = "UPDATE turnos SET descripcion = ? WHERE codigoTurno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, turno.getDescripcion());
            stmt.setInt(2, turno.getCodigoTurno());
            stmt.executeUpdate();
        }
    }

    public void eliminarTurno(int codigoTurno) throws SQLException {
        String sql = "DELETE FROM turnos WHERE codigoTurno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoTurno);
            stmt.executeUpdate();
        }
    }
}
