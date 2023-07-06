package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class DemoBlazeStepDefinitions {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the Demoblaze homepage")
    public void navigateToHomePage() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://www.demoblaze.com/");
    }

    @When("I register with a valid username and password")
    public void registerWithValidCredentials() {
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
        driver.findElement(By.id("sign-username")).sendKeys("myusername");
        driver.findElement(By.id("sign-password")).sendKeys("mypassword");
        driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();
    }

    @When("I login with the registered credentials")
    public void loginWithRegisteredCredentials() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys("myusername");
        driver.findElement(By.id("loginpassword")).sendKeys("mypassword");
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
    }

    @Then("I should be logged in successfully")
    public void verifyLoginSuccess() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        Assert.assertTrue(logoutButton.isDisplayed());
    }

    @Given("I am logged in")
    public void login() {
        // Perform login steps (e.g., navigate to login page, enter credentials, submit form)
    }

    @When("I navigate to the Categories page")
    public void navigateToCategoriesPage() {
        driver.findElement(By.linkText("Categories")).click();
    }

    @Then("I should see items listed under each category")
    public void verifyCategoryItems() {
        List<WebElement> categoryLinks = driver.findElements(By.xpath("//a[@class='list-group-item']"));
        for (WebElement categoryLink : categoryLinks) {
            categoryLink.click();
            WebElement itemsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
            List<WebElement> items = itemsContainer.findElements(By.tagName("tr"));
            Assert.assertTrue(items.size() > 0);
            driver.navigate().back();
        }
    }

    @When("I add a random item to the cart")
    public void addRandomItemToCart() {
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//a[contains(text(),'Add to cart')]"));
        Random random = new Random();
        int randomIndex = random.nextInt(addToCartButtons.size());
        addToCartButtons.get(randomIndex).click();
    }

    @Then("the item should be added successfully")
    public void verifyItemAddedToCart() {
        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartur")));
        Assert.assertEquals("1", cartCount.getText());
    }

    @Given("I have items in my cart")
    public void addItemToCart() {
        // Add some items to the cart
    }

    @When("I remove an item from the cart")
    public void removeItemFromCart() {
        driver.findElement(By.linkText("Cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tbodyid']/tr/td[4]/a")));
        driver.findElement(By.xpath("//tbody[@id='tbodyid']/tr/td[4]/a")).click();
    }

    @Then("the item should be removed successfully")
    public void verifyItemRemovedFromCart() {
        WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Cart is empty')]")));
        Assert.assertTrue(emptyCartMessage.isDisplayed());
    }

    @When("I proceed to checkout")
    public void proceedToCheckout() {
        driver.findElement(By.linkText("Cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Place Order')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
    }

    @When("I complete the checkout process")
    public void completeCheckout() {
        driver.findElement(By.id("name"));
    }
}
