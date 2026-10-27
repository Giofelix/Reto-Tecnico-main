package co.sqasa.tasks;

import co.sqasa.questions.DiasDelMes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea de negocio: elegir el último día que ofrece el calendario en el mes mostrado.
 * El "último día" se toma de la pantalla, no de Java, para probar lo que el calendario realmente ofrece.
 */
public class SeleccionarElUltimoDia implements Task {

    public static SeleccionarElUltimoDia disponible() {
        return instrumented(SeleccionarElUltimoDia.class);
    }

    @Override
    @Step("{0} selecciona el último día disponible del mes")
    public <T extends Actor> void performAs(T actor) {
        int ultimoDia = actor.asksFor(DiasDelMes.disponibles());
        actor.attemptsTo(SeleccionarElDia.numero(ultimoDia));
    }
}
