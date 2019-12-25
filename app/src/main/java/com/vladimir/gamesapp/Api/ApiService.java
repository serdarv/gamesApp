package com.vladimir.gamesapp.Api;

import com.vladimir.gamesapp.Api.Model.GameCoverModel;
import com.vladimir.gamesapp.Api.Model.GameModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //TODO - implement url paths with help of ApiConst parameters

    @GET("games/")
    Call<ArrayList<GameModel>> searchGame(@Query("search") String term,
                                          @Query("fields") String fields);

    @GET("covers")
    Call<ArrayList<GameCoverModel>> getGameImageUrl(@Query("fields") String fields,
                                                    @Query("filter[game][eq]") int filter,
                                                    @Query("limit") String limit);


}
