package pepinazo;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static junit.framework.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class StepDefs {

	public WebDriver driver;

	@Before
	public void setUpTest() {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void tearDownTest() {
		driver.quit();
	}

	@Given("I have open the browser")
	public void I_have_open_the_browser() {
		assertNotNull("The browser is not open", driver);
	}

	@When("I open {word} website")
	public void I_open_Facebook_website(String site) {
		if(site.equals("Facebook"))
			driver.get("https://facebook.com");
		if(site.equals("Sinatra"))
			driver.get("https://songs-by-sinatra.herokuapp.com");
	}

	@Then("Login button should exist")
	public void Login_button_should_exist() {
		String loginButtonXpath = "//input[@data-testid='royal_login_button']";
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXpath));
		assertTrue(loginButton.isDisplayed());
	}

	@Then("Login button should be disabled")
	public void Login_button_should_be_disabled() {
		String loginButtonXpath = "//input[@data-testid='royal_login_button']";
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXpath));
		assertFalse(loginButton.isEnabled());
	}

	@When("I click the login button")
	public void I_click_the_login_button() {
		String loginButtonXpath = "//input[@data-testid='royal_login_button']";
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXpath));
		loginButton.click();
	}

	@Then("Facebook asks me to enter credentials")
	public void Facebook_asks_me_to_enter_credentials() throws Exception {
		WebElement emailField = driver.findElement(By.id("email"));
		WebElement passField = driver.findElement(By.id("pass"));
		assertTrue(emailField.isDisplayed() && passField.isDisplayed());

	}

	@When("I login using cell phone number")
	public void I_login_using_cell_phone_number() {
		WebElement emailField = driver.findElement(By.id("email"));
		WebElement passField = driver.findElement(By.id("pass"));
		String loginButtonXpath = "//input[@data-testid='royal_login_button']";
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXpath));

		emailField.sendKeys("omar.selenium.abril@gmail.com");

		passField.sendKeys("Test@1234");
		

		loginButton.click();
	}

	@Then("Facebook Home Page must appear")
	public void Facebook_Home_Page_must_appear() {
		WebElement facebookLogo = driver.findElement(By.cssSelector("[data-click='bluebar_logo']"));
		WebElement searchBox = driver.findElement(By.name("q"));
		assertTrue(facebookLogo.isDisplayed() && searchBox.isDisplayed());
	}

	@When("I login with {word} and {word}")
	public void I_login_with_user_and_pass(String username, String password) {
		WebElement emailField = driver.findElement(By.id("email"));
		WebElement passField = driver.findElement(By.id("pass"));
		String loginButtonXpath = "//input[@data-testid='royal_login_button']";
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXpath));

		emailField.sendKeys(username);

		passField.sendKeys(password);

		loginButton.click();

	}

	@Then("I can see Home Page")
	public void I_can_see_Home_Page() {
		WebElement facebookLogo = driver.findElement(By.cssSelector("[data-click='bluebar_logo']"));
		WebElement searchBox = driver.findElement(By.name("q"));
		assertTrue(facebookLogo.isDisplayed() && searchBox.isDisplayed());
	}

	@When("I log into Sinatra with {word} and {word}")
	public void i_log_into_Sinatra_with_frank_and_sinatra(String username, String password) {
		WebElement loginLink = driver.findElement(By.xpath("//a[@href='/login']"));
		loginLink.click();
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

	}

	@When("I log into Sinatra with empty fields")
	public void i_log_into_Sinatra_with_empty_fields() {
		WebElement loginLink = driver.findElement(By.xpath("//a[@href='/login']"));
		loginLink.click();
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));

		usernameField.clear();
		passwordField.clear();
		loginButton.click();

	}
	@Then("Sinatra Home Page must be displayed")
	public void sinatra_Home_Page_must_be_displayed() {
		String homePageUrl = "https://songs-by-sinatra.herokuapp.com/";
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals(homePageUrl));
	}

	@Then("I can see Sinatra Login Page")
	public void i_can_see_Sinatra_Login_Page() {
		String loginPageUrl = "https://songs-by-sinatra.herokuapp.com/login";
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals(loginPageUrl));
	}

	@Then("An error is displayed")
	public void an_error_is_displayed() {
		WebElement errorElement = null;
		try {
			errorElement = driver.findElement(By.id("flash"));
		} catch(NoSuchElementException nsee) {}
		assertNotNull(errorElement);
		assertTrue(errorElement.isDisplayed());
		assertTrue(errorElement.getText().equals("The username or password you entered are incorrect"));
	}
}
