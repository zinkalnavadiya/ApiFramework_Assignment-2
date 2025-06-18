package com.example.moviedatabaseapp;


public class Movie {
    private String id;
    private String title;
    private String year;
    private String imageUrl;

    public Movie() {}

    public Movie(String title, String year, String imageUrl) {
        this.title = title;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
