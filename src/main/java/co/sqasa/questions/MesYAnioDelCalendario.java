package co.sqasa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.sqasa.userinterfaces.DatepickerPage.ANIO_DEL_CALENDARIO;
import static co.sqasa.userinterfaces.DatepickerPage.MES_DEL_CALENDARIO;

/**
 * Pregunta: ¿qué mes y año muestra el calendario? Responde, por ejemplo, "October 2026".
 */
@Subject("el mes y año mostrados en el calendario")
public class MesYAnioDelCalendario implements Question<String> {

    public static MesYAnioDelCalendario mostrado() {
        return new MesYAnioDelCalendario();
    }

    @Override
    public String answeredBy(Actor actor) {
        String mes = MES_DEL_CALENDARIO.resolveFor(actor).getText();
        String anio = ANIO_DEL_CALENDARIO.resolveFor(actor).getText();
        return mes + " " + anio;
    }
}
