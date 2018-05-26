package io.basis.productservice.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "product_service", catalog = "")
public class Product {
    private int id;
    private String name;
    private double price;
    private double discount;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private int status;
    private int enabled;
    private String description;
    private String attributes;
    private String images;
    private int categoryId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "enabled")
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "attributes")
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    @Basic
    @Column(name = "images")
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "website_id")
    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.discount, discount) == 0 &&
                status == that.status &&
                enabled == that.enabled &&
                categoryId == that.categoryId &&
                websiteId == that.websiteId &&
                Objects.equals(name, that.name) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(modifiedDate, that.modifiedDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(attributes, that.attributes) &&
                Objects.equals(images, that.images);
    }

    public Product(String name, double price, double discount, Timestamp createdDate, Timestamp modifiedDate, int status, int enabled, String description, String attributes, String images, int categoryId, int websiteId) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
        this.enabled = enabled;
        this.description = description;
        this.attributes = attributes;
        this.images = images;
        this.categoryId = categoryId;
        this.websiteId = websiteId;
    }

    public Product() {
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, discount, createdDate, modifiedDate, status, enabled, description, attributes, images, categoryId, websiteId);
    }
}
