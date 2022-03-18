package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage {

    private  WebDriver driver;

    private  By noAcount = By.className("no-account");

    public SigninPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage acessoDeUsuario() {
        driver.findElement(noAcount).click();
        return new RegisterPage(driver);
    }
}
