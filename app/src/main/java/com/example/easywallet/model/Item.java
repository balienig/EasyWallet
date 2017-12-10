package com.example.easywallet.model;

/**
 * Created by balie on 10-Dec-17.
 */

public class Item {
    public final int id;
    public final String title;
    public final String picture;

    public Item(int id, String title, String picture) {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }
}
