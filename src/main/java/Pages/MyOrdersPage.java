package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyOrdersPage {

    WebDriver driver;

    public MyOrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"my-orders-table\"]/tbody/tr/td[6]/span/a[1]")
    public WebElement viewOrderButton;

    @FindBy(css = "tbody .first .number")
    public WebElement orderID;

    @FindBy(css = "tbody .first .status em")
    public WebElement orderStatus;

    @FindBy(className = "link-reorder")
    public WebElement reorderButton;


}
