package lukuvinkki;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import lukuvinkki.domain.Tag;
import lukuvinkki.domain.Tip;
import lukuvinkki.repository.TagRepository;
import lukuvinkki.repository.TipRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TipStepdefs extends AbstractStepdefs {

    private WebDriver driver = new HtmlUnitDriver(true);
    private String url = "http://localhost:" + 8080 + "/";
    private Tip dummyTip1, dummyTip2, dummyTip3, dummyTip4;
    @Resource
    private TipRepository tipRepository;

    @Resource
    private TagRepository tagRepository;


    @Given("^there are some tips created$")
    public void there_are_some_tips_created() throws Throwable {
        saveDummyTips();
    }

    @Given("^command view tips is selected$")
    public void command_view_tips_is_selected() throws Throwable {
        driver.get(url);
        //TODO: Add a proper id to the link
        WebElement webElement = driver.findElement(By.linkText("täältä"));
        webElement.click();
    }

    @Given("^command new tip is selected$")
    public void command_new_tip_is_selected() throws Throwable {
        driver.get(url);
        //TODO: Add a proper id to the link
        WebElement webElement = driver.findElement(By.linkText("lukuvinkki"));
        webElement.click();
    }
    

    @Given("^command search is selected$")
    public void command_search_is_selected() throws Throwable {
        driver.get(url);
        WebElement webElement = driver.findElement(By.linkText("täältä"));
        webElement.click();
    }
    
    @Given("^tip is created with tag \"([^\"]*)\"$")
    public void tip_is_created_with_tag(String tag) throws Throwable {
        driver.get(url);
        WebElement webElement = driver.findElement(By.linkText("lukuvinkki"));
        webElement.click();
        addTip("plaah", "plaah", "plaah", "plaah", tag);
    }

    @Given("^command submit tip is selected$")
    public void command_submit_tip_is_selected() throws Throwable {
        driver.get(url + "addTip");
        WebElement webElement = driver.findElement(By.name("submit"));
        webElement.click();
    }

    @When("^title \"([^\"]*)\", author \"([^\"]*)\", url \"([^\"]*)\" and description \"([^\"]*)\" are given$")
    public void title_author_url_and_description_are_given(String title, String author, String url, String desc) throws Throwable {
        addTip(title, author, url, desc, "");
    }

    @When("^title \"([^\"]*)\", author \"([^\"]*)\", url \"([^\"]*)\", description \"([^\"]*)\" and tags \"([^\"]*)\" are given$")
    public void title_author_url_description_and_tags_are_given(String title, String author, String url, String desc, String tags) throws Throwable {
        addTip(title, author, url, desc, tags);
    }
    
    @When("^search is done with keyword \"([^\"]*)\"$")
    public void command_search_is_selected_with_keyword(String keyword) throws Throwable {
        searchTips(keyword);
    }

    @When("^search is done with mismatching keyword \"([^\"]*)\"$")
    public void command_search_is_selected_with_mismatching_keyword(String keyword) throws Throwable {
        searchTips(keyword);
    }
    
    @Then("^page contains a list of tips with tag matches shown first")
    public void page_contains_a_list_of_tips_with_tag_matches_shown_first() throws Throwable {
        List<WebElement> tipElements = driver.findElements(By.cssSelector(".table tbody tr"));
        assertTipTableElement(tipElements.get(0), dummyTip3.getTitle(), dummyTip3.getAuthor(), dummyTip3.getUrl(), dummyTip3.getDescription()); // tag match, created later
        assertTipTableElement(tipElements.get(1), dummyTip1.getTitle(), dummyTip1.getAuthor(), dummyTip1.getUrl(), dummyTip1.getDescription()); // tag match
        assertTipTableElement(tipElements.get(2), dummyTip4.getTitle(), dummyTip4.getAuthor(), dummyTip4.getUrl(), dummyTip4.getDescription()); // title match
    }

    @Then("^page contains a list of tips sorted by creation time$")
    public void page_contains_a_list_of_tips_sorted_by_creation_time() throws Throwable {
        List<WebElement> tipElements = driver.findElements(By.cssSelector(".table tbody tr"));
        assertTipTableElement(tipElements.get(0), dummyTip4.getTitle(), dummyTip4.getAuthor(), dummyTip4.getUrl(), dummyTip4.getDescription());
        assertTipTableElement(tipElements.get(1), dummyTip3.getTitle(), dummyTip3.getAuthor(), dummyTip3.getUrl(), dummyTip3.getDescription());
        assertTipTableElement(tipElements.get(2), dummyTip2.getTitle(), dummyTip2.getAuthor(), dummyTip2.getUrl(), dummyTip2.getDescription());
        assertTipTableElement(tipElements.get(3), dummyTip1.getTitle(), dummyTip1.getAuthor(), dummyTip1.getUrl(), dummyTip1.getDescription());
    }

    @Then("^a new tip is created with title \"([^\"]*)\", author \"([^\"]*)\", url \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void a_new_tip_is_created_with_title_author_url_and_description(String title, String author, String url, String desc) throws Throwable {
        List<WebElement> tipElements = driver.findElements(By.cssSelector(".table tbody tr"));
        assertTipTableElement(tipElements.get(0), title, author, url, desc);
    }

    @Then("^a proper form with title, author, url and description is shown$")
    public void a_proper_form_with_title_author_url_and_description_is_shown() throws Throwable {
        List<WebElement> webElements = driver.findElements(By.cssSelector("input"));
        webElements.forEach(element -> assertEquals(element.getAttribute("type"), "text"));
        assertEquals("title", webElements.get(0).getAttribute("name"));
        assertEquals("author", webElements.get(1).getAttribute("name"));
        assertEquals("url", webElements.get(2).getAttribute("name"));
        assertEquals("description", webElements.get(3).getAttribute("name"));
        assertEquals("rawTags", webElements.get(4).getAttribute("name"));
    }

    @Then("^following tags are found in newly created tip:$")
    public void following_tags_are_found_in_newly_created_tip(DataTable dt) {
        List<String> tags = dt.asList(String.class);
        List<WebElement> rows = driver.findElements(By.cssSelector(".table tbody tr"));
        assertTagsInTipTableRow(rows.get(0), tags);
    }

    @Then("^only following tags are created:$")
    public void only_following_tags_are_created(DataTable dt) throws Throwable {
        List<String> tags = dt.asList(String.class);
        assertCreatedTags(tags);
    }

    @Then("^list contains tip with tag \"([^\"]*)\"$")
    public void list_contains_tip_with_tag(String tag) throws Throwable {
        List<String> tags = new ArrayList();
        tags.add(tag);
        List<WebElement> rows = driver.findElements(By.cssSelector(".table tbody tr"));
        assertTagsInTipTableRow(rows.get(0), tags);
    }
        
    @Then("^the main page is shown$")
    public void the_main_page_is_shown() throws Throwable {
        pageContains("Lukuvinkit");
    }

    @Then("^list doesnt contain tip with tag \"([^\"]*)\"$")
    public void list_doesnt_contain_tip_with_tag(String tag) throws Throwable {
        assertTrue(!driver.getPageSource().contains(tag));
    }
    
    @After
    public void tearDown() {
        // Don't change the order of these delete statements
        tipRepository.deleteAll();
        tagRepository.deleteAll();
        driver.quit();
    }

    private void addTip(String title, String author, String url, String desc, String tags) {
        WebElement element = driver.findElement(By.cssSelector("input[name='title']"));
        element.sendKeys(title);
        element = driver.findElement(By.cssSelector("input[name='author']"));
        element.sendKeys(author);
        element = driver.findElement(By.cssSelector("input[name='url']"));
        element.sendKeys(url);
        element = driver.findElement(By.cssSelector("input[name='description']"));
        element.sendKeys(desc);
        element = driver.findElement(By.cssSelector("input[name='rawTags']"));
        element.sendKeys(tags);
        element.submit();
    }

    private void assertTagsInTipTableRow(WebElement row, List<String> tags) {
        List<WebElement> tagElements = row.findElements(By.className("tag"));
        assertEquals("Correct amount of tags are added", tags.size(), tagElements.size());
        for (WebElement tagElement : tagElements) {
            assertTrue("Tag element is found", tags.contains(tagElement.getText()));
        }
    }

    private void assertTipTableElement(WebElement element, String title, String author, String url, String desc) {
        WebElement titleElement = element.findElement(By.className("title"));
        WebElement authorElement = element.findElement(By.className("author"));
        WebElement urlElement = element.findElement(By.className("url"));
        WebElement descriptionElement = element.findElement(By.className("description"));
        assertEquals(titleElement.getText(), title);
        assertEquals(authorElement.getText(), author);
        assertEquals(urlElement.getText(), url);
        assertEquals(descriptionElement.getText(), desc);
    }
    
    private void assertCreatedTags(List<String> tagNames) {
        List<Tag> tags = tagRepository.findAll();
        assertEquals("Expected amount of tags", tagNames.size(), tags.size());
        for (Tag tag : tags) {
            assertTrue(tagNames.contains(tag.getName()));
        }
    }
    
    private void searchTips(String keyword) {
        WebElement webElement = driver.findElement(By.name("keyword"));
        webElement.sendKeys(keyword);
        webElement.submit();
    }

    private void saveDummyTips() {
        Tag fooTag = new Tag("foo");
        Tag barTag = new Tag("bar");
        
        tagRepository.save(fooTag);
        tagRepository.save(barTag);
        
        dummyTip1 = new Tip();
        dummyTip1.setAuthor("Seppo");
        dummyTip1.setTitle("Sepon tarinat");
        dummyTip1.setUrl("https://google.com");
        dummyTip1.setDescription("Toiseksi mahtavin tarina ikinä");
        dummyTip1.addTag(fooTag);
        tipRepository.save(dummyTip1);
        
        dummyTip2 = new Tip();
        dummyTip2.setAuthor("Keijo");
        dummyTip2.setTitle("Keijon tarinat");
        dummyTip2.setUrl("https://example.com");
        dummyTip2.setDescription("Mahtavin tarina ikinä");
        dummyTip2.addTag(barTag);
        tipRepository.save(dummyTip2);
        
        dummyTip3 = new Tip();
        dummyTip3.setAuthor("Seppo");
        dummyTip3.setTitle("Toinen tarina");
        dummyTip3.setUrl("https://seppourl.com");
        dummyTip3.setDescription("Hieno toinen tarina");
        dummyTip3.addTag(fooTag);
        dummyTip3.addTag(barTag);
        tipRepository.save(dummyTip3);
        
        dummyTip4 = new Tip();
        dummyTip4.setAuthor("Foo");
        dummyTip4.setTitle("Joku titteli");
        dummyTip4.setUrl("https://foourl.com");
        dummyTip4.setDescription("Foo Fighters");
        dummyTip4.addTag(barTag);
        tipRepository.save(dummyTip4);
    }
    
    private void pageContains(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

}
