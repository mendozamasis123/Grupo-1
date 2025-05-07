-- CREACIÓN DE BASE DE DATOS
CREATE DATABASE IF NOT EXISTS Gestionmatriculas;
USE Gestionmatriculas;

-- TABLA ALUMNO
CREATE TABLE Alumno (
  Codigo_Estudiante INT AUTO_INCREMENT PRIMARY KEY,
  Nombre1 VARCHAR(15),
  Nombre2 VARCHAR(15),
  Apellido1 VARCHAR(20),
  Apellido2 VARCHAR(20),
  Nombre_Tutor VARCHAR(30),
  Telefono_Tutor VARCHAR(30),
  Direccion TEXT,
  Telefono VARCHAR(8),
  Sexo CHAR(1),
  Fecha_Nac DATE
);

-- TABLA PROFESOR
CREATE TABLE Profesor (
  Codigo_Prof INT AUTO_INCREMENT PRIMARY KEY,
  Cedula VARCHAR(14),
  Nombre1 VARCHAR(15),
  Nombre2 VARCHAR(15),
  Apellido1 VARCHAR(20),
  Apellido2 VARCHAR(20),
  Direccion TEXT,
  Telefono VARCHAR(8)
);

-- TABLA GRADO
CREATE TABLE Grado (
  Codigo_Grado INT AUTO_INCREMENT PRIMARY KEY,
  Descripcion VARCHAR(15),
  Seccion VARCHAR(10)
);

-- TABLA TURNO
CREATE TABLE Turno (
  Codigo_Turno INT AUTO_INCREMENT PRIMARY KEY,
  Descripcion VARCHAR(15)
);

-- TABLA MATRICULA
CREATE TABLE Matricula (
  Codigo_Matricula INT AUTO_INCREMENT PRIMARY KEY,
  Codigo_Estudiante INT,
  Codigo_Prof INT,
  Codigo_Grado INT,
  Codigo_Turno INT,
  Fecha_Mat DATE,
  FOREIGN KEY (Codigo_Estudiante) REFERENCES Alumno(Codigo_Estudiante),
  FOREIGN KEY (Codigo_Prof) REFERENCES Profesor(Codigo_Prof),
  FOREIGN KEY (Codigo_Grado) REFERENCES Grado(Codigo_Grado),
  FOREIGN KEY (Codigo_Turno) REFERENCES Turno(Codigo_Turno)
);

-- INSERCIONES EN ALUMNO
INSERT INTO Alumno (Nombre1, Nombre2, Apellido1, Apellido2, Nombre_Tutor, Telefono_Tutor, Direccion, Telefono, Sexo, Fecha_Nac) VALUES
('Juan', 'Carlos', 'Pérez', 'Gómez', 'María Gómez', '88887777', 'Colonia San José', '99998888', 'M', '2012-05-15'),
('Ana', 'Lucía', 'Ramírez', 'López', 'Carlos Ramírez', '77776666', 'Barrio El Centro', '88889999', 'F', '2013-11-20'),
('Luis', 'Miguel', 'Duarte', 'Martínez', 'Sandra Martínez', '66665555', 'Colonia Santa Rosa', '87776666', 'M', '2011-03-10'),
('María', 'José', 'Hernández', 'Reyes', 'Pedro Hernández', '55554444', 'Colonia El Bosque', '86665555', 'F', '2014-09-01'),
('Carlos', 'Eduardo', 'Zelaya', 'Ramos', 'Gloria Ramos', '44443333', 'Barrio Los Pinos', '85554444', 'M', '2012-01-25'),
('Sofía', 'Elena', 'González', 'Navarro', 'Luis González', '33332222', 'Colonia Universitaria', '84443333', 'F', '2013-07-18');

-- INSERCIONES EN PROFESOR
INSERT INTO Profesor (Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Direccion, Telefono) VALUES
('0801199012345', 'Mario', 'Alberto', 'Guzmán', 'Ruiz', 'Col. Kennedy', '22334455'),
('0801198711223', 'Elena', 'Patricia', 'Lopez', 'Mejía', 'Col. Alameda', '33445566'),
('0801200312456', 'José', 'Manuel', 'Sosa', 'Martínez', 'Col. Las Colinas', '44556677'),
('0801199909876', 'Karen', 'Lisseth', 'Zúniga', 'Castro', 'Col. El Prado', '55667788'),
('0801200512345', 'Roberto', 'Carlos', 'Chávez', 'Pineda', 'Col. Florencia', '66778899'),
('0801201012345', 'Isabel', 'María', 'Paz', 'Rodríguez', 'Col. Miraflores', '77889900');

