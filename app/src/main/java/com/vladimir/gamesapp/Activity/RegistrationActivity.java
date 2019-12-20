package com.vladimir.gamesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.vladimir.gamesapp.Database.DBUser;
import com.vladimir.gamesapp.Model.UserModel;
import com.vladimir.gamesapp.R;
import com.vladimir.gamesapp.Utils.FlowController;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.email_et)
    EditText email;
    @BindView(R.id.first_name_et)
    EditText first_name;
    @BindView(R.id.last_name_et)
    EditText last_name;
    @BindView(R.id.age_et)
    EditText age;
    @BindView(R.id.password_et)
    EditText password;
    @BindView(R.id.repeat_password_et)
    EditText repeat_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_btn) void register() {
        if(email.getText().toString().isEmpty()) {
            email.setError(getString(R.string.error_string));
        } else if (!isValidEmail(email.getText().toString())) {
            email.setError(getString(R.string.error_string));
        } else if (first_name.getText().toString().isEmpty()) {
            first_name.setError(getString(R.string.error_string));
        } else if (last_name.getText().toString().isEmpty()) {
            last_name.setError(getString(R.string.error_string));
        } else if (age.getText().toString().isEmpty()) {
            age.setError(getString(R.string.error_string));
        } else if (password.getText().toString().isEmpty()) {
            password.setError(getString(R.string.error_string));
        } else if (repeat_password.getText().toString().isEmpty()) {
            repeat_password.setError(getString(R.string.error_string));
        } else if (!password.getText().toString().equals(repeat_password.getText().toString())) {
            repeat_password.setError(getString(R.string.diff_passwords));
        } else {
            UserModel userModel = new UserModel();
            userModel.setEmail(email.getText().toString());
            userModel.setFirst_name(first_name.getText().toString());
            userModel.setLast_name(last_name.getText().toString());
            userModel.setAge(Integer.valueOf(age.getText().toString()));
            userModel.setPassword(password.getText().toString());

            DBUser db = new DBUser(this);

            if(db.saveUser(userModel) > 0) {
                Toast.makeText(this,getString(R.string.user_created),Toast.LENGTH_SHORT).show();
                FlowController.showLogin(this);
            } else {
                Toast.makeText(this,getString(R.string.db_error),Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
