package com.vladimir.gamesapp.Api.Model;

public class GameModel {

    private int id;
    private String name,storyline,summary,url;
    private int screenshots;

    public GameModel() {
    }

    public GameModel(int id, String name, String storyline, String summary, String url, int screenshots) {
        this.id = id;
        this.name = name;
        this.storyline = storyline;
        this.summary = summary;
        this.url = url;
        this.screenshots = screenshots;
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

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(int screenshots) {
        this.screenshots = screenshots;
    }
}
