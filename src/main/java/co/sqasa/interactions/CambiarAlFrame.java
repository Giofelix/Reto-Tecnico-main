package co.sqasa.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Interacción personalizada: mueve el foco del navegador al iframe indicado.
 * Serenity 2.0.80 no trae una interacción para iframes, por eso se crea aquí
 * (una Interaction = una sola acción técnica).
 */
public class CambiarAlFrame implements Interaction {

    private final Target iframe;

    public CambiarAlFrame(Target iframe) {
        this.iframe = iframe;
    }

    public static CambiarAlFrame llamado(Target iframe) {
        return instrumented(CambiarAlFrame.class, iframe);
    }

    @Override
    @Step("{0} cambia al #iframe")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver()
                .switchTo()
                .frame(iframe.resolveFor(actor));
    }
}
