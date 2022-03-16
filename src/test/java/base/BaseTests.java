package base;

import pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTests {

    private static WebDriver driver;
    protected HomePage homePage;

    @BeforeAll
    public static void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Web Drivers\\ChromeDriver\\99\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void carregarPaginaInicial() {
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
        homePage = new HomePage(driver);
    }

    @AfterAll
    public static void finalizar() {
        driver.quit();
    }

}
