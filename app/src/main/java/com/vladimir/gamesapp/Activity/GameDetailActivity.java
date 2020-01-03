package com.vladimir.gamesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vladimir.gamesapp.Api.GameApi;
import com.vladimir.gamesapp.Api.GamesCoverApiCallbackInterface;
import com.vladimir.gamesapp.Api.Model.GameCoverModel;
import com.vladimir.gamesapp.Api.Model.GameModel;
import com.vladimir.gamesapp.Database.DBGame;
import com.vladimir.gamesapp.Enums.UserGameSelection;
import com.vladimir.gamesapp.R;
import com.vladimir.gamesapp.Utils.FlowController;

import java.util.ArrayList;

public class GameDetailActivity extends AppCompatActivity {

    //Private variables

    private GameModel game = new GameModel();
    private DBGame dbGame;

    //Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        Bundle bundle = getIntent().getExtras();

        dbGame = new DBGame(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        game = gson.fromJson(bundle.getString("game_model"),GameModel.class);

        ImageView gameImage = findViewById(R.id.game_img);
        setTitle(game.getName());
        TextView url = findViewById(R.id.game_url_tv);
        TextView summary = findViewById(R.id.game_summary_tv);
        TextView storyline = findViewById(R.id.game_storyline_tv);

        GameApi.getGameImageUrl(game.getGame_id(), new GamesCoverApiCallbackInterface() {
            @Override
            public void onSuccess(ArrayList<GameCoverModel> covers) {
                Picasso.get().load(covers.get(0).getUrl().replace("//","http://")).into(gameImage);
            }

            @Override
            public void onFailure() {

            }
        });

        url.setText(game.getUrl());
        summary.setText(game.getSummary());
        storyline.setText(game.getStoryline());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }

    //ButterKnife OnClick bindings

    @OnClick(R.id.favourites_btn) void addFav() {
        if (dbGame.saveGame(game, UserGameSelection.FAVOURITE) > 1)
        {
            FlowController.showHome(this);
            Toast.makeText(this,R.string.game_added,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.played_btn) void addPlayed() {
        if (dbGame.saveGame(game, UserGameSelection.PLAYED) > 1)
        {
            FlowController.showHome(this);
            Toast.makeText(this,R.string.game_added,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.toPlay_btn) void addToPlay() {
        if (dbGame.saveGame(game, UserGameSelection.TOPLAY) > 1)
        {
            FlowController.showHome(this);
            Toast.makeText(this,R.string.game_added,Toast.LENGTH_SHORT).show();
        }

    }
}
