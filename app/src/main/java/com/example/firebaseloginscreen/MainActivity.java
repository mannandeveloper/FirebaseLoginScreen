package com.example.firebaseloginscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    private FirebaseUser mCurrentUser;
    private FirebaseAuth mFireBaseAuth;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = mFireBaseAuth.getCurrentUser();

        mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (mCurrentUser == null)
        {
            startActivity(new Intent(MainActivity.this, LogInActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.logOut)
        {
            mFireBaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, LogInActivity.class));
        }
        return true;
    }
}
