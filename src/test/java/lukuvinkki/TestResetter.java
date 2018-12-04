package lukuvinkki;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import lukuvinkki.repository.CommentRepository;
import lukuvinkki.repository.TagRepository;
import lukuvinkki.repository.TipRepository;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class TestResetter extends AbstractStepdefs {
    @Autowired
    WebDriver driver;

    @Resource
    TipRepository tipRepository;

    @Resource
    TagRepository tagRepository;

    @Resource
    CommentRepository commentRepository;

    @Before
    public void setUp() {
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        // Don't change the order of these delete statements
        commentRepository.deleteAll();
        tipRepository.deleteAll();
        tagRepository.deleteAll();
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);
    }
}
