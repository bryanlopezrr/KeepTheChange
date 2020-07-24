package com.example.keepthechange;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvestmentsActivity extends AppCompatActivity {


    public AnyChartView anyChartView;



    DatabaseReference database;


    float totalCash = 0.0f;
    float totalInvested = 6.37f;

    public String[] account = {"Cash","Investments"};
    public float[] amount = {25.6f,89.3f};

//    List<Integer> amount = new ArrayList<>();


    public int globalCash;
    public int globalInvested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investments);
        anyChartView = findViewById(R.id.any_chart_view);


//        percent.setText("7 - 10%"); //medium orange
//        percent.setText("12 - 15%"); //high red

//        changeInvestmentRisk();

//        public void readDB() {
            database = FirebaseDatabase.getInstance().getReference("cash");
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
                                float value = Float.parseFloat(r);
                                totalCash += value;
//                                totalInvested = 6.37f;

//                                Toast.makeText(InvestmentsActivity.this, String.valueOf(totalCash), Toast.LENGTH_LONG).show();

                            }
                        }
                        TextView cashTV = findViewById(R.id.textViewCash);
                        TextView investedTV = findViewById(R.id.textViewInvested);
                        investedTV.setText(String.valueOf("Invested: " + totalInvested));
                        cashTV.setText(String.valueOf("Cash: " + totalCash));
//
//                        globalCash = (int) totalCash;
//                        globalInvested = (int) totalInvested;

//                        Toast toast = Toast.makeText(InvestmentsActivity.this, String.valueOf(globalCash), Toast.LENGTH_SHORT);
//                        toast.show();

                    }


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

//        }
//
//        readDB();

        setUpChart();

        BottomNavigationView bottomNavBar = findViewById(R.id.navigationBar);
        Menu menu = bottomNavBar.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
//                        Toast.makeText(MeditateActivity.this, "Reports!!!!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InvestmentsActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_investments:
//                        Toast.makeText(MeditateActivity.this, "Meditate!!!!", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(InvestmentsActivity.this, InvestmentsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.nav_account:
//                        Toast.makeText(MeditateActivity.this, "Monitor!!!!", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(InvestmentsActivity.this, AccountActivity.class);
                        startActivity(intent2);
                        break;
                }
            }

        });



    }

    public void changeInvestmentRisk1(View v){

        Toast toast = Toast.makeText(InvestmentsActivity.this, "Risk Level Updated", Toast.LENGTH_SHORT);
        toast.show();
        //add other logic to update the backend

        TextView percent = findViewById(R.id.textViewRiskPercent);
        percent.setTextColor(Color.GREEN);
        percent.setText("3 - 5%"); //low green

    }


    public void changeInvestmentRisk2(View v){

        Toast toast = Toast.makeText(InvestmentsActivity.this, "Risk Level Updated", Toast.LENGTH_SHORT);
        toast.show();
        //add other logic to update the backend

        TextView percent = findViewById(R.id.textViewRiskPercent);
        percent.setTextColor(Color.rgb(255,165, 0));
        percent.setText("7 - 10%"); //medium orange


    }

    public void changeInvestmentRisk3(View v){

        Toast toast = Toast.makeText(InvestmentsActivity.this, "Risk Level Updated", Toast.LENGTH_SHORT);
        toast.show();
        //add other logic to update the backend

        TextView percent = findViewById(R.id.textViewRiskPercent);
        percent.setTextColor(Color.RED);
        percent.setText("12 - 15%"); //high red

    }



    public void setUpChart(){

//        amount.add(globalCash);
//        amount.add(globalInvested);
//
//        Toast toast = Toast.makeText(InvestmentsActivity.this, String.valueOf(globalInvested), Toast.LENGTH_SHORT);
//        toast.show();


        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i = 0; i < account.length; i++){
            dataEntries.add(new ValueDataEntry(account[i], amount[i]));
        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);

    }
}
