package lukuvinkki.domain;

public class Tip {

    private int id;
    private String title;
    private String author;
    private String url;
    private String description;

    public Tip(String title, String author, String url, String description) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
    }

    public Tip(int id, String title, String author, String url, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.url = url;
        this.description = description;
    }
    public Tip() {

    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format("Id: %d, Title: %s, Author; %s, Url: %s",
                this.getId(),this.getTitle(), this.getAuthor(), this.getUrl());
    }
}
