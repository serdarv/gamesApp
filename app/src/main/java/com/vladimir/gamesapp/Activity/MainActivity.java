package com.vladimir.gamesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vladimir.gamesapp.Database.DBUser;
import com.vladimir.gamesapp.Model.LoginUserModel;
import com.vladimir.gamesapp.R;
import com.vladimir.gamesapp.Utils.FlowController;
import com.vladimir.gamesapp.Utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    //Bind views

    @BindView(R.id.email_et) EditText email;
    @BindView(R.id.password_et) EditText password;
    @BindView(R.id.login_btn) Button login_btn;
    @BindView(R.id.not_registered_tv) TextView register_link;

    //Private variables

    private LoginUserModel loginUserModel;
    private DBUser userDB;

    //DB initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userDB = new DBUser(this);
        initVariables();

        if (isUserLoggedIn()) {
            FlowController.loginRedirect(this,HomeActivity.class);
        }
    }

    //Methods

    private boolean isUserLoggedIn() {
        if(SharedPreferencesUtils.getUser(getApplicationContext()) != null) {
            return true;
        } else {
            return false;
        }
    }

    private void initVariables() {
        loginUserModel = new LoginUserModel();
    }

    //ButterKnife OnClick bindings

    @OnClick(R.id.login_btn) void login(){

        LoginUserModel loginUserModel = new LoginUserModel(email.getText().toString(),password.getText().toString());
        if (userDB.loginUser(loginUserModel)){
            FlowController.loginRedirect(this,HomeActivity.class);
        } else {
            Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.not_registered_tv) void register() {
        FlowController.showRegister(this,RegistrationActivity.class);
    }

    //TODO - implement fragments,SQLite database,registration

}
