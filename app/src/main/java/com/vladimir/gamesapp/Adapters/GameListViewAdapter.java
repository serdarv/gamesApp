package com.vladimir.gamesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vladimir.gamesapp.Api.GameApi;
import com.vladimir.gamesapp.Api.GamesCoverApiCallbackInterface;
import com.vladimir.gamesapp.Api.Model.GameCoverModel;
import com.vladimir.gamesapp.Api.Model.GameModel;
import com.vladimir.gamesapp.Database.DBGame;
import com.vladimir.gamesapp.Enums.UserGameSelection;
import com.vladimir.gamesapp.R;

import java.util.ArrayList;

public class GameListViewAdapter extends BaseAdapter {

    private ArrayList<GameModel> games;
    private Context context;
    private Boolean visibility;
    private DBGame dbGame;

    public GameListViewAdapter(Context context,ArrayList<GameModel> games,Boolean visibility) {
        this.games = games;
        this.context = context;
        this.visibility = visibility;
        dbGame = new DBGame(context);
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int i) {
        return games.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi=view;
        if(view==null)
            vi = inflater.inflate(R.layout.game_list_item, null);

        ImageView iv = vi.findViewById(R.id.game_iv);
        TextView tv = vi.findViewById(R.id.game_name_tv);
        ImageView rightButton = vi.findViewById(R.id.right_button);

        GameApi.getGameImageUrl(games.get(i).getGame_id(), new GamesCoverApiCallbackInterface() {
            @Override
            public void onSuccess(ArrayList<GameCoverModel> covers) {
                Picasso.get().load(covers.get(0).getUrl().replace("//","http://")).into(iv);
            }

            @Override
            public void onFailure() {

            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbGame.deleteGame(games.get(i).getId())) {
                    if (games.get(i).getSelectionType().equals(UserGameSelection.FAVOURITE.name())) {
                        Toast.makeText(context,R.string.remove_fav,Toast.LENGTH_SHORT).show();
                    } else if (games.get(i).getSelectionType().equals(UserGameSelection.TOPLAY.name())) {
                        Toast.makeText(context,R.string.add_to_played,Toast.LENGTH_SHORT).show();
                        dbGame.saveGame(games.get(i),UserGameSelection.PLAYED);
                    }
                    games.remove(i);
                    notifyDataSetChanged();
                }

            }
        });
        if (visibility) {
            if (games.get(i).getSelectionType().equals(UserGameSelection.FAVOURITE.name()))
                rightButton.setImageDrawable(context.getDrawable(R.drawable.filledstar));
            else if (games.get(i).getSelectionType().equals(UserGameSelection.TOPLAY.name()))
                rightButton.setImageDrawable(context.getDrawable(R.drawable.toplay));
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.INVISIBLE);
        }
        tv.setText(games.get(i).getName());

        return vi;
    }
}
