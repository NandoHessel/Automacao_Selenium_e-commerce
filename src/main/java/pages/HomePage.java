package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList<>();

    private By produtos = By.cssSelector("div.thumbnail-container");
    private By textoProdutosNoCarrinho = By.className("cart-products-count");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public int contarProdutos() {
        carregarListaProdutos();
        return listaProdutos.size();
    }

    private void carregarListaProdutos() {
        listaProdutos = driver.findElements(produtos);
    }

    public int obterQuantidadeProdutosNoCarrinho() {
        String quantidadeProdutosNoCarrinho=  driver.findElement(textoProdutosNoCarrinho).getText();
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(","");
        quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");
        int qtdProdutosNoCarrinho = Integer.parseInt(quantidadeProdutosNoCarrinho);
        return qtdProdutosNoCarrinho;
    }
}
