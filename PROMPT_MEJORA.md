# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/investment/portfolio/application/service/InvestorRequestServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/investment/portfolio/Application.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/investment/portfolio/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/investment/portfolio/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.operationType`: Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.id`: Se invoca `id` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.status`: Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.investorId`: Se invoca `investorId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.amount`: Se invoca `amount` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `InvestorRequest.operationType`: Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `InvestorRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.getId`: Se invoca `getId` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.setStatus`: Se invoca `setStatus` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.setFailureReason`: Se invoca `setFailureReason` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.id`: Se invoca `id` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.operationType`: Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.status`: Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.failureReason`: Se invoca `failureReason` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.investorId`: Se invoca `investorId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.portfolioId`: Se invoca `portfolioId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.amount`: Se invoca `amount` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.assetId`: Se invoca `assetId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.createdAt`: Se invoca `createdAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.updatedAt`: Se invoca `updatedAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.processedAt`: Se invoca `processedAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.isPresent`: Se invoca `isPresent` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.get`: Se invoca `get` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.status`: Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.failureReason`: Se invoca `failureReason` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de Servicio en Framework
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno — objetivo: Establecer el entorno de desarrollo y configurar las variables de entorno necesarias para el servicio. — entregable (NO resolver): Entorno de desarrollo configurado con las variables de entorno necesarias.
- Fase 2: Enrutamiento de Solicitudes — objetivo: Implementar la funcionalidad para enrutar las solicitudes de los inversores a los componentes apropiados. — entregable (NO resolver): Servicio con enrutamiento de solicitudes idempotente.
- Fase 3: Generación de Registros de Trazas — objetivo: Implementar la funcionalidad para generar registros de trazas para las solicitudes manejadas por el servicio. — entregable (NO resolver): Servicio con generación de registros de trazas.
- Fase 4: Conexión con Base de Datos — objetivo: Implementar la funcionalidad para conectar el servicio con la base de datos utilizando un ORM. — entregable (NO resolver): Servicio conectado a la base de datos utilizando un ORM.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.investment</groupId>
    <artifactId>portfolio</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>investment-portfolio</name>
    <description>Platform for managing investor portfolios with idempotency and high throughput</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Logging -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>

        <!-- OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.9.1</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/investment/portfolio/Application.java ===
package com.investment.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class Application implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final ApplicationProperties applicationProperties;

    public Application(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Application started with properties: maxRequestsPerSecond={}, idempotencyKeyTtl={} seconds",
                applicationProperties.getMaxRequestsPerSecond(),
                applicationProperties.getIdempotencyKeyTtl());
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(applicationProperties.getThreadPoolCoreSize());
        executor.setMaxPoolSize(applicationProperties.getThreadPoolMaxSize());
        executor.setQueueCapacity(applicationProperties.getThreadPoolQueueCapacity());
        executor.setThreadNamePrefix("investor-request-");
        executor.initialize();
        return executor;
    }

    private void validateProperties() {
        if (applicationProperties.getMaxRequestsPerSecond() <= 0) {
            throw new IllegalStateException("maxRequestsPerSecond must be positive");
        }
        if (applicationProperties.getIdempotencyKeyTtl() <= 0) {
            throw new IllegalStateException("idempotencyKeyTtl must be positive");
        }
        if (applicationProperties.getThreadPoolCoreSize() <= 0 ||
            applicationProperties.getThreadPoolMaxSize() <= 0 ||
            applicationProperties.getThreadPoolQueueCapacity() <= 0) {
            throw new IllegalStateException("Thread pool configuration values must be positive");
        }
        if (applicationProperties.getThreadPoolMaxSize() < applicationProperties.getThreadPoolCoreSize()) {
            throw new IllegalStateException("maxPoolSize must be >= corePoolSize");
        }
    }
}
"

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: investment-portfolio
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/investment_portfolio}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: ${DB_POOL_SIZE:20}
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: ${DDL_AUTO:none}
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 50
        order_inserts: true
        order_updates: true
  mvc:
    async:
      request-timeout: 30000

