package TestProject.tests_Testng;

import TestProject.POMautomateClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.configUtil;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestNGTest {
    POMautomateClass pom;
    WebDriver driver;

    @BeforeClass //@BeforeClass: Sets up the WebDriver and opens the login page.
    public void initiatePage(){
        System.setProperty("webdriver.chrome.driver", "D:\\Development Softwares\\chromedriver.exe");

        driver=new ChromeDriver();

        // Page-Object Model Approach

        pom=new POMautomateClass();

        //I used this to wait for the username and the password when the login field
        // is automated.

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // to solve synchronization issues with the website being automated.

        driver.manage().window().maximize();
           driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        pom.pageTitle(driver);
        pom.loginPage(driver);

    }

    //use priority to make it clear that which test-case should execute first,
    // and which test-case should follow it.


    @Test (description = "Add User1" ,priority = 1)
    public void addUserTestCase1(){

        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("\n\n//Invalid: Non-existent employee.");
        // Invalid: Non-existent employee
        pom.addUserTest(driver, "Admin", "Huzaifa Kashif", "newUser3", "Enabled", "Password123!", "Password123!", "Save");

        //pom.addUserTest(driver,"Admin","Timothy Lewis Amiano", "Timothy1","Enabled","Charles@123","Charles@123", "Save");

        //To navigate back to the Add User page to execute the next test case.

      //  driver.findElement(By.className("oxd-main-menu-item active")).click();
      //  driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

      /*  pom.addUserTest(driver, "Admin", "John Smith", "", "Enabled", "Password123!", "Password123!", "Save"); // Invalid: Empty username

        driver.findElement(By.className("oxd-main-menu-item active")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        pom.addUserTest(driver, "Admin", "John Smith", "newUser2", "Enabled", "pass", "pass", "Save"); // Invalid: Weak password

        driver.findElement(By.className("oxd-main-menu-item active")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        pom.addUserTest(driver, "Admin", "Invalid Employee", "newUser3", "Enabled", "Password123!", "Password123!", "Save"); // Invalid: Non-existent employee

       */
    }

    @Test (description = "Add User2" ,priority = 2)
    public void addUserTestCase2(){
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("//Invalid: Empty username.");
        // Invalid: Empty username
        pom.addUserTest(driver, "Admin", "Charles Carter", "", "Enabled", "Password123!", "Password123!", "Save");
    }

    @Test (description = "Add User3" ,priority = 3)
    public void addUserTestCase3(){
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("//Invalid add user test case: Password and Confirm Password do not match.");
        //Invalid add user test case: Password and Confirm Password do not match
        pom.addUserTest(driver,"Admin","Charles Carter", "Timothy1","Enabled","Charles@123","Charles@12", "Save");
    }

    @Test (description = "Add User4" ,priority = 4)
    public void addUserTestCase4(){
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("//Valid add user test case: Add a new User..");
        //Valid add user test case: Add a new User.
        pom.addUserTest(driver,"Admin","Charles Carter", "Timothy1","Enabled","Charles@123","Charles@123", "Save");
    }

    @Test (description = "Add User5" ,priority = 5)
    public void addUserTestCase5(){
        driver.findElement(By.className("oxd-main-menu-item")).click();
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("Invalid add user test case: User already exists.");
        //Invalid add user test case: User already exists
        pom.addUserTest(driver,"Admin","Charles Carter", "Timothy1","Enabled","Charles@123","Charles@123", "Save");
    }

    @Test (description = "Search User1" ,priority = 6)
    public void searchUserTestCase1() {
        driver.findElement(By.className("oxd-main-menu-item")).click();
        //  driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("\n\n//valid search user test case: find existing user by passing some parameters.");
        //valid search user test case: find existing user by passing all parameters
        pom.searchUserTest(driver, "Admin","Charles Carter","","","Search");

    }

    @Test (description = "Search User2" ,priority = 7)
    public void searchUserTestCase2(){
        driver.findElement(By.className("oxd-main-menu-item")).click();
      //  driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']")).click();

        System.out.println("//valid search user test case: find existing user by passing all parameters.");
        //valid search user test case: find existing user by passing all parameters
        pom.searchUserTest(driver, "Admin","Charles Carter","Timothy1","Enabled","Search");
    }

    @AfterClass //@AfterClass: Closes the WebDriver after a short delay.
    public void closePage() throws InterruptedException {
        Thread.sleep(5000);
        //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        pom.logoutPage(driver);
        Thread.sleep(3000);
        driver.close();
    }
}
