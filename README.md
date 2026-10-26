# Guia5CrossPlatform

**Aplicación Android de registro ambiental con SQLite**

Proyecto académico desarrollado para la asignatura Computación Móvil de la Universidad Manuela Beltrán, correspondiente a la Guía 5.

## Descripción

Guia5CrossPlatform es una aplicación Android desarrollada en Java y XML mediante Android Studio. Permite registrar y consultar información ambiental de distintos lugares y utiliza SQLite para almacenar los datos localmente.

El proyecto incorpora una pantalla de bienvenida, un menú principal, un contador de clics y un sistema de registros ambientales.

## Funcionalidades

- **Splash:** pantalla de presentación que conduce al menú principal.
- **Menú principal:** navegación entre los distintos módulos.
- **Contador de clics:** permite incrementar y reiniciar un contador, conservando su valor localmente.
- **Registro ambiental:** formulario para ingresar lugar, temperatura, humedad y observaciones.
- **SQLite:** almacenamiento y consulta de información ambiental.
- **Historial:** visualización y eliminación individual o total de registros, con confirmación.

## Tecnologías utilizadas

| Tecnología | Función |
|---|---|
| Java | Programación de la aplicación |
| XML | Diseño de las interfaces |
| Android Studio | Entorno de desarrollo |
| SQLite | Base de datos local |
| Gradle | Configuración y construcción |
| GitHub | Control de versiones y publicación |

## Base de datos

La aplicación utiliza la base de datos `registro_ambiental.db` y la tabla `registros`, que contiene los campos:

- `id`: identificador del registro.
- `lugar`: nombre del lugar.
- `temperatura`: temperatura en grados Celsius.
- `humedad`: porcentaje de humedad.
- `observaciones`: descripción de las condiciones ambientales.
- `fecha`: fecha y hora del registro.

## Instalación y ejecución

1. Descargar o clonar el repositorio desde GitHub.
2. Abrir el proyecto en Android Studio.
3. Sincronizar las dependencias con Gradle.
4. Seleccionar un dispositivo Android o emulador compatible.
5. Compilar y ejecutar la aplicación.

## Evidencias

Las evidencias previstas incluyen capturas del Splash, menú principal, contador, formulario ambiental, historial, funcionamiento de SQLite y resultado de compilación.

Estas evidencias deben incorporarse después de realizar las respectivas pruebas.

## Alcance

Este repositorio contiene la versión Android nativa de la aplicación. La implementación multiplataforma con .NET MAUI en Visual Studio corresponde a un proyecto independiente.

## Autores

**Ariadna Cardona Martínez**

**Julián Eduardo Lozano Ríos**

Universidad Manuela Beltrán

Asignatura: Computación Móvil

Docente: Robert Osorio Torres

Periodo académico: 2026-2

## Repositorio

https://github.com/aricarmar/Guia5CrossPlatform
