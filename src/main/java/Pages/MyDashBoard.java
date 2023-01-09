package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyDashBoard {

    WebDriver driver;

    public MyDashBoard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".success-msg ul li span")
    public WebElement successMessage;


    @FindBy(xpath = "//a[text()='TV']")
    public WebElement TVTab;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div[2]/ul/li[8]/a")
    public WebElement myWishListTab;
    @FindBy(xpath = "//*[@id=\"top\"]/body/div/div/div[2]/div/div[1]/div/div[2]/ul/li[4]/a")
    public WebElement myOrdersTab;





}
