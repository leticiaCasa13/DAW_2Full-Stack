Práctica 4  (Api Rest Service)

Una API REST: un programa que escucha peticiones HTTP y responde con datos, normalmente en formato JSON. Esta app:

Recibe una petición GET en /greeting
Responde con un saludo en JSON
Petición:   GET http://localhost:8080/greeting?name=User
Respuesta:  {"id":2,"content":"Hello, User!"}

Conceptos básicos:

HTTP: el protocolo con el que hablan navegador y servidor.
GET: el tipo de petición para pedir datos (es el que usa el navegador al abrir una URL).
JSON: formato de texto para representar datos, con forma {"clave": valor}.
localhost:8080: en el ordenador (localhost), puerto 8080.  La app escucha ahí.

2. El proyecto y el pom.xml

El pom.xml, archivo Maven, la herramienta que descarga las librerías y compila el proyecto. Lo importante:

spring-boot-starter-parent: fija las versiones compatibles de todas las librerías de Spring, para que no tengamos que elegirlas.
spring-boot-starter-webmvc: trae lo necesario para una app web: Spring MVC (la parte que maneja peticiones), Tomcat (el servidor web, ya incluido dentro de la app) 
y Jackson (la librería que convierte objetos Java a JSON).
Ya no instalamos servidor con Spring Boot: Tomcat viene embebido y arranca en la aplicación.


Práctica 05 (spring boot)

Aprender a guardar datos en una base de datos relacional y exponerlos mediante una API, usando una arquitectura por capas. El flujo completo es:

Navegador → Controller → Service → Repository → Base de datos (H2)
                                                       │
Navegador ← JSON ← Controller ← DTOs ← Service ← Entidades

Cada capa tiene una única responsabilidad. Eso es lo que explica por qué hay tantos paquetes.


Las capas, una por una son:

1. model: la entidad Customer
   
@Entity
@Table(name="customers")
public class Customer { ... }

Es la clase Java que representa una tabla de la base de datos. Cada objeto Customer es una fila.

@Entity: le dice a JPA “esta clase se corresponde con una tabla”.
@Table(name="customers"): la tabla se llamará customers.
@Id: este campo es la clave primaria.
@GeneratedValue(strategy = IDENTITY): el id lo genera la base de datos automáticamente (1, 2, 3...). No lo asignas tú.
@Column(name="first_name", nullable=false, length=100): el campo se guarda en la columna first_name, no admite nulos y tiene un máximo de 100 caracteres.
protected Customer() {}: JPA exige un constructor vacío para poder crear objetos al leer de la base de datos. Es protected para que tú no lo uses directamente.
public Customer(String, String): el constructor que usas tú para crear clientes nuevos.

JPA es lo que te permite no escribir SQL: tú trabajas con objetos y él traduce a tablas.

2. repository: CustomerRepository
   
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}

Es la capa que habla con la base de datos. Fíjate en que es una interfaz y no escribiste ninguna implementación: Spring Data JPA la genera por ti.

Al extender JpaRepository<Customer, Long> (entidad, tipo del id) ya tienes gratis:

findAll(): todos los registros
findById(id): buscar por id
save(customer): insertar o actualizar
deleteById(id), count()...

Y además, métodos derivados del nombre: findByLastName(String) no tiene código, pero Spring lo lee, entiende “busca por el campo lastName” y genera la consulta SQL:

sql
SELECT * FROM customers WHERE last_name = ?

Por eso los nombres deben coincidir con los campos de la entidad (findByLastNameAndFirstName, etc.).

3. dto: CustomerDto

public record CustomerDto(String firstName, String lastName) { }

DTO = Data Transfer Object: un objeto que solo sirve para transportar datos hacia fuera.

¿Por qué no devolver directamente Customer? Por tres razones:

La entidad tiene detalles internos (como el id) que quizá no quieres exponer.
Si cambias la tabla, no quieres romper la API que ya usan otros.
Evitas problemas con relaciones entre entidades cuando el proyecto crece.

El DTO define exactamente qué ve el cliente: aquí solo nombre y apellido.

4. service: CustomerService

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = customerRepository.findAll();
        return lc.stream()
                .map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
                .toList();
    }
}

Es la capa de lógica de negocio: aquí van las reglas y las transformaciones. Paso a paso:

@Service: Spring crea una instancia y la gestiona.
@Autowired: inyección de dependencias. Tú no haces new CustomerRepository(); Spring te da la instancia ya creada. Es uno de los pilares de Spring.
findAll(): obtiene todos los Customer de la base de datos.
.stream().map(...).toList(): recorre la lista y convierte cada Customer en un CustomerDto. Es la transformación de entidad a DTO.

5. controller.rest: CustomerResource
   
@RestController
@RequestMapping("/customers")
public class CustomerResource {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}

Es la capa que recibe las peticiones HTTP.
