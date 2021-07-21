package com.example.anroid_networking.Lab3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fashionn {
    @SerializedName("id") @Expose
    private String id;
    @SerializedName("name") @Expose
    private String name;
    @SerializedName("producttype") @Expose
    private String producttype;
    @SerializedName("price") @Expose
    private String price;
    @SerializedName("profile_pic") @Expose
    private String profilePic;
    @SerializedName("style") @Expose
    private String style;
    @SerializedName("mystyle") @Expose
    private Mystyle mystyle;


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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Mystyle getMystyle() {
        return mystyle;
    }

    public void setMystyle(Mystyle mystyle) {

        this.mystyle = mystyle;
    }

    public class Mystyle {
        @SerializedName("modern")
        @Expose
        private String modern;
        @SerializedName("vintage")
        @Expose
        private String vintage;

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
}
