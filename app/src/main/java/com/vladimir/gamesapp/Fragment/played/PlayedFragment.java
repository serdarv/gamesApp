package com.vladimir.gamesapp.Fragment.played;

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

public class PlayedFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_played, container, false);

        ListView gamesList = root.findViewById(R.id.games_lv);
        DBGame dbGame = new DBGame(getContext());
        GameListViewAdapter gameListViewAdapter = new GameListViewAdapter(getContext(),dbGame.getGames(UserGameSelection.PLAYED),true);
        gamesList.setAdapter(gameListViewAdapter);

        return root;
    }
}

// played = send, home = favourites, gallery = search,share = toPlay