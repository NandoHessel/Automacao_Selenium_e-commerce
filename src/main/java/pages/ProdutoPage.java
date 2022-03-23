package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProdutoPage {

    private WebDriver driver;

    private By nomeProduto_ProdutoPage = By.className("h1");
    private By precoDosProduto_ProdutoPage = By.cssSelector(".current-price span:nth-child(1)");

    private By tamanhoProduto = By.id("group_1");
    private By inputCorPreta = By.cssSelector("input[class=\"input-color\"][value=\"11\"]");
    private By qtdeProduto = By.id("quantity_wanted");
    private By addcarrinho = By.className("add-to-cart");

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
    } //construtor

    public String obterNomeProduto_ProdutoPage() {
        return driver.findElement(nomeProduto_ProdutoPage).getText();
    }

    public String obterPrecoProduto_ProdutoPage() {
        return driver.findElement(precoDosProduto_ProdutoPage).getText();
    }

    public void selecionarOpcaoDropDown(String opcao) {
        encontrarDropDownSize().selectByVisibleText(opcao);
    }

    public List<String> obterOpecoesSelecionadas() {
        List<WebElement> elementosSelecionados = encontrarDropDownSize().getAllSelectedOptions();

        List<String> listaOpcoesSelecionadas = new ArrayList();
        for (WebElement elemento : elementosSelecionados) {
            listaOpcoesSelecionadas.add(elemento.getText());
        }
        return listaOpcoesSelecionadas;
    }

    public Select encontrarDropDownSize() {
        return new Select(driver.findElement(tamanhoProduto));
    }

    public void selecionarCorPreta() {
        driver.findElement(inputCorPreta).click();
    }

    public void alterarQuantidade(int qtde) {
        driver.findElement(qtdeProduto).clear();
        driver.findElement(qtdeProduto).sendKeys(Integer.toString(qtde));
    }

    public void adcionarAoCarrinho() {
        driver.findElement(addcarrinho).click();
    }
}
