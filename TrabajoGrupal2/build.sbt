import scala.collection.immutable.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
lazy val root = (project in file("."))
  .settings(
    name := "untitled1",
    libraryDependencies ++= Seq("io.reactivex" %% "rxscala" % "0.27.0",            // Última versión compatible
      "com.lihaoyi" %% "scalarx" % "0.4.3",              // Actualización de scalarx
      "com.nrinaudo" %% "kantan.csv" % "0.6.1",          // Actualización de kantan.csv
      "com.nrinaudo" %% "kantan.csv-generic" % "0.6.1",  // Actualización de kantan.csv-generic
      "com.typesafe.play" %% "play-json" % "2.9.2",       // Librerías para trabajar con JSON
      "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC5",      // Dependencias de doobie
      "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC5",    // Para gestión de conexiones
      "com.mysql" % "mysql-connector-j" % "8.0.31",       // Driver para MySQL
      "com.typesafe" % "config"           % "1.4.2",
      "ch.qos.logback" % "logback-classic" % "1.2.3"// Para gestión de archivos de configuración
    )
  )