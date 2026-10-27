package co.sqasa.tasks;

import co.sqasa.questions.MesYAnioDelCalendario;
import co.sqasa.utils.Fechas;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea de negocio: llevar el calendario hasta un mes y año concretos.
 * Lee el mes que muestra el calendario, calcula la diferencia y reutiliza
 * NavegarEnElCalendario (las tareas se combinan como piezas de LEGO).
 */
public class IrAlMes implements Task {

    private final YearMonth destino;

    public IrAlMes(YearMonth destino) {
        this.destino = destino;
    }

    public static IrAlMes de(YearMonth destino) {
        return instrumented(IrAlMes.class, destino);
    }

    @Override
    @Step("{0} navega en el calendario hasta #destino")
    public <T extends Actor> void performAs(T actor) {
        // Leer la pantalla para decidir cuántos clics dar no es una validación, es parte del flujo
        YearMonth mostrado = Fechas.desdeEncabezado(actor.asksFor(MesYAnioDelCalendario.mostrado()));
        int diferencia = (int) ChronoUnit.MONTHS.between(mostrado, destino);
        actor.attemptsTo(NavegarEnElCalendario.meses(diferencia));
    }
}
