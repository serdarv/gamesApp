package com.vladimir.gamesapp.Api;

import com.vladimir.gamesapp.Api.Model.GameCoverModel;
import com.vladimir.gamesapp.Api.Model.GameModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameApi {

    public static void searchGame(String searchTerm, GamesApiCallbackInterface callbackInterface) {
        BaseApi.getApiClient().searchGame(searchTerm, ApiConst.API_GAME_FIELDS).enqueue(new Callback<ArrayList<GameModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GameModel>> call, Response<ArrayList<GameModel>> response) {
                callbackInterface.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<GameModel>> call, Throwable t) {
                System.out.println(t.getMessage());
                callbackInterface.onFailure();
            }
        });
    }

    public static void getGameImageUrl(int gameId,GamesCoverApiCallbackInterface inGamesCoverApiCallbackInterface) {
        BaseApi.getApiClient().getGameImageUrl(ApiConst.API_COVER_FIELDS,gameId,ApiConst.API_COVER_LIMIT).enqueue(new Callback<ArrayList<GameCoverModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GameCoverModel>> call, Response<ArrayList<GameCoverModel>> response) {

                if(response.body()!=null && response.body().size()>0) {
                    inGamesCoverApiCallbackInterface.onSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<GameCoverModel>> call, Throwable t) {
                System.out.println(t.getMessage());
                inGamesCoverApiCallbackInterface.onFailure();
            }
        });
    }

}
