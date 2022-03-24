package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ModalProdutoPage {

    private WebDriver driver;

    private By mensagemProdutoAdicionado = By.id("myModalLabel");

    private By produtoSelecionado = By.cssSelector("h6[class=\"h6 product-name\"]");
    private By precoProduto = By.cssSelector("div.modal-body p.product-price");
    private By listaValoresInformados = By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong");
    private By subtotal = By.cssSelector(".cart-content p:nth-child(2) span.value");


    public ModalProdutoPage (WebDriver driver) {
        this.driver = driver;
    }

    public String obterMensagemProdutoAdicionado() {
        //função para fazer com que o Selenium espere até o item ficar visível para acioná-lo
        FluentWait wait = new FluentWait(driver).
                withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofSeconds(1)).
                ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));

        return driver.findElement(mensagemProdutoAdicionado).getText();
    }

    public String obterNomeDoProduto() {
        return driver.findElement(produtoSelecionado).getText();
    }

    public String obterPrecoDoProduto() {
        return driver.findElement(precoProduto).getText();
    }

    public String obterTamanhoProduto() {
        return driver.findElements(listaValoresInformados).get(0).getText();
    }

    public String obterCorProduto() {
        return driver.findElements(listaValoresInformados).get(1).getText();
    }

    public String obterQuantidadeProduto() {
        return driver.findElements(listaValoresInformados).get(2).getText();
    }

    public String obterSubtotal() {
        return driver.findElement(subtotal).getText();
    }
}
