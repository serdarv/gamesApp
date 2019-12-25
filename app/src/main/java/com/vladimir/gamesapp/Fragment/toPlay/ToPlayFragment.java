package com.vladimir.gamesapp.Fragment.toPlay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vladimir.gamesapp.Adapters.GameListViewAdapter;
import com.vladimir.gamesapp.Database.DBGame;
import com.vladimir.gamesapp.Enums.UserGameSelection;
import com.vladimir.gamesapp.R;

public class ToPlayFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_to_play, container, false);

        ListView gamesList = root.findViewById(R.id.games_lv);
        DBGame dbGame = new DBGame(getContext());
        GameListViewAdapter gameListViewAdapter = new GameListViewAdapter(getContext(),dbGame.getGames(UserGameSelection.TOPLAY),true);
        gamesList.setAdapter(gameListViewAdapter);

        return root;
    }
}