package co.sqasa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static co.sqasa.userinterfaces.DatepickerPage.CALENDARIO;
import static co.sqasa.userinterfaces.DatepickerPage.DIA;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

/**
 * Tarea de negocio: elegir un día del mes que muestra el calendario.
 * Requiere el calendario desplegado.
 */
public class SeleccionarElDia implements Task {

    private final Integer dia;

    public SeleccionarElDia(Integer dia) {
        this.dia = dia;
    }

    public static SeleccionarElDia numero(int dia) {
        return instrumented(SeleccionarElDia.class, dia);
    }

    @Override
    @Step("{0} selecciona el día #dia")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DIA.of(String.valueOf(dia))),
                // Al elegir un día el calendario se cierra con una animación: se espera a que termine
                WaitUntil.the(CALENDARIO, isNotVisible()).forNoMoreThan(5).seconds()
        );
    }
}
