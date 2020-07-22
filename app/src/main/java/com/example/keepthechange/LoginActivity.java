package com.example.keepthechange;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textViewForgotAccountDetails);

        mTextView.setClickable(true);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.citi.com'> Forgot account details? </a>";
        mTextView.setText(Html.fromHtml(text));

    }

    public void login(View view){
        Toast toast = Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 1200);
                            toast.show();

        Intent intent = new Intent( LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
