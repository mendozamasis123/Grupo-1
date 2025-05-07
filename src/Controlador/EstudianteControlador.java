package Controlador;

import DAO.EstudianteDAO;
import Modelo.Estudiante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class EstudianteControlador {

    private final EstudianteDAO estudianteDAO;

    public EstudianteControlador() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://@author DELL 5590/Gestionmatriculas", "root", "123456789");
            this.estudianteDAO = new EstudianteDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarEstudiante(int codigoEstudiante, String nombre1, String nombre2, String apellido1, String apellido2, String nombreTutor, String telefonoTutor, String direccion, String telefono, String sexo, Date fechaNac) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCodigoEstudiante(codigoEstudiante);
            estudiante.setNombre1(nombre1);
            estudiante.setNombre2(nombre2);
            estudiante.setApellido1(apellido1);
            estudiante.setApellido2(apellido2);
            estudiante.setNombreTutor(nombreTutor);
            estudiante.setTelefonoTutor(telefonoTutor);
            estudiante.setDireccion(direccion);
            estudiante.setTelefono(telefono);
            estudiante.setSexo(sexo);
            estudiante.setFechaNac(fechaNac);

            estudianteDAO.agregarEstudiante(estudiante);
            JOptionPane.showMessageDialog(null, "Estudiante creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Estudiante> obtenerEstudiante() {
        try {
            return estudianteDAO.listarEstudiantes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los estudiantes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarEstudiante(int codigoEstudiante, String nombre1, String nombre2, String apellido1, String apellido2, String nombreTutor, String telefonoTutor, String direccion, String telefono, String sexo, Date fechaNac) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setCodigoEstudiante(codigoEstudiante);
            estudiante.setNombre1(nombre1);
            estudiante.setNombre2(nombre2);
            estudiante.setApellido1(apellido1);
            estudiante.setApellido2(apellido2);
            estudiante.setNombreTutor(nombreTutor);
            estudiante.setTelefonoTutor(telefonoTutor);
            estudiante.setDireccion(direccion);
            estudiante.setTelefono(telefono);
            estudiante.setSexo(sexo);
            estudiante.setFechaNac(fechaNac);

            estudianteDAO.actualizarEstudiante(estudiante);
            JOptionPane.showMessageDialog(null, "Estudiante actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarEstudiante(int codigoEstudiante) {
        try {
            estudianteDAO.eliminarEstudiante(codigoEstudiante);
            JOptionPane.showMessageDialog(null, "Estudiante eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
