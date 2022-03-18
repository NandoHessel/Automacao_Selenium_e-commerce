package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage {

    private  WebDriver driver;

    private  By noAcount = By.className("no-account");

    private By emailAcesso = By.cssSelector("input[class=\"form-control\"][name=\"email\"]");
    private By senhaAcesso = By.cssSelector("input[name=\"password\"]");
    private By btnSignIn = By.id("submit-login");
    private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");

    String email = "fernando@email.com";
    String password = "password";

    public SigninPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage acessoDeUsuario() {
        driver.findElement(noAcount).click();
        return new RegisterPage(driver);
    }

    public void preencherDadosDoUsuarioEAcesso() {
        driver.findElement(emailAcesso).sendKeys(email);
        driver.findElement(senhaAcesso).sendKeys(password);

        driver.findElement(btnSignIn).click();
    }

    public boolean estaLogado(String texto) {
        return texto.contentEquals(driver.findElement(usuarioLogado).getText());
    }
}
