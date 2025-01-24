# Taller-Grupal-2-Persistencia-de-datos-en-archivos---Base-de-datos1
**Informe sobre el Código y su Funcionalidad**

Este trabajo se centra en el manejo de datos de estudiantes a través de una base de datos MySQL, utilizando la tecnología Doobie en Scala para interactuar con la base de datos. A continuación se explica cada sección del código y su función:

### CSV de Estudiantes

El archivo CSV contiene información sobre varios estudiantes. Las columnas son:
- **nombre**: el nombre del estudiante
- **edad**: la edad del estudiante
- **calificación**: la calificación obtenida por el estudiante
- **género**: el género del estudiante (M para masculino, F para femenino)

#### Datos:
```
nombre  edad  calificacion  genero
Andrés  10    20            M
Ana     11    19            F
Luis    9     18            M
Cecilia 9     18            F
Katy    11    15            F
Jorge   8     17            M
Rosario 11    18            F
Nieves  10    20            F
Pablo   9     19            M
Daniel  10    20            M
```

### Configuración de Conexión a Base de Datos

La configuración de la conexión a la base de datos está incluida en un archivo `db.conf` que contiene los siguientes parámetros:
```scala
db {
  driver = "com.mysql.cj.jdbc.Driver"
  url = "jdbc:mysql://localhost:3306/trabajoGrupo2"
  user = "root"
  password = "kame"
}
```
Aquí se establece:
- **driver**: el controlador JDBC necesario para conectar con MySQL.
- **url**: la URL de la base de datos (en este caso, se conecta a un servidor local con la base de datos `trabajoGrupo2`).
- **user** y **password**: las credenciales de acceso a la base de datos.

### Gestión de Conexiones con Doobie

En el objeto `Database`, se configura la conexión a la base de datos utilizando el transactor de Doobie:
```scala
def transactor: Resource[IO, HikariTransactor[IO]] = {
  val config = ConfigFactory.load().getConfig("db")
  HikariTransactor.newHikariTransactor[IO](
    config.getString("driver"),
    config.getString("url"),
    config.getString("user"),
    config.getString("password"),
    connectEC
  )
}
```
Esto crea un transactor que facilita la ejecución de consultas SQL en un contexto de efectos en Scala, garantizando que las conexiones a la base de datos se gestionen adecuadamente.

### DAO (Data Access Object)

El objeto `TemperaturaDAO` contiene métodos que permiten insertar datos en la base de datos y obtenerlos:
- **insert**: inserta un solo estudiante en la base de datos.
- **insertAll**: inserta múltiples estudiantes a la vez, utilizando un `IO` para manejar efectos asíncronos.
- **getAll**: obtiene todos los estudiantes de la base de datos y los devuelve como una lista.

### Modelo `Estudiantes`

El caso de clase `Estudiantes` define los campos que se utilizarán para mapear los datos obtenidos de la base de datos:
```scala
case class Estudiantes(
  nombre: String,
  edad: Int,
  calificacion: Double,
  genero: String
)
```


### Requisitos

Para ejecutar este proyecto, se necesitan los siguientes requisitos:
- **Scala** y **SBT** instalados.
- **MySQL** en el servidor local o remoto.
- **Doobie** para la interacción con la base de datos.

### Instalación

1. Clonar el repositorio.
2. Configurar la base de datos MySQL con la tabla `estudiantes`.
3. Actualizar el archivo `db.conf` con las credenciales correctas.
4. Ejecutar el proyecto usando SBT.

### Dependencias

El proyecto utiliza las siguientes dependencias de Scala:
- **Doobie**: Para interactuar con la base de datos.
- **Cats Effect**: Para manejar efectos asíncronos.
- **HikariCP**: Para gestionar las conexiones de la base de datos.


