package Controlador;

import DAO.GradoDAO;
import Modelo.Grado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class GradoControlador {

    private final GradoDAO gradoDAO;

    public GradoControlador() {
        try {
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://@author DELL 5590/Gestionmatriculas", "root", "123456789");
            this.gradoDAO = new GradoDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarGrado(int codigoGrado, String descripcion, String seccion) {
        try {
            Grado grado = new Grado();
            grado.setCodigoGrado(codigoGrado);
            grado.setDescripcion(descripcion);
            grado.setSeccion(seccion);

            gradoDAO.agregarGrado(grado);
            JOptionPane.showMessageDialog(null, "Grado agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el grado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Grado> listarGrados() {
        try {
            return gradoDAO.listarGrados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los grados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarGrado(int codigoGrado, String descripcion, String seccion) {
        try {
            Grado grado = new Grado();
            grado.setCodigoGrado(codigoGrado);
            grado.setDescripcion(descripcion);
            grado.setSeccion(seccion);

            gradoDAO.actualizarGrado(grado);
            JOptionPane.showMessageDialog(null, "Grado actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el grado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarGrado(int codigoGrado) {
        try {
            gradoDAO.eliminarGrado(codigoGrado);
            JOptionPane.showMessageDialog(null, "Grado eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el grado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Grado obtenerGrado(int codigoGrado) {
        try {
            return gradoDAO.obtenerGrado(codigoGrado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el grado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
