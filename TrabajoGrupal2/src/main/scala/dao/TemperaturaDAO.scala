package dao

import doobie._
import doobie.implicits._
import cats.effect.IO
import cats.implicits._
import models.Estudiantes
import config.Database

object TemperaturaDAO {
  def insert(estudiantes: Estudiantes): ConnectionIO[Int] = {
    sql"""
     INSERT INTO estudiantes (nombre, edad, calificacion,genero)
     VALUES (
       ${estudiantes.nombre},
       ${estudiantes.edad},
       ${estudiantes.calificacion},
       ${estudiantes.genero}
     )
   """.update.run
  }

  def insertAll(estudiantes: List[Estudiantes]): IO[List[Int]] = {
    Database.transactor.use { xa =>
      estudiantes.traverse(t => insert(t).transact(xa))
    }
  }
}
