package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompareProductsPage {

    WebDriver driver;

    public CompareProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".product-name a[title=\"Sony Xperia\"]")
    public WebElement sony;

    @FindBy(css = ".product-name a[title=\"IPhone\"]")
    public WebElement iphone;

    @FindBy(css = "button[title=\"Close Window\"]")
    public WebElement closeButton;

    @FindBy(css = ".page-title h1")
    public WebElement headTitle;


}
