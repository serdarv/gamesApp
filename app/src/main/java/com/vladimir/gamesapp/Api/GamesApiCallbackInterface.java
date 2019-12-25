package com.vladimir.gamesapp.Api;

import com.vladimir.gamesapp.Api.Model.GameModel;

import java.util.ArrayList;

public interface GamesApiCallbackInterface {
    void onSuccess(ArrayList<GameModel> gameModels);
    void onFailure();
}
