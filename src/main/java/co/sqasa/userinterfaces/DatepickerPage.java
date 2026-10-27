package co.sqasa.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Localizadores de la página del demo Datepicker de jQuery UI.
 * Regla Screenplay: los localizadores SOLO viven en las clases de userinterfaces.
 */
public class DatepickerPage {

    public static final String URL = "https://jqueryui.com/datepicker/";

    // El demo se renderiza dentro de un iframe: hay que entrar en él antes de usar el campo
    public static final Target IFRAME_DEMO = Target.the("iframe del demo")
            .located(By.cssSelector("iframe.demo-frame"));

    // Los siguientes elementos están DENTRO del iframe
    public static final Target CAMPO_FECHA = Target.the("campo Date")
            .located(By.id("datepicker"));

    public static final Target CALENDARIO = Target.the("calendario")
            .located(By.id("ui-datepicker-div"));

    public static final Target MES_DEL_CALENDARIO = Target.the("mes mostrado en el calendario")
            .located(By.cssSelector("#ui-datepicker-div .ui-datepicker-month"));

    public static final Target ANIO_DEL_CALENDARIO = Target.the("año mostrado en el calendario")
            .located(By.cssSelector("#ui-datepicker-div .ui-datepicker-year"));

    public static final Target BOTON_MES_SIGUIENTE = Target.the("botón Next")
            .located(By.cssSelector("#ui-datepicker-div .ui-datepicker-next"));

    public static final Target BOTON_MES_ANTERIOR = Target.the("botón Prev")
            .located(By.cssSelector("#ui-datepicker-div .ui-datepicker-prev"));

    // Target dinámico: {0} se reemplaza con el número del día usando DIA.of("15")
    public static final Target DIA = Target.the("día {0} del calendario")
            .locatedBy("#ui-datepicker-div td[data-handler='selectDay'] a[data-date='{0}']");

    // Solo las celdas seleccionables del mes mostrado (no incluye celdas vacías)
    public static final Target DIAS_DISPONIBLES = Target.the("días seleccionables del mes")
            .located(By.cssSelector("#ui-datepicker-div td[data-handler='selectDay']"));

    private DatepickerPage() {
    }
}
