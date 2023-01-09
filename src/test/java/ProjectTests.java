import BackEnd.AdminPage;
import BackEnd.BackLoginPage;
import Pages.*;
import Util.Util;
import com.google.common.collect.Ordering;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static org.testng.Assert.assertEquals;

public class ProjectTests extends BaseTests {

    HomePage homePage;
    MobilePage mobilePage;
    DetailProductPage detailProductPage;
    ShoppingCartPage shoppingCartPage;
    CompareProductsPage compareProductsPage;
    CustomerLoginPage customerLoginPage;
    CreateAccountPage createAccountPage;
    TVPage tvPage;
    MyWishListPage myWishListPage;
    MyDashBoard myDashBoard;
    ShareWishlistPage shareWishlistPage;
    CheckOutPage checkOutPage;
    ReceivedOrderPage receivedOrderPage;
    MyOrdersPage myOrdersPage;
    String orderId;
    OrderPendingPage orderPendingPage;
    BackLoginPage backLoginPage;
    AdminPage adminPage;
    public double GURU50 = 0.05;

    @BeforeClass
    public void navigateToWebSite() {
        homePage = new HomePage(driver);
        mobilePage = new MobilePage(driver);
        detailProductPage = new DetailProductPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        compareProductsPage = new CompareProductsPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        tvPage = new TVPage(driver);
        myWishListPage = new MyWishListPage(driver);
        myDashBoard = new MyDashBoard(driver);
        shareWishlistPage = new ShareWishlistPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkOutPage = new CheckOutPage(driver);
        receivedOrderPage = new ReceivedOrderPage(driver);
        myOrdersPage = new MyOrdersPage(driver);
        orderPendingPage = new OrderPendingPage(driver);
        backLoginPage = new BackLoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @BeforeMethod
    public void homePage() {
        //        1. Go to http://live.techpanda.org/
        driver.get(Util.BASEURL);
    }

    @Test(priority = 3)
    public void sortMobilePageByName() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        // Assertion HomePage's Title
        softAssert.assertTrue(homePage.pageTitle.getText().contains("THIS IS DEMO SITE FOR"), "Home PageTitle Incorrect");

        homePage.MobileTab.click();
        // Assertion MobilePage's Title
        softAssert.assertEquals(mobilePage.pageTitle.getText(), "MOBILE", "Mobile PageTitle Incorrect");
        Select select = new Select(mobilePage.sortBySelect);
        select.selectByVisibleText("Name");

        // Assertion Products Sorted by name
        List<String> productsNamelist = new ArrayList<>();
        mobilePage.productsName.forEach(webElement -> productsNamelist.add(webElement.getText()));
        softAssert.assertTrue(Ordering.natural().isOrdered(productsNamelist), "Products not Sorted By name");
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("screenShots\\sortMobilePageByName\\SortByName.png"));
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyProductCost() throws IOException {
        homePage.MobileTab.click();
        String sonyPrice = mobilePage.sonyPrice.getText();
        File sourceFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile1, new File("screenShots\\verifyProductCost\\priceFromMobilePage.png"));
        mobilePage.sonyProduct.click();
        assertEquals(detailProductPage.productPrice.getText(), sonyPrice, "Price did not match");
        File sourceFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile2, new File("screenShots\\verifyProductCost\\priceFromDetailPage.png"));

    }

    @Test(priority = 3)
    public void verifyProductAvailableChecker() {
        SoftAssert softAssert = new SoftAssert();
        homePage.MobileTab.click();
        mobilePage.sonyAddToCartButton.click();
        shoppingCartPage.Qty.sendKeys("1000");
        shoppingCartPage.updateButton.click();
        softAssert.assertTrue(shoppingCartPage.errorMessage.getText().contains("The maximum quantity allowed for purchase is 500"), "Failed to Check Quantity");
        shoppingCartPage.emptyCartButton.click();
        softAssert.assertTrue(shoppingCartPage.cartEmptyMessage.getText().contains("You have no items in your shopping cart."), "Cart Not Empty");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void VerifyAbilityToCompareTwoProduct() {
        homePage.MobileTab.click();
        mobilePage.sonyAddToCompareButton.click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(200));
        mobilePage.iphoneAddToCompareButtons.click();
        mobilePage.compareButton.click();

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        // Assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(compareProductsPage.headTitle.getText(), "COMPARE PRODUCTS", "Title did not match");
        softAssert.assertEquals(compareProductsPage.sony.getText(), mobilePage.sonyXperiaMobile.getText(), "Sony don't match");
        softAssert.assertEquals(compareProductsPage.iphone.getText(), mobilePage.iphoneMobile.getText(), "iphone don't match");

        compareProductsPage.closeButton.click();
        softAssert.assertAll();
    }

    @Test(priority = 0)
    public void createAccountAndShareWishlistUsingEmail() {
        ClickOnMyAccountLink();
        customerLoginPage.createAnAccount.click();

        // Fill New user information
        createAccountPage.firstName.sendKeys("firstName");
        createAccountPage.middleName.sendKeys("middleName");
        createAccountPage.lastName.sendKeys("lastName");
        createAccountPage.email_address.sendKeys(Util.EmailID);
        createAccountPage.password.sendKeys(Util.Password);
        createAccountPage.confirmation.sendKeys("password");
        createAccountPage.registerButton.click();

        // Verify registration is Done
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(myDashBoard.successMessage.getText(), "Thank you for registering with Main Website Store.", "Filed to Registration");

        addItemToWishList();
        // go to Share wish list page
        myWishListPage.shareWishListButton.click();
        // Fill information
        shareWishlistPage.email_address.sendKeys("test@test.com");
        shareWishlistPage.message.sendKeys("I want to share my wish list with you");
        shareWishlistPage.shareWithListButton.click();

        softAssert.assertEquals(myWishListPage.successMessage.getText(), "Your Wishlist has been shared.", "Filed to Share WithList");

        softAssert.assertAll();
        logout();

    }

    @Test(priority = 1)
    public void abilityToPurchaseProductUsingEmailId() throws InterruptedException {
//        2. Click on my account link
        ClickOnMyAccountLink();

//        3. Login in application using previously created credential
        LoginInApplication();

//        4. Click on MY WISHLIST link
//        addItemToWishList();
        myDashBoard.myWishListTab.click();

//        5. In next page, Click ADD TO CART link
        myWishListPage.addToCartButton.click();


//        6. Enter general shipping country, state/province and zip for the shipping cost estimate
        String Country = "United States";
        String State = "New York";
        String PostCode = "542896";
        Select countrySelect = new Select(shoppingCartPage.countrySelect);
        countrySelect.selectByVisibleText(Country);
        Select stateSelect = new Select(shoppingCartPage.stateSelect);
        stateSelect.selectByVisibleText(State);
        shoppingCartPage.postcode.sendKeys(PostCode);

//        7. Click Estimate
        shoppingCartPage.estimateButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

//        8. Verify Shipping cost generated
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(shoppingCartPage.flatRateShipping.getText(), "$5.00", "Flat Rate Incorrect");

//        9. Select Shipping Cost, Update Total
        shoppingCartPage.flatRateCheck.click();
        shoppingCartPage.updateTotalButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

//        10. Verify shipping cost is added to total
        softAssert.assertEquals(shoppingCartPage.totalProductCost.getText(), "$620.00", "Total Cost Incorrect");

//        11. Click "Proceed to Checkout"
        shoppingCartPage.proceedToCheckoutButton.click();

//        12a. Enter Billing Information, and click Continue
//        12b. Enter Shipping Information, and click Continue

//        try {
//            Select bAddr = new Select(checkOutPage.addressSelect);
//            int bAddrSize = bAddr.getOptions().size();
//            bAddr.selectByIndex(bAddrSize - 1);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            System.out.println("No dropdown element present");
//        }

        checkOutPage.address.sendKeys("ABC");
        checkOutPage.city.sendKeys(State);
        Select checkStateSelect = new Select(checkOutPage.stateSelect);
        checkStateSelect.selectByVisibleText(State);
        Select checkCountrySelect = new Select(checkOutPage.countrySelect);
        checkCountrySelect.selectByVisibleText(Country);
        checkOutPage.postcode.sendKeys(PostCode);
        checkOutPage.telephone.sendKeys("12345678");

        checkOutPage.shippingInformationContinueButton.click();

//        13. In Shipping Method, Click Continue
        checkOutPage.shippingMethodContinueButton.click();

//        14. In Payment Information select 'Check/Money Order' radio button. Click Continue
        checkOutPage.moneyOrderCheck.click();
        checkOutPage.paymentContinueButton.click();

//        15. Click 'PLACE ORDER' button
        checkOutPage.placeOrderButton.click();
        Thread.sleep(3000);

//        16. Verify Oder is generated. Note the order number
        softAssert.assertEquals(receivedOrderPage.titleOfPage.getText(), "YOUR ORDER HAS BEEN RECEIVED.", "Order Incorrect");
        System.out.println(receivedOrderPage.orderNumber.getText());
        orderId = receivedOrderPage.orderNumber.getText();
        softAssert.assertAll();
        logout();
    }

    @Test(priority = 2)
    public void savePreviouslyPlacedOrderAsAPDFFile() {
        //        2. Click on my account link
        ClickOnMyAccountLink();

        //    3. Login in application using previously created credential
        LoginInApplication();

        //    4. Click on 'My Orders' link
        myDashBoard.myOrdersTab.click();

        //    5. Verify the previously created order is displayed in "Recent Orders' table and status is Pending
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(myOrdersPage.orderID.getText(), "100017740", "OrderId Incorrect");
        softAssert.assertEquals(myOrdersPage.orderStatus.getText(), "Pending", "Order Status Incorrect");

        //   6. Click on 'View Order' link
        myOrdersPage.viewOrderButton.click();
        System.out.println(driver.getTitle());

        //   7. Click on 'Print Order' link
        orderPendingPage.printOrderButton.click();

        for (var handel : driver.getWindowHandles()) {
            driver.switchTo().window(handel);
        }
        System.out.println(driver.getTitle());
        softAssert.assertTrue(driver.getTitle().contains("Print Order"), "Print Incorrect");
        softAssert.assertAll();


        // 8. Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.
        // note:  This does not exist:   "Change...." link


        // 9. Click on 'Save' button and save the file in some location.
        // note: unable to execute this step due to issue in step 8.

        // 10.Verify Order is saved as PDF
        // note: unable to execute this step due to issue in steps 8 and 9.
        logout();
    }

    @Test(priority = 2)
    public void AbilityToReorderPreviouslyAddedProduct() throws InterruptedException {

//      2. Click on My Account link
        ClickOnMyAccountLink();

//      3. Login in application using previously created credential
        LoginInApplication();

//      4. Click on 'REORDER' link , Change QTY & click update
        myDashBoard.myOrdersTab.click();
        myOrdersPage.reorderButton.click();
        String grandTotal = shoppingCartPage.totalProductCost.getText();
        shoppingCartPage.Qty.clear();
        shoppingCartPage.Qty.sendKeys("10");
        shoppingCartPage.updateButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

//        5. Verify Grand Total is changed
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(shoppingCartPage.totalProductCost.getText().contains(grandTotal), "Total Not change");

//        6. Complete Billing & Shipping Information
        shoppingCartPage.proceedToCheckoutButton.click();

        checkOutPage.shippingInformationContinueButton.click();
        checkOutPage.shippingMethodContinueButton.click();
        checkOutPage.moneyOrderCheck.click();
        checkOutPage.paymentContinueButton.click();
        checkOutPage.placeOrderButton.click();
        Thread.sleep(3000);

//        7. Verify order is generated and note the order number
        softAssert.assertEquals(receivedOrderPage.titleOfPage.getText(), "YOUR ORDER HAS BEEN RECEIVED.", "Order Incorrect");
        System.out.println(receivedOrderPage.orderNumber.getText());
        softAssert.assertAll();
        logout();
    }

    @Test(priority = 3)
    public void VerifyDiscountCouponWorksCorrectly() {
        // 2. Go to Mobile and add IPHONE to cart
        homePage.MobileTab.click();
        mobilePage.iphoneAddToCart.click();

        // 3. Enter Coupon Code
        shoppingCartPage.couponCodeInput.sendKeys("GURU50");
        shoppingCartPage.applyCouponCodeButton.click();

        String subTotal = shoppingCartPage.supTotal.getText();
        String discount = shoppingCartPage.discount.getText();
        String totalCost = shoppingCartPage.totalProductCost.getText();

        // Remove the $ sign
        subTotal = subTotal.replaceAll("\\$", " ");
        discount = discount.replaceAll("\\$", " ");
        totalCost = totalCost.replaceAll("\\$", " ");

        // Remove the - negative sign
        discount = discount.replaceAll("-", " ");

        // trim the String variables to get it ready for calculation
        String fSubTot = subTotal.trim();                                 // e.g. $500.00
        String fDiscount = discount.trim();                              // e.g. $25.00
        String fTotalCost = totalCost.trim();                           // e.g. $475.00


        double dSubTotal = Double.parseDouble(fSubTot);
        double dDiscount = Double.parseDouble(fDiscount);
        double dTotalCost = Double.parseDouble(fTotalCost);

        // 4. Verify the discount generated

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(dDiscount, dSubTotal * GURU50, "*** Derived discount amount not equal displayed discount amount ***");
        softAssert.assertEquals(dTotalCost, dSubTotal - dDiscount, "*** Derived discount amount not equal displayed discount amount ***");
        softAssert.assertAll();

    }

    @Test
    public void ExportAllOrdersInCSVFileAndDisplayInConsole() {

        // 1. Go to backend
        driver.get("http://live.techpanda.org/index.php/backendlogin/");

        // 2. login
        backLoginPage.username.sendKeys("user01");
        backLoginPage.password.sendKeys("guru99com");
        backLoginPage.password.submit();
        adminPage.closePopUpMessage.click();

        // 3. Go to Sales  -> Orders Menu
        Actions actions = new Actions(driver);
        actions.moveToElement(adminPage.salesMenu).perform();
        actions.moveToElement(adminPage.orders).click().perform();

        // 4. Select 'CSV' in export to DropDown and click Export button
        Select exportSelect = new Select(adminPage.exportSelect);
        exportSelect.selectByVisibleText("CSV");

        adminPage.exportButton.click();

        // 5.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // 6.
    }


    void addItemToWishList() {
        // go To TV products page
        myDashBoard.TVTab.click();
        // add LG to wish list
        tvPage.lgLCDWishListButton.click();
    }

    void ClickOnMyAccountLink() {
        homePage.accountLink.click();
        homePage.myAccountLink.click();
    }

    void LoginInApplication() {
        customerLoginPage.emailFiled.sendKeys(Util.EmailID);
        customerLoginPage.passwordFiled.sendKeys(Util.Password);
        customerLoginPage.loginButton.click();

    }

    public void logout() {
        homePage.accountLink.click();
        homePage.logOut.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }


}
