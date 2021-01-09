package io.licence.imagesearchwrapper.unsplash;

import java.util.List;

public class Image {

    private String id;
    private String description;
    private Integer width;
    private Integer height;
    private String color;
    private Links urls;

    public Image(String id, String description, Integer width, Integer height, String color, Links urls) {
        this.id = id;
        this.description = description;
        this.width = width;
        this.height = height;
        this.color = color;
        this.urls = urls;
    }

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Links getUrls() {
        return urls;
    }

    public void setUrls(Links urls) {
        this.urls = urls;
    }
}
