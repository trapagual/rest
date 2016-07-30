# REST

Ejemplo de RESTful API en Java con Jersey, MySQL y Tomcat

El ejemplo reside en el servidor OpenShift y pueden probarse los métodos GET, desde un cliente REST (no desde el navegador) en estas URLs:

+ GET todas las personas de la b.d.: http://rest-trapasoft.rhcloud.com/rest/personas

+ GET una persona, sabiendo su id: http://rest-trapasoft.rhcloud.com/rest/personas/25 (hay números que no existen, debido a las pruebas de borrado: existen desde el 1 hasta el 300, con huecos)

+ GET de una persona, por id, recuperando también el nombre del departamento al que pertenece: http://rest-trapasoft.rhcloud.com/rest/personas/25?dept=true

El sistema admite peticiones JSON y devuelve objetos JSON.

Puede utilizarse con clientes REST, como el complemento Advanced Rest Client Application, para Chrome, u otros complementos de navegador o clientes de otro tipo, de los que abundan por la red.

Se usará este sistema para posteriores pruebas de clientes web y android.

Documentación: próximamente en http://investigando.esy.es




