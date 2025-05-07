package Controlador;

import DAO.TurnoDAO;
import Modelo.Turno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class TurnoControlador {

    private final TurnoDAO turnoDAO;

    public TurnoControlador() {
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://@author DELL 5590/Gestionmatriculas", "root", "123456789");
            this.turnoDAO = new TurnoDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarTurno(int codigoTurno, String descripcion) {
        try {
            Turno turno = new Turno();
            turno.setCodigoTurno(codigoTurno);
            turno.setDescripcion(descripcion);

            turnoDAO.agregarTurno(turno);
            JOptionPane.showMessageDialog(null, "Turno agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el turno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Turno> listarTurnos() {
        try {
            return turnoDAO.listarTurnos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar turnos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarTurno(int codigoTurno, String descripcion) {
        try {
            Turno turno = new Turno();
            turno.setCodigoTurno(codigoTurno);
            turno.setDescripcion(descripcion);

            turnoDAO.actualizarTurno(turno);
            JOptionPane.showMessageDialog(null, "Turno actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el turno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarTurno(int codigoTurno) {
        try {
            turnoDAO.eliminarTurno(codigoTurno);
            JOptionPane.showMessageDialog(null, "Turno eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el turno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Turno obtenerTurno(int codigoTurno) {
        try {
            return turnoDAO.obtenerTurno(codigoTurno);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el turno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
