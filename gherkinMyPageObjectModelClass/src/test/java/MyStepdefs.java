
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.netty.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/**
 * This class set steps for create to BDD Test
 */

public class MyStepdefs {

    private WebDriver driver;

    /**
     * Set up ChromeDriver
     */
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\BrowserDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    /**
     * This method Quit driver and close all windows
     */
    @After
    public void tearDown() {
        this.driver.quit();
    }

    /**
     * This method open admin area page
     */
    @Given("admin is on the admin area page")
    public void adminIsOnTheAdminAreaPage() {
        driver.get("http://shop.pragmatic.bg/admin/");
    }

    /**
     * This method login whit admin user
     * @param username accept String param for username
     * @param password accept String param for password
     */
    @And("login whit admin {string} and {string}")
    public void loginWhitAdminAnd(String username, String password) {
        driver.findElement(By.id("input-username")).sendKeys(username);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }

    /**
     * This method navigate to Attributes page
     */
    @When("he is on admin area he go to Attributes page")
    public void heIsOnAdminAreaHeGoToAttributesPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10,1));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu")));
        driver.findElement(By.cssSelector("#menu-catalog a.parent i.fa-tags")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#collapse1 a.parent")));
        driver.findElement(By.cssSelector("#collapse1 a.parent")).click();
        driver.findElement(By.xpath("//*[@id=\"collapse1-4\"]/li[1]/a")).click();
    }

    /**
     * This method click button New attributes form
     */
    @And("he click add New button")
    public void heClickAddNewButton() {
        driver.findElement(By.cssSelector("div.pull-right a.btn.btn-primary")).click();

//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input.form-control[placeholder='Attribute Name']")));
//

    }

    /**
     * This method fills a form New Attributes for name , select processor and enter sort order
     * @param name accept String param for name
     */
    @And("he enter {string} select processor enter sort order")
    public void heEnterNameSelectProcessorEnterSortOrder(String name) {
        driver.findElement(By.cssSelector("input.form-control[placeholder='Attribute Name']")).sendKeys(name);
        Select menuOptions = new Select(driver.findElement(By.id("input-attribute-group")));
        menuOptions.selectByValue("6");
        driver.findElement(By.id("input-sort-order")).sendKeys("5");

    }

    /**
     * This method create New Attributes
     */
    @And("he Submits new Attributes")
    public void heSubmitsNewAttributes() {
        driver.findElement(By.cssSelector("div.pull-right button.btn-primary")).click();
    }

    /**
     * This method assert and validate New Attributes create
     * @param expMessages accept String param for expected messages
     */
    @Then("ensure the new Attributes is complete with {string} message")
    public void ensureTheNewAttributesIsCompleteWithMessage(String expMessages) {
        String messageValid = driver.findElement(By.cssSelector("div.alert.alert-success")).getText();
        Assert.assertTrue(messageValid.contains(expMessages), "HAVE A BUG");


    }

    /**
     *This method delete selected attributes
     * before delete assert for Attributes from list
     * @param nameAttributesToDell accept param String for name of attributes
     */
    @And("he enter {string} of Attributes to dell")
    public void heEnterOfAttributesToDell(String nameAttributesToDell) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5,1));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("table.table-bordered tbody")));
        String attDellContain = driver.findElement(By.cssSelector("table.table-bordered tbody")).getText();
        Assert.assertTrue(attDellContain.contains(nameAttributesToDell), "Attributes it`s not in the list");
        driver.findElement(By.xpath("//*[@id=\"form-attribute\"]/div/table/tbody/tr[2]/td[1]/input")).click();

    }

    /**
     * This method click button Delete Attributes
     */
    @And("he click button dell")
    public void heClickButtonDell() {
        driver.findElement(By.cssSelector("div.pull-right button.btn-danger")).click();
        driver.switchTo().alert().accept();
    }

    /**
     * This method assert and validate delete Attributes from list
     * @param expMessages accept String param for expected messages
     */
    @Then("ensure the Attributes is complete dell whit {string} message")
    public void ensureTheAttributesIsCompleteDellWhitMessage(String expMessages) {

        String actResultMessages = driver.findElement(By.cssSelector("div.alert.alert-success ")).getText();
        Assert.assertTrue(actResultMessages.contains(expMessages), "HaVE BUG!");

    }

    /**
     * This method fills a form New Attributes for name and enter sort order
     * not select group
     * @param attributesName accept String param for name
     */
    @And("he enter {string} and not select group and enter Sort Order")
    public void heEnterAndNotSelectGroupAndEnterSortOrder(String attributesName) {
        driver.findElement(By.cssSelector("input.form-control[placeholder='Attribute Name']")).sendKeys(attributesName);
        driver.findElement(By.id("input-sort-order")).sendKeys("4");


    }

    /**
     * This method assert and validate the new Attributes is not create
     * @param expMessage accept String param for expected messages
     */
    @Then("ensure the new Attributes is not complete with {string} message")
    public void ensureTheNewAttributesIsNotCompleteWithMessage(String expMessage) {
     Assert.assertTrue(driver.findElement(By.cssSelector("div.text-danger")).getText().contains(expMessage),"HAVE A BUG!!");


    }

    /**
     * This method assert and validate the new Attributes is not create
     * @param expMassages accept String param for expected messages
     */
    @Then("ensure the new AttributesName is not complete with {string} message")
    public void ensureTheNewAttributesNameIsNotCompleteWithMessage(String expMassages) {
     Assert.assertTrue(driver.findElement(By.cssSelector("div.required.has-error div.text-danger")).getText().contains(expMassages));
    }
}
