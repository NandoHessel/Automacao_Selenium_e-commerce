package base;

import pages.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.Funcoes;

public class BaseTests {

    public static WebDriver driver;
    protected HomePage homePage;
    protected  SigninPage signinPage;
    protected RegisterPage registerPage;
    protected ProdutoPage produtoPage;
    protected ModalProdutoPage modalProdutoPage;
    protected CarrinhoPage carrinhoPage;
    protected Funcoes funcoes;
    protected CheckoutPage checkoutPage;

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
        modalProdutoPage = new ModalProdutoPage(driver);
        carrinhoPage = new CarrinhoPage(driver);
        funcoes = new Funcoes();
        checkoutPage = new CheckoutPage(driver);
    }

    //@AfterAll
    //public static void finalizar() {
      //  driver.quit();
    //}

}
