package io.basis.postservice.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Post {
    private int postId;
    private String title;
    private String content;
    private Long createDate;
    private Long updateDate;
    private String thumbnailPost;
    private Integer websiteId;

    @Id
    @Column(name = "post_id")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_date")
    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "thumbnail_post")
    public String getThumbnailPost() {
        return thumbnailPost;
    }

    public void setThumbnailPost(String thumbnailPost) {
        this.thumbnailPost = thumbnailPost;
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
        Post post = (Post) o;
        return postId == post.postId &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(createDate, post.createDate) &&
                Objects.equals(updateDate, post.updateDate) &&
                Objects.equals(thumbnailPost, post.thumbnailPost) &&
                Objects.equals(websiteId, post.websiteId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, title, content, createDate, updateDate, thumbnailPost, websiteId);
    }
}
