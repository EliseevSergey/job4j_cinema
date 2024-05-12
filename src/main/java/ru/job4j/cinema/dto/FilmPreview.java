package ru.job4j.cinema.dto;

import java.util.Objects;

public class FilmPreview {
    private int id;
    private String name;
    private String description;
    private int year;
    private int minAge;
    private int duration;
    private String genre;
    private int fileId;

    public FilmPreview(int id, String name, String description, int year, int minAge, int duration, String genre, int fileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.minAge = minAge;
        this.duration = duration;
        this.genre = genre;
        this.fileId = fileId;
    }

    public FilmPreview(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmPreview that = (FilmPreview) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
