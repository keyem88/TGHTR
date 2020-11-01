package com.meisterk.tghtr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String email;
    private  String password;
    Button btnLogin;
    EditText etEmail;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        //Log.i("mAuth", mAuth.getUid());

        //Views zuordnen
        btnLogin = findViewById(R.id.login_btn_login);
        etEmail = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);

        btnLogin.setOnClickListener(v -> {

            signIn(etEmail.getText().toString(), etPassword.getText().toString());
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //User is already signed in?

        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            setContentView(R.layout.activity_main);
        }
    }

    protected void signIn(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                currentUser = mAuth.getCurrentUser();
                                assert currentUser != null;
                                updateUI(currentUser);
                            } else{
                                Log.i("signIn","Login Error", task.getException());
                            }

                        }

                    });
    }

    protected void updateUI(FirebaseUser user){
        if(user != null){
            setContentView(R.layout.activity_main);
        }else{
            Toast.makeText(this, "Nutzer nicht bekannt", Toast.LENGTH_SHORT).show();
        }
    }
}