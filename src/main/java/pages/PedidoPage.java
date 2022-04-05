package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.Funcoes;

public class PedidoPage {

    private WebDriver driver;
    
    private By textoPedidoConfirmado = By.cssSelector("#content-hook_order_confirmation h3");
    private By emailUsuarioConectado = By.cssSelector("#content-hook_order_confirmation p");
    private By totalProducts = By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold");
    private By totalTaxincl = By.cssSelector(".order-confirmation-table table tr.total-value td:nth-child(2)");
    private By metodoPagamento = By.cssSelector("#order-details ul li:nth-child(2)");

    public PedidoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String obter_textoConfirmação() {
        return driver.findElement(textoPedidoConfirmado).getText();
    }

    public String obter_emailUsuarioConectado() {
        //An email has been sent to the marcelo@teste.com address.
        String texto = driver.findElement(emailUsuarioConectado).getText();
        texto = Funcoes.removeTexto(texto, "An email has been sent to the ");
        texto = Funcoes.removeTexto(texto, " address.");
        return texto;
    }

    public Double obter_totalProducts() {
        return Funcoes.removeCifraoDevolveDouble(driver.findElement(totalProducts).getText());
    }

    public Double obter_totalTaxIncl() {
        return Funcoes.removeCifraoDevolveDouble(driver.findElement(totalTaxincl).getText());
    }

    public String obter_metodoPagamento() {
        return Funcoes.removeTexto(driver.findElement(metodoPagamento).getText(), "Payment method: Payments by ");
    }
    
}
