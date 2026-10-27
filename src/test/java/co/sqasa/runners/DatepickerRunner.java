package co.sqasa.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)                              // JUnit ejecuta usando Serenity + Cucumber
@CucumberOptions(
        features = "src/test/resources/features/datepicker.feature", // qué feature ejecutar
        glue = "co.sqasa.stepdefinitions",                           // dónde están los steps y hooks
        snippets = SnippetType.CAMELCASE                             // formato de los métodos sugeridos
)
public class DatepickerRunner {
}
