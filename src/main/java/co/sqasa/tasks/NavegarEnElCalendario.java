package co.sqasa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static co.sqasa.userinterfaces.DatepickerPage.BOTON_MES_ANTERIOR;
import static co.sqasa.userinterfaces.DatepickerPage.BOTON_MES_SIGUIENTE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea de negocio: moverse entre meses con los botones Prev / Next.
 * Valores positivos avanzan, negativos retroceden. Requiere el calendario desplegado.
 */
public class NavegarEnElCalendario implements Task {

    private final Integer meses;

    // Se usa Integer (no int) porque instrumented() busca el constructor por el tipo de los argumentos
    public NavegarEnElCalendario(Integer meses) {
        this.meses = meses;
    }

    public static NavegarEnElCalendario alMesSiguiente() {
        return meses(1);
    }

    public static NavegarEnElCalendario alMesAnterior() {
        return meses(-1);
    }

    public static NavegarEnElCalendario meses(int meses) {
        return instrumented(NavegarEnElCalendario.class, meses);
    }

    @Override
    @Step("{0} navega #meses mes(es) en el calendario")
    public <T extends Actor> void performAs(T actor) {
        Target boton = meses > 0 ? BOTON_MES_SIGUIENTE : BOTON_MES_ANTERIOR;
        for (int i = 0; i < Math.abs(meses); i++) {
            actor.attemptsTo(Click.on(boton));
        }
    }
}
