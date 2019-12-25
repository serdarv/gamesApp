package com.vladimir.gamesapp.Api.Model;

import com.google.gson.annotations.SerializedName;

public class GameModel {

    transient private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("storyline")
    private String storyline;
    @SerializedName("summary")
    private String summary;
    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private int game_id;

    private String selectionType;

    public GameModel() {
    }

    public GameModel(int id, String name, String storyline, String summary, String url, int game_id, String selectionType) {
        this.id = id;
        this.name = name;
        this.storyline = storyline;
        this.summary = summary;
        this.url = url;
        this.game_id = game_id;
        this.selectionType = selectionType;
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

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
