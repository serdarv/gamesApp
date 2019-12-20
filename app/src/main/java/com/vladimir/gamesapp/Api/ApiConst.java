package com.vladimir.gamesapp.Api;

public class ApiConst {

    //Api constants

    public static final String BASE_API = "https://api-v3.igdb.com";
    public static final String API_KEY = "637db49dad9b1982b20c7311236beb6b";

    //Different api constants for search path

    ///Need following parameter as search term
    public static final String SEARCH_API = "search=";
    public static final String API_FIELDS = "fields=id,name,cover,storyline,summary,url";
    ///Need following parameter for result limit
    public static final String API_LIMIT = "limit=";
    ///Need following parameter for coverID
    public static final String COVER_API = "covers?limit=1&fields=url&filter[id][eq]=";

    //TODO - make other parameters
}
