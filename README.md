# Implementación de Servicio en Framework

En el contexto de una plataforma de gestión de cartera de inversiones, se requiere implementar un servicio que maneje las solicitudes de los inversores. El servicio debe enrutar las solicitudes a los componentes apropiados, controlar las variables de entorno, generar registros de trazas y conectarse con la base de datos utilizando un ORM. Los inversores pueden realizar solicitudes para consultar su cartera, realizar inversiones y retirar fondos. El servicio debe manejar un volumen de 1 500 solicitudes por segundo en hora pico y garantizar la idempotencia de las solicitudes mediante una clave única por operación.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno

**Objetivo:** Establecer el entorno de desarrollo y configurar las variables de entorno necesarias para el servicio.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las variables de entorno necesarias para el servicio.
- Configurar el entorno de desarrollo para que el servicio pueda acceder a estas variables.

**Entregable:** Entorno de desarrollo configurado con las variables de entorno necesarias.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la gestión de variables de entorno en un servicio backend.

</details>

### Fase 2: Enrutamiento de Solicitudes

**Objetivo:** Implementar la funcionalidad para enrutar las solicitudes de los inversores a los componentes apropiados.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseñar y implementar el enrutamiento de solicitudes para las operaciones de consulta, inversión y retiro.
- Asegurar que el enrutamiento sea idempotente utilizando una clave única por operación.

**Entregable:** Servicio con enrutamiento de solicitudes idempotente.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo puedes garantizar que el enrutamiento de solicitudes sea idempotente.
- Considera los posibles edge cases y cómo manejarlos.

</details>

### Fase 3: Generación de Registros de Trazas

**Objetivo:** Implementar la funcionalidad para generar registros de trazas para las solicitudes manejadas por el servicio.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Diseñar y implementar la generación de registros de trazas para cada solicitud.
- Asegurar que los registros incluyan información relevante para la depuración y el monitoreo.

**Entregable:** Servicio con generación de registros de trazas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera qué información es relevante incluir en los registros de trazas.
- Piensa en cómo puedes asegurar que los registros sean útiles para la depuración y el monitoreo.

</details>

### Fase 4: Conexión con Base de Datos

**Objetivo:** Implementar la funcionalidad para conectar el servicio con la base de datos utilizando un ORM.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseñar y implementar la conexión con la base de datos utilizando un ORM.
- Asegurar que la conexión sea eficiente y escalable.

**Entregable:** Servicio conectado a la base de datos utilizando un ORM.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la conexión con la base de datos utilizando un ORM.
- Piensa en cómo puedes asegurar que la conexión sea eficiente y escalable.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un ORM y por qué se utiliza en el desarrollo de servicios backend?
- **paraQueSirve**: ¿Para qué sirve el enrutamiento de solicitudes en un servicio backend?
- **comoSeUsa**: ¿Cómo se utiliza un ORM para conectar un servicio con una base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar el enrutamiento de solicitudes y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de un servicio backend utilizando un framework y un ORM?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo con variables de entorno.
- Implementación del enrutamiento de solicitudes idempotente.
- Generación de registros de trazas relevantes para la depuración y el monitoreo.
- Conexión eficiente y escalable con la base de datos utilizando un ORM.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
