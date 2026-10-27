package co.sqasa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.sqasa.userinterfaces.DatepickerPage.CALENDARIO;

/**
 * Pregunta: ¿el calendario está visible en pantalla?
 */
@Subject("la visibilidad del calendario")
public class ElCalendario implements Question<Boolean> {

    public static ElCalendario esVisible() {
        return new ElCalendario();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return CALENDARIO.resolveFor(actor).isVisible();
    }
}
