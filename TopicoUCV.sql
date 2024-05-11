create database SIMSETopicoUCV;
go
GRANT SELECT ON dbo.Usuario TO elvis;
use SIMSETopicoUCV;

create table Usuario
(
id int primary key IDENTITY(1,1),
codigo varchar(50) not null,
contrasena varchar(70)
);
CREATE TABLE Personas (
    idPersona INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    edad INT
);
CREATE TABLE Alumnos (
    idAlumno INT PRIMARY KEY,
    codigoAlumno VARCHAR(50),
    carrera VARCHAR(100),
    ciclo INT
);
CREATE TABLE Enfermeras (
    idEnfermera INT PRIMARY KEY,
    trabajo VARCHAR(100),
    especialidad VARCHAR(100)
);
CREATE TABLE EstadoSalud (
    idEstadoSalud INT PRIMARY KEY,
    idAlumno INT FOREIGN KEY REFERENCES Alumnos(idAlumno),
    fecha DATE,
    descripcion VARCHAR(255)
);
CREATE TABLE ProductosFarmaceuticos (
    codigo VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT,
    fechaVencimiento DATE
);
CREATE TABLE Reportes (
    idReporte INT PRIMARY KEY,
    idAlumno INT,
    idEnfermera INT,
    fecha DATE,
    descripcion VARCHAR(255),
    FOREIGN KEY (idAlumno) REFERENCES Alumnos(idAlumno),
    FOREIGN KEY (idEnfermera) REFERENCES Enfermeras(idEnfermera)
);
CREATE TABLE Sesiones (
    idSesion INT PRIMARY KEY,
    idUsuario INT,
    fechaInicio DATETIME,
    fechaFin DATETIME,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(id)
);
CREATE TABLE Traslados (
    idTraslado INT PRIMARY KEY,
    idAlumno INT,
    idEnfermera INT,
    fechaInicio DATETIME,
    fechaFin DATETIME,
    FOREIGN KEY (idAlumno) REFERENCES Alumnos(idAlumno),
    FOREIGN KEY (idEnfermera) REFERENCES Enfermeras(idEnfermera)
);


INSERT INTO Usuario (codigo, contrasena) VALUES ('Rosio Lopez', '736467224');

INSERT INTO Personas (idPersona, nombre, apellido, edad) VALUES (1, 'Elvis Antonio', 'Vega Miranda', 19);
INSERT INTO Personas (idPersona, nombre, apellido, edad) VALUES (2, 'Jakeline Rose Meylan', 'Garamendi Cerna', 25);


INSERT INTO Alumnos (idAlumno, codigoAlumno, carrera, ciclo) VALUES (1, 'AL001', 'Computer Science', 2);
INSERT INTO Alumnos (idAlumno, codigoAlumno, carrera, ciclo) VALUES (2, 'AL002', 'Electrical Engineering', 3);

INSERT INTO Enfermeras (idEnfermera, trabajo, especialidad) VALUES (1, 'Hospital General', 'Cuidados Intensivos');
INSERT INTO Enfermeras (idEnfermera, trabajo, especialidad) VALUES (2, 'Clínica Pediátrica', 'Pediatría');

INSERT INTO EstadoSalud (idEstadoSalud, idAlumno, fecha, descripcion) VALUES (1, 1, '2024-05-10', 'Síntomas leves de gripe.');
INSERT INTO EstadoSalud (idEstadoSalud, idAlumno, fecha, descripcion) VALUES (2, 2, '2024-05-09', 'Sin novedades.');

INSERT INTO ProductosFarmaceuticos (codigo, nombre, precio, stock, fechaVencimiento) 
VALUES ('PF001', 'Paracetamol', 5.99, 100, '2024-12-31');

INSERT INTO ProductosFarmaceuticos (codigo, nombre, precio, stock, fechaVencimiento) 
VALUES ('PF002', 'Ibuprofeno', 7.49, 80, '2025-06-30');

INSERT INTO Reportes (idReporte, idAlumno, idEnfermera, fecha, descripcion) 
VALUES (1, 1, 1, '2024-05-10', 'El alumno presenta síntomas de gripe.');

INSERT INTO Reportes (idReporte, idAlumno, idEnfermera, fecha, descripcion) 
VALUES (2, 2, 2, '2024-05-09', 'El alumno está en buen estado de salud.');

INSERT INTO Sesiones (idSesion, idUsuario, fechaInicio, fechaFin) 
VALUES (1, 1, '2024-05-10 08:00:00', '2024-05-10 09:00:00');

INSERT INTO Sesiones (idSesion, idUsuario, fechaInicio, fechaFin) 
VALUES (2, 2, '2024-05-10 10:00:00', '2024-05-10 11:00:00');

INSERT INTO Traslados (idTraslado, idAlumno, idEnfermera, fechaInicio, fechaFin) 
VALUES (1, 1, 1, '2024-05-10 08:00:00', '2024-05-10 09:00:00');

INSERT INTO Traslados (idTraslado, idAlumno, idEnfermera, fechaInicio, fechaFin) 
VALUES (2, 2, 2, '2024-05-10 10:00:00', '2024-05-10 11:00:00');

select * from Usuario
select * from ProductosFarmaceuticos
select * from Alumnos
select * from Personas
select * from Enfermeras
select * from EstadoSalud
select * from Sesiones
select * from Reportes
select * from Traslados
