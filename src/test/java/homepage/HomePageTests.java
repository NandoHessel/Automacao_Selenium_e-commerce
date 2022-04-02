package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.ProdutoPage;
import pages.SigninPage;
import util.Funcoes;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    //FLUXO PADRÃO
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
        String genero = "";
        if (genero.toLowerCase().contains("m")) {
            registerPage.generoMasculinoDeUsuario();
        } else {
            registerPage.generoFeminimoDeUsuario();
        }
        //informa os dados do usuário. Preencher no parâmetro
        registerPage.nomeDeUsuario("");
        registerPage.sobrenomeDeUsuario("");
        registerPage.emailDeUsuario("");
        registerPage.senhaDeUsuario("");
        //mês/dia/ano
        registerPage.anoDeNascimentoDeUsuario("");
        registerPage.finalizarCadastro();

    }

    //Teste de Login
    @Test
    public void testLoginComSucesso_UsuarioLogado() {
        homePage.cadastroDeUsuário();
        signinPage.emailAcesso("fernando@email.com");
        signinPage.senhaAcesso("password");
        signinPage.loginAcesso();
        //validação
        assertThat(signinPage.estaLogado("Fernando Hessel"), is(true));

        //após a validação, volta para a tela inicial
        carregarPaginaInicial();
    }

    //adicionando itens ao carrinho após alterar características e quantidade
    @Test
    public void testIncluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {

        var nomeProduto = "Hummingbird printed t-shirt";
        var precoProduto = 19.12;
        var tamanhoProduto = "M";
        var corProduto = "Black";
        var quantidadeProduto = 2;
        var subtotal = quantidadeProduto * precoProduto;

        //pré-condição: usuário logado
        if (!signinPage.estaLogado("Fernando Hessel")) {
            testLoginComSucesso_UsuarioLogado();
        }
        //selecionando produto
        testVAlidarDetalhesDoProduto_DescricaoEValorIguais();
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
        assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().
                endsWith("Product successfully added to your shopping cart"));
        //validar se as características são as mesmas
        assertThat(modalProdutoPage.obterNomeDoProduto().toLowerCase(), is(nomeProduto.toLowerCase()));

        String precoProdutoRetornado = modalProdutoPage.obterPrecoDoProduto();
        precoProdutoRetornado = precoProdutoRetornado.replace("$", "");
        Double preco_produto = Double.parseDouble(precoProdutoRetornado);
        assertThat(preco_produto, is(precoProduto));

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeProduto)));

        String subtotalProdutoRetornado = modalProdutoPage.obterSubtotal();
        subtotalProdutoRetornado = subtotalProdutoRetornado.replace("$", "");
        Double subtotal_Produto = Double.parseDouble(subtotalProdutoRetornado);
        assertThat(subtotal_Produto, is(subtotal));
    }

    //finalizando ida para o carrinho
    @Test
    public void testIrParaCarrinho_InformacoesPersistidas() {
        //valores esperados
        var esperado_nomeProduto = "Hummingbird printed t-shirt";
        var esperado_precoProduto = 19.12;
        var esperado_tamanhoProduto = "M";
        var esperado_corProduto = "Black";
        var esperado_input_quantidadeProduto = 2;
        var esperado_subtotalProduto = esperado_precoProduto * esperado_input_quantidadeProduto;
        var esperado_nomeCliente = "Fernando Hessel";

        //pré condição
        testIncluirProdutoNoCarrinho_ProdutoIncluidoComSucesso();
        modalProdutoPage.clicarBotaoProceedToCheckout();

        //asserções por Hamcrest
        assertThat(carrinhoPage.obter_nomeProduto(), is(esperado_nomeProduto));
        assertThat(funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_precoProduto()), is(esperado_precoProduto));
        assertThat(carrinhoPage.obter_tamanhoProduto(), is(esperado_tamanhoProduto));
        assertThat(carrinhoPage.obter_corProduto(), is(esperado_corProduto));
        assertThat(Funcoes.removeTextoDevolveInt(carrinhoPage.obter_quantidadeProduto()), is(esperado_input_quantidadeProduto));
        assertThat(Funcoes.removeCifraoDevolveDouble(carrinhoPage.obter_subtotalProduto()), is(esperado_subtotalProduto));

        carrinhoPage.clicarBotaoProceedToCheckout();
    }

    //preencher dados para frete e pagamento
    @Test
    public void testDadosFreteEPagamento() {
        testIrParaCarrinho_InformacoesPersistidas();

        //validar informações na tela
        assertThat(Funcoes.removeCifraoDevolveDouble(checkoutPage.obter_totalTaxaIncTotal()), is(45.24));
        assertTrue(checkoutPage.obter_nomeCliente().startsWith("Fernando Hessel"));

        checkoutPage.clicarBotaoContinueAddresses();

        String shippinString = Funcoes.removeTexto(checkoutPage.obter_shippingValor(), " tax excl.");
        Double shippingDouble = Funcoes.removeCifraoDevolveDouble(shippinString);

        assertThat(shippingDouble, is(7.00));

        checkoutPage.clicarContinuarShipping();
    }
}



