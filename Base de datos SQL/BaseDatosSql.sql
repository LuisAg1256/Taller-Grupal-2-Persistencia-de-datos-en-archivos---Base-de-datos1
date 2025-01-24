CREATE DATABASE trabajoGrupo2;
use trabajoGrupo2;
CREATE TABLE estudiantes
(
	ID int(11) NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100),
    edad INT NOT NULL,
    calificacion DOUBLE,
    genero VARCHAR(2),
    PRIMARY KEY(ID)
);
DROP TABLE estudiantes;
select * from estudiantes;