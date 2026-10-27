package co.sqasa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Pregunta: ¿la fecha del campo cumple el formato mm/dd/yyyy?
 * Revisa la forma (2 dígitos / 2 dígitos / 4 dígitos) y que sea una fecha real (mes 1-12, día válido).
 */
@Subject("si la fecha del campo cumple el formato mm/dd/yyyy")
public class FormatoDeLaFecha implements Question<Boolean> {

    private static final String PATRON = "^\\d{2}/\\d{2}/\\d{4}$";

    // STRICT rechaza fechas como 02/30/2027; "uuuu" es el año que exige el modo estricto
    private static final DateTimeFormatter MM_DD_YYYY =
            DateTimeFormatter.ofPattern("MM/dd/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static FormatoDeLaFecha esMmDdYyyy() {
        return new FormatoDeLaFecha();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String fecha = actor.asksFor(FechaDelCampo.mostrada());
        if (fecha == null || !fecha.matches(PATRON)) {
            return false;
        }
        try {
            LocalDate.parse(fecha, MM_DD_YYYY);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
