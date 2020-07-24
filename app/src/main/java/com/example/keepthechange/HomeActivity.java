package com.example.keepthechange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.anychart.core.grids.Map;
import com.anychart.scales.Linear;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class HomeActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    int whatColortoShade = 0;

    DatabaseReference database;


    public void readDB() {
        database = FirebaseDatabase.getInstance().getReference("transaction");
        // Read from the database
        database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange (DataSnapshot dataSnapshot){
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    if (dataSnapshot.getValue() != null) {

                        {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String r = "" + snapshot.getValue();
                                TextView transactionRead = createNewTextView(r);
                                mLayout.addView(transactionRead);
                            }
                        }
                    }

                }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        readDB();


            mLayout = (LinearLayout) findViewById(R.id.linearLayoutTransaction);
//            mEditText = (EditText) findViewById(R.id.editTextTransaction);

//            TextView textView = new TextView(this);
//            textView.setText("Add");


        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_investments:
                        Intent intent1 = new Intent(HomeActivity.this, InvestmentsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_account:
                        Intent intent2 = new Intent(HomeActivity.this, AccountActivity.class);
                        startActivity(intent2);
                        break;
                }
            }

        });
    }


    public void randomTransaction(View view) {

        String randomText = randomizer();

        if(randomText == ""){
            randomText = "Other" + "\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t" +  "3.50";
        }

        TextView newTransaction = createNewTextView(randomText);

        String idDb = database.push().getKey();
        String transaction = randomText;

        database.child(idDb).setValue(transaction);
        mLayout.addView(newTransaction);

    }

    public String randomizer(){
        //This function will return a random string that has a transaction for a certain amount

        String[] items = {"Pizza","Burger", "Uber", "Sushi", "Udemy",
                "T-Shirt", "Amazon", "Netflix", "Movies", "Fortnite", "Massage",
                "Milkshake", "Burrito", "Pants", "Spotify", "iCloud"};

        try {
            int randomNum = ThreadLocalRandom.current().nextInt(0, items.length + 1);

//         float[] cost = {2.5, 1.37, 4.89, 10.99};
            Random randomCost = new Random(); // creating Random object
            float totalCost = randomCost.nextFloat() + (float) randomNum;

            double investedAmount = Math.ceil(totalCost) - totalCost;

            Double toBeTruncated = new Double(investedAmount);

            Double truncatedDouble = BigDecimal.valueOf(toBeTruncated)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            updateCash(truncatedDouble);

            //Right here I will need to capture the value and send it to the cloud db for easy access and manipulation
            //Toast toast = Toast.makeText(HomeActivity.this, "Invested amount: " + String.valueOf(truncatedDouble) + " ", Toast.LENGTH_LONG);

            Toast toast = Toast.makeText(HomeActivity.this, "Invested amount: " + String.valueOf(truncatedDouble) + " ", Toast.LENGTH_LONG);
            toast.show();

            String cost = String.valueOf(String.format("%.2f", totalCost));
            return (items[randomNum] + "\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t" +  cost);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(HomeActivity.this, "Oops, something went wrong!", Toast.LENGTH_LONG);
            return "";
        }

    }


    private TextView createNewTextView(String text) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);


        if((whatColortoShade % 2) == 0){

            textView.setBackgroundColor(getResources().getColor(R.color.grayColor));
        }
        else{
            textView.setBackgroundColor(getResources().getColor(R.color.whiteColor));
        }

        whatColortoShade++;
        return textView;
    }


    public void updateCash(Double dvalue){
        DatabaseReference cashTable;

        cashTable = FirebaseDatabase.getInstance().getReference("cash");

        String stringDouble = String.valueOf(dvalue);

        String ceilNum = cashTable.push().getKey();
//        String floatCash = ceilNum;

        cashTable.child(ceilNum).setValue(stringDouble);


    }


}
