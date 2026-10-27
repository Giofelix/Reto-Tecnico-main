package co.sqasa.stepdefinitions;

import co.sqasa.questions.DiasDelMes;
import co.sqasa.questions.ElCalendario;
import co.sqasa.questions.FechaDelCampo;
import co.sqasa.questions.FormatoDeLaFecha;
import co.sqasa.questions.MesYAnioDelCalendario;
import co.sqasa.tasks.AbrirElDemo;
import co.sqasa.tasks.DesplegarElCalendario;
import co.sqasa.tasks.IrAlMes;
import co.sqasa.tasks.NavegarEnElCalendario;
import co.sqasa.tasks.SeleccionarElDia;
import co.sqasa.tasks.SeleccionarElUltimoDia;
import co.sqasa.utils.Fechas;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.time.YearMonth;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * "Pegamento" entre el Gherkin y Screenplay.
 * Regla: aquí no se usa Selenium; solo se piden Tasks y Questions al actor.
 */
public class DatepickerStepDefinitions {

    // ---------- Dado ----------

    @Dado("^que (.*) abre el demo del Datepicker$")
    public void queAbreElDemoDelDatepicker(String nombreActor) {
        theActorCalled(nombreActor).wasAbleTo(AbrirElDemo.delDatepicker());
    }

    // ---------- Cuando ----------

    @Cuando("^hace clic en el campo Date$")
    public void haceClicEnElCampoDate() {
        theActorInTheSpotlight().attemptsTo(DesplegarElCalendario.desdeElCampoDeFecha());
    }

    @Cuando("^selecciona el día (\\d+) del mes actual$")
    public void seleccionaElDiaDelMesActual(int dia) {
        theActorInTheSpotlight().attemptsTo(
                DesplegarElCalendario.desdeElCampoDeFecha(),
                SeleccionarElDia.numero(dia)
        );
    }

    @Cuando("^navega al mes (siguiente|anterior) en el calendario$")
    public void navegaAlMesEnElCalendario(String direccion) {
        theActorInTheSpotlight().attemptsTo(
                DesplegarElCalendario.desdeElCampoDeFecha(),
                "siguiente".equals(direccion)
                        ? NavegarEnElCalendario.alMesSiguiente()
                        : NavegarEnElCalendario.alMesAnterior()
        );
    }

    @Cuando("^navega en el calendario hasta el mes (\\d+) del año (\\d+)$")
    public void navegaEnElCalendarioHasta(int mes, int anio) {
        theActorInTheSpotlight().attemptsTo(
                DesplegarElCalendario.desdeElCampoDeFecha(),
                IrAlMes.de(YearMonth.of(anio, mes))
        );
    }

    // Selecciona un día del mes que el calendario muestra en ese momento
    @Cuando("^selecciona el día (\\d+)$")
    public void seleccionaElDia(int dia) {
        theActorInTheSpotlight().attemptsTo(SeleccionarElDia.numero(dia));
    }

    @Cuando("^selecciona el último día disponible$")
    public void seleccionaElUltimoDiaDisponible() {
        theActorInTheSpotlight().attemptsTo(SeleccionarElUltimoDia.disponible());
    }

    // ---------- Entonces ----------

    @Entonces("^debería ver el calendario desplegado con el mes y año actuales$")
    public void deberiaVerElCalendarioConElMesYAnioActuales() {
        theActorInTheSpotlight().should(
                seeThat(ElCalendario.esVisible(), is(true)),
                seeThat(MesYAnioDelCalendario.mostrado(), equalTo(Fechas.mesYAnioActual()))
        );
    }

    @Entonces("^el campo Date debería mostrar el día (\\d+) del mes (actual|siguiente|anterior)$")
    public void elCampoDeberiaMostrarElDiaDelMes(int dia, String mes) {
        String fechaEsperada = Fechas.fechaDelMes(mesesDesdeHoy(mes), dia);
        theActorInTheSpotlight().should(seeThat(FechaDelCampo.mostrada(), equalTo(fechaEsperada)));
    }

    @Entonces("^el campo Date debería mostrar la fecha \"([^\"]*)\"$")
    public void elCampoDeberiaMostrarLaFecha(String fechaEsperada) {
        theActorInTheSpotlight().should(seeThat(FechaDelCampo.mostrada(), equalTo(fechaEsperada)));
    }

    @Entonces("^el calendario debería cerrarse$")
    public void elCalendarioDeberiaCerrarse() {
        theActorInTheSpotlight().should(seeThat(ElCalendario.esVisible(), is(false)));
    }

    @Entonces("^la fecha del campo Date debería tener el formato mm/dd/yyyy$")
    public void laFechaDeberiaTenerElFormatoMmDdYyyy() {
        theActorInTheSpotlight().should(seeThat(FormatoDeLaFecha.esMmDdYyyy(), is(true)));
    }

    @Entonces("^el mes debería tener (\\d+) días$")
    public void elMesDeberiaTenerDias(int diasEsperados) {
        theActorInTheSpotlight().should(seeThat(DiasDelMes.disponibles(), equalTo(diasEsperados)));
    }

    // Traduce la palabra del feature a cuántos meses se mueve desde el mes actual
    private static int mesesDesdeHoy(String mes) {
        switch (mes) {
            case "siguiente":
                return 1;
            case "anterior":
                return -1;
            default:
                return 0;
        }
    }
}
