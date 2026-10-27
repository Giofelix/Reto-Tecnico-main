package co.sqasa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.sqasa.userinterfaces.DatepickerPage.DIAS_DISPONIBLES;

/**
 * Pregunta: ¿cuántos días seleccionables tiene el mes del calendario?
 * Sirve para validar bisiestos y fines de mes (28, 29, 30 o 31 días).
 */
@Subject("la cantidad de días del mes en el calendario")
public class DiasDelMes implements Question<Integer> {

    public static DiasDelMes disponibles() {
        return new DiasDelMes();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return DIAS_DISPONIBLES.resolveAllFor(actor).size();
    }
}
