package com.example.walmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText edit_name, edit_last_name, edit_email, edit_password, edit_confirm_password;
    Button btn_login1, btn_signup1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_login1 = findViewById(R.id.btn_login1);
        btn_signup1 = findViewById(R.id.btn_signup1);
        edit_name = findViewById(R.id.name);
        edit_last_name = findViewById(R.id.Lname);
        edit_email = findViewById(R.id.email);
        edit_password = findViewById(R.id.password);
        edit_confirm_password = findViewById(R.id.Cpassword);
        firebaseAuth = firebaseAuth.getInstance();


        btn_login1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent btn_login1inent = new Intent(Signup.this, Login.class);
                startActivity(btn_login1inent);
            }
        });

        btn_signup1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString().trim();
                String password = edit_password.getText().toString().trim();
                String confirm_password = edit_confirm_password.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(confirm_password))
                {
                    Toast.makeText(Signup.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                }

                if (password.length()<6)
                {
                    Toast.makeText(Signup.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }

                if (confirm_password.equals(password))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Intent signupIntent=new Intent(getApplicationContext(), Login.class);
                                startActivity(signupIntent);
                                Toast.makeText(Signup.this, "Signup Complete", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Signup.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }

}