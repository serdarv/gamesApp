package com.vladimir.gamesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vladimir.gamesapp.Api.BaseApi;
import com.vladimir.gamesapp.Model.LoginUserModel;
import com.vladimir.gamesapp.R;
import com.vladimir.gamesapp.Utils.FlowController;

public class MainActivity extends AppCompatActivity {

    //Bind views

    @BindView(R.id.email_et) EditText email;
    @BindView(R.id.password_et) EditText password;
    @BindView(R.id.login_btn) Button login_btn;
    @BindView(R.id.not_registered_tv) TextView register_link;

    //Private variables

    private LoginUserModel loginUserModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initVariables();

        if (isUserLoggedIn()) {

        } else {

        }
    }

    //Methods

    private boolean isUserLoggedIn() {

        //TODO - check if user is logged in than change root activity

        return true;
    }

    private void initVariables() {
        loginUserModel = new LoginUserModel();
    }

    //ButterKnife OnClick bindings

    @OnClick(R.id.login_btn) void login(){
        //TODO - after creating database check if email and password exist in database,than login

        FlowController.loginRedirect(this,HomeActivity.class);
    }

    @OnClick(R.id.not_registered_tv) void register() {
        FlowController.registerRedirect(this,RegistrationActivity.class);
    }

    //TODO - implement fragments,SQLite database,registration

}
