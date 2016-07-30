# REST

Ejemplo de RESTful API en Java con Jersey, MySQL y Tomcat

El ejemplo reside en el servidor OpenShift y pueden probarse los métodos GET, desde un cliente REST (no desde el navegador) en estas URLs:

GET todas las personas de la b.d.: http://rest-trapasoft.rhcloud.com/rest/personas
GET una persona, sabiendo su id: http://rest-trapasoft.rhcloud.com/rest/personas/25 (hay números que no existen, debido a las pruebas de borrado: existen desde el 1 hasta el 300, con huecos)
GET de una persona, por id, recuperando también el nombre del departamento al que pertenece: http://rest-trapasoft.rhcloud.com/rest/personas/25?dept=true

Documentación: próximamente en http://investigando.esy.es




