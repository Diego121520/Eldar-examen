# Eldar-examen

## Capturas de pantalla

![image](https://github.com/Diego121520/Eldar-examen/assets/72827720/7371ce48-d791-4bdb-8275-be452db6f614)

![image](https://github.com/Diego121520/Eldar-examen/assets/72827720/3a75b950-5ba9-4678-9514-efa78c97fba9)


## Tecnologías Utilizadas

- Java: 17
- Spring Boot: 3.1.5
- Angular: 16.2.9
- MySQL
- Hibernate
- Bootstrap
- HTML/CSS
- Git

## Instrucciones de Instalación

1. Clona este repositorio en tu máquina local:

2. Configura la base de datos MySQL según las especificaciones en `application.properties`:
url=jdbc:mysql://localhost:3306/birthday_management
username=root
password=

3. Ejecuta el backend de Spring Boot y el servidor de Angular.

4. Accede a la aplicación en tu navegador: http://localhost:4200

## Estructura del Proyecto 

- `backend/`: Contiene el código del servidor Spring Boot.
- `frontend/`: Contiene el código de la aplicación Angular.

### Frontend

## Funcionalidades Clave

- Mover invitados de una lista a otra: La aplicación permite arrastrar los invitados de una lista a otra manteniendo presionado el "click" izquierdo.
- Agregar invitados: La aplicacion permite agregar invitados con los siguientes datos: nombre, apellido, dni, fecha de nacimiento.
- Editar invitados : La aplicación permite editar cualquier información de los invitados.
- Eliminar invitados : La aplicación permite eliminar invitados.
- Confirmar ivitados : La aplicación permite "enviar" la invitacioón a los clientes que se encuentren en la lista de "confirmados".
- Obtener invitados sin confirmar : La aplicación muentra todos los invitados sin confirmar.

