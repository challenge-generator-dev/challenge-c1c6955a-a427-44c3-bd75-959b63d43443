# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Servicio en Framework**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa 3.5.6
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-configuration-processor n/a
- com.fasterxml.jackson.core:jackson-databind n/a
- org.postgresql:postgresql n/a
- org.projectlombok:lombok n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a
- ch.qos.logback:logback-classic n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.9.1

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno**: Entorno de desarrollo configurado con las variables de entorno necesarias.
- **Fase 2 — Enrutamiento de Solicitudes**: Servicio con enrutamiento de solicitudes idempotente.
- **Fase 3 — Generación de Registros de Trazas**: Servicio con generación de registros de trazas.
- **Fase 4 — Conexión con Base de Datos**: Servicio conectado a la base de datos utilizando un ORM.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/investment/portfolio/application/service/InvestorRequestServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (33)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/investment/portfolio/Application.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/investment/portfolio/infrastructure/config/LoggingConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/investment/portfolio/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.operationType`
      Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.id`
      Se invoca `id` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.status`
      Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.investorId`
      Se invoca `investorId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java` — `InvestorRequest.amount`
      Se invoca `amount` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `InvestorRequest.operationType`
      Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java` — `InvestorRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.getId`
      Se invoca `getId` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.setStatus`
      Se invoca `setStatus` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java` — `InvestorRequestEntity.setFailureReason`
      Se invoca `setFailureReason` sobre `InvestorRequestEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.id`
      Se invoca `id` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.idempotencyKey`
      Se invoca `idempotencyKey` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.operationType`
      Se invoca `operationType` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.status`
      Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.failureReason`
      Se invoca `failureReason` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.investorId`
      Se invoca `investorId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.portfolioId`
      Se invoca `portfolioId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.amount`
      Se invoca `amount` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.assetId`
      Se invoca `assetId` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.createdAt`
      Se invoca `createdAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.updatedAt`
      Se invoca `updatedAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java` — `InvestorRequest.processedAt`
      Se invoca `processedAt` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.isPresent`
      Se invoca `isPresent` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.get`
      Se invoca `get` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.status`
      Se invoca `status` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java` — `InvestorRequest.failureReason`
      Se invoca `failureReason` sobre `InvestorRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (15)

- `pom.xml`
- `src/main/java/com/investment/portfolio/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/investment/portfolio/domain/model/InvestorRequest.java`
- `src/main/java/com/investment/portfolio/domain/port/in/InvestorRequestPort.java`
- `src/main/java/com/investment/portfolio/domain/port/out/InvestorRequestPersistencePort.java`
- `src/main/java/com/investment/portfolio/application/service/InvestorRequestService.java`
- `src/main/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestController.java`
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/InvestorRequestJpaAdapter.java`
- `src/main/java/com/investment/portfolio/infrastructure/adapter/out/persistence/entity/InvestorRequestEntity.java`
- `src/main/java/com/investment/portfolio/infrastructure/config/IdempotencyConfig.java`
- `src/main/java/com/investment/portfolio/infrastructure/config/LoggingConfig.java`
- `src/main/java/com/investment/portfolio/infrastructure/exception/GlobalExceptionHandler.java`
- `src/test/java/com/investment/portfolio/application/service/InvestorRequestServiceTest.java`
- `src/test/java/com/investment/portfolio/infrastructure/adapter/in/web/InvestorRequestControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/investment/portfolio`
- `src/main/java/com/investment/portfolio/application`
- `src/main/java/com/investment/portfolio/domain`
- `src/main/java/com/investment/portfolio/infrastructure`
- `src/main/resources`
- `src/test/java/com/investment/portfolio`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
