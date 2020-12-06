package com.meisterk.tghtr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUserActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnRegister;
    EditText etEmail;
    EditText etPassword;
    EditText etPasswordRepeat;
    private final String tag = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        //Views zuweisen

        btnRegister = findViewById(R.id.newUser_btn_newUser);
        etEmail = findViewById(R.id.newUser_et_email);
        etPassword = findViewById(R.id.newUser_et_password);
        etPasswordRepeat = findViewById(R.id.newUser_et_password2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
            }
        });
    }

    //Passwort und Wiederholung auf Gleichheit prüfen
    private boolean samePassword(String password, String repeat){
        Log.d(tag, Boolean.toString(password.equals(repeat)));
        Log.d(tag, password + " " + repeat);
        return password.equals(repeat);
    }

    private void newUser(){
        mAuth = FirebaseAuth.getInstance();
        if(samePassword(etPassword.getText().toString(),etPasswordRepeat.getText().toString())){
            mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(task -> {
                Log.d(tag, "User created" + task.getResult());
                Toast.makeText(NewUserActivity.this, getResources().getText(R.string.SUCCESS_new_user), Toast.LENGTH_SHORT).show();
                finish();
            });
        }else {
            Toast.makeText(this, getResources().getText(R.string.ERROR_different_password), Toast.LENGTH_SHORT).show();
        }
        
    }
}