package co.sqasa.tasks;

import co.sqasa.interactions.CambiarAlFrame;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import static co.sqasa.userinterfaces.DatepickerPage.IFRAME_DEMO;
import static co.sqasa.userinterfaces.DatepickerPage.URL;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea de negocio: dejar el demo del Datepicker listo para usarse.
 * Abre la página y entra al iframe donde vive el campo de fecha.
 */
public class AbrirElDemo implements Task {

    public static AbrirElDemo delDatepicker() {
        return instrumented(AbrirElDemo.class);
    }

    @Override
    @Step("{0} abre el demo del Datepicker de jQuery UI")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(URL),
                CambiarAlFrame.llamado(IFRAME_DEMO)
        );
    }
}
