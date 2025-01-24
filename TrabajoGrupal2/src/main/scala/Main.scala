import cats.effect.{IO, IOApp}
import dao.TemperaturaDAO
import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._
import models.Estudiantes

import java.io.File

// Extiende de IOApp.Simple para manejar efectos IO y recursos de forma segura
object Main extends IOApp.Simple {
  val path2DataFile2 = "src/main/resources/data/estudiantes.csv"

  val dataSource = new File(path2DataFile2)
    .readCsv[List, Estudiantes](rfc.withHeader.withCellSeparator(','))

  val estudiantes = dataSource.collect {
    case Right(estudiantes) => estudiantes
  }

  // Secuencia de operaciones IO usando for-comprehension
  def run: IO[Unit] = for {
    result <- TemperaturaDAO.insertAll(estudiantes)  // Inserta datos y extrae resultado con <-
    _ <- IO.println(s"Registros insertados: ${result.size}")  // Imprime cantidad
  } yield ()  // Completa la operación
}