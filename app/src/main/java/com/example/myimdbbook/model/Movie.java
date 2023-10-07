package com.example.myimdbbook.model;

public class Movie {
    private int id;
    private String movieName;
    private String date;
    private String score;

    public Movie(int id,String movieName, String date, String score) {
        this.id=id;
        this.movieName = movieName;
        this.date = date;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