server:
  port: ${SERVER_PORT:8080}
  tomcat:
    threads:
      max: ${TOMCAT_THREADS_MAX:200}
    connection-timeout: 20000

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    tags:
      application: ${spring.application.name}

application:
  max-requests-per-second: ${MAX_REQUESTS_PER_SECOND:1500}
  idempotency-key-ttl: ${IDEMPOTENCY_KEY_TTL:86400} # 24 horas en segundos
  thread-pool:
    core-size: ${THREAD_POOL_CORE_SIZE:50}
    max-size: ${THREAD_POOL_MAX_SIZE:100}
    queue-capacity: ${THREAD_POOL_QUEUE_CAPACITY:1000}

logging:
  level:
    root: INFO
    com.investment.portfolio: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.orm: INFO
    org.springframework.transaction: INFO

// === ARCHIVO: src/main/java/com/investment/portfolio/domain/model/InvestorRequest.java ===
package com.investment.portfolio.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InvestorRequest(
    UUID id,
    @NotNull(message = "El tipo de operación no puede ser nulo")
    OperationType operationType,
    @NotNull(message = "El inversor ID no puede ser nulo")
    UUID investorId,
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,
    @NotNull(message = "La clave de idempotencia no puede ser nula")
    String idempotencyKey,
    LocalDateTime createdAt,
    RequestStatus status,
    String failureReason
) {
    public enum OperationType {
        PORTFOLIO_QUERY,
        INVESTMENT,
        WITHDRAWAL
    }

    public enum RequestStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    public InvestorRequest {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
    }

    public InvestorRequest withStatus(RequestStatus newStatus) {
        return new InvestorRequest(
            this.id,
            this.operationType,
            this.investorId,
            this.amount,
            this.idempotencyKey,
            this.createdAt,
            newStatus,
            this.failureReason
        );
    }

    public InvestorRequest withFailureReason(String reason) {
        return new InvestorRequest(
            this.id,
            this.operationType,
            this.investorId,
            this.amount,
            this.idempotencyKey,
            this.createdAt,
            RequestStatus.FAILED,
            reason
        );
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/domain/port/in/InvestorRequestPort.java ===
package com.investment.portfolio.domain.port.in;

import com.investment.portfolio.domain.model.InvestorRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InvestorRequestPort {
    InvestorRequest processInvestorRequest(@NotNull @Valid InvestorRequest request);

    InvestorRequest getInvestorRequestById(@NotNull UUID requestId);

    InvestorRequest getInvestorRequestByIdempotencyKey(@NotNull String idempotencyKey);
}

// === ARCHIVO: src/main/java/com/investment/portfolio/domain/port/out/InvestorRequestPersistencePort.java ===
package com.investment.portfolio.domain.port.out;


import com.investment.portfolio.domain.model.RequestStatus;
import com.investment.portfolio.domain.model.InvestorRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface InvestorRequestPersistencePort {
    InvestorRequest save(@NotNull @Valid InvestorRequest request);

    Optional<InvestorRequest> findById(@NotNull UUID requestId);

    Optional<InvestorRequest> findByIdempotencyKey(@NotNull String idempotencyKey);

    void updateStatus(@NotNull UUID requestId, @NotNull InvestorRequest.RequestStatus status);

    void updateFailureReason(@NotNull UUID requestId, @NotNull String failureReason);
}

// === ARCHIVO: src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java ===
package com.investment.portfolio.application.service;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import com.investment.portfolio.domain.port.in.InvestorRequestPort;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Servicio de aplicación que implementa la lógica de negocio para enrutar y manejar
 * solicitudes de inversores, garantizando idempotencia y registrando trazas.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvestorRequestService implements InvestorRequestPort {

    private final InvestorRequestPersistencePort persistencePort;

    @Override
    @Transactional
    public InvestorRequest processInvestorRequest(@NotNull @Valid InvestorRequest request) {
        log.info("Iniciando procesamiento de solicitud de inversor: operationType={}, idempotencyKey={}",
                request.operationType(), request.idempotencyKey());

        Optional<InvestorRequest> existingRequest = persistencePort.findByIdempotencyKey(
                request.idempotencyKey());

        if (existingRequest.isPresent()) {
            InvestorRequest cached = existingRequest.get();
            log.info("Solicitud procesada previamente encontrada con id: {}", cached.id());
            return cached;
        }

        InvestorRequest savedRequest = persistencePort.save(request);
        log.info("Solicitud guardada exitosamente con id: {}", savedRequest.id());

        try {
            InvestorRequest processedRequest = executeOperation(savedRequest);
            persistencePort.updateStatus(processedRequest.id(), processedRequest.status());
            log.info("Operación completada exitosamente para solicitud: {}", processedRequest.id());
            return processedRequest;
        } catch (Exception e) {
            log.error("Error al procesar solicitud: {}", e.getMessage(), e);
            persistencePort.updateStatus(savedRequest.id(), RequestStatus.FAILED);
            persistencePort.updateFailureReason(savedRequest.id(), e.getMessage());
            throw e;
        }
    }

    @Override
    public InvestorRequest getInvestorRequestById(@NotNull UUID requestId) {
        log.debug("Consultando solicitud por id: {}", requestId);
        return persistencePort.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solicitud no encontrada con id: " + requestId));
    }

    @Override
    public InvestorRequest getInvestorRequestByIdempotencyKey(@NotNull String idempotencyKey) {
        log.debug("Consultando solicitud por clave de idempotencia: {}", idempotencyKey);
        return persistencePort.findByIdempotencyKey(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solicitud no encontrada con clave de idempotencia: " + idempotencyKey));
    }

    private InvestorRequest executeOperation(InvestorRequest request) {
        log.info("Ejecutando operación: {}", request.operationType());

        return switch (request.operationType()) {
            case CONSULT_CARGO -> {
                log.info("Consultando cartera del inversor: {}", request.investorId());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
            case INVEST -> {
                log.info("Procesando inversión para inversor: {}, monto: {}",
                        request.investorId(), request.amount());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
            case WITHDRAW -> {
                log.info("Procesando retiro para inversor: {}, monto: {}",
                        request.investorId(), request.amount());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
        };
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java ===
package com.investment.portfolio.infrastructure.adapter.in.web;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.port.in.InvestorRequestPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador REST que expone endpoints para las operaciones de consulta,
 * inversión y retiro de fondos.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/investor-requests")
@RequiredArgsConstructor
@Tag(name = "Investor Requests", description = "API para gestionar solicitudes de inversores")
public class InvestorRequestController {

    private final InvestorRequestPort investorRequestPort;

    @PostMapping
    @Operation(summary = "Procesar solicitud de inversor",
            description = "Procesa una solicitud de inversión, consulta o retiro de fondos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud procesada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto de idempotencia",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> processInvestorRequest(
            @Valid @RequestBody InvestorRequest request) {
        log.info("Recibida solicitud de tipo: {}, idempotencyKey: {}",
                request.operationType(), request.idempotencyKey());

        InvestorRequest processedRequest = investorRequestPort.processInvestorRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedRequest);
    }

    @GetMapping("/{requestId}")
    @Operation(summary = "Consultar solicitud por ID",
            description = "Obtiene una solicitud de inversor por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> getInvestorRequestById(
            @PathVariable UUID requestId) {
        log.info("Consultando solicitud con id: {}", requestId);
        InvestorRequest request = investorRequestPort.getInvestorRequestById(requestId);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/idempotency/{idempotencyKey}")
    @Operation(summary = "Consultar solicitud por clave de idempotencia",
            description = "Obtiene una solicitud de inversor por su clave de idempotencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<InvestorRequest> getInvestorRequestByIdempotencyKey(
            @PathVariable String idempotencyKey) {
        log.info("Consultando solicitud con clave de idempotencia: {}", idempotencyKey);
        InvestorRequest request = investorRequestPort.getInvestorRequestByIdempotencyKey(idempotencyKey);
        return ResponseEntity.ok(request);
    }

    @Schema(description = "Respuesta de error estándar")
    public record ErrorResponse(
            @Schema(description = "Código de error", example = "INVALID_REQUEST")
            String errorCode,
            @Schema(description = "Mensaje de error", example = "La solicitud es inválida")
            String message,
            @Schema(description = "Ruta de la solicitud que causó el error")
            String path) {
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java ===
package com.investment.portfolio.infrastructure.adapter.out.persistence;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import com.investment.portfolio.infrastructure.adapter.out.persistence.entity.InvestorRequestEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto de salida utilizando
 * Spring Data JPA y JPA Repository para interactuar con la base de datos.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InvestorRequestJpaAdapter implements InvestorRequestPersistencePort {

    private final InvestorRequestRepository repository;
    private final InvestorRequestMapper mapper;

    @Override
    @Transactional
    public InvestorRequest save(@NotNull @Valid InvestorRequest request) {
        log.debug("Guardando solicitud de inversor: idempotencyKey={}", request.idempotencyKey());
        InvestorRequestEntity entity = mapper.toEntity(request);
        InvestorRequestEntity savedEntity = repository.save(entity);
        log.info("Solicitud guardada con id: {}", savedEntity.getId());
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorRequest> findById(@NotNull UUID requestId) {
        log.debug("Buscando solicitud por id: {}", requestId);
        return repository.findById(requestId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorRequest> findByIdempotencyKey(@NotNull String idempotencyKey) {
        log.debug("Buscando solicitud por clave de idempotencia: {}", idempotencyKey);
        return repository.findByIdempotencyKey(idempotencyKey)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void updateStatus(@NotNull UUID requestId, @NotNull RequestStatus status) {
        log.debug("Actualizando estado de solicitud {} a {}", requestId, status);
        InvestorRequestEntity entity = repository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Solicitud no encontrada con id: " + requestId));
        entity.setStatus(mapper.toEntityStatus(status));
        repository.save(entity);
        log.info("Estado actualizado exitosamente para solicitud: {}", requestId);
    }

    @Override
    @Transactional
    public void updateFailureReason(@NotNull UUID requestId, @NotNull String failureReason) {
        log.debug("Actualizando razón de fallo para solicitud: {}", requestId);
        InvestorRequestEntity entity = repository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Solicitud no encontrada con id: " + requestId));
        entity.setFailureReason(failureReason);
        entity.setStatus(mapper.toEntityStatus(RequestStatus.FAILED));
        repository.save(entity);
        log.info("Razón de fallo actualizada para solicitud: {}", requestId);
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java ===
package com.investment.portfolio.infrastructure.adapter.out.persistence.entity;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.OperationType;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investor_requests", indexes = {
    @Index(name = "idx_idempotency_key", columnList = "idempotency_key", unique = true),
    @Index(name = "idx_request_status", columnList = "status"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestorRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 255)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false, length = 50)
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private RequestStatus status;

    @Column(name = "failure_reason", length = 1000)
    private String failureReason;

    @Column(name = "investor_id", nullable = false, length = 100)
    private String investorId;

    @Column(name = "portfolio_id", length = 100)
    private String portfolioId;

    @Column(name = "amount", precision = 19, scale = 4)
    private java.math.BigDecimal amount;

    @Column(name = "asset_id", length = 100)
    private String assetId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Version
    @Column(name = "version")
    private Long version;

    public static InvestorRequestEntity fromDomain(InvestorRequest request) {
        if (request == null) {
            return null;
        }
        return InvestorRequestEntity.builder()
                .id(request.id())
                .idempotencyKey(request.idempotencyKey())
                .operationType(request.operationType())
                .status(request.status())
                .failureReason(request.failureReason())
                .investorId(request.investorId())
                .portfolioId(request.portfolioId())
                .amount(request.amount())
                .assetId(request.assetId())
                .createdAt(request.createdAt())
                .updatedAt(request.updatedAt())
                .processedAt(request.processedAt())
                .build();
    }

    public InvestorRequest toDomain() {
        return new InvestorRequest(
                this.id,
                this.idempotencyKey,
                this.operationType,
                this.status,
                this.failureReason,
                this.investorId,
                this.portfolioId,
                this.amount,
                this.assetId,
                this.createdAt,
                this.updatedAt,
                this.processedAt
        );
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java ===
package com.investment.portfolio.infrastructure.config;




import com.investment.portfolio.domain.model.RequestStatus;
import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
@EnableCaching
public class IdempotencyConfig {

    @Bean
    public CacheManager idempotencyCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(java.util.List.of("idempotency-keys"));
        return cacheManager;
    }

    @Component
    @Primary
    public static class IdempotencyService {

        private final ConcurrentHashMap<String, IdempotencyRecord> idempotencyStore = new ConcurrentHashMap<>();
        private final com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort persistencePort;
        private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(IdempotencyService.class);

        public IdempotencyService(com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort persistencePort) {
            this.persistencePort = persistencePort;
        }

        public <T> T executeWithIdempotency(String idempotencyKey, Supplier<T> operation, Class<T> resultType) {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                logger.warn("Idempotency key is null or blank, executing without idempotency guarantee");
                return operation.get();
            }

            logger.debug("Checking idempotency for key: {}", idempotencyKey);

            var existingRequest = persistencePort.findByIdempotencyKey(idempotencyKey);
            if (existingRequest.isPresent()) {
                var request = existingRequest.get();
                logger.info("Idempotent request found for key: {}, status: {}", 
                        idempotencyKey, request.status());
                
                if (request.status() == com.investment.portfolio.domain.model.InvestorRequest.RequestStatus.COMPLETED) {
                    return resultType.cast(createSuccessResult(request));
                } else if (request.status() == com.investment.portfolio.domain.model.InvestorRequest.RequestStatus.FAILED) {
                    throw new IdempotencyException("Request already failed: " + request.failureReason(), request);
                }
            }

            try {
                T result = operation.get();
                logger.info("Idempotent operation completed for key: {}", idempotencyKey);
                return result;
            } catch (Exception e) {
                logger.error("Idempotent operation failed for key: {}", idempotencyKey, e);
                throw e;
            }
        }

        public boolean isDuplicate(String idempotencyKey) {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                return false;
            }
            return persistencePort.findByIdempotencyKey(idempotencyKey).isPresent();
        }

        public void recordDuplicate(String idempotencyKey) {
            idempotencyStore.put(idempotencyKey, new IdempotencyRecord(idempotencyKey, System.currentTimeMillis()));
            logger.debug("Recorded duplicate check for key: {}", idempotencyKey);
        }

        private Object createSuccessResult(com.investment.portfolio.domain.model.InvestorRequest request) {
            return request;
        }

        private record IdempotencyRecord(String key, long timestamp) {}

        public static class IdempotencyException extends RuntimeException {
            private final com.investment.portfolio.domain.model.InvestorRequest existingRequest;

            public IdempotencyException(String message, com.investment.portfolio.domain.model.InvestorRequest existingRequest) {
                super(message);
                this.existingRequest = existingRequest;
            }

            public com.investment.portfolio.domain.model.InvestorRequest getExistingRequest() {
                return existingRequest;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/config/LoggingConfig.java ===
package com.investment.portfolio.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.MDC;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class LoggingConfig {

    @Value("${application.logging.level:INFO}")
    private String defaultLogLevel;

    @Value("${application.logging.pattern:%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n}")
    private String logPattern;

    @Value("${application.logging.file.path:logs/application.log}")
    private String logFilePath;

    @Value("${application.logging.file.max-size:10MB}")
    private String maxFileSize;

    @Value("${application.logging.file.max-history:30}")
    private int maxHistory;

    private static final String CORRELATION_ID_KEY = "correlationId";
    private static final String USER_ID_KEY = "userId";
    private static final String OPERATION_KEY = "operation";

    @PostConstruct
    public void initializeLogging() {
        System.setProperty("LOG_PATTERN", logPattern);
        System.setProperty("LOG_FILE", logFilePath);
        LoggerFactory.getLogger(LoggingConfig.class).info(
                "Logging initialized with level: {}, pattern: {}, file: {}",
                defaultLogLevel, logPattern, logFilePath
        );
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }

    public static class LoggingService {

        private final Logger logger = LoggerFactory.getLogger(LoggingService.class);

        public void logInfo(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.info(message);
            clearMDC();
        }

        public void logInfo(String message, Object... args) {
            logger.info(message, args);
        }

        public void logDebug(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.debug(message);
            clearMDC();
        }

        public void logDebug(String message, Object... args) {
            logger.debug(message, args);
        }

        public void logWarn(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.warn(message);
            clearMDC();
        }

        public void logWarn(String message, Object... args) {
            logger.warn(message, args);
        }

        public void logError(String message, Throwable throwable, Map<String, Object> context) {
            enrichMDC(context);
            logger.error(message, throwable);
            clearMDC();
        }

        public void logError(String message, Throwable throwable) {
            logger.error(message, throwable);
        }

        public void logError(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.error(message);
            clearMDC();
        }

        public String generateCorrelationId() {
            return UUID.randomUUID().toString();
        }

        public void setCorrelationId(String correlationId) {
            if (correlationId != null) {
                MDC.put(CORRELATION_ID_KEY, correlationId);
            }
        }

        public void setUserId(String userId) {
            if (userId != null) {
                MDC.put(USER_ID_KEY, userId);
            }
        }

        public void setOperation(String operation) {
            if (operation != null) {
                MDC.put(OPERATION_KEY, operation);
            }
        }

        public void clearCorrelationId() {
            MDC.remove(CORRELATION_ID_KEY);
        }

        public void clearUserId() {
            MDC.remove(USER_ID_KEY);
        }

        public void clearOperation() {
            MDC.remove(OPERATION_KEY);
        }

        private void enrichMDC(Map<String, Object> context) {
            if (context != null) {
                context.forEach((key, value) -> {
                    if (key != null && value != null) {
                        MDC.put(key, value.toString());
                    }
                });
            }
        }

        private void clearMDC() {
            MDC.remove(CORRELATION_ID_KEY);
            MDC.remove(USER_ID_KEY);
            MDC.remove(OPERATION_KEY);
        }

        public Map<String, String> getContext() {
            Map<String, String> context = new HashMap<>();
            String correlationId = MDC.get(CORRELATION_ID_KEY);
            String userId = MDC.get(USER_ID_KEY);
            String operation = MDC.get(OPERATION_KEY);
            
            if (correlationId != null) context.put(CORRELATION_ID_KEY, correlationId);
            if (userId != null) context.put(USER_ID_KEY, userId);
            if (operation != null) context.put(OPERATION_KEY, operation);
            
            return context;
        }

        public void logRequestReceived(String operationType, String investorId, String idempotencyKey) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_RECEIVED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("idempotencyKey", idempotencyKey != null ? idempotencyKey : "N/A");
            
            logInfo("Investor request received", context);
        }

        public void logRequestProcessed(String operationType, String investorId, String requestId, long durationMs) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_PROCESSED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("requestId", requestId);
            context.put("durationMs", durationMs);
            
            logInfo("Investor request processed successfully", context);
        }

        public void logRequestFailed(String operationType, String investorId, String errorMessage, Throwable throwable) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_FAILED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("errorMessage", errorMessage);
            
            logError("Investor request failed: " + errorMessage, throwable, context);
        }
    }
}

// === ARCHIVO: src/main/java/com/investment/portfolio/infrastructure/exception/GlobalExceptionHandler.java ===
package com.investment.portfolio.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("details", errors);
        
        logger.warn("Validation error: {}", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage(),
                        (existing, replacement) -> existing
                ));
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Constraint Violation");
        response.put("details", errors);
        
        logger.warn("Constraint violation: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", ex.getMessage());
        
        logger.warn("Illegal argument: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Data Integrity Violation");
        response.put("message", "The request could not be processed due to duplicate or conflicting data");
        
        logger.error("Data integrity violation: {}", ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", "An unexpected error occurred");
        
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

// === ARCHIVO: src/test/java/com/investment/portfolio/application/service/InvestorRequestServiceTest.java ===
package com.investment.portfolio.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvestorRequestServiceTest {

    @Mock
    private InvestorRequestPersistencePort persistencePort;

    @InjectMocks
    private InvestorRequestService service;

    @Test
    void processInvestorRequest_shouldSaveRequestSuccessfully() {
        // Given: a valid investor request
        // When: the service processes the request
        // Then: it should save and return the processed request
    }

    @Test
    void processInvestorRequest_shouldReturnExistingRequest_whenIdempotencyKeyExists() {
        // Given: an idempotency key that already exists
        // When: processing a request with that key
        // Then: it should return the existing request without creating a new one
    }

    @Test
    void getInvestorRequestById_shouldReturnRequest_whenExists() {
        // Given: a request ID that exists in persistence
        // When: retrieving by ID
        // Then: it should return the investor request
    }

    @Test
    void getInvestorRequestById_shouldThrowException_whenNotFound() {
        // Given: a request ID that does not exist
        // When: retrieving by ID
        // Then: it should throw an appropriate exception
    }

    @Test
    void getInvestorRequestByIdempotencyKey_shouldReturnRequest_whenExists() {
        // Given: an idempotency key that exists
        // When: retrieving by idempotency key
        // Then: it should return the investor request
    }

    @Test
    void processInvestorRequest_shouldHandleValidationErrors() {
        // Given: a request with invalid data
        // When: processing the request
        // Then: it should throw validation exception
    }

    @Test
    void processInvestorRequest_shouldLogRequestProcessing() {
        // Given: a valid investor request
        // When: processing the request
        // Then: appropriate log statements should be generated
    }
}

// === ARCHIVO: src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java ===
package com.investment.portfolio.infrastructure.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.investment.portfolio.application.service.InvestorRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InvestorRequestController.class)
class InvestorRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvestorRequestService investorRequestService;

    @Test
    void createRequest_shouldReturn201_whenRequestIsValid() throws Exception {
        // Given: a valid request payload
        // When: POST /api/investor-requests is called
        // Then: it should return 201 Created with the request details
    }

    @Test
    void createRequest_shouldReturn400_whenRequestIsInvalid() throws Exception {
        // Given: an invalid request payload (missing required fields)
        // When: POST /api/investor-requests is called
        // Then: it should return 400 Bad Request
    }

    @Test
    void getRequestById_shouldReturn200_whenRequestExists() throws Exception {
        // Given: a request ID that exists
        // When: GET /api/investor-requests/{id} is called
        // Then: it should return 200 OK with the request details
    }

    @Test
    void getRequestById_shouldReturn404_whenRequestNotFound() throws Exception {
        // Given: a request ID that does not exist
        // When: GET /api/investor-requests/{id} is called
        // Then: it should return 404 Not Found
    }

    @Test
    void getRequestByIdempotencyKey_shouldReturn200_whenKeyExists() throws Exception {
        // Given: an idempotency key that exists
        // When: GET /api/investor-requests/by-key/{key} is called
        // Then: it should return 200 OK with the request details
    }

    @Test
    void createRequest_shouldReturn409_whenIdempotencyKeyIsDuplicate() throws Exception {
        // Given: a request with an existing idempotency key
        // When: POST /api/investor-requests is called
        // Then: it should return 409 Conflict
    }

    @Test
    void createRequest_shouldReturn500_whenInternalErrorOccurs() throws Exception {
        // Given: a valid request but service throws exception
        // When: POST /api/investor-requests is called
        // Then: it should return 500 Internal Server Error
    }
}
```
