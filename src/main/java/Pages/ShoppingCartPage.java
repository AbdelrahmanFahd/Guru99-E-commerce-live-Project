package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {


    WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "input[title=\"Qty\"]")
    public WebElement Qty;

    @FindBy(css = "button[title=\"Update\"]")
    public WebElement updateButton;
    @FindBy(css = ".item-msg")
    public WebElement errorMessage;
    @FindBy(css = "#empty_cart_button")
    public WebElement emptyCartButton;

    @FindBy(css = ".cart-empty p")
    public WebElement cartEmptyMessage;
    @FindBy(css = "button[title=\"Proceed to Checkout\"]")
    public WebElement proceedToCheckoutButton;


    @FindBy(id = "country")
    public WebElement countrySelect;

    @FindBy(id = "region_id")
    public WebElement stateSelect;

    @FindBy(id = "postcode")
    public WebElement postcode;
    @FindBy(css = ".buttons-set button[title=\"Estimate\"]")
    public WebElement estimateButton;
    @FindBy(id = "s_method_flatrate_flatrate")
    public WebElement flatRateCheck;

    @FindBy(css = "label span[class=\"price\"]")
    public WebElement flatRateShipping;

    @FindBy(css = "strong span[class=\"price\"]")
    public WebElement totalProductCost;
    @FindBy(name = "do")
    public WebElement updateTotalButton;

    @FindBy(id = "coupon_code")
    public WebElement couponCodeInput;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div/div[2]/form/div/div/div/div/button")
    public WebElement applyCouponCodeButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div/div[3]/div/table/tbody/tr[1]/td[2]/span")
    public WebElement supTotal;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div/div/div[3]/div/table/tbody/tr[2]/td[2]/span")
    public WebElement discount;


}
