package com.example.walmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button login, signup;
    TextView forgot_text;
    FirebaseAuth firebaseAuth;
    EditText login_email, login_password;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forgot_text= findViewById(R.id.forgot_text);
        login_email = findViewById(R.id.Lemail);
        login_password = findViewById(R.id.Lpassword);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        remember = findViewById(R.id.checkbox);
        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent loginintent = new Intent(Login.this, Home.class);
                            startActivity(loginintent);
                            Toast.makeText(Login.this, "Login complete", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent signupintent = new Intent(Login.this, Signup.class);
                startActivity(signupintent);
            }
        });

        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent forgot_pass_intent = new Intent(Login.this, Forgot_Password.class);
                startActivity(forgot_pass_intent);
            }
        });

    }
}