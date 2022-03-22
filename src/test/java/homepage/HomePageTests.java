package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.ProdutoPage;
import pages.SigninPage;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageTests extends BaseTests {

    //VALIDAÇÕES BÁSICAS
    @Test
    public void testContarProdutos_oitoProdutosDiferentes() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
        int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
        //System.out.println(produtosNoCarrinho);
        assertThat(produtosNoCarrinho, is(0));
    }

    //FLUXO PADRÃO - PARTE 1
    @Test
    public void testVAlidarDetalhesDoProduto_DescricaoEValorIguais() {
        int indice = 0;
        String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
        String precoProduto_HomePAge = homePage.obterPrecoProduto(indice);
        System.out.println(nomeProduto_HomePage);
        System.out.println(precoProduto_HomePAge);

        ProdutoPage produtoPage = homePage.clicarProduto(indice);
        String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto_ProdutoPage();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto_ProdutoPage();
        System.out.println(nomeProduto_ProdutoPage);
        System.out.println(precoProduto_ProdutoPage);

        assertThat(nomeProduto_HomePage.toLowerCase(), is(nomeProduto_ProdutoPage.toLowerCase()));
        assertThat(precoProduto_HomePAge, is(precoProduto_ProdutoPage));
    }

    //Cadastro de usuário
    @Test
    public void testCadastroDeNovoUsuário() {
        //abre a página de acesso
        homePage.cadastroDeUsuário();
        //abre a página de cadastro de usuário
        signinPage.acessoDeUsuario();
        //seleciona o gênero do usuário. M ou F
        String genero = "F";
        if (genero.toLowerCase().contains("m")) {
            registerPage.generoMasculinoDeUsuario();
        } else {
            registerPage.generoFeminimoDeUsuario();
        }
        //informa os dados do usuário. Preencher no parâmetro
        registerPage.nomeDeUsuario("Gessica");
        registerPage.sobrenomeDeUsuario("de Macedo");
        registerPage.emailDeUsuario("gessica@email.com");
        registerPage.senhaDeUsuario("gessica");
        //mês/dia/ano
        registerPage.anoDeNascimentoDeUsuario("06/18/1992");
        registerPage.finalizarCadastro();

    }

    //Teste de Login
    @Test
    public void testLoginComSucesso_UsuarioLogado() {
        homePage.cadastroDeUsuário();
        signinPage.emailAcesso("gessica@email.com");
        signinPage.senhaAcesso("gessica");
        signinPage.loginAcesso();
        //validação
        assertThat(signinPage.estaLogado("Gessica de Macedo"), is(true));
    }
}
