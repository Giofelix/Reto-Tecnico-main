# Reto de Automatización – Datepicker jQuery UI

Pruebas automatizadas de interfaz sobre el [Datepicker de jQuery UI](https://jqueryui.com/datepicker/), construidas con **Serenity BDD + Cucumber + JUnit 4** usando el patrón **Screenplay**.

## Tecnologías

| Herramienta | Versión |
|---|---|
| Java (JDK) | 8 a 15 (recomendado 11) |
| Gradle (wrapper incluido) | 6.8 |
| Serenity BDD | 2.0.80 |
| Serenity Cucumber | 1.9.45 |
| JUnit | 4 |
| Navegadores | Chrome, Edge, Firefox |

## Requisitos previos

1. **JDK 8 a 15** instalado y la variable `JAVA_HOME` apuntando a él. Verificar con:
   ```powershell
   java -version
   ```
   > Gradle 6.8 **no funciona** con JDK 16 o superior.
2. **No hace falta instalar Gradle**: el proyecto trae el wrapper (`gradlew.bat` / `gradlew`), que descarga la versión correcta la primera vez.
3. Al menos uno de los navegadores instalado (Chrome por defecto).
4. Conexión a internet (para descargar dependencias y abrir la página bajo prueba).

### Drivers de los navegadores

La carpeta `driver/` incluye los drivers para Windows:

| Archivo | Navegador | Versión |
|---|---|---|
| `chromedriver.exe` | Google Chrome | 155 |
| `msedgedriver.exe` | Microsoft Edge | 155 |
| `geckodriver.exe` | Mozilla Firefox | 0.37.1 |

Cada driver debe coincidir con la **versión mayor** de tu navegador. Si tu navegador tiene otra versión (o no usas Windows), descarga el driver correspondiente y reemplázalo en `driver/` con el mismo nombre:

- Chrome: https://googlechromelabs.github.io/chrome-for-testing/
- Edge: https://developer.microsoft.com/microsoft-edge/tools/webdriver/
- Firefox: https://github.com/mozilla/geckodriver/releases

Las rutas se configuran en `serenity.properties`.

## Instalación

```powershell
git clone <url-del-repositorio>
cd <carpeta-del-proyecto>
.\gradlew.bat clean build -x test
```

El último comando descarga las dependencias y compila el proyecto sin ejecutar las pruebas.

## Ejecución

Todos los comandos se ejecutan desde la raíz del proyecto (en Linux/macOS usar `./gradlew` en lugar de `.\gradlew.bat`).

| Qué ejecutar | Comando |
|---|---|
| Todos los escenarios (CP-01 a CP-06) | `.\gradlew.bat clean test` |
| Solo smoke (CP-01 a CP-03) | `.\gradlew.bat clean test "-Dcucumber.options=--tags @smoke"` |
| Regresión completa | `.\gradlew.bat clean test "-Dcucumber.options=--tags @regresion"` |
| Un caso puntual | `.\gradlew.bat clean test "-Dcucumber.options=--tags @CP-02"` |
| En otro navegador | `.\gradlew.bat clean test "-Dwebdriver.driver=edge"` (`chrome` \| `edge` \| `firefox`) |
| Multinavegador (CP-07) | `.\gradlew.bat clean multinavegador` |
| Regenerar el reporte | `.\gradlew.bat aggregate` |

### Desde VS Code

El proyecto incluye `.vscode/tasks.json` con tareas listas: `Ctrl+Shift+P` → **Tasks: Run Task** → elegir la tarea (todas, smoke, regresión, un CP, otro navegador, multinavegador, abrir reporte).

## Reporte

Al terminar cada ejecución se genera automáticamente el reporte de Serenity en:

```
target/site/serenity/index.html
```

Abrirlo en Windows:

```powershell
Start-Process target\site\serenity\index.html
```

> **Importante:** el build está configurado con `ignoreFailures = true` para que el reporte siempre se genere. Por eso Gradle muestra `BUILD SUCCESSFUL` aunque haya escenarios fallidos: **revisa el reporte** para conocer el resultado real.

## Casos de prueba

Definidos en `src/test/resources/features/datepicker.feature` (Gherkin en español).

| ID | Escenario | Tags |
|---|---|---|
| CP-01 | Mostrar el calendario al hacer clic en el campo | `@smoke` `@regresion` `@multinavegador` |
| CP-02 | Seleccionar una fecha del mes actual | `@smoke` `@regresion` `@multinavegador` |
| CP-03 | La fecha seleccionada se muestra en formato mm/dd/yyyy | `@smoke` `@regresion` `@multinavegador` |
| CP-04 | Navegar al mes siguiente y seleccionar una fecha | `@regresion` `@multinavegador` |
| CP-05 | Navegar al mes anterior y seleccionar una fecha | `@regresion` |
| CP-06 | Fechas límite del calendario (bisiesto, fin de mes) | `@regresion` |
| CP-07 | Multinavegador: CP-01 a CP-04 en Chrome, Edge y Firefox | tarea `multinavegador` |

Las fechas esperadas se calculan en tiempo de ejecución (no están quemadas), así que las pruebas funcionan en cualquier día.

## Estructura del proyecto (Screenplay)

```
src
├── main/java/co/sqasa
│   ├── userinterfaces/   # Localizadores (Target) de la página
│   ├── tasks/            # Tareas de negocio (abrir demo, navegar, seleccionar día...)
│   ├── interactions/     # Interacciones propias (cambiar al iframe del demo)
│   ├── questions/        # Preguntas usadas en las validaciones
│   └── utils/            # Utilidades (cálculo de fechas)
└── test
    ├── java/co/sqasa
    │   ├── runners/          # Runner de Cucumber + Serenity
    │   └── stepdefinitions/  # Definición de pasos y Hooks
    └── resources/features/   # Archivos .feature
```

## Configuración

`serenity.properties` permite ajustar sin tocar código:

- `webdriver.driver`: navegador por defecto (`chrome`).
- `webdriver.*.driver`: rutas de los drivers.
- `serenity.take.screenshots`: cuándo tomar capturas (`FOR_EACH_ACTION`, `AFTER_EACH_STEP`, `FOR_FAILURES`, `DISABLED`).
- `webdriver.timeouts.implicitlywait`: espera implícita en milisegundos.

## Solución de problemas

| Problema | Solución |
|---|---|
| `Unsupported class file major version` o error al iniciar Gradle | Estás usando JDK 16+. Instala JDK 11 y apunta `JAVA_HOME` a él. |
| `SessionNotCreatedException: This version of ChromeDriver only supports Chrome version X` | El driver no coincide con tu navegador. Descarga el driver de tu versión y reemplázalo en `driver/`. |
| Se usa un Gradle instalado globalmente y falla | Usa siempre `.\gradlew.bat`, no `gradle`. |
| `BUILD SUCCESSFUL` pero hay fallos | Es esperado: revisa `target/site/serenity/index.html`. |
