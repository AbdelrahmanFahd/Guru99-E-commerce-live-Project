package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
    WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "firstname")
    public WebElement firstName;
    @FindBy(id = "middlename")
    public WebElement middleName;
    @FindBy(id = "lastname")
    public WebElement lastName;
    @FindBy(id = "email_address")
    public WebElement email_address;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(id = "confirmation")
    public WebElement confirmation;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div/form/div[2]/button")
    public WebElement registerButton;

}
