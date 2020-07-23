package com.example.keepthechange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.auth.AWSCognitoIdentityProvider;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;


public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);



        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


//        TextView name = findViewById(R.id.textViewName);
//        String username = AWSMobileClient.getInstance().getUsername();
//        name.setText(username);

        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
//                        Toast.makeText(MeditateActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_investments:
//                        Toast.makeText(MeditateActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(AccountActivity.this, InvestmentsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_account:
//                        Toast.makeText(MeditateActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(AccountActivity.this, AccountActivity.class);
                        startActivity(intent2);
                        break;
                }
            }


        });
    }

    public void logout(View view){

        AWSMobileClient.getInstance().signOut();

//        switchTo(LoginActivity.class);

        IdentityManager.getDefaultIdentityManager().signOut();
//
//        Toast toast = Toast.makeText(AccountActivity.this, "Goodbye !", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 1350);
//        toast.show();
//
        Intent intent = new Intent( AccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
