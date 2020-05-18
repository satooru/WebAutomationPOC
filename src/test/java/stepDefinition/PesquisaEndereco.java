package stepDefinition;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import pages.HomePage;
import pages.ResultPage;
import utils.WebDriverManager;

public class PesquisaEndereco {

    WebDriver driver;
    HomePage homePage;
    ResultPage resultPage;

    @Dado("que abra a página dos correios")
    public void que_abra_a_página_dos_correios() {
        driver = WebDriverManager.getInstance().getWebDriver();
        WebDriverManager.getInstance().openPage("https://correios.com.br/");
        assertTrue("titulo da pagina", driver.getTitle().contains("Correios"));
    }

    @Dado("que digite {string} no textbox de pesquisa de endereço")
    public void que_digite_o_no_textbox_de_pesquisa(String string) {
        homePage = new HomePage(driver);
        homePage.buscarEndereco(string);
    }

    @Então("deve mostrar o output {string} das informações retornadas")
    public void deve_mostrar_o_output_das_informações_retornadas(String string) {
        boolean found = Boolean.valueOf(string);
        switchTab();
        resultPage = new ResultPage(driver);
        assertTrue("Resultado esperado", (found == true && resultPage.getResultLabelText().equals("DADOS ENCONTRADOS COM SUCESSO."))
                                      || (found == false && resultPage.getResultLabelText().equals("DADOS NAO ENCONTRADOS")));
        resultPage.outputResults();
    }

    public void switchTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
}
