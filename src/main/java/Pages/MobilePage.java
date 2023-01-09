package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MobilePage {
    WebDriver driver;

    public MobilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".sort-by select")
    public WebElement sortBySelect;

    @FindBy(css = ".page-title h1")
    public WebElement pageTitle;

    @FindBy(css = ".product-info .product-name a")
    public List<WebElement> productsName;

    @FindBy(css = "#product-price-1 span")
    public WebElement sonyPrice;

    @FindBy(id = "product-collection-image-1")
    public WebElement sonyProduct;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")
    public WebElement sonyAddToCartButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button")
    public WebElement iphoneAddToCart;

    @FindBy(xpath = "//h2/a[@title='IPhone']")
    public WebElement iphoneMobile;

    @FindBy(xpath = "//h2/a[@title='Sony Xperia']")
    public WebElement sonyXperiaMobile;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/ul/li[2]/a")
    public WebElement iphoneAddToCompareButtons;

    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/div[3]/ul/li[2]/a")
    public WebElement sonyAddToCompareButton;

    @FindBy(css = ".actions button[title=\"Compare\"]")
    public WebElement compareButton;


}
