package io.basis.mediaservice.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Media {
    private int mediaId;
    private String name;
    private String link;
    private Long createDate;
    private Boolean type;
    private Integer websiteId;

    @Id
    @Column(name = "media_id")
    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
    @Column(name = "type")
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Basic
    @Column(name = "website_id")
    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return mediaId == media.mediaId &&
                Objects.equals(name, media.name) &&
                Objects.equals(link, media.link) &&
                Objects.equals(createDate, media.createDate) &&
                Objects.equals(type, media.type) &&
                Objects.equals(websiteId, media.websiteId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mediaId, name, link, createDate, type, websiteId);
    }
}
