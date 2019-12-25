package com.vladimir.gamesapp.Fragment.searchGames;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

import com.google.gson.Gson;
import com.vladimir.gamesapp.Adapters.GameListViewAdapter;
import com.vladimir.gamesapp.Api.GamesApiCallbackInterface;
import com.vladimir.gamesapp.Api.GameApi;
import com.vladimir.gamesapp.Api.Model.GameModel;
import com.vladimir.gamesapp.R;
import com.vladimir.gamesapp.Utils.FlowController;

import java.util.ArrayList;

public class SearchGamesFragment extends Fragment {

    private SearchView searchView;
    private ListView gamesList;
    private GameListViewAdapter gameListViewAdapter;
    private ArrayList<GameModel> modelArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = root.findViewById(R.id.game_sv);
        gamesList = root.findViewById(R.id.games_lv);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                GameApi.searchGame(s, new GamesApiCallbackInterface() {
                    @Override
                    public void onSuccess(ArrayList<GameModel> gameModels) {
                        modelArrayList = gameModels;
                        System.out.println(new Gson().toJson(gameModels));
                        gameListViewAdapter = new GameListViewAdapter(getActivity(),gameModels,false);
                        gamesList.setAdapter(gameListViewAdapter);
                    }

                    @Override
                    public void onFailure() {

                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        gamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FlowController.showGameDetailActivity(getActivity(),modelArrayList.get(i));
            }
        });

        return root;
    }
}