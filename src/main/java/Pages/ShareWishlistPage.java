package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShareWishlistPage {

    WebDriver driver;

    public ShareWishlistPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "email_address")
    public WebElement email_address;
    @FindBy(id = "message")
    public WebElement message;

    @FindBy(css = "button[title=\"Share Wishlist\"]")
    public WebElement shareWithListButton;


}
