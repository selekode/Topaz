# Topaz

**Topaz** es una aplicación de diario y desarrollo personal desarrollada en Java y Spring Boot, con una interfaz web moderna integrada mediante JavaFX y Tailwind. Está pensada para ayudarte a reflexionar, regular tus emociones y mejorar tu bienestar a través de escritura, estadísticas y ejercicios de introspección.

## ¿Qué es Topaz?

Topaz es una aplicación diseñada para ayudarte a registrar tus pensamientos, emociones y objetivos personales de manera sencilla. A través de entradas de diario y revisiones emocionales, puedes reflexionar sobre tu día y hacer un seguimiento de tu bienestar.

Además, Topaz ofrece estadísticas que analizan tus entradas de diario, mostrando patrones en tus emociones y productividad, lo que te permite comprender mejor tu progreso y tomar decisiones informadas para tu crecimiento personal.

La función de **Trabajo interno** te guía en ejercicios de autoconocimiento y desarrollo personal, con preguntas introspectivas diseñadas para profundizar en tus experiencias, valores y creencias, fomentando una mayor claridad y equilibrio emocional.

Finalmente, Topaz incluye una función de **meditación** con un temporizador ajustable, ayudándote a encontrar momentos de calma y concentración a lo largo del día.

## Funcionalidades

- 📝 Registro de diario libre o estructurado (modo revisión)
- 📈 Estadísticas visuales sobre emociones y hábitos
- 💡 Ejercicios de trabajo interno con preguntas reflexivas
- 🧘‍♀️ Temporizador de meditación con música integrada
- 🔁 Seguimiento de rachas y recordatorios diarios
- 🌐 Interfaz web embebida usando JavaFX + WebView

## Tecnologías utilizadas

- **Lenguaje principal**: Java 21
- **Framework backend**: Spring Boot 3.4.1
- **Base de datos**: SQLite
- **Frontend**: Thymeleaf + Tailwind CSS
- **Gráficos**: Chart.js
- **Integración de interfaz**: JavaFX (WebView)

## Estructura del proyecto

- `topaz/`
  - `controller/`  
    Controladores Spring MVC que manejan las rutas y la lógica de las peticiones del usuario.

  - `model/`  
    Clases de dominio que representan las entidades del sistema.
    
  - `database/`  
    Lógica de inicialización de la base de datos. Contiene `DatabaseCreator`, que crea automáticamente el archivo `.db` en `C:/Topaz/data`.

  - `repository/`  
    Interfaces para el acceso a datos usando `SQLite`.

  - `service/`  
    Lógica de negocio de la aplicación.

  - `utils/`  
    Métodos de utilidad como formateadores.

  - `webview/`  
    Código JavaFX para cargar la interfaz web dentro de un WebView.

  - `templates/`  
    Archivos HTML Thymeleaf que componen la interfaz de usuario.

  - `static/`  
    Archivos estáticos como hojas de estilo, scripts, etc.

  - `TopazApplication.java`  
    Clase principal que lanza la aplicación Spring Boot.

## Estado del proyecto

🔧 Actualmente en desarrollo.  
📌 Versión: `25.6.alpha`  
🔍 Nota: La nomenclatura hace referencia a la versión del desarrollo en curso. En este caso:  
- **25.6** → Año 2025, mes 6 (junio)  
- **alpha** → Primera versión funcional, aún sin pulir ni publicada oficialmente.

## Persistencia de datos

Topaz utiliza SQLite para guardar tus datos de forma local.  
Está **pensado para funcionar en sistemas Windows**.

- Al iniciar la aplicación por primera vez, se crea automáticamente el archivo de base de datos en: 'C:\Topaz\data\topazdatabase.db'

## Autor

Desarrollado por [Sean Leitch](https://selekode.github.io/SeanLeitch/)  
GitHub: [@selekode](https://github.com/selekode)



