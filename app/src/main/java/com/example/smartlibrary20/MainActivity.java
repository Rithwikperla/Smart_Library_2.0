package com.example.smartlibrary20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mEmail, mPassword;
        Button mLoginbtn;
        TextView mCreateacc,mForgotPass;
        FirebaseAuth fAuth;

        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById((R.id.Password));
        mLoginbtn = findViewById(R.id.mLoginbtn);
        mCreateacc = findViewById(R.id.CreateAcc);
        mForgotPass = findViewById(R.id.Forgotpass);
        fAuth = FirebaseAuth.getInstance();

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required.");
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password must be 8 characters.");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(fAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(MainActivity.this, "Logged in Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, HomeScreen.class));
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Verify your email address.", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Error!"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mCreateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        mForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Resetpassword.class);
                startActivity(intent);
            }
        });


    }
}