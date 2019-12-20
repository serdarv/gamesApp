package com.vladimir.gamesapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vladimir.gamesapp.Model.LoginUserModel;
import com.vladimir.gamesapp.Model.UserModel;
import com.vladimir.gamesapp.Utils.SharedPreferencesUtils;

public class DBUser {

    //Variables

    private SQLiteDatabase db;
    private Context context;

    //Constructors

    public DBUser(Context context) {
        db = new DBInit(context).getWritableDatabase();
        this.context = context;
    }

    //Public methods

    public long saveUser(UserModel userModel) {

        ContentValues values = new ContentValues();
        values.put(DBConst.USER_COLUMN_FIRST_NAME, userModel.getFirst_name());
        values.put(DBConst.USER_COLUMN_LAST_NAME, userModel.getLast_name());
        values.put(DBConst.USER_COLUMN_EMAIL, userModel.getEmail());
        values.put(DBConst.USER_COLUMN_PASSWORD, userModel.getPassword());
        values.put(DBConst.USER_COLUMN_AGE, userModel.getAge());

        long result = db.insert(DBConst.USER_TABLE_NAME,null,values);

        return result;
    }

    public boolean loginUser(LoginUserModel loginUserModel) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DBConst.USER_COLUMN_ID,
                DBConst.USER_COLUMN_FIRST_NAME,
                DBConst.USER_COLUMN_LAST_NAME,
                DBConst.USER_COLUMN_EMAIL,
                DBConst.USER_COLUMN_PASSWORD,
                DBConst.USER_COLUMN_AGE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = DBConst.USER_COLUMN_EMAIL + " = ? and " + DBConst.USER_COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { loginUserModel.getEmail(),loginUserModel.getPassword()};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBConst.USER_COLUMN_ID + " DESC";

        Cursor cursor = db.query(
                DBConst.USER_TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        UserModel userModel = new UserModel();
        while(cursor.moveToNext()) {
            userModel.setId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_ID)));
            userModel.setFirst_name(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_FIRST_NAME)));
            userModel.setLast_name(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_LAST_NAME)));
            userModel.setEmail(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_EMAIL)));
            userModel.setPassword(cursor.getString(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_PASSWORD)));
            userModel.setAge(cursor.getInt(
                    cursor.getColumnIndexOrThrow(DBConst.USER_COLUMN_AGE)));
        }
        cursor.close();
        if(userModel.getFirst_name() != null){
            SharedPreferencesUtils.saveUser(userModel,context);
            return true;
        } else {
            return false;
        }
    }



}
