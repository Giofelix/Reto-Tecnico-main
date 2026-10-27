package co.sqasa.stepdefinitions;

import cucumber.api.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    // Antes de cada escenario se prepara el "escenario de teatro":
    // OnlineCast entrega actores que ya tienen la habilidad BrowseTheWeb (navegador de Serenity)
    @Before
    public void prepararElEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }
}
