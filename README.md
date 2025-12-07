# Verteilte Systeme: RESTful Webservices

## Aufgabenstellung

Dies ist ein Projekt-Template für Ihre erste RESTful-Webservice-Applikation rund um die CRUD-Methoden der Ressource (Entity) *Student*.

Die Aufgabenstellung finden Sie [hier](AUFGABENSTELLUNG.md).


## JAX-RS 3.0 RESTful Webservices in Java mit JAX-RS und Jersey

[Jakarta RESTful Web Services 3.0](https://jakarta.ee/specifications/restful-ws/3.0) spezifiziert die Unterstützung von RESTful-Webservice-APIs in Java. 

[Eclipse Jersey](https://eclipse-ee4j.github.io/jersey/) ist die zugehörige JAX-RS-Referenzimplementierung für eine konkrete Umsetzung. Nutzen Sie die Dokumentationsseiten und weitere Suchergebnisse im Netz zu diesen Themen, sofern Sie sich mit dieser Thematik tiefer befassen möchten.


## (JAX-RS) Clients

JAX-RS bietet natürlich auch API-Klassen an, um eine Client-Anwendung in Java zu implementieren. 

Im Rahmen der Übungen werden zur Vereinfachung aber nur Browser-Plugins als generische Client verwendet. 
Installieren Sie hierzu ein passendes Plugin für Ihren Browser, z. B. [RESTer for Crome](https://chrome.google.com/webstore/detail/rester/eejfoncpjfgmeleakejdcanedmefagga?hl=en) oder für [Firefox](https://addons.mozilla.org/en-US/firefox/addon/rester/).

Alternativ sind auch ein Kommandozeilentool wie [HTTPie](https://httpie.io) oder ein umfänglicheres Testtool wie [Postman](https://www.postman.com/product/api-client/) möglich.

- Create
  ```shell
  cat jeffery.xml | http POST localhost:8081/api/v1/students/ Content-Type:application/xml
  ```
- Read
  ```shell
  http localhost:8081/api/v1/students/1
  http localhost:8081/api/v1/students/all
  ```
- Update
  ```shell
  cat luna.xml | http PUT localhost:8081/api/v1/students/1 Content-Type:application/xml
  ```
- Delete
  ```shell
  http DELETE localhost:8081/api/v1/students/2
  ```
