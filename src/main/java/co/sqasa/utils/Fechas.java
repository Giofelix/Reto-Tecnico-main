package co.sqasa.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utilidades de fechas. Se calculan en tiempo de ejecución para no depender
 * de fechas fijas que dejarían de ser válidas con el paso de los días.
 */
public final class Fechas {

    // El datepicker de jQuery UI muestra los meses en inglés: "October 2026"
    private static final DateTimeFormatter MES_Y_ANIO =
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

    // Formato con el que el datepicker escribe la fecha en el campo
    private static final DateTimeFormatter FORMATO_CAMPO = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Fechas() {
    }

    public static String mesYAnioActual() {
        return LocalDate.now().format(MES_Y_ANIO);
    }

    /** Convierte el encabezado del calendario ("October 2026") en un YearMonth. */
    public static YearMonth desdeEncabezado(String encabezado) {
        return YearMonth.parse(encabezado, MES_Y_ANIO);
    }

    /**
     * Fecha esperada en el campo para un día de un mes relativo al actual.
     * Ejemplo: fechaDelMes(1, 10) = día 10 del mes siguiente → "11/10/2026".
     */
    public static String fechaDelMes(int mesesDesdeHoy, int dia) {
        return YearMonth.now().plusMonths(mesesDesdeHoy).atDay(dia).format(FORMATO_CAMPO);
    }
}
