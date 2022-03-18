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
    private  By descricoesDosProdutos = By.cssSelector(".product-description a");
    private By precoDosProdutos = By.cssSelector(".price");

    private By signin = By.cssSelector("a[title=\"Log in to your customer account");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    } //construtor

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

    public String obterNomeProduto(int indice) {
        return driver.findElements(descricoesDosProdutos).get(indice).getText(); //findElements gera uma lista
    }

    public String obterPrecoProduto(int indice) {
        return driver.findElements(precoDosProdutos).get(indice).getText();
    }

    public ProdutoPage clicarProduto(int indice) {
        driver.findElements(descricoesDosProdutos).get(indice).click();
        return new ProdutoPage(driver);
    }

    public SigninPage cadastroDeUsuário() {
        driver.findElement(signin).click();
        return new SigninPage(driver);
    }




}
