package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    String fisrtName = "Fernando";
    String lastName = "Hessel";
    String email = "fernando@email.com";
    String password = "password";
    String birthday = "02/18/1993";

    private WebDriver driver;

    private By socialTitle = By.cssSelector("input[name=\"id_gender\"][value=\"1\"]");
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

    public void registroDeUsuario() {
        driver.findElement(socialTitle).click();

        driver.findElement(nome).sendKeys(fisrtName);
        driver.findElement(sobrenome).sendKeys(lastName);
        driver.findElement(e_mail).sendKeys(email);
        driver.findElement(senha).sendKeys(password);
        driver.findElement(nascimento).sendKeys(birthday);

        driver.findElement(checkbox).click();
        driver.findElement(save).click();
    }
}
