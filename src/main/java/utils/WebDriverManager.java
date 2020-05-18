package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WebDriverManager takes care of WebDriver (ChromeDriver)
 */
public class WebDriverManager {

    private static WebDriverManager manager = null;

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor identifies which OS the application is running on, in order to specify if it should include ".exe" for windows
     * Constructor assumes ChromeDriver is located at src/main/resources/
     */
    public WebDriverManager() {
        newWebDriver();
    }

    public void newWebDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("running on " + os);
        if (os.contains("win"))
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        else if (os.contains("osx") || os.contains("nix") || os.contains("aix") || os.contains("nux"))
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        else
            new Exception("OS not known to utils.WebDriverManager");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 90);
        
    }

    public static WebDriverManager getInstance() {
        if (manager == null)
            manager = new WebDriverManager();
        return manager;
    }

    /**
     * returns WebDriver
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        if(driver == null)
            newWebDriver();
        return driver;
    }

    /**
     * sets WebDriver
     * @param WebDriver driver
     */
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * returns Page Title
     * @return String title
     */
    public String getTitle() {
        if(driver == null)
            newWebDriver();
        return driver.getTitle();
    }

    /**
     * opens page given an address
     * @param String url
     */
    public void openPage(String url) {
        if(driver == null)
            newWebDriver();
        driver.get(url);
        waitPageLoad();
    }

    /**
     * closes all tabs and quit browser
     */
    public void quit() {
        driver.quit();
        driver = null;
    }

    public void close() {
        if(driver == null)
            newWebDriver();
        driver.close();
    }

    public void waitPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
