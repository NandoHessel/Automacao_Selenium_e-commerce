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
    
    private By payByCheck = By.cssSelector("input[id=\"payment-option-1\"]");
    private By valorAmount = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");
    private By iAgree = By.cssSelector("input[id=\"conditions_to_approve[terms-and-conditions]\"]");

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
    
    public void clicarPayByCheck() {
        driver.findElement(payByCheck).click();
    }
    
    public String obter_amount() {
        return driver.findElement(valorAmount).getText();
    }

    public void clicarIAgree() {
        driver.findElement(iAgree).click();
    }

    public boolean estaSelecionadoIAgree() {
        return driver.findElement(iAgree).isSelected();
    }

}
