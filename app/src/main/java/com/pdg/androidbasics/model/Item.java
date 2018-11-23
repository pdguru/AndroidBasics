package com.pdg.androidbasics.model;

public class Item {
    int id;
    String name;
    String subtitle;
    String image;
    float price;
    String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Item(int id,
                String name,
                String subtitle,
                String image,
                float price,
                String description) {
        this.id = id;
        this.name = name;
        this.subtitle = subtitle;
        this.image = image;
        this.price = price;
        this.description = description;

    }
}
