package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverManager;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "acesso-busca")
    WebElement acessoBusca;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void buscarEndereco(String conteudo) {
        WebDriverManager.getInstance().waitUntilElementIsClickable(acessoBusca);
        acessoBusca.sendKeys(conteudo);
        acessoBusca.sendKeys(Keys.ENTER);
    }
}
