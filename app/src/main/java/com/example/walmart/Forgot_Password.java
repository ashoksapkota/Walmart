package com.example.walmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    EditText get_email;
    Button confirm_email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        get_email = findViewById(R.id.email);
        confirm_email = findViewById(R.id.btn_forgotPass);
        firebaseAuth = FirebaseAuth.getInstance();

        confirm_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetEmail();
            }
        });
    }

    private void GetEmail()
    {
        String email = get_email.getText().toString().trim();
        if (email.isEmpty()){
            get_email.setError("Email is Required ");
            get_email.requestFocus();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful()){
                    Toast.makeText(Forgot_Password.this, "Check Email", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Forgot_Password.this, "Try Again ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}