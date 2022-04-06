package homepage;

import base.BaseTests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTeste_Massa extends BaseTests {

    //Teste utilizando massa
    @ParameterizedTest
    @CsvFileSource(resources = "/massaTeste_Login.csv", numLinesToSkip = 1, delimiter = ';')
    public void testLogin_UsuarioLogadoComDadosValidos(
            String nomeTeste, String email, String password, String nomeUsuario, String resultado) {
        //Clicar no botão SignIn na homePage
        homePage.cadastroDeUsuário();
        evidenciarPasso("Cadastro","resources/screenShots/");

        //Preencher usuário e senha
        signinPage.emailAcesso(email);
        signinPage.senhaAcesso(password);
        evidenciarPasso("Dados", "resources/screenShots/");

        //Clicar no botão SignIn para logar
        signinPage.loginAcesso();

        boolean esperado_loginOk;
        if (resultado.equals("positivo"))
            esperado_loginOk = true;
        else
            esperado_loginOk = false;

        //validação se o usuário está logado
        assertThat(signinPage.estaLogado(nomeUsuario), is(esperado_loginOk));
        capturarTela(nomeTeste, resultado);

        if (esperado_loginOk)
            homePage.clicarBotaoSignOut();

    }
}
