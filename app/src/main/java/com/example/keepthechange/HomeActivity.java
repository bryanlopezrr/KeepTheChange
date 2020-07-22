package com.example.keepthechange;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class HomeActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


            mLayout = (LinearLayout) findViewById(R.id.linearLayoutTransaction);
//            mEditText = (EditText) findViewById(R.id.editTextTransaction);

//            TextView textView = new TextView(this);
//            textView.setText("Add");


        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
//                        Toast.makeText(MeditateActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_investments:
//                        Toast.makeText(MeditateActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(HomeActivity.this, InvestmentsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_account:
//                        Toast.makeText(MeditateActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(HomeActivity.this, AccountActivity.class);
                        startActivity(intent2);
                        break;
                }
            }

        });
    }


    public void randomTransaction(View view) {

        String someText = "bryanIsTestingthis\n";
        String randomText = "Mariana is awake";
        mLayout.addView(createNewTextView(randomText));

//        return "";
    }

    private TextView createNewTextView(String text) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("New text: " + text);
        return textView;
    }
}
