# language: es
#
# Tags de la suite:
#   @smoke          -> CP-01 a CP-03 (lo mínimo para saber que el calendario funciona)
#   @regresion      -> CP-01 a CP-06
#   @multinavegador -> CP-01 a CP-04. [CP-07] Multi-navegador no es un escenario aparte:
#                      es la ejecución de estos escenarios en Chrome, Edge y Firefox
#                      con la tarea de Gradle "multinavegador" (ver README.md).
@datepicker
Característica: Selección de fecha con el Datepicker de jQuery UI
  Como usuario del formulario
  Quiero seleccionar una fecha desde un calendario
  Para no tener que escribirla manualmente

  Antecedentes:
    Dado que Felix abre el demo del Datepicker

  @smoke @regresion @multinavegador @CP-01
  Escenario: [CP-01] Mostrar el calendario al hacer clic en el campo
    Cuando hace clic en el campo Date
    Entonces debería ver el calendario desplegado con el mes y año actuales

  @smoke @regresion @multinavegador @CP-02
  Escenario: [CP-02] Seleccionar una fecha del mes actual
    Cuando selecciona el día 15 del mes actual
    Entonces el campo Date debería mostrar el día 15 del mes actual
    Y el calendario debería cerrarse

  # Se elige un día de un solo dígito para comprobar que se completa con cero (05, no 5)
  @smoke @regresion @multinavegador @CP-03
  Escenario: [CP-03] La fecha seleccionada se muestra en formato mm/dd/yyyy
    Cuando selecciona el día 5 del mes actual
    Entonces la fecha del campo Date debería tener el formato mm/dd/yyyy
    Y el campo Date debería mostrar el día 5 del mes actual

  @regresion @multinavegador @CP-04
  Escenario: [CP-04] Navegar al mes siguiente y seleccionar una fecha
    Cuando navega al mes siguiente en el calendario
    Y selecciona el día 10
    Entonces el campo Date debería mostrar el día 10 del mes siguiente

  @regresion @CP-05
  Escenario: [CP-05] Navegar al mes anterior y seleccionar una fecha
    Cuando navega al mes anterior en el calendario
    Y selecciona el día 20
    Entonces el campo Date debería mostrar el día 20 del mes anterior

  @regresion @CP-06
  Esquema del escenario: [CP-06] Fechas límite del calendario (bisiesto, fin de mes)
    Cuando navega en el calendario hasta el mes <mes> del año <anio>
    Y selecciona el último día disponible
    Entonces el mes debería tener <dias> días
    Y el campo Date debería mostrar la fecha "<fecha>"

    Ejemplos:
      | caso               | mes | anio | dias | fecha      |
      | febrero bisiesto   | 2   | 2028 | 29   | 02/29/2028 |
      | febrero no bisiesto| 2   | 2027 | 28   | 02/28/2027 |
      | mes de 30 días     | 4   | 2027 | 30   | 04/30/2027 |
      | fin de año         | 12  | 2026 | 31   | 12/31/2026 |
