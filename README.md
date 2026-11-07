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
