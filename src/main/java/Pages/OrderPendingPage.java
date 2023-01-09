package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPendingPage {

    WebDriver driver;

    public OrderPendingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/a[2]")
    public WebElement printOrderButton;


}
