5af71e91eaf73f10fa1613245b945fe2

play.mailer {
  host = "live.smtp.mailtrap.io"
  port = 587
  ssl = no
  tls = yes
  user = "api"
  password = "<YOUR_API_TOKEN>"
}


# Notas de arquitectura

## Apache Tomcat

* Servidor Web y contenedor de servlets de Código abierto.
* Contenedor de Servlets: permite ejecutar componentes (servlet) que responden a solicitudes HTTP, aplicaciones dinamicas.
* Servidor Web: peticiones web las gestiona y envia las respuestas al cliente correspondientes
* Es una herramientas mas utilizadas para desplegar aplicaciones web java.

## Java en la web

* Servlets: clases java que reciben y procesan solicitudes HTTP, respuestas dinamicas.
* JavaServer Pages(JSP): pagina HTML que puede incluir codigo Java para crear contenido dinamico.
* Contenedores de Servlets: servidores como Apache Tomcat que gestionan la ejecucion de Servlets y JSP y enrutan las peticiones
* Frameworks web: herramientas que facilitan la organizacion , el enrutamiento y la integracion de componentes en aplicaciones web.
* Configuracion y despliegue: uso de archivos de configuracion para mapear URLs a componentes y empaquetar la aplicaion en archivos WAR para su despliegue.

## Gestores de dependencias

* Dependencias es una aplicacion o biblioteca requirada por otro programa
* Los gestores de dependencias son modulos de software que coordina la integracion de bibliotecas o paquetes externos.
* Usan archivos de configuracion como composer.json, package.json, pom.xml para determinar que dependencias obtener, que version de la dependencia en particular y desde cual repositorio se obtiene.

### Algunos gestores de dependencias son:

**MAVEN**

* utiliza pom.xml, administra repositorios centrales y resuelve dependencias transitivas automaticamente.
* herramienta de automatizacion para la construccion , el testeo, empaquetado y despliegue de aplicaciones Java.

---

## Porque son importantes las arquitecturas de software

* gestionar la complejidad
* facilitar la comunicacion y colaboracion
* para cumplir con los atributos de calidad
* para facilitar la evolucion y el matenimiento a largo plazo

## Arquitectura Cliente-Servidor

* La arquitectura cliente-servidor es un modelo de diseño de software en el que las tareas de reparten entre los proveedores de recursos o servidores llamados servidores y los clientes.
* Basicamente es un cliente realiza peticiones a otro programa , el servidor, quien le da respuestas, utiliza la web(internet) como canal de comunicacion.

## Acciones de Request

* Get
* Post
* Delete
* Put

---

## Frameworks

* Se requiere documentar muy bien la arquitectura, librerias, clases , funciones basicas,etc.
* Frameworks verticales: se centran un un solo dominio, estan compuestos de multiples modulos apropiados para el dominio que se trabaja.
* Frameworks horizontales: son utilizes en el desarrollo de aplicaciones multiples, no proveen funcionalidades implementadas desde el principio, muchos de ellos cuentan con grandes comunidades y gran cantidad de documentacion.
* Opinados : tienen a ser mas riguidos , tienen una arquitectura base.
* No opinionados: son mas abiertas, minimalistas, no defienen una estructura de directorios base.

## Spring

* Framework de codigo abierto para hacer aplicaciones java, es flexible para implementar diferentes tipos de arquitectura
* Consta de diferentes modulos que se agrupan en Core container, dataaccess, web, AOP, Instrumentation, Messaging.

## Spring Boot

* permite la creacion facil y rapida de aplicaciones web listas para produccion con el concepto de solo ejecutar.
* Minima configuracion y se complementa con muchos proyectos de spring platform y librerias de terceros.
* reduce en gran medida el tiempo de desarrollo y aumenta la productividad.

---

## Spring Boot - Annotations

Sirve para dar informacion al compilador o a las herramientas de desarrollo para hacer algo, va delante de una clase, metodo o declaracion.

### Ejemplos más usados de Annotations:

* **@Deprecated** → usado para indicar que un metodo es obsoleto
* **@Override** → informa al copilador sobreescribir metodos de la clase padre
* **@SuppressWarnings** → elimina las advertencias que puede generar ese metodo.

### Anotaciones que pertenecen al JPA y son utilizados por Hibernate:

* **@Entity**: clase que va ser mapeada por el ORM con una tabla de la BD.
* **@Table**: especifica detalles de la tabla que ser usada para persistir la entidad en la BD
* **@Id**: cada clase tipo entity va tener un primary key , permite que la BD genere un nuevo valor con cada opercion de insercion.
* **@GeneratedValue**: genera un dato dependiendo del atrivuto, ejemplo: `@GeneratedValue(strategy=generationType.AUTO)`
* **@Column**: detalles de una columna para indicar a que atrivuto o campo sera mapeada.

