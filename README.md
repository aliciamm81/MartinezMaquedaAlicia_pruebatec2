# Desarrollo de una Aplicación de Gestión de Turnos

**Martinez Maqueda Alicia - pruebatec2**

Ejercicio para la **pruebatec2** de Java avanzado - Documentación del proyecto.

## Comenzando 🚀

Con estas instrucciones podrás obtener
una copia del proyecto en tu repositorio local y también conocerás el funcionamiento de la aplicación.

## Pre-requisitos 📋

Para arrancar el proyecto es necesario tener instalado JDK 17, un entorno de desarrollo integrado (IDE) y un motor de
base
de datos.

## Instalación 🔧

Para empezar hay que tener creada una base de datos con la siguiente sentencia:

#### Crear base de datos :

```
CREATE DATABASE IF NOT EXISTS `gestor_turnos` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `gestor_turnos`;

```
Al tener configurado en la unidad de persistencia la opción de crear, las tablas se crearán automáticamente cuando se realice el primer registro. 

## Despliegue 📦

Para desplegar la aplicación en local se pueden seguir los siguiente pasos: 
1. Clonar o descargar el repositorio desde el enlace de GitHub a un entorno local.
2. Asegurarse de tener configurado un servidor compatible para alojar la base de datos.
3. Ejecutar las migraciones de base de datos o scripts SQL proporcionados para crearla.
4. Configurar un servidor de aplicaciones compatible, como Apache Tomcat, para desplegar la aplicación Java.
5. Utilizar Maven u otra herramienta de gestión de dependencias para asegurar que todas las dependencias del proyecto estén instaladas y configuradas correctamente.
6. Compilar y ejecutar la aplicación utilizando un entorno de desarrollo integrado (IDE) o la línea de comandos.
7. Acceder a la aplicación a través de un navegador web, utilizando la URL proporcionada por la aplicación desplegada en el servidor de aplicaciones.

## Instrucciones de la aplicación 📄

Es una aplicación web que permite a los usuarios gestionar turnos para diferentes trámites y ciudadanos.

**Interfaz** 

**Crear Turno:** Esta es la primera opción del menú lateral, al realizar click se abrirá un formulario que permite ingresar los detalles del nuevo turno. Una vez completado, al presionar el botón "Registrar Turno", se almacenará la información en la base de datos.

Al acceder a esta sección, se presentará un formulario detallado para ingresar la información del nuevo turno. Se solicitará el número del turno, fecha del trámite, descripción del trámite y los detalles del ciudadano asignado al turno. Una vez completado, hacer clic en "Registrar Turno" almacenará la información en la base de datos.

**Listar Turnos:** Es la segunda opción del menú lateral, en esta opción se permite visualizar todos los turnos existentes. Al marcar la casilla "Mostrar", se desplegará una tabla con la lista de todos los turnos registrados en la base de datos.

1. Listar Todos: Al seleccionar esta opción, se mostrará una tabla con todos los turnos existentes en la base de datos, detallando número, fecha, estado y ciudadano asignado.

2. Filtrado de Turnos: Aquí, puedes filtrar los turnos en base a una fecha específica y el estado del turno, ya sea "En Espera" o "Atendido". Para ello, introduce la fecha deseada y selecciona el estado del turno. Posteriormente, al hacer clic en "Mostrar", se mostrarán los turnos que cumplan con los criterios especificados.

Dentro de esta tabla, al lado de cada registro, se dispone de la opción de modificar su estado de "En espera" y "Atendido", para ello hay que hacer click en el estado al que se quiere modificar y se cambiará automáticamente.  


## Pruebas ⚙️

En esta sección realizaré una serie de pruebas que demostrarán si la aplicación puede realizar las funciones básicas
de una base de datos **CRUD**.

### 1. Crear un turno
#### Caso 1: Registro Exitoso de Turno

    Descripción: Se completa el formulario con datos válidos y se registra el turno.
    Acciones:
```
        Nombre: Juan
        Primer Apellido: Pérez
        Segundo Apellido: García
        Fecha Nacimiento: 01/01/1990
        Email: juan@example.com
        DNI: 12345678A
        Teléfono: 123456789
        Dirección: Calle Principal 123
        Tipo de Trámite: Renovación de Documentos
        Fecha del Turno: 30/11/2023
        Descripción del Trámite: Renovación de Carnet de Conducir
```
#### Resultado obtenido: 

El turno se registra correctamente en la base de datos.

#### Caso 2: Registro de Turno con Datos Incompletos

    Descripción: Se intenta registrar un turno dejando campos obligatorios en blanco.
    Acciones:

```    
        Nombre: Juan
        Primer Apellido: Pérez
        Fecha Nacimiento: 01/01/1990
        DNI: 12345678A
        Tipo de Trámite: Renovación de Licencia
        Descripción del Trámite: Renovación de Carnet de Conducir
        Fecha del Turno: 30/11/2023
        Email, Segundo Apellido, Teléfono, Dirección: Campos vacíos.

```         

#### Resultado obtenido:

Se muestra un mensaje de error indicando los campos obligatorios faltantes.

### 2. Listar turnos
#### Caso 1: Visualizar Todos los Turnos Registrados


    Descripción: El usuario accede a la opción "Listar Turnos" sin aplicar ningún filtro.
    Acciones:
```  
        Selecciona la opción "Listar Todos".
        Presiona el botón "Mostrar".

```      
#### Resultado obtenido: 
Se muestra una tabla con todos los turnos registrados en la base de datos.

#### Caso 2: Filtrar Turnos por Fecha y Estado

    Descripción: El usuario aplica un filtro de fecha y estado para visualizar turnos específicos.
    Acciones:
```  
        Fecha: 2023-11-30
        Estado: "En Espera"
        Presiona el botón "Mostrar".

```  
#### Resultado obtenido:
Se muestran solo los turnos que cumplen con los criterios especificados en una tabla.


## Versionado 📌

Versión: 1.0.0

## Autores ✒️

* **Alicia Martínez Maqueda** *https://github.com/aliciamm81*