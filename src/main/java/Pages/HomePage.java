package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Mobile']")
    public WebElement MobileTab;
    @FindBy(xpath = "//a[text()='TV']")
    public WebElement TVTab;

    @FindBy(css = ".page-title h2")
    public WebElement pageTitle;

    @FindBy(xpath = "/html/body/div/div/header/div/div[2]/div/a")
    public WebElement accountLink;

    @FindBy(xpath = "/html/body/div/div/header/div/div[5]/div/ul/li[1]/a")
    public WebElement myAccountLink;
    @FindBy(css = ".last a[title=\"Log Out\"]")
    public WebElement logOut;

}