### @SpringApplication

Se usa para implementar una aplicacion con spring y vemos como spring boot nos ayuda a simplificar las annotation que teniamos con spring Framework.

Ejemplos comunes:

* **@Configuration** → indica la clase en la que se encuentra contienela configuracion principal del proyecto
* **@EnableAutoConfiguration** → se aplicara la configuracion automatica del starter que hemos utilizado, frecuentemente se situa en la clase main.
* **@ComponentScan** → localiza elementos etiquetados con otras anotaciones cuando sean necesarios.

### Para modelar relaciones entre entidades, JPA proporciona varias anotaciones:

* **@OneToMany**: Relación uno-a-muchos
* **@ManyToOne**: Relación muchos-a-uno
* **@OneToOne**: Relación uno-a-uno
* **@ManyToMany**: Relación muchos-a-muchos
* **@JoinColumn**: Especifica la columna de clave foránea
* **@JoinTable**: Define una tabla de unión (para relaciones @ManyToMany)

### Un objeto gestionado por Spring

* **@Component**: anotacion generica para cualquier bean (bean en esencia es un objeto de java)
* **@Service**: para la logica de negocios, crea una instancia en mi contenedor.
* **@Repository**: para el acceso a datos.
* **@Controller**: clase particular cumple la function de un controlador, intermediario entre una interfaz y el algoritmo que implementa.
* **@RequestMapping("/")**: es la URL de la clase, se aplica a todos los metodos. Ejemplo: `@RequestMapping("/hello")` → url: `http://localhost/hello`
* **@RequestParam**: asocia y convierte un parametro HTTP a un parametro Java.
* Otra forma: **@GetMapping("/hola")** → se especifica que es un GET
* **ResponseEntity**: configura y devuelve headers

---

## Entornos

*(se puede usar .yaml en vez de properties)*

### Priority system:

Aquí especifico los modos de operacion que se utilizan en H2, relacionados con los entornos.

* **application.properties** → principal
* **application-staging.properties** → modo basado en archivos, los datos persisten en un archivo despues de reiniciar
* **application-dev.properties** → modo memoria , los datos se almacenan en memoria y se pierden al reiniciar la aplicacion.

*Aparte de modo memoria , modo basado en archivo, existe el modo servido que funciona como un servidor independiente y el modo embebido integrado en la aplicacion.*

### @Value

Inyecta valores individuales, permite la inyeccion directa de `Map<String,String>`.

### @ConfigurationProperties

Ideal para estructuras complejas , permite agrupar propiedas relacionadas , facilita el manejo y validacion centralizada.

### Variables de entrono

Tienen mayor prioridad que los archivos de propiedades.

### Coding Standard

Conjunto de reglas al usar un lenguaje de programación en particular.

---

## Thymeleaf

Es un lenguaje plantilla que nos ayuda generar HTML puro , tomando datos de alguna parte y utilizarlos.

### Sintaxis:

* **Th:text** → si queremos mostrar texto
* **Th:class** → permite modificar las clases de un elemento
* **Th:href** → URL absolutas o relativas
* **Th:each** → iterar sobre colecciones de diferentes tipos de objetos

---

## ¿Qué es la Inyección de Dependencias?

La inyección de dependencias (DI) es un patrón de diseño en el que una clase recibe sus dependencias de fuentes externas en lugar de crearlas internamente. Esto promueve el bajo acoplamiento y facilita las pruebas.

---

## DTO

### Cual es la unica clase de springboot que debe interactuar con identidad?

Repositorios

### Un servicio se puede comunicar con mas de un repositorio, ejemplo:

```java
@Autowired
AlumnoRepositoriy repoAlumno;
@Autowired
AlumnoRepository repoProfe;```


- las consultas HQL se ponen dentro del repository con @Query ejemplo:
@Query("select u from UsuarioModel u where u.userName = ?1 order by u.userName") 
// el ?1 es la posición de ese parámetro en la tabla, cuando esta solo , asume el valor por defecto

------------------------------------------------------------------2do---
## Service definition
función pura: para las mismas entradas tiene las mismas salidas.
un servicio se considera una función pura, con aplicación en una api rest o un api wsl.

#Comunicacion con servicios: 
*SOAP -> aerolíneas bancos
*REST -> todo lo otro básicamente 

Para que sirve una API KEY:
- seguridad: nadie que no este asociado a la pagina 
- saber que usuario esta usando el servicio 

Swagger
librería para definir correctamente las API's

Cuando hacer un SOA (Arquitectura orientada a servicios)
- cuando no se tiene  muy claro como va ser la vita 
- cuando se trabaja en equipo


## Docker/Contenedores

- 








