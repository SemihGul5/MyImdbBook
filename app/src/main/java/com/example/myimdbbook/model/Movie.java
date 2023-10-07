package com.example.myimdbbook.model;

public class Movie {
    private String movieName;
    private String date;
    private String score;

    public Movie(String movieName, String date, String score) {
        this.movieName = movieName;
        this.date = date;
        this.score = score;
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
