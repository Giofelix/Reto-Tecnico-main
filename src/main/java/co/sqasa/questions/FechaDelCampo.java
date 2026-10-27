package co.sqasa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static co.sqasa.userinterfaces.DatepickerPage.CAMPO_FECHA;

/**
 * Pregunta: ¿qué fecha quedó escrita en el campo Date?
 */
@Subject("la fecha del campo Date")
public class FechaDelCampo implements Question<String> {

    public static FechaDelCampo mostrada() {
        return new FechaDelCampo();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Es un <input>: su contenido se lee con el atributo value, no con getText()
        return CAMPO_FECHA.resolveFor(actor).getValue();
    }
}
