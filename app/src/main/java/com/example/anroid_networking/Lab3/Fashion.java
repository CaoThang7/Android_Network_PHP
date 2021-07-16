package com.example.anroid_networking.Lab3;

public class Fashion {
    String id;
    String name, producttype, price, style,modern,vintage;

    public Fashion() {
    }

    public Fashion(String id, String name, String producttype, String price, String style) {
        this.id = id;
        this.name = name;
        this.producttype = producttype;
        this.price = price;
        this.style = style;
    }

    public Fashion(String id, String name, String producttype, String price, String style, String modern, String vintage) {
        this.id = id;
        this.name = name;
        this.producttype = producttype;
        this.price = price;
        this.style = style;
        this.modern = modern;
        this.vintage = vintage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getModern() {
        return modern;
    }

    public void setModern(String modern) {
        this.modern = modern;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }
}
