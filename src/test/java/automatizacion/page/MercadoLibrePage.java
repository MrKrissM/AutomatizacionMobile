package automatizacion.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;

import static java.time.Duration.*;

public class MercadoLibrePage {

    public AppiumDriver<MobileElement> driver;

    protected final WebDriverWait wait;

    public MercadoLibrePage(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(10)), this);
    }

    protected static final int DEFAULT_WAIT_TIMEOUT = 30;

    private final String XPATH_BUSCAR = "//*[@resource-id='com.mercadolibre:id/ui_components_toolbar_search_field']";

    private final String TEXT_NOTIFICACION = "//*[@resource-id='com.mercadolibre:id/andes_button_text' and @text='Activar notificaciones']";

    private final String XPATH_TEXT = "//*[@text='%s']";

    private final String XPATH_CONTAINS_TEXT = "//android.widget.TextView[contains(@text,'%s')]";
    private final String INPUT_SEARCH = "//*[@resource-id='com.mercadolibre:id/search_input_edittext']";

    private final String SEARCH_ELEMENT = "//*[@resource-id='com.mercadolibre:id/search_cell_title_text_view' and @text='%s']";
    private final String SOME_TEXT_BUTTON = "//*[@resource-id='com.mercadolibre:id/andes_button_text' and @text='%s']";

    private final String TEXT_TITLE = "//*[@resource-id='com.mercadolibre:id/login_identification_title' and @text='%s']";

    private final String TEXT_ESTRELLAS = "(//*[@resource-id='com.mercadolibre:id/search_cell_review_stars_rating_bar'])[2]";
    private final String TEXT_CALIFICACION = "//*[@resource-id='reviews-capability.mobile']/android.view.View/android.view.View[1]";
    private final String TEXT_OPINION = "//*[@resource-id='reviews-capability.mobile']/android.view.View/android.view.View[2]";

    private final String TEXT_OPINION_USER = "//*[@resource-id='reviews-capability.mobile']/android.view.View/android.view.View[3]";
    private final String NRO_RATING = "//android.widget.TextView[@resource-id='com.mercadolibre:id/header_component_rating_qualification']";


    public void clickBuscar() {
        click(By.xpath(String.format(XPATH_BUSCAR)));
    }

    public void clickExtrellas() {
        click(By.xpath(String.format(TEXT_ESTRELLAS)));
    }

    public void sendKeysProducto(String name) {
        sendKeys(By.xpath(INPUT_SEARCH), name);
    }

    public void clickXpathText(String text) {
        click(By.xpath(String.format(XPATH_TEXT, text)));
    }

    public void clickNroRating() {
        click(By.xpath(String.format(NRO_RATING)));
    }

    public boolean isVisibleTextNotify() {
        return isVisible(By.xpath(TEXT_NOTIFICACION));
    }

    public boolean isVisibleText(String text) {
        By by = By.xpath(String.format(XPATH_TEXT, text));
        return isVisible(by, 5);
    }

    public boolean isVisibleContainsText(String text) {
        By by = By.xpath(String.format(XPATH_CONTAINS_TEXT, text));
        return isVisible(by, 5);
    }

    public boolean buscarElemento(String item) throws Exception {
        String xpathExpression = String.format(SEARCH_ELEMENT, item);
        By locator = By.xpath(xpathExpression);
        System.out.println("Buscando con XPath: " + xpathExpression);
        return scrollDownToElement(locator);
    }

    public void buscarPaginaPrincipal(String item) throws Exception {
        String xpathExpression = String.format(XPATH_TEXT, item);
        By locator = By.xpath(xpathExpression);
        scrollDownUntilElementVisible(locator);
    }

    public void buscarUnElemento() throws Exception {
        String xpathExpression = String.format(TEXT_ESTRELLAS);
        By locator = By.xpath(xpathExpression);
        scrollDownUntilElementVisible(locator);
    }

    public void clicSearchElement(String item) {
        click(By.xpath(String.format(SEARCH_ELEMENT, item)));
    }

    public void waitSec(int sec) {
        waitFor(sec);
    }


    public void sendKeysEnter() {
        Actions act = new Actions(driver);
        act.sendKeys(Keys.ENTER);
        act.build().perform();
    }

    protected void click(WebElement webElement) {
        waitUntilElementIsClickeable(webElement);
        webElement.click();
    }

    protected void click(By selector) {
        waitUntilElementIsClickeable(selector);
        WebElement element = getDriver().findElement(selector);
        element.click();
    }

    protected void waitUntilElementIsClickeable(By element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(String.format("El elemento: %s no es clickeable", element));
        }
    }

    protected void waitUntilElementIsClickeable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(String.format("El elemento: %s no es clickeable", element));
        }
    }


    protected void sendKeys(By by, String text) {
        WebElement webElement = getDriver().findElement(by);
        waitUntilElementIsVisible(webElement);
        if (webElement.isEnabled()) {
            webElement.clear();
            webElement.sendKeys(text);
        }
    }

    public void isVisibleXpathText(String text) throws Exception {
        By by = By.xpath(String.format(TEXT_TITLE, text));
        waitUntilElementIsVisible(by);
        isVisible(by, 10);
    }

    public void isVisibleXpathCalificacion() throws Exception {
        By by = By.xpath(String.format(TEXT_CALIFICACION));
        waitUntilElementIsVisible(by);
        isVisible(by, 10);
    }

    public void isVisibleXpathOpinion() throws Exception {
        By by = By.xpath(String.format(TEXT_OPINION));
        waitUntilElementIsVisible(by);
        isVisible(by, 10);
    }

    public void isVisibleNroRating() throws Exception {
        By by = By.xpath(String.format(NRO_RATING));
        waitUntilElementIsVisible(by);
        isVisible(by, 10);
    }

    public String getTextXpath() {
        return getText(By.xpath(String.format(TEXT_OPINION)));
    }

    public String getTextXpathUser() {
        return getText(By.xpath(String.format(TEXT_OPINION_USER)));
    }


    protected String getText(By by) {
        WebElement webElement = getDriver().findElement(by);
        waitUntilElementIsVisible(webElement);
        return webElement.getText();
    }

    protected void waitUntilElementIsVisible(WebElement element) {
        try {
            this.wait.withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new ConditionTimeoutException(String.format(
                    "No se encuentra el elemento despues de " + DEFAULT_WAIT_TIMEOUT + " segundos\nElemento: %s",
                    element));
        }
    }

    protected void waitUntilElementIsVisible(By element) throws Exception {
        try {
            this.wait.withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT))
                    .until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            throw new ConditionTimeoutException(String.format(
                    "No se encuentra el elemento despues de " + DEFAULT_WAIT_TIMEOUT + " segundos\nElemento: %s",
                    element));
        }
    }

    protected boolean isVisible(By byElement, int timeOutSeconds) {
        try {
            this.wait.withTimeout(Duration.ofSeconds(timeOutSeconds))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isVisible(By MobileElement) {
        try {
            return this.getDriver().findElement(MobileElement).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected static void waitFor(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ignored) {

        }
    }


    public void scrollDown() {
        new TouchAction(driver)
                .press(PointOption.point(500, 1500))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(500, 500))
                .release()
                .perform();
    }

    public void scrollUp() {
        new TouchAction(driver)
                .press(PointOption.point(500, 500))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(500, 1500))
                .release()
                .perform();
    }


    protected void scrollDownUntilElementVisible(By locator) throws Exception {
        MobileElement element = null;
        int attempts = 0;
        int maxAttempts = 5;
        while ((element == null || !element.isDisplayed()) && attempts < maxAttempts) {
            try {
                element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return;
                }
            } catch (NoSuchElementException e) {
                new TouchAction<>(driver)
                        .press(PointOption.point(500, 1000))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(80)))
                        .moveTo(PointOption.point(500, 500))
                        .release()
                        .perform();
                attempts++;
                waitSec(1);
            }
        }
        if (element == null || !element.isDisplayed()) {
            throw new Exception("El elemento no se pudo encontrar después de 5 intentos de desplazamiento.");
        }
    }


    protected boolean scrollDownToElement(By element) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            if (!webElement.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error haciendo scroll al elemento", e);
        }
    }


}
