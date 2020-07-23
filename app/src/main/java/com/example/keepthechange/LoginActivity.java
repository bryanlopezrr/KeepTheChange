package com.example.keepthechange;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobile.auth.ui.AuthUIConfiguration;
import com.amazonaws.mobile.auth.ui.SignInUI;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {
//
//    TextView mTextView;
//    TextView mTVpassword;
//    TextView mTVusername;

    private final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails result) {
                switch (result.getUserState()) {
                    case SIGNED_IN: {
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);

                        Toast toast = Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();


                        startActivity(i);
                        break;
                    }
                    case SIGNED_OUT: {
                        showSignIn();
                        break;
                    }
                    default: {
                        AWSMobileClient.getInstance().signOut();
                        showSignIn();
                        break;
                    }

                }
            }


            @Override
            public void onError(Exception e) {
                //error just in case we crash
            }


        });


        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(final AWSStartupResult awsStartupResult) {
                AuthUIConfiguration config =
                        new AuthUIConfiguration.Builder()
                                .userPools(true)  // true? show the Email and Password UI
                                .logoResId(R.drawable.app_logo) // Change the logo
                                .backgroundColor(Color.rgb(19,181,255)) // Change the backgroundColor
                                .isBackgroundColorFullScreen(false) // Full screen backgroundColor the backgroundColor full screenff
                                .fontFamily("sans-serif-condensed") // Apply sans-serif-light as the global font
                                .canCancel(true)
                                .build();
                SignInUI signinUI = (SignInUI) AWSMobileClient.getInstance().getClient(LoginActivity.this, SignInUI.class);
                signinUI.login(LoginActivity.this, HomeActivity.class).authUIConfiguration(config).execute();
            }
        }).execute();
    }

        private void showSignIn() {
            try {
                AWSMobileClient.getInstance().showSignIn(this,
                        SignInUIOptions.builder().nextActivity((HomeActivity.class)).build());
            }
            catch (Exception e){

            }

        }


//
//        mTextView = (TextView) findViewById(R.id.textViewForgotAccountDetails);
//
//        //These are the values for signin in with parse
//        mTVpassword = findViewById(R.id.textViewPassword);
//        mTVusername = findViewById(R.id.textViewUserName);
//
//
//        mTextView.setClickable(true);
//        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
//        String text = "<a href='http://www.citi.com'> Forgot account details? </a>";
//        mTextView.setText(Html.fromHtml(text));

//    }

//
//    public void login(View view) {
//
//        //Intent intent = new Intent( LoginActivity.this, ReportsActivity.class);
//        //startActivity(intent);
//        if (TextUtils.isEmpty(mTVpassword.getText()) && TextUtils.isEmpty(mTVusername.getText())) {
//            mTVusername.setError("Username is required");
//            mTVpassword.setError("Password is required");
//        } else if (TextUtils.isEmpty(mTVusername.getText())) {
//            mTVusername.setError("Username is required");
//
//        } else if (TextUtils.isEmpty(mTVpassword.getText())) {
//            mTVpassword.setError("Password is required");
//        } else {
//            mTVpassword.setError("Invalid username or password");
//            mTVusername.setError("Invalid username or password");
//
//        }
//
//        Toast toast = Toast.makeText(LoginActivity.this, "Welcome back !", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
//        toast.show();
//
//        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//        startActivity(intent);
//        finish();
//
//    }

}
