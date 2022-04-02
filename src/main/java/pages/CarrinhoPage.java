package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

    private WebDriver driver;

    private By nomeProduto = By.cssSelector("div.product-line-info a");
    private By precoProduto = By.cssSelector("span.price");
    private By tamanhoProduto = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class,'value')]");
    private By corProduto = By.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class,'value')]");
    private By qtdeProduto = By.cssSelector("input.js-cart-line-product-quantity");
    private By subtotalProduto = By.cssSelector("span.product-price strong");
    private By botaoProceedToCheckout = By.cssSelector("a.btn-primary");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_nomeProduto() {
        return driver.findElement(nomeProduto).getText();
    }

    public String obter_precoProduto() {
        return driver.findElement(precoProduto).getText();
    }

    public String obter_tamanhoProduto() {
        return driver.findElement(tamanhoProduto).getText();
    }

    public String obter_corProduto() {
        return driver.findElement(corProduto).getText();
    }

    public String obter_quantidadeProduto() {
        return driver.findElement(qtdeProduto).getAttribute("value");
    }

    public String obter_subtotalProduto() {
        return driver.findElement(subtotalProduto).getText();
    }

    public CheckoutPage clicarBotaoProceedToCheckout() {
        driver.findElement(botaoProceedToCheckout).click();
        return new CheckoutPage(driver);

    }

}
