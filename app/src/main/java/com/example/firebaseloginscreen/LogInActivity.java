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
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity
{
    private FirebaseAuth mFireBaseAuth;
    private FirebaseUser mFirebaseUser;
    private EditText userName, password;
    private Button signUpButton;
    private TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initialization();

        mFireBaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFireBaseAuth.getCurrentUser();

        createAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LogInActivity.this, RegistrationActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                allowUserToLogin();
            }
        });
    }

    private void initialization()
    {
        userName = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        signUpButton = findViewById(R.id.sign_in);
        createAccount = findViewById(R.id.create_account);
    }

    private void allowUserToLogin()
    {
        final String emailAddress = userName.getText().toString();
        String loginPassword = password.getText().toString();

        if (emailAddress.isEmpty())
        {
            userName.setError("Please enter email address");
            userName.requestFocus();
        }
        else if (loginPassword.isEmpty())
        {
            password.setError("Please enter password");
            password.requestFocus();
        }
        else {
            mFireBaseAuth.signInWithEmailAndPassword(emailAddress, loginPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isComplete())
                            {
                                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(LogInActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
