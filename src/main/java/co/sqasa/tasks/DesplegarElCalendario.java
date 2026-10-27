package co.sqasa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static co.sqasa.userinterfaces.DatepickerPage.CALENDARIO;
import static co.sqasa.userinterfaces.DatepickerPage.CAMPO_FECHA;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Tarea de negocio: abrir el calendario haciendo clic en el campo Date.
 * La espera explícita no es una validación: solo da tiempo a la animación de apertura
 * (la validación real está en la Question).
 */
public class DesplegarElCalendario implements Task {

    public static DesplegarElCalendario desdeElCampoDeFecha() {
        return instrumented(DesplegarElCalendario.class);
    }

    @Override
    @Step("{0} hace clic en el campo Date para desplegar el calendario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CAMPO_FECHA),
                WaitUntil.the(CALENDARIO, isVisible()).forNoMoreThan(5).seconds()
        );
    }
}
