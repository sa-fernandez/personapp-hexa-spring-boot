# PersonApp-Hexa-Spring-Boot 🛐

Este proyecto corresponde a la implementación de arquitectura limpia de una aplicación que permite gestionar personas, sus profesiones, estudios y teléfonos asociados. 

El proyecto ofrece dos formas de interacción: a través de una API REST con Swagger y mediante una interfaz de consola de comandos (CLI).

## Requisitos

Antes de ejecutar este proyecto, asegúrate de tener instalados Maven, Docker y Docker Compose en tu máquina. Además, se recomienda tener las extensiones o plugins de Lombok y Spring Boot habilitados en tu entorno de desarrollo.

## Instrucciones de configuración y ejecución
1. Abrir una consola de comandos o terminal en la raíz del proyecto. 
2. Ejecutar el comando `docker compose up` para construir y levantar los contenedores de las bases de datos.
3. Esperar unos segundos a que el proceso se complete. 
4. Ejecutar el comando `mvn install`. 

## Instrucciones de ejecución
- Para acceder al API REST, inicie el proyecto relacionado con `rest-input-adapter`. Posterior a esto, acceder en su navegador de preferencia al enlace `http://localhost:3000` o `http://127.0.0.1:3000` para visualizar la aplicación en ejecución. 
- Para acceder a la interfaz por CLI, inicie el proyecto relacionado con `cli-input-adapter`. Posterior a esto, podrá visualizar todas las opciones de funcionalidad de la aplicación desde la terminal de comandos. 

## Licencia

Este proyecto está bajo la Licencia Apache.

## Colaboradores

- Santiago Fernández (sa.fernandez@javeriana.edu.co)
- Juan Sebastián Vargas (vargas_jsebastian@javeriana.edu.co)
- Andrés Vásquez (af.vasquezr@javeriana.edu.co)
