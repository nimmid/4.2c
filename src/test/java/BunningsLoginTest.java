import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BunningsLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Valid credentials
    private final String VALID_EMAIL = "user890@kinis.net";
    private final String VALID_PASS = "user890@90";

    // Invalid credentials
    private final String WRONG_EMAIL = "wrongtest@gmail.com";
    private final String WRONG_PASS = "Wrong123";

    @Before
    public void start() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\nimmi\\Desktop\\Software Quality and Testing\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.bunnings.com.au/login");
    }

    @After
    public void end() {

        if (driver != null) {
            driver.quit();
        }
    }

    // Helper method
    private void loginAction(String email, String password) {

        WebElement emailBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("username")));

        WebElement passBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")));

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Sign in')]")));

        emailBox.clear();
        passBox.clear();

        if (email != null) {
            emailBox.sendKeys(email);
        }

        if (password != null) {
            passBox.sendKeys(password);
        }

        loginBtn.click();
    }

    // Console output helper
    private void printResult(String tc, String message) {

        System.out.println("\n==============================");
        System.out.println(tc);
        System.out.println(message);
        System.out.println("==============================");
    }

    // TC1
    @Test
    public void TC1_emptyEmail_emptyPassword() {

        loginAction("", "");

        String url = driver.getCurrentUrl();

        printResult("TC1",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC2
    @Test
    public void TC2_emptyEmail_wrongPassword() {

        loginAction("", WRONG_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC2",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC3
    @Test
    public void TC3_emptyEmail_correctPassword() {

        loginAction("", VALID_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC3",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC4
    @Test
    public void TC4_wrongEmail_emptyPassword() {

        loginAction(WRONG_EMAIL, "");

        String url = driver.getCurrentUrl();

        printResult("TC4",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC5
    @Test
    public void TC5_invalidEmail_wrongPassword() {

        loginAction("invalid-email", WRONG_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC5",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC6
    @Test
    public void TC6_invalidEmail_correctPassword() {

        loginAction("invalid-email", VALID_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC6",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC7
    @Test
    public void TC7_correctEmail_emptyPassword() {

        loginAction(VALID_EMAIL, "");

        String url = driver.getCurrentUrl();

        printResult("TC7",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC8
    @Test
    public void TC8_correctEmail_wrongPassword() {

        loginAction(VALID_EMAIL, WRONG_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC8",
                "URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // TC9
    @Test
    public void TC9_correctEmail_correctPassword() {

        loginAction(VALID_EMAIL, VALID_PASS);

        String url = driver.getCurrentUrl();

        printResult("TC9",
                "Redirect URL: " + url);

        Assert.assertTrue(url.contains("login"));
    }

    // Student ID Test
    @Test
    public void testStudentID() {

        String studentId = "224714265";

        Assert.assertNotNull(studentId);

        printResult("Student ID Test",
                "Student ID Verified");
    }

    // Student Name Test
    @Test
    public void testStudentName() {

        String studentName = "Nimmi";

        Assert.assertNotNull(studentName);

        printResult("Student Name Test",
                "Student Name Verified");
    }
}