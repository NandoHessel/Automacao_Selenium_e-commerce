package steps;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;
import pages.SigninPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComprarProdutosSteps {

    private static WebDriver driver;
    private HomePage homePage = new HomePage(driver);
    private SigninPage signinPage = new SigninPage(driver);
    private ProdutoPage produtoPage = new ProdutoPage(driver);
    private ModalProdutoPage modalProdutoPage = new ModalProdutoPage(driver);

    @Before //especifico do cucumber
    public static void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Web Drivers\\ChromeDriver\\99\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Dado("que estou na pagina inicial")
    public void queEstouNaPaginaInicial() {
        homePage.carregarPaginaInicial();
        assertThat(homePage.obterTituloPagina(), is("Loja de Teste"));
    }

    @Quando("nao estou logado")
    public void naoEstouLogado() {
        assertThat(signinPage.estaLogado(), is(false));
    }

    @Entao("visualizo {int} produtos disponiveis")
    public void visualizoProdutosDisponiveis(int num) {
        assertThat(homePage.contarProdutos(), is(num));
    }

    @E("carrinho zerado")
    public void carrinhoZerado() {
        assertThat(homePage.obterQuantidadeProdutosNoCarrinho(), is(0));
    }

    @Quando("estou logado")
    public void estouLogado() {
        homePage.cadastroDeUsuário();

        signinPage.emailAcesso("fernando@email.com");
        signinPage.senhaAcesso("password");

        signinPage.loginAcesso();
        //validação
        assertThat(signinPage.estaLogado("Fernando Hessel"), is(true));

        //após a validação, volta para a tela inicial
        homePage.carregarPaginaInicial();
    }

    String nomeProduto_HomePage;
    String precoProduto_HomePAge;

    String nomeProduto_ProdutoPage;
    String precoProduto_ProdutoPage;

    @E("seleciono um produto na posição {int}")
    public void selecionoUmProdutoNaPosição(int posicao) {
        nomeProduto_HomePage = homePage.obterNomeProduto(posicao);
        precoProduto_HomePAge = homePage.obterPrecoProduto(posicao);
        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePAge);

        homePage.clicarProduto(posicao);

        nomeProduto_ProdutoPage = produtoPage.obterNomeProduto_ProdutoPage();
        precoProduto_ProdutoPage = produtoPage.obterPrecoProduto_ProdutoPage();
        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);
    }

    @E("nome do produto na tela principal eh {string}")
    public void nomeDoProdutoNaTelaPrincipalEh(String nomeProduto) {
        assertThat(nomeProduto_HomePage.toLowerCase(), is(nomeProduto.toLowerCase()));
        assertThat(nomeProduto_ProdutoPage.toLowerCase(), is(nomeProduto.toLowerCase()));
    }

    @E("o preco do produto eh {string}")
    public void oPrecoDoProdutoEh(String precoProduto) {
        assertThat(precoProduto_HomePAge, is(precoProduto));
        assertThat(precoProduto_ProdutoPage, is(precoProduto));
    }

    @E("adiciono o produto no carrinho com tamanho {string}, com cor {string} e quantidade {int}")
    public void adicionoOProdutoNoCarrinhoComTamanhoComCorEQuantidade(
            String tamanhoProduto, String corProduto, Integer quantidadeProduto) {
        //selecionar tamanho
        List<String> listaOpcoes = produtoPage.obterOpecoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());

        produtoPage.selecionarOpcaoDropDown(tamanhoProduto);
        listaOpcoes = produtoPage.obterOpecoesSelecionadas();
        System.out.println(listaOpcoes.get(0));
        System.out.println("Tamanho da lista: " + listaOpcoes.size());
        //selecionar cor
        if (corProduto.toLowerCase().contains("black")) {
            produtoPage.selecionarCorPreta();
        }
        //selecionar quantidade
        produtoPage.alterarQuantidade(quantidadeProduto);
        //adicionar no carrinho
        produtoPage.adcionarAoCarrinho();
        //validar se o produto foi adicionado corretamente
        assertThat(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"), is(true));

    }

    @Entao("o produto aparece na confirmacao com nome {string}, com preco {string}, com tamanho {string}, com cor {string} e com quantidade {int}")
    public void oProdutoApareceNaConfirmacaoComNomeComPrecoComTamanhoComCorEComQuantidade
            (String nomeProduto, String precoProduto, String tamanhoProduto, String corProduto, Integer quantidadeProduto) {
        //validar se as características são as mesmas
        assertThat(modalProdutoPage.obterNomeDoProduto().toLowerCase(), is(nomeProduto.toLowerCase()));

        Double precoProsutoEncontrado = Double.parseDouble(modalProdutoPage.obterPrecoDoProduto().replace("$", ""));
        Double precoProdutoDoubleEsperado = Double.parseDouble(precoProduto.replace("$", ""));

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        if (!corProduto.equals("N/A")) {
            assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        }
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));

        String subtotalProdutoRetornado = modalProdutoPage.obterSubtotal();
        subtotalProdutoRetornado = subtotalProdutoRetornado.replace("$", "");
        Double subtotalEncontrado = Double.parseDouble(subtotalProdutoRetornado);

        Double subtotalCalculadoEsperado = quantidadeProduto * precoProdutoDoubleEsperado;

        assertThat(subtotalEncontrado, is(subtotalCalculadoEsperado));

    }
}