-- INSERCIONES EN GRADO
INSERT INTO Grado (Descripcion, Seccion) VALUES
('Primero', 'A'),
('Segundo', 'B'),
('Tercero', 'A'),
('Cuarto', 'B'),
('Quinto', 'A'),
('Sexto', 'B');

-- INSERCIONES EN TURNO
INSERT INTO Turno (Descripcion) VALUES
('Mañana'),
('Tarde'),
('Vespertino'),
('Nocturno'),
('Intensivo'),
('Sabatino');

-- INSERCIONES EN MATRICULA
INSERT INTO Matricula (Codigo_Estudiante, Codigo_Prof, Codigo_Grado, Codigo_Turno, Fecha_Mat) VALUES
(1, 1, 1, 1, '2024-01-15'),
(2, 2, 2, 2, '2024-01-16'),
(3, 3, 3, 1, '2024-01-17'),
(4, 4, 4, 2, '2024-01-18'),
(5, 5, 5, 1, '2024-01-19'),
(6, 6, 6, 2, '2024-01-20');

-- VISTA: CANTIDAD DE ESTUDIANTES POR GRADO
CREATE VIEW Vista_Estudiantes_Por_Grado AS
SELECT 
    G.Descripcion AS Grado,
    G.Seccion,
    COUNT(M.Codigo_Estudiante) AS Total_Estudiantes
FROM 
    Matricula M
JOIN 
    Grado G ON M.Codigo_Grado = G.Codigo_Grado
GROUP BY 
    G.Descripcion, G.Seccion;
select *from Vista_Estudiantes_Por_Grado;

--  Cantidad de estudiantes por turno
CREATE VIEW Vista_Estudiantes_Por_Turno AS
SELECT 
    T.Descripcion AS Turno,
    COUNT(M.Codigo_Estudiante) AS Total_Estudiantes
FROM 
    Matricula M
JOIN 
    Turno T ON M.Codigo_Turno = T.Codigo_Turno
GROUP BY 
    T.Descripcion;
select *from Vista_Estudiantes_Por_Turno;

-- : Detalle de estudiantes con grado, sección, turno y profesor
CREATE VIEW Vista_Detalle_Estudiantes AS
SELECT 
    A.Codigo_Estudiante,
    CONCAT(A.Nombre1, ' ', A.Nombre2, ' ', A.Apellido1, ' ', A.Apellido2) AS Nombre_Estudiante,
    G.Descripcion AS Grado,
    G.Seccion,
    T.Descripcion AS Turno,
    CONCAT(P.Nombre1, ' ', P.Nombre2, ' ', P.Apellido1, ' ', P.Apellido2) AS Profesor_Asignado,
    M.Fecha_Mat
FROM 
    Matricula M
JOIN 
    Alumno A ON M.Codigo_Estudiante = A.Codigo_Estudiante
JOIN 
    Grado G ON M.Codigo_Grado = G.Codigo_Grado
JOIN 
    Turno T ON M.Codigo_Turno = T.Codigo_Turno
JOIN 
    Profesor P ON M.Codigo_Prof = P.Codigo_Prof;
    select *from Vista_Detalle_Estudiantes;
    
    -- : Reporte de estudiantes con tutor, grado, sección, turno y profesor
CREATE VIEW Vista_Estudiantes_Tutor_Grado_Turno_Profesor AS
SELECT 
    A.Codigo_Estudiante,
    CONCAT(A.Nombre1, ' ', A.Nombre2, ' ', A.Apellido1, ' ', A.Apellido2) AS Nombre_Estudiante,
    A.Nombre_Tutor,
    A.Telefono_Tutor,
    G.Descripcion AS Grado,
    G.Seccion,
    T.Descripcion AS Turno,
    CONCAT(P.Nombre1, ' ', P.Nombre2, ' ', P.Apellido1, ' ', P.Apellido2) AS Profesor_Asignado,
    M.Fecha_Mat
