package com.example.keepthechange;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view){
        Toast toast = Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 1350);
                            toast.show();

        Intent intent = new Intent( LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
