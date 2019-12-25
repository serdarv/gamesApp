package com.vladimir.gamesapp.Api;

public class ApiConst {

    //Api constants

    public static final String BASE_API = "https://api-v3.igdb.com/";
    public static final String API_KEY = "637db49dad9b1982b20c7311236beb6b";

    //Different api constants for search path

    public static final String SEARCH_API = "?search={searchTerm}";
    public static final String API_GAME_FIELDS = "id,name,storyline,summary,url,screenshots";
    public static final String API_COVER_FIELDS = "url";
    public static final String API_COVER_LIMIT = "1";

    //TODO - make other parameters
}
