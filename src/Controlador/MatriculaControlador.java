package Controlador;

import DAO.MatriculaDAO;
import Modelo.Estudiante;
import Modelo.Matricula;
import Modelo.Profesor;
import Modelo.Turno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class MatriculaControlador {

    private final MatriculaDAO matriculaDAO;

    public MatriculaControlador() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://@author DELL 5590/Gestionmatriculas", "root", "123456789");
            this.matriculaDAO = new MatriculaDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void agregarMatricula(int codigoMatricula, Estudiante estudiante, Profesor profesor, Turno turno, Date fechaMat) {
        try {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(codigoMatricula);
            matricula.setEstudiante(estudiante);
            matricula.setProfesor(profesor);
            matricula.setTurno(turno);
            matricula.setFechaMat(fechaMat);

            matriculaDAO.agregarMatricula(matricula);
            JOptionPane.showMessageDialog(null, "Matrícula agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la matrícula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Matricula> listarMatriculas() {
        try {
            return matriculaDAO.listarMatriculas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar matrículas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Matricula obtenerMatricula(int codigoMatricula) {
        try {
            return matriculaDAO.obtenerMatricula(codigoMatricula);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la matrícula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void actualizarMatricula(int codigoMatricula, Estudiante estudiante, Profesor profesor, Turno turno, Date fechaMat) {
        try {
            Matricula matricula = new Matricula();
            matricula.setCodigoMatricula(codigoMatricula);
            matricula.setEstudiante(estudiante);
            matricula.setProfesor(profesor);
            matricula.setTurno(turno);
            matricula.setFechaMat(fechaMat);

            matriculaDAO.actualizarMatricula(matricula);
            JOptionPane.showMessageDialog(null, "Matrícula actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la matrícula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarMatricula(int codigoMatricula) {
        try {
            matriculaDAO.eliminarMatricula(codigoMatricula);
            JOptionPane.showMessageDialog(null, "Matrícula eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la matrícula: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
