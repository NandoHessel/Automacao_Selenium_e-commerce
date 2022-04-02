package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    private By totalTaxaIncTotal = By.cssSelector("div.cart-total span.value");
    private By nomeCliente = By.cssSelector("div.address");
    private By btnContinuar = By.cssSelector("button[name=\"confirm-addresses\"]");
    private By shippingValor = By.cssSelector("span.carrier-price");
    private By continueShipping = By.cssSelector("button[name=\"confirmDeliveryOption\"]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_totalTaxaIncTotal() {
        return driver.findElement(totalTaxaIncTotal).getText();
    }
    
    public String obter_nomeCliente() {
        return driver.findElement(nomeCliente).getText();
    }

    public void clicarBotaoContinueAddresses() {
        driver.findElement(btnContinuar).click();
    }

    public String obter_shippingValor() {
        return driver.findElement(shippingValor).getText();
    }

    public void clicarContinuarShipping() {
        driver.findElement(continueShipping).click();
    }

}
