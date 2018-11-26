package lukuvinkki.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tip")
public class Tip {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private String url;
    private String description;

    @Transient
    private String rawTags;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = { @JoinColumn(name = "tip_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )

    
    private Set<Tag> tags = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return this.created;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getTips().add(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getTips().remove(this);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setRawTags(String rawTags) {
        this.rawTags = rawTags;
    }

    public String getRawTags() {
        return this.rawTags;
    }


    public String toString() {
        return String.format("Id: %d, Title: %s, Author; %s, Url: %s, Created: %s",
                this.getId(),this.getTitle(), this.getAuthor(), this.getUrl(), this.getCreated());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tip)) return false;
        Tip tip = (Tip) o;
        return getId() == tip.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
