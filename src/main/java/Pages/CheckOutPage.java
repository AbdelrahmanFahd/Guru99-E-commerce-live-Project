package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "billing:company")
    public WebElement company;

    @FindBy(id = "billing:street1")
    public WebElement address;

    @FindBy(id = "billing:city")
    public WebElement city;

    @FindBy(id = "billing:region_id")
    public WebElement stateSelect;

    @FindBy(id = "billing:postcode")
    public WebElement postcode;
    @FindBy(id = "billing:country_id")
    public WebElement countrySelect;
    @FindBy(id = "billing:telephone")
    public WebElement telephone;
    @FindBy(id = "billing:fax")
    public WebElement fax;

    @FindBy(css = "button[title=\"Continue\"]")
    public WebElement shippingInformationContinueButton;
    @FindBy(css = "#shipping-method-buttons-container button")
    public WebElement shippingMethodContinueButton;

    @FindBy(id = "p_method_checkmo")
    public WebElement moneyOrderCheck;
    @FindBy(css = "#payment-buttons-container button")
    public WebElement paymentContinueButton;


    @FindBy(css = "#review-buttons-container button")
    public WebElement placeOrderButton;

    @FindBy(id = "billing-address-select")
    public WebElement addressSelect;
}
