package Controlador;

import DAO.ProfesorDAO;
import Modelo.Profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ProfesorControlador {

    private final ProfesorDAO profesorDAO;

    public ProfesorControlador() {
        try {
            // ⚠️ Ajusta la conexión a tu base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://@author DELL 5590/Gestionmatriculas", "root", "123456789");
            this.profesorDAO = new ProfesorDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarProfesor(int codigoProf, String cedula, String nombre1, String nombre2, String apellido1, String apellido2, String direccion, String telefono) {
        try {
            Profesor profesor = new Profesor();
            profesor.setCodigoProf(codigoProf);
            profesor.setCedula(cedula);
            profesor.setNombre1(nombre1);
            profesor.setNombre2(nombre2);
            profesor.setApellido1(apellido1);
            profesor.setApellido2(apellido2);
            profesor.setDireccion(direccion);
            profesor.setTelefono(telefono);

            profesorDAO.agregarProfesor(profesor);
            JOptionPane.showMessageDialog(null, "Profesor agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar el profesor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Profesor> listarProfesores() {
        try {
            return profesorDAO.listarProfesores();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar los profesores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Profesor obtenerProfesor(int codigoProf) {
        try {
            return profesorDAO.obtenerProfesor(codigoProf);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el profesor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarProfesor(int codigoProf, String cedula, String nombre1, String nombre2, String apellido1, String apellido2, String direccion, String telefono) {
        try {
            Profesor profesor = new Profesor();
            profesor.setCodigoProf(codigoProf);
            profesor.setCedula(cedula);
            profesor.setNombre1(nombre1);
            profesor.setNombre2(nombre2);
            profesor.setApellido1(apellido1);
            profesor.setApellido2(apellido2);
            profesor.setDireccion(direccion);
            profesor.setTelefono(telefono);

            profesorDAO.actualizarProfesor(profesor);
            JOptionPane.showMessageDialog(null, "Profesor actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el profesor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarProfesor(int codigoProf) {
        try {
            profesorDAO.eliminarProfesor(codigoProf);
            JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el profesor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
