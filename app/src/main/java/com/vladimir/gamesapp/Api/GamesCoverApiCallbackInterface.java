package com.vladimir.gamesapp.Api;

import com.vladimir.gamesapp.Api.Model.GameCoverModel;

import java.util.ArrayList;

public interface GamesCoverApiCallbackInterface {
    void onSuccess(ArrayList<GameCoverModel> covers);
    void onFailure();
}
