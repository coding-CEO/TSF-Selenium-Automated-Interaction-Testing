import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    private static JavascriptExecutor jsExecutor;

    public static void main(String[] args) {

        // Setup basic Things
        System.setProperty("webdriver.chrome.driver", "src/utils/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;

        try {
            driver.manage().window().maximize();

            // Load Website
            driver.get("https://www.thesparksfoundationsingapore.org/");

            // Testing begins
            //...

        }catch (Exception e) {
            System.out.println("Program Crashed !");
            System.out.println("Reason - " + e.getMessage());

        }finally {
            driver.quit();
        }

    }
}
