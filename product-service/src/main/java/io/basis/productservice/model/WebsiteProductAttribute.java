package io.basis.productservice.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "website_product_attribute", schema = "product_service", catalog = "")
public class WebsiteProductAttribute {
    private int id;
    private String attributeName;
    private int websiteId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "attribute_name")
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
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
        WebsiteProductAttribute that = (WebsiteProductAttribute) o;
        return id == that.id &&
                Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, attributeName, websiteId);
    }

    public String[] convertJsonToArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String[] array = mapper.readValue(this.attributeName, String[].class);
        return array;
    }
}
