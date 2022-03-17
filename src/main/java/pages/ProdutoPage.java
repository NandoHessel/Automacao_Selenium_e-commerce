package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutoPage {

    private WebDriver driver;

    private By nomeProduto_ProdutoPage = By.className("h1");
    private By precoDosProduto_ProdutoPage = By.cssSelector(".current-price span:nth-child(1)");

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
    } //construtor

    public String obterNomeProduto_ProdutoPage() {
        return driver.findElement(nomeProduto_ProdutoPage).getText();
    }

    public String obterPrecoProduto_ProdutoPage() {
        return driver.findElement(precoDosProduto_ProdutoPage).getText();
    }

}
