package automatizacion.definitions;

import automatizacion.page.MercadoLibrePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MercadoLibreDefinition {

    AppiumDriver<MobileElement> driver = setUp.getDriver();

    private WebDriverWait wait = new WebDriverWait(driver, 10);

    MercadoLibrePage mercadoLibrePage = new MercadoLibrePage(driver, wait);


    @Given("ingreso a la aplicacion mobile")
    public void ingresoALaAplicacionMobile() {
        if (mercadoLibrePage.isVisibleTextNotify()) {
            mercadoLibrePage.clickXpathText("Ahora no");
        } else if (mercadoLibrePage.isVisibleText("Continuar como visitante")) {
            mercadoLibrePage.clickXpathText("Continuar como visitante");
        }

    }

    @When("entro a realizar una busqueda en el search")
    public void yoEntroARealizarUnaBusquedaEnElBuscador() {
        mercadoLibrePage.clickBuscar();
    }

    @And("ingreso la busqueda de {string}")
    public void ingresoLaBusquedaDe(String producto) {
        mercadoLibrePage.sendKeysProducto(producto);
        mercadoLibrePage.sendKeysEnter();
        mercadoLibrePage.isVisibleContainsText("resultados");
    }

    @Then("busco algun ipad de mi interes {string}")
    public void buscoAlgunIpadDeMiInteres(String item) throws Exception {
        mercadoLibrePage.buscarElemento(item);
        mercadoLibrePage.clicSearchElement(item);
    }


    @When("busco el elemento {string} en la pagina principal")
    public void buscoElElementoEnLaPaginaPrincipal(String item) throws Exception {
        mercadoLibrePage.buscarPaginaPrincipal(item);
        mercadoLibrePage.clickXpathText(item);
        mercadoLibrePage.waitSec(2);
    }

    @And("selecciono la opcion {string}")
    public void seleccionoLaOpcion(String opcion) {
        mercadoLibrePage.waitSec(1);
        mercadoLibrePage.clickXpathText(opcion);
    }


    @Then("valido titulo {string}")
    public void validoTitulo(String titulo) throws Exception {
        mercadoLibrePage.isVisibleXpathText(titulo);
    }

    @And("busco el segundo elemento de la pantalla")
    public void buscoUnElementoDeseado() throws Exception {
        mercadoLibrePage.buscarUnElemento();
        mercadoLibrePage.clickExtrellas();
    }

    @And("selecciono el numero de rating")
    public void seleccionoElNumeroDeRating() throws Exception {
        mercadoLibrePage.isVisibleNroRating();
        mercadoLibrePage.clickNroRating();
    }


    @Then("valido que la calificacion sea visible")
    public void validoLaCalificacionVisible() throws Exception {
        mercadoLibrePage.isVisibleXpathCalificacion();
        mercadoLibrePage.isVisibleXpathOpinion();
    }



}
