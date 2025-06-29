# Franchise System API

Este proyecto implementa un sistema de gestión de franquicias utilizando una arquitectura moderna basada en contenedores, infraestructura como código, despliegue continuo y buenas prácticas de desarrollo.

## 🧱 Arquitectura del Proyecto

El backend está diseñado siguiendo los principios de **Clean Architecture**, lo que permite una alta separación de responsabilidades y facilita el mantenimiento, testeo y escalabilidad del sistema.

### 📦 Estructura de paquetes

```text
com.mariavasquez.franchis.system
├── application
│   ├── mapper              # Conversión entre entidades y DTOs
│   └── usecase             # Casos de uso (lógica de aplicación)
├── config
│   ├── CorsGlobalConfiguration
│   └── SwaggerConfig       # Configuración de CORS y Swagger
├── domain
│   ├── model               # Entidades del dominio
│   └── port                # Interfaces para la infraestructura (salientes)
├── infrastructure
│   ├── r2dbc               # Implementación R2DBC (repositorios reactivos)
│   └── web                 # Controladores (adapters de entrada)
├── shared
│   ├── constants
│   ├── dto                 # Objetos de transferencia
│   └── exception           # Manejo de errores y respuestas
└── Application             # Clase principal de arranque
```

---

## 🚀 Tecnologías Utilizadas

- **Spring Boot + WebFlux**
- **R2DBC** para acceso reactivo a base de datos
- **MySQL** como motor de base de datos
- **Swagger/OpenAPI** para documentación y pruebas
- **SonarQube** para análisis de calidad de código
- **Docker** para contenerización
- **Terraform** para provisión de infraestructura
- **GitHub Actions** para CI/CD
- **AWS (RDS, ECS Fargate, ECR, ALB, ACM, Security Groups, Route53)**

---

## ✅ CI/CD

- **GitHub Actions** realiza:
    - Build del proyecto
    - Análisis de código con **SonarQube**
    - Construcción de imagen Docker
    - Push a **ECR**
    - Despliegue en **ECS Fargate**
    - Validación del estado

---

## 🌐 Documentación Interactiva

La documentación de la API está disponible en:

🔗 **[https://api.mvdeccenter.tech/api/docs](https://api.mvdeccenter.tech/api/docs)**

Desde aquí puedes probar todos los endpoints REST expuestos.

---

## 🧪 Pruebas e Inicialización

- **RDS MySQL 8.0.36** gestionado con Terraform
- **Inicialización del esquema** mediante una ECS Task `init` única
- Swagger activo para ambientes de prueba
- Validación y errores gestionados con excepciones personalizadas

---

## 📦 Infraestructura como Código (Terraform)

Todo el entorno fue construido con Terraform:

- VPC, subredes y grupos de seguridad
- RDS (MySQL)
- ECS (clúster y tareas)
- ECR (repositorio de imágenes)
- ALB + Target Group
- Certificados SSL (ACM) y dominio `.tech`
- DNS y mapeo HTTPS

---

## 📂 Estructura General

```text
├── app/
│ ├── Dockerfile
│ └── src/...
├── terraform/
│ ├── main.tf
│ ├── variables.tf
│ └── modules/
├── .github/
│ └── workflows/
│ └── ci-cd.yml
├── sonar-project.properties
└── README.md
```

---

## 🧑‍💻 Autor

Desarrollado por [Maria Vásquez](mailto:mfvasquez1890@gmail.com)
