package Assessment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Code_Screen_Task {

    public static void main(String[] args) {

        // 1- Setting up webdriver manager
        WebDriverManager.chromedriver().setup();

        // 2- Create instance of WebDriver
        WebDriver driver = new ChromeDriver();


        // 3- Make page full screen
        driver.manage().window().maximize();


        // 4- Navigate to Website
        driver.get("https://www.webstaurantstore.com/");


        // 5- Locate search box and send text
        WebElement searchBox = driver.findElement(By.id("searchval"));
        searchBox.sendKeys("stainless work table");

        // 6- Locate search button and click
        WebElement searchBtn = driver.findElement(By.xpath("//button[@value='Search']"));
        searchBtn.click();

        // 7- Locate all products listing in the page and store them in a List of WebElements
        List<WebElement> productListing = driver.findElements(By.xpath("//div[@id='ProductBoxContainer']"));

        // 8- Get the size of the search
        // System.out.println(productListing.size());

        // 9- Go through each element of the List
        for (WebElement product : productListing) {

            // 10- Get text of each element
            String productTitle = product.getText();

            // 11- Assert that every product has the word "Table"
            Assert.assertTrue(productTitle.contains("Table"));

            // 12- Locate last element of the search in the page
            if (productListing.indexOf(product) == productListing.size() - 1) {

                // 13 - Make sure locating last item
                //String lastItem = product.getText();
                //System.out.println(lastItem);

                // 14 - Locate "Add to Cart" of the last item
                WebElement lastAddToCart = driver.findElement(By.xpath("(//input[@value='Add to Cart'])[last()]"));
                lastAddToCart.click();

            }
        }


        // 15 - Locate JS Popup using Javascript executor
        WebElement jsPopup = driver.findElement(By.xpath("//a[@class='btn btn-small btn-primary']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", jsPopup);

        // 16 - Locate "Empty Cart" Button and click
        WebElement emptyCart = driver.findElement(By.xpath("//div[@class='inline']/button"));
        emptyCart.click();

        // 17 - Locate "Empty Cart" Popup and click
        WebElement box = driver.findElement(By.xpath("//footer[@class='bg-gray-100 border-gray-300 border-solid border-0 border-t flex justify-end p-3 ']/button[1]"));
        box.click();


        // 18 - Close opened page
        //driver.close();

    }

}
