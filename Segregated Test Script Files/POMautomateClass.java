package TestProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class POMautomateClass {

    public void pageTitle(WebDriver obj){
        //to print the page title on the console.
        System.out.println("\nPage Title: ");
        String pagetitle= obj.getTitle();
        System.out.println(pagetitle);
    }

    public void loginPage(WebDriver obj){ //instead of passing obj of class Webdriver as a
        //parameter for every function within this class, I can also pass obj of class Webdriver
        //only once in this class's parameterized constructor.

        System.out.println("\nLogin Page URL:");
        String pageurl= obj.getCurrentUrl();
        System.out.println(pageurl);

        //Login
        obj.findElement(By.name("username")).sendKeys("Admin");
        obj.findElement(By.name("password")).sendKeys("admin123");
        obj.findElement(By.xpath("//button[@type='submit']")).click();
        //using assertion to confirm the login success via dashboard text.

        String loginsuccessviatext=obj.findElement(By.xpath("//div[@class='oxd-topbar-header-title']")).getText();

        if (loginsuccessviatext.equals("Dashboard")){
            System.out.println("Login Successful.\nCommencing Operations...");
        }
        else {
            System.out.println("Login Unsuccessful");
        }

        // Aside from using the login successful if-else condition for verification (lines 32-37) in the console,
        // we can also use TestNG Assertions which are more efficient.

        Assert.assertEquals(loginsuccessviatext,"Dashboard");
    }

    public void addUserTest(WebDriver obj, String userRole, String employeeNameExisting, String userName,String status, String password, String confirmPassword, String button){
        try{
            List<WebElement> dropDown = obj.findElements(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']"));

            //for userRole;
            // Click on the dropdown arrow to open the dropdown menu

            if (userRole.equals("ESS")){
                // Click on the dropdown arrow to open the dropdown menu

                // Open the User Role dropdown
                dropDown.get(0).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                // Select the 'ESS' option from the dropdown
                WebElement essOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='ESS']"));
                essOption.click();
            }else if (userRole.equals("Admin")){

                // Open the User Role dropdown
                dropDown.get(0).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                WebElement adminOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Admin']"));
                adminOption.click();
            }

            //for Employee Name
            obj.findElement(By.xpath("//input[@placeholder='Type for hints...']")).clear();
            obj.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys(employeeNameExisting);


            Thread.sleep(6000);

            WebElement firstOption = obj.findElement(By.xpath("//div[contains(@class, 'oxd-autocomplete-option')]"));
            firstOption.click();

            //for Username, Password, and Confirm Password textfields, which have no unique attribute among them, and are identified by the same xpath, I will use List<>;

            // Locate all three input fields using the shared XPath
            List<WebElement> inputFields = obj.findElements(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//input[@class='oxd-input oxd-input--active']"));

            // Type into each field by accessing them through their index
            // Username field (first input element)
            inputFields.get(0).sendKeys(userName);

            // Password field (second input element)
            inputFields.get(1).sendKeys(password);

            // Confirm Password field (third input element)
            inputFields.get(2).sendKeys(confirmPassword);


            // for userName;
            //  obj.findElement(By.className("oxd-input oxd-input--active")).clear();
          //  obj.findElement(By.className("oxd-input oxd-input--active")).sendKeys(userName);

            //for password
            //  obj.findElement(By.className("oxd-input oxd-input--active")).clear();
            //  obj.findElement(By.className("oxd-input oxd-input--active")).sendKeys(password);

            //for confirm password
            //  obj.findElement(By.className("oxd-input oxd-input--active")).clear();
            //  obj.findElement(By.className("oxd-input oxd-input--active")).sendKeys(confirmPassword);

            //for status
            if (status.equals("Enabled")){
                // Click on the dropdown arrow to open the dropdown menu

                // Open the status dropdown
               // obj.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();

                dropDown.get(1).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                // Select the 'Enabled' option from the dropdown
                WebElement enabledOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Enabled']"));
                enabledOption.click();

            }else if (status.equals("Disabled")){

                // Open the User Role dropdown
               // obj.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
                dropDown.get(1).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                WebElement disabledOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Disabled']"));
                disabledOption.click();
            }

            if (button.equals("Save")){

                Thread.sleep(3000);
                //for save button
                obj.findElement(By.xpath("//button[@type='submit']")).click();
            }
            else if(button.equals("Cancel")){
                Thread.sleep(3000);
               // for cancel button
                obj.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")).click();
            }

            obj.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            List<WebElement> errorMessages = obj.findElements(By.xpath("//span[contains(@class, 'oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message')]"));

            if (errorMessages.isEmpty()){
                System.out.println("Add User: "+userName+" -- Successful");
            }else{
                System.out.println("Add User: "+userName+" -- Unsuccessful");

                for (WebElement errorMessage:errorMessages){
                    System.out.println(errorMessage.getText());
                }
            }

        }catch(Exception e){
            System.out.println("An unexpected error occurred while adding User: "+userName);
        }
    }

    public void searchUserTest(WebDriver obj, String userRole, String employeeNameExisting, String userName,String status, String button){
        try{

            //for User Role
            List<WebElement> dropDown = obj.findElements(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']"));

            if (userRole.equals("ESS")){

                // Open the User Role dropdown
                dropDown.get(0).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                // Select the 'ESS' option from the dropdown
                WebElement essOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='ESS']"));
                essOption.click();
            }else if (userRole.equals("Admin")){

                // Open the User Role dropdown
                dropDown.get(0).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                WebElement adminOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Admin']"));
                adminOption.click();
            }

            //Username
            //Search and Username in the search area share the same xpath, and no unique attributes. Hence, using List<>
            List<WebElement> inputFields = obj.findElements(By.xpath("//input[@class='oxd-input oxd-input--active']"));

            // Type into userName field by accessing it through it index
            // Username field (second input index element. First input index element is search)
            inputFields.get(1).sendKeys(userName);

            //for status
            if (status.equals("Enabled")){
                // Click on the dropdown arrow to open the dropdown menu

                // Open the status dropdown
                dropDown.get(1).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                // Select the 'Enabled' option from the dropdown
                WebElement enabledOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Enabled']"));
                enabledOption.click();

            }else if (status.equals("Disabled")){

                // Open the User Role dropdown
                dropDown.get(1).click();
                Thread.sleep(2000); // Wait briefly to ensure the dropdown is fully expanded

                WebElement disabledOption = obj.findElement(By.xpath("//div[@role='option']//span[text()='Disabled']"));
                disabledOption.click();
            }

            //for Employee Name
            obj.findElement(By.xpath("//input[@placeholder='Type for hints...']")).clear();
            obj.findElement(By.xpath("//input[@placeholder='Type for hints...']")).sendKeys(employeeNameExisting);

            Thread.sleep(6000); // allows the program to wait for the drop-down to completely expand before selectinf the first-most option

            WebElement firstOption = obj.findElement(By.xpath("//div[contains(@class, 'oxd-autocomplete-option')]"));
            firstOption.click();

            if (button.equals("Search")){
                Thread.sleep(2000);
                //for search button
                obj.findElement(By.xpath("//button[@type='submit']")).click();
                Thread.sleep(5000);
             //   reset search bar via reset button after searching for the required record
                obj.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")).click();

            }
            else if(button.equals("Reset")){
                Thread.sleep(2000);
                // for reset button
                obj.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")).click();
            }

        }catch (Exception e){
            System.out.println("An unexpected error occurred while searching for an existing User: "+userName);
        }
    }

    public void logoutPage(WebDriver obj) throws InterruptedException {
        //Logout
        obj.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
        Thread.sleep(2000); //for slowing down the execution of the next line of code.
        obj.findElement(By.linkText("Logout")).click();

        String logoutURL=obj.getCurrentUrl();

        if (logoutURL.contains("/login")){
            System.out.println("Logout Successful.");
        }else{
            System.out.println("Logout Unsuccessful.");
        }

        // Aside from using the logout successful if-else condition for verification (lines 55-59) in the console,
        // we can also use TestNG Assertions which are more efficient.

       Assert.assertTrue(logoutURL.contains(("/login")));

        System.out.println("\nAutomation Successful.");

    }
}