FROM 
    Matricula M
JOIN 
    Alumno A ON M.Codigo_Estudiante = A.Codigo_Estudiante
JOIN 
    Grado G ON M.Codigo_Grado = G.Codigo_Grado
JOIN 
    Turno T ON M.Codigo_Turno = T.Codigo_Turno
JOIN 
    Profesor P ON M.Codigo_Prof = P.Codigo_Prof;
    select *from Vista_Estudiantes_Tutor_Grado_Turno_Profesor;
    
    CREATE VIEW Vista_Estudiantes_Por_Sexo AS
SELECT 
    Sexo,
    COUNT(*) AS Total_Estudiantes
FROM 
    Alumno
GROUP BY 
    Sexo;
SELECT * FROM Vista_Estudiantes_Por_Sexo;
    
    DELIMITER //
CREATE PROCEDURE InsertarAlumno(
    IN p_Nombre1 VARCHAR(15),
    IN p_Nombre2 VARCHAR(15),
    IN p_Apellido1 VARCHAR(20),
    IN p_Apellido2 VARCHAR(20),
    IN p_Nombre_Tutor VARCHAR(30),
    IN p_Telefono_Tutor VARCHAR(30),
    IN p_Direccion TEXT,
    IN p_Telefono VARCHAR(8),
    IN p_Sexo CHAR(1),
    IN p_Fecha_Nac DATE
)
BEGIN
    INSERT INTO Alumno(Nombre1, Nombre2, Apellido1, Apellido2, Nombre_Tutor, Telefono_Tutor, Direccion, Telefono, Sexo, Fecha_Nac)
    VALUES(p_Nombre1, p_Nombre2, p_Apellido1, p_Apellido2, p_Nombre_Tutor, p_Telefono_Tutor, p_Direccion, p_Telefono, p_Sexo, p_Fecha_Nac);
END //
DELIMITER ;

CALL InsertarAlumno(
  'Daniel', 'Andrés', 'Mejía', 'Torres',
  'Juana Torres', '88881111',
  'Barrio Bella Vista', '89991111',
  'M', '2014-02-20'
);
    SELECT * FROM Alumno WHERE Nombre1 = 'Daniel' AND Apellido1 = 'Mejía';
    
    DELIMITER //
CREATE PROCEDURE MostrarMatriculaPorEstudiante(IN p_Codigo_Estudiante INT)
BEGIN
    SELECT 
        A.Codigo_Estudiante,
        CONCAT(A.Nombre1, ' ', A.Apellido1) AS Nombre,
        G.Descripcion AS Grado,
        G.Seccion,
        T.Descripcion AS Turno,
        M.Fecha_Mat
    FROM Matricula M
    JOIN Alumno A ON M.Codigo_Estudiante = A.Codigo_Estudiante
    JOIN Grado G ON M.Codigo_Grado = G.Codigo_Grado
    JOIN Turno T ON M.Codigo_Turno = T.Codigo_Turno
    WHERE A.Codigo_Estudiante = p_Codigo_Estudiante;
END //
DELIMITER ;
CALL MostrarMatriculaPorEstudiante(1);

DELIMITER //
CREATE PROCEDURE ActualizarTelefonoTutor(
    IN p_Codigo_Estudiante INT,
    IN p_NuevoTelefono VARCHAR(30)
)
BEGIN
    UPDATE Alumno
    SET Telefono_Tutor = p_NuevoTelefono
    WHERE Codigo_Estudiante = p_Codigo_Estudiante;
END //
DELIMITER ;
SELECT 
    Codigo_Estudiante,
    CONCAT(Nombre1, ' ', Nombre2, ' ', Apellido1, ' ', Apellido2) AS Nombre_Estudiante,
    Telefono_Tutor
FROM 
    Alumno
WHERE 
    Codigo_Estudiante = 2;


DELIMITER //
CREATE PROCEDURE EliminarMatricula(
    IN p_Codigo_Matricula INT
)
BEGIN
    DELETE FROM Matricula
    WHERE Codigo_Matricula = p_Codigo_Matricula;
END //
DELIMITER ;
CALL EliminarMatricula(6);
SELECT * FROM Matricula WHERE Codigo_Matricula = 6;


