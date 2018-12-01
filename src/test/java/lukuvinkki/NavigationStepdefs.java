package lukuvinkki;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class NavigationStepdefs extends AbstractStepdefs {

    @Autowired
    private WebDriver driver;

    @Given("^command view tips is selected$")
    public void command_view_tips_is_selected() throws Throwable {
        //TODO: Add a proper id to the link
        WebElement webElement = driver.findElement(By.linkText("täältä"));
        webElement.click();
    }

    @Given("^command new tip is selected$")
    public void command_new_tip_is_selected() throws Throwable {
        //TODO: Add a proper id to the link
        WebElement webElement = driver.findElement(By.linkText("lukuvinkki"));
        webElement.click();
    }

    @Given("^command search is selected$")
    public void command_search_is_selected() throws Throwable {
        WebElement webElement = driver.findElement(By.linkText("täältä"));
        webElement.click();
    }

    @Given("^command submit tip is selected$")
    public void command_submit_tip_is_selected() throws Throwable {
        driver.get(BASE_URL + "addTip");
        WebElement webElement = driver.findElement(By.name("submit"));
        webElement.click();
    }

    @When("^command Mark as Read is selected$")
    public void command_mark_as_read_is_selected()throws Throwable {
        WebElement webElement = driver.findElement(By.name("markAsRead"));
        webElement.click();
    }

    @When("^search is done with keyword \"([^\"]*)\"$")
    public void command_search_is_selected_with_keyword(String keyword) throws Throwable {
        searchTips(keyword);
    }

    @When("^search is done with mismatching keyword \"([^\"]*)\"$")
    public void command_search_is_selected_with_mismatching_keyword(String keyword) throws Throwable {
        searchTips(keyword);
    }

    private void searchTips(String keyword) {
        WebElement webElement = driver.findElement(By.name("keyword"));
        webElement.sendKeys(keyword);
        webElement.submit();
    }
}