package io.basic.website.model;

import javax.persistence.*;

@Entity
@Table(name = "page")
public class Page {
    private int id;
    private String title;
    private long createDate;
    private String path;
    private boolean homePage;
    private int websiteId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "create_date")
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "home_page")
    public boolean isHomePage() {
        return homePage;
    }

    public void setHomePage(boolean homePage) {
        this.homePage = homePage;
    }

    @Basic
    @Column(name = "website_id")
    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }
}
