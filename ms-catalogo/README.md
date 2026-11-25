# ms-catalogo (versión sencilla)

Java 17, Spring Boot 3.5.8, Oracle, JWT, Swagger. Puerto 8082.
Ejecutar el SQL de catálogo del informe y abrir esta carpeta (la que contiene pom.xml) en VS Code. Ejecutar MsCatalogoApplication.java o `mvn spring-boot:run`.
Swagger: http://localhost:8082/swagger-ui.html

La configuración local usa C##ms_catalogo. application-local.yml es local y está ignorado por Git. No publicar secretos; para otro ambiente usar JWT_SECRET y DB_PASSWORD.

CRUD: /api/regiones, /api/comunas, /api/categorias, /api/colecciones, /api/productos, /api/ofertas. GET público; POST, PUT y DELETE requieren token admin de identidad. Las rutas adicionales anteriores se conservan. Los datos JSON de éxito se mantienen.

Ejemplo: POST /api/categorias con Bearer token admin y body {"nombre":"Prueba catálogo"}; luego GET, PUT y DELETE usando el ID que devolvió el POST. Verificar 201, 200, 200, 204. Sin token, POST debe dar 401; con token cliente, 403.

No se incluye src/test, target ni Node. target aparecerá automáticamente al compilar. Las pruebas Vitest se realizan en React y las de integración se documentan en Postman.
