package BackEnd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[3]/ul/li[1]/a/span")
    public WebElement salesMenu;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[3]/ul/li[1]/ul/li[1]/a")
    public WebElement orders;

    @FindBy(xpath = "/html/body/div[1]/div[5]/div[1]/a")
    public WebElement closePopUpMessage;

    @FindBy(id = "sales_order_grid_export")
    public WebElement exportSelect;

    @FindBy(css = ".export button")
    public WebElement exportButton;


}
