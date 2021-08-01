package com.example.vigsafeversion01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class UsersManager extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton, deleteButton;
    ProgressBar loadingProgressBar;
    Spinner functionSpinner;
    TextView userDelete, functionDelete;
    ImageView trash;

    ConstraintLayout usermanager;
    LayoutInflater layoutdeleteuserrow;
    LinearLayout deletionuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manager);

        usernameEditText = findViewById(R.id.usernamemanager);
        passwordEditText = findViewById(R.id.passwordmanager);
        loginButton = findViewById(R.id.loginmanager);
        loadingProgressBar = findViewById(R.id.loadingmanager);
        functionSpinner = findViewById(R.id.functionmanager);
        loginButton.setEnabled(true);
        deleteButton = findViewById(R.id.buttonDeleteUser);
        usermanager = findViewById(R.id.usermanager);
        deletionuser = findViewById(R.id.deletionuser);
        layoutdeleteuserrow = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                DbManager db = new DbManager(getApplicationContext());
                String username = usernameEditText.getText().toString().toLowerCase();
                String password = passwordEditText.getText().toString();
                String function = functionSpinner.getSelectedItem().toString();


                if ((!username.equals("")) && (password.length() > 5)) {
                    if (db.checkUser(username)) {
                        showLoginFailed(getString(R.string.userexist));
                        loadingProgressBar.setVisibility(View.INVISIBLE);
                    } else {
                        String response = db.addUser(username, password, function);
                        registeredUser(response);

                        loadingProgressBar.setVisibility(View.INVISIBLE);
                    }

                } else {
                    showLoginFailed(getString(R.string.nulluserorpassword));
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbManager db = new DbManager(getApplicationContext());
                String username = usernameEditText.getText().toString().toLowerCase();
                String function = functionSpinner.getSelectedItem().toString();

                if (!username.equals("") && db.checkUser(username)) {

                    @SuppressLint("ResourceType") View deleteView = layoutdeleteuserrow.inflate(R.layout.deleteuserrow, null, false);
                    deletionuser.addView(deleteView);
                    userDelete = findViewById(R.id.userdelete);
                    functionDelete = findViewById(R.id.functiondelete);
                    trash = findViewById(R.id.eraser);
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    userDelete.setText(username);
                    functionDelete.setText(function);
                    loadingProgressBar.setVisibility(View.INVISIBLE);

                    trash.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Integer res = db.deleteUser(username);
                                if (res > 0) {
                                    deleteusuarioSuccess();

                                } else {
                                    deleteusuarioFail();
                                }
                            }

                    });


                } else if (username.equals("")) {
                    usernameEditText.setText("insert a user");
                    usernameEditText.requestFocus();

                } else {
                    deleteusuarioFail();
                }

            }
        });
    }

    private void registeredUser(String db) {
        usernameEditText.setText("");
        passwordEditText.setText("");
        Toast.makeText(this, db, Toast.LENGTH_LONG).show();
        }


    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void deleteusuarioSuccess() {
        Toast.makeText(this, "Successfully deleted!", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        }

    private void deleteusuarioFail() {
        Toast.makeText(this, getString(R.string.somthingWrong), Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}