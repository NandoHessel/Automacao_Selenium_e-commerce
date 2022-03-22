package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;

    private By masculino = By.cssSelector("input[name=\"id_gender\"][value=\"1\"]");
    private By feminimo = By.cssSelector("input[name=\"id_gender\"][value=\"2\"]");
    private By nome = By.cssSelector("input[name=\"firstname\"]");
    private By sobrenome = By.cssSelector("input[name=\"lastname\"]");
    private By e_mail = By.cssSelector("input[name=\"email\"]");
    private By senha = By.cssSelector("input[name=\"password\"]");
    private By nascimento = By.cssSelector("input[name=\"birthday\"]");
    private By checkbox = By.cssSelector("input[name=\"psgdpr\"]");
    private By save = By.cssSelector("button[class=\"btn btn-primary form-control-submit float-xs-right\"]");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    } //construtor

    public void generoMasculinoDeUsuario() {
        driver.findElement(masculino).click();
    }
    public void generoFeminimoDeUsuario() {
        driver.findElement(feminimo).click();
    }
    public void nomeDeUsuario(String texto) {
        driver.findElement(nome).sendKeys(texto);
        }
    public void sobrenomeDeUsuario(String texto) {
        driver.findElement(sobrenome).sendKeys(texto);
    }
    public void emailDeUsuario(String texto) {
        driver.findElement(e_mail).sendKeys(texto);
    }
    public void senhaDeUsuario(String texto) {
        driver.findElement(senha).sendKeys(texto);
    }
    public void anoDeNascimentoDeUsuario(String texto) {
        driver.findElement(nascimento).sendKeys(texto);
    }
    public void finalizarCadastro() {
        driver.findElement(checkbox).click();
        driver.findElement(save).click();
    }
}
