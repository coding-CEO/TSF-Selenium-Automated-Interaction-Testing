import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class Main {

    private static JavascriptExecutor jsExecutor;
    private static Actions action;
    private static final String NAV_BUTTONS_X_PATH = "/html/body/div[1]/div/div[2]/nav/div[2]/nav/ul";
    private static final String TSF_ORIGINAL_LINKEDIN_LINK = "https://www.linkedin.com/company/the-sparks-foundation/";

    public static void main(String[] args) {

        // Setup basic Things
        System.setProperty("webdriver.chrome.driver", "src/utils/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);

        try {
            driver.manage().window().maximize();

            // Load Website
            driver.get("https://www.thesparksfoundationsingapore.org/");

            // Testing begins
            startTesting(driver);

            // Testing Done !
            System.out.println("Automated Interaction Testing Successful !");

            Thread.sleep(5000); // wait before closing program

        }catch (Exception e) {
            System.out.println("Program Crashed !");
            System.out.println("Reason - " + e.getMessage());

        }finally {
            driver.quit();
        }
    }

    private static void startTesting(WebDriver driver) throws Exception {
        // Test 1)
        Utils.scroll(jsExecutor, 600);
        testYoutubeVideoInteraction(driver);

        // Test 2)
        Utils.scroll(jsExecutor, -600);
        testDropDownNavButtons(driver);

        // Test 3)
        testContactUsPage(driver);

        // Test 4)
        Utils.scroll(jsExecutor, 1700);
        Thread.sleep(2000);
        testGRIPLink(driver);

        // Test 5)
        testAboutPageUIAnimation(driver);
    }

    private static void testYoutubeVideoInteraction(@NotNull WebDriver driver) throws Exception {
        WebElement introYoutubeVideo = driver.findElement(By.id("youtube-video"));
        introYoutubeVideo.click(); // Start Youtube video
        Thread.sleep(5000); // wait for some seconds
        introYoutubeVideo.click(); // Pause Youtube video
        System.out.println("Test 1) Intro Youtube Video interacting properly");
    }

    private static void testDropDownNavButtons(WebDriver driver) throws Exception {
        String navButtonsPath = NAV_BUTTONS_X_PATH + "/li[contains(@class, 'dropdown')]";
        List<WebElement> navButtons = driver.findElements(By.xpath(navButtonsPath));

        for(WebElement navButton : navButtons) {
            navButton.click();
            Thread.sleep(1000);
        }

        System.out.println("Test 2) Drop Down Nav Buttons are interacting properly");
    }

    private static void testContactUsPage(WebDriver driver) throws Exception {
        String contactPageNavElementXPath = NAV_BUTTONS_X_PATH + "/li[last()]";
        WebElement contactPageNavElement = driver.findElement(By.xpath(contactPageNavElementXPath));
        contactPageNavElement.click();

        // Now we are in the contact us page
        Utils.scroll(jsExecutor, 500);
        Thread.sleep(3000);

        WebElement mapDivElement = driver.findElement(By.className("map-agileits"));

        action.doubleClick(mapDivElement).perform();
        Thread.sleep(2000);
        action.doubleClick(mapDivElement).perform();
        Thread.sleep(3000);

        driver.navigate().back();

        System.out.println("Test 3) Contact Us page interacting properly");
    }

    private static void testGRIPLink(WebDriver driver) throws Exception {
        String gRIPLinkXPath = "//div[contains(@class, 'register-wthree')]/div/div/a";

        WebElement gRIPButtonElement = driver.findElement(By.xpath(gRIPLinkXPath));
        String testLink = gRIPButtonElement.getAttribute("href");

        assert(TSF_ORIGINAL_LINKEDIN_LINK.equals(testLink)); // Verify link url

        action.clickAndHold(gRIPButtonElement).pause(Duration.ofSeconds(1)).perform();

        driver.navigate().to(TSF_ORIGINAL_LINKEDIN_LINK);
        Thread.sleep(2000);

        WebElement signInToggle = driver.findElement(By.xpath("/html/body/main/div/div/form[2]/section/p/button"));
        signInToggle.click();

        Thread.sleep(2000);

        WebElement emailField = driver.findElement(By.name("session_key"));
        emailField.sendKeys("Your Email Here ...");

        Thread.sleep(2000);

        WebElement passwordField = driver.findElement(By.name("session_password"));
        passwordField.sendKeys("Your Password Here ...");

        Thread.sleep(3000);
        driver.navigate().back();

        System.out.println("Test 4) GRIP Link is valid and interacting properly");
    }

    private static void testAboutPageUIAnimation(WebDriver driver) throws Exception {
        String aboutDropDownXPath = NAV_BUTTONS_X_PATH + "/li[1]";
        String visionMissionPageXPath = aboutDropDownXPath + "/ul/li[1]";

        WebElement aboutDropDownElement = driver.findElement(By.xpath(aboutDropDownXPath));
        aboutDropDownElement.click();

        Thread.sleep(2000);

        WebElement visionMissionPageElement = driver.findElement(By.xpath(visionMissionPageXPath));
        visionMissionPageElement.click();

        Utils.scroll(jsExecutor, 400);
        Thread.sleep(2000);

        String listXPath = "/html/body/div[2]/div/div[2]/div/ul/li";
        List<WebElement> lists = driver.findElements(By.xpath(listXPath));

        for(WebElement list : lists) {
            action.clickAndHold(list).pause(Duration.ofSeconds(1)).perform();
        }

        driver.navigate().back();

        System.out.println("Test 5) About Page UI Animation interacting properly");
    }
}
