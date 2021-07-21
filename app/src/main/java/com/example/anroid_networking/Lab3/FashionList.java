package com.example.anroid_networking.Lab3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FashionList {
    @SerializedName("fashions")
    @Expose
    private ArrayList<Fashionn> fashion = new ArrayList<>();
    /**
     * @return The contacts */
    public ArrayList<Fashionn> getContacts() {
        return fashion;
    }
    /**
     * @param fashion The fashion */
    public void setFashion(ArrayList<Fashionn> fashion) {
        this.fashion = fashion;
    }
}
