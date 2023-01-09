package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceivedOrderPage {
    WebDriver driver;

    public ReceivedOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".page-title h1")
    public WebElement titleOfPage;
    @FindBy(css = ".col-main p a")
    public WebElement orderNumber;

}
