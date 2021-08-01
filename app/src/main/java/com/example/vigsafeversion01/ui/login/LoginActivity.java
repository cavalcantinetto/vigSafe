package com.example.vigsafeversion01.ui.login;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vigsafeversion01.DbManager;
import com.example.vigsafeversion01.FetchDataFoodList;
import com.example.vigsafeversion01.Manager;
import com.example.vigsafeversion01.R;
import com.example.vigsafeversion01.UsersManager;
import com.example.vigsafeversion01.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setEnabled(true);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadingProgressBar.setVisibility(View.VISIBLE);
                DbManager db = new DbManager(getApplicationContext());
                Cursor response = db.isUser(usernameEditText.getText().toString().toLowerCase(),
                        passwordEditText.getText().toString());
                if (response != null) {
                    response.moveToFirst();
                    String function = response.getString(3);
                    String name = response.getString(1);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    updateUiWithUser(function, name);



                } else {
                    showLoginFailed("Login Failed!");
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void updateUiWithUser(String response, String name) {
        String welcome = getString(R.string.welcome) + name + "!";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
        if (response.equals("MANAGER")) {
            Intent intent = new Intent(getApplicationContext(), Manager.class);
            startActivity(intent);
        } else if (response.equals("SUPERADMIN")){
            Intent intent = new Intent(this, UsersManager.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(getApplicationContext(), FetchDataFoodList.class);
            startActivity(intent);
        }


    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}