package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWishListPage {

    WebDriver driver;

    public MyWishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".success-msg ul li span")
    public WebElement successMessage;


    @FindBy(name = "save_and_share")
    public WebElement shareWishListButton;

    @FindBy(css = "button[title=\"Add to Cart\"]")
    public WebElement addToCartButton;


}
