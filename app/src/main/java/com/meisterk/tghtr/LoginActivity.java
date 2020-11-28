package com.meisterk.tghtr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Button btnLogin;
    Button btnNewUser;
    EditText etEmail;
    EditText etPassword;
    private final String tag = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        //Log.i("mAuth", mAuth.getUid());

        //Views zuordnen
        btnLogin = findViewById(R.id.login_btn_login);
        btnNewUser = findViewById(R.id.login_btn_newAccount);
        etEmail = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);

        //Login Button
        btnLogin.setOnClickListener(v -> {
            signIn(etEmail.getText().toString(), etPassword.getText().toString());
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //User is already signed in?
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Log.d(tag, currentUser.getEmail());
            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Main2Activity.class);
            i.putExtra("currentUser", currentUser);
            startActivity(i);
        }
    }

    //LoginMethode
    protected void signIn(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            currentUser = mAuth.getCurrentUser();
                            assert currentUser != null;
                            updateUI(currentUser);
                        }
                    });
    }
    protected void updateUI(FirebaseUser user){
        if(user != null){
            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Nutzer nicht bekannt", Toast.LENGTH_SHORT).show();
        }
    }

    protected void register(){
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
    }
}