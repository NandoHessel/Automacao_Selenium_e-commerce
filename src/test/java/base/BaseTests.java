package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.Funcoes;

import java.io.File;
import java.io.IOException;

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
    protected PedidoPage pedidoPage;


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
        pedidoPage = new PedidoPage(driver);
    }

    public void capturarTela(String nomeTeste, String resultado) {
        var camera = (TakesScreenshot) driver;
        File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(capturaDeTela, new File("resources/screenShots/" + nomeTeste + "_" + resultado + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void evidenciarPasso(String nome, String local) {
        var camera = (TakesScreenshot) driver;
        File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(capturaDeTela, new File(local + nome + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@AfterAll
    //public static void finalizar() {
      //  driver.quit();
    //}
}


