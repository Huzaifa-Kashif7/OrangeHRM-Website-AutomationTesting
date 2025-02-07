package TestProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.configUtil;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class tut_1 {
    public static void main(String[] args) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "D:\\Development Softwares\\chromedriver.exe");

    WebDriver driver=new ChromeDriver();

    // Data-Driven Approach (lines 17-19)
    Properties props= configUtil.getProperties("data");
    driver.get(props.getProperty("URL")); //gets the property of URL from the "data.properties" resource file.

        // Page-Object Model Approach
    POMautomateClass ac=new POMautomateClass();

// no need to use line 24 now, as we have gotten the URL through data-driven framework (line 19).
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    //I used this to wait for the username and the password when the login field
    // is automated. Unlike the other websites, for some reason, the login
    // for the 2024 version of OrangeHRM was not being automated, but by implicitly waiting,
    // I got the login page to get automated.
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    ac.pageTitle(driver);
    ac.loginPage(driver);
    ac.logoutPage(driver);

}
}
