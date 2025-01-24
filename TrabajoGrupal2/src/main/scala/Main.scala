import cats.effect.{IO, IOApp}
import dao.TemperaturaDAO
import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._
import models.Estudiantes

import java.io.File

object Main extends IOApp.Simple {
  val path2DataFile2 = "src/main/resources/data/estudiantes.csv"

  val dataSource = new File(path2DataFile2)
    .readCsv[List, Estudiantes](rfc.withHeader.withCellSeparator(','))

  val estudiantes = dataSource.collect {
    case Right(estudiantes) => estudiantes
  }

  def run: IO[Unit] = for {
    // Inserta los registros en la base de datos
    inserted <- TemperaturaDAO.insertAll(estudiantes)
    _ <- IO.println(s"Registros insertados: ${inserted.size}")

    // Obtiene todos los registros de la base de datos
    allEstudiantes <- TemperaturaDAO.getAll
    _ <- IO.println("Registros actuales en la base de datos:")
    _ <- IO.println(allEstudiantes.mkString("\n"))
  } yield ()
}
