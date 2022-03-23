package base;

import pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProdutoPage;
import pages.RegisterPage;
import pages.SigninPage;

public class BaseTests {

    public static WebDriver driver;
    protected HomePage homePage;
    protected  SigninPage signinPage;
    protected RegisterPage registerPage;
    protected ProdutoPage produtoPage;

    @BeforeAll
    public static void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Web Drivers\\ChromeDriver\\99\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void carregarPaginaInicial() {
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
        homePage = new HomePage(driver);
        signinPage = new SigninPage(driver);
        registerPage = new RegisterPage(driver);
        produtoPage = new ProdutoPage(driver);
    }

    //@AfterAll
    //public static void finalizar() {
      //  driver.quit();
    //}

}
