package com.example.best_of_the_year.models;

public class Song {
    private int id;
    private String title;

    // Costruttore
    public Song(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}