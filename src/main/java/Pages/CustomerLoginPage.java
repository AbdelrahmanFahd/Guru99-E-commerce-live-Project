package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLoginPage {

    WebDriver driver;

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[title=\"Create an Account\"]")
    public WebElement createAnAccount;

    @FindBy(id = "email")
    public WebElement emailFiled;
    @FindBy(id = "pass")
    public WebElement passwordFiled;
    @FindBy(id = "send2")
    public WebElement loginButton;


}
