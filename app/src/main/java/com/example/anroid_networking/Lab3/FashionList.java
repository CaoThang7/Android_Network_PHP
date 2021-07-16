package com.example.anroid_networking.Lab3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FashionList {
    @SerializedName("fashions")
    @Expose
    private ArrayList<Contact> contacts = new ArrayList<>();
    /**
     * @return The contacts */
    public ArrayList<Contact> getContacts() { return contacts;
    }
    /**
     * @param contacts The contacts */
    public void setContacts(ArrayList<Contact> contacts) { this.contacts = contacts;
    }
}
