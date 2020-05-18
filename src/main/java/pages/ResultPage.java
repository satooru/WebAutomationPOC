package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverManager;

public class ResultPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='ctrlcontent']/p")
    WebElement labelResultado;

    @FindBy(xpath = "//table[@class='tmptabela']/tbody")
    WebElement tableResultado;

    @FindBy(xpath = "//div[@class='ctrlcontent']/div[2]/a")
    List<WebElement> linksProximo;

    public ResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getResultLabelText() {
        return labelResultado.getText();
    }

    public void outputResults() {
        System.out.println("Resultado: " + labelResultado.getText());
        int i = 0;
        boolean lastPage = false;
        if(!labelResultado.getText().contains("NAO")) {
            do { //Paginacao
                List<WebElement> linhas = tableResultado.findElements(By.tagName("tr"));
                for(WebElement linha : linhas) {
                    if (linha.findElements(By.tagName("td")).size() > 0) {
                        i++;
                        System.out.println("Logradouro/Nome: " + linha.findElements(By.tagName("td")).get(0).getText());
                        System.out.println("Bairro/Distrito: " + linha.findElements(By.tagName("td")).get(1).getText());
                        System.out.println("Localidade/UF: " + linha.findElements(By.tagName("td")).get(2).getText());
                        System.out.println("CEP: " + linha.findElements(By.tagName("td")).get(3).getText());
                        System.out.println("-------------------------------");
                    }
                }
                if (linksProximo.size() > 0) {
                    linksProximo.get(0).click();
                    WebDriverManager.getInstance().waitUntilElementIsClickable(tableResultado);
                }
                else{
                    lastPage = true;
                }
            }while(linksProximo.size() > 0 || !lastPage);
            System.out.println(i + " Registro(s) Encontrado(s)");
        }
    }
}
