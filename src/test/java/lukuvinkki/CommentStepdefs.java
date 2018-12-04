package lukuvinkki;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lukuvinkki.domain.Comment;
import lukuvinkki.repository.CommentRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

public class CommentStepdefs extends AbstractStepdefs {
    @Autowired
    private WebDriver driver;

    @Resource
    CommentRepository commentRepository;

    @When("^new comment is created with nickname \"([^\"]*)\" and content \"([^\"]*)\"$")
    public void new_comment_created_with_nickname_and_content(String nickname, String content) throws Throwable {
        createNewComment(nickname, content);
    }

    @Then("^comment is found with nickname \"([^\"]*)\" and content \"([^\"]*)\"$")
    public void comment_is_found(String nickname, String content) throws Throwable {
        Comment comment = commentRepository.findAll().get(0);
        assertEquals(nickname, comment.getNickname());
        assertEquals(content, comment.getContent());
    }

    private void createNewComment(String nickname, String content) {
        WebElement webElement = driver.findElement(By.name("nickname"));
        webElement.sendKeys(nickname);
        webElement = driver.findElement(By.name("content"));
        webElement.sendKeys(content);
        webElement.submit();
    }
}
