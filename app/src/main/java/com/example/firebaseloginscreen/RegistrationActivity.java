package com.example.firebaseloginscreen;

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

public class RegistrationActivity extends AppCompatActivity
{
    private TextView account;
    private EditText mEmailAddress, password;
    private Button signUpButton;
    private FirebaseAuth mFireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFireBaseAuth = FirebaseAuth.getInstance();

        initialization();

        account.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegistrationActivity.this, LogInActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                createNewAccount();
            }
        });
    }

    private void initialization()
    {
        account = findViewById(R.id.account);
        mEmailAddress = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sign_up);
    }

    private void createNewAccount()
    {
        String emailAddress = mEmailAddress.getText().toString();
        String loginPassword = password.getText().toString();

        if (emailAddress.isEmpty())
        {
            mEmailAddress.setError("Please enter email address");
            mEmailAddress.requestFocus();
        }
        else if (loginPassword.isEmpty())
        {
            password.setError("Please enter password");
            password.requestFocus();
        }
        else
        {
            mFireBaseAuth.createUserWithEmailAndPassword(emailAddress, loginPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isComplete())
                            {
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
