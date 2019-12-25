package com.vladimir.gamesapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vladimir.gamesapp.Api.Model.GameModel;
import com.vladimir.gamesapp.Enums.UserGameSelection;
import com.vladimir.gamesapp.Model.LoginUserModel;
import com.vladimir.gamesapp.Model.UserModel;
import com.vladimir.gamesapp.Utils.SharedPreferencesUtils;

import java.util.ArrayList;

public class DBGame {

    //Variables

    private SQLiteDatabase db;
    private Context context;

    //Constructors

    public DBGame(Context context) {
        db = new DBInit(context).getWritableDatabase();
        this.context = context;
    }

    //Public methods

    public long saveGame(GameModel gameModel, UserGameSelection selection) {

        ContentValues values = new ContentValues();
        values.put(DBConst.GAME_COLUMN_NAME, gameModel.getName());
        values.put(DBConst.GAME_COLUMN_STORYLINE, gameModel.getStoryline());
        values.put(DBConst.GAME_COLUMN_SUMMARY, gameModel.getSummary());
        values.put(DBConst.GAME_COLUMN_URL, gameModel.getUrl());
        values.put(DBConst.GAME_COLUMN_TYPE, selection.name());
        values.put(DBConst.GAME_COLUMN_GAME_ID, gameModel.getGame_id());

        long result = db.insert(DBConst.GAME_TABLE_NAME,null,values);

        return result;
    }

    public ArrayList<GameModel> getGames() {

        ArrayList<GameModel> gameModels = new ArrayList<>();

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBConst.GAME_COLUMN_ID + " DESC";

        Cursor cursor = db.query(
                DBConst.GAME_TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while (cursor.moveToNext()) {
            GameModel gameModel = new GameModel();
            gameModel.setId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_ID)));
            gameModel.setGame_id(cursor.getInt(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_GAME_ID)));
            gameModel.setName(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_NAME)));
            gameModel.setStoryline(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_STORYLINE)));
            gameModel.setSummary(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_SUMMARY)));
            gameModel.setUrl(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_URL)));

            gameModels.add(gameModel);
        }

        return gameModels;
    }

    public ArrayList<GameModel> getGames(UserGameSelection gameSelection) {

        ArrayList<GameModel> gameModels = new ArrayList<>();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DBConst.GAME_COLUMN_ID,
                DBConst.GAME_COLUMN_NAME,
                DBConst.GAME_COLUMN_SUMMARY,
                DBConst.GAME_COLUMN_STORYLINE,
                DBConst.GAME_COLUMN_URL,
                DBConst.GAME_COLUMN_TYPE,
                DBConst.GAME_COLUMN_GAME_ID
        };

        // Filter results WHERE
        String selection = DBConst.GAME_COLUMN_TYPE + " = ?";
        String[] selectionArgs = { gameSelection.name() };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBConst.GAME_COLUMN_ID + " DESC";

        Cursor cursor = db.query(
                DBConst.GAME_TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while(cursor.moveToNext()) {
            GameModel gameModel = new GameModel();
            gameModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_ID)));
            gameModel.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_URL)));
            gameModel.setSummary(cursor.getString(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_SUMMARY)));
            gameModel.setStoryline(cursor.getString(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_STORYLINE)));
            gameModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_NAME)));
            gameModel.setSelectionType(cursor.getString(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_TYPE)));
            gameModel.setGame_id(cursor.getInt(cursor.getColumnIndexOrThrow(DBConst.GAME_COLUMN_GAME_ID)));

            gameModels.add(gameModel);
        }
        cursor.close();

        return gameModels;
    }

    public boolean deleteGame(int id)
    {
        return db.delete(DBConst.GAME_TABLE_NAME, DBConst.GAME_COLUMN_ID + "=?", new String[]{""+id}) > 0;
    }

}
