package com.example.sriharsha.salesassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class SalesRepWorkCreate extends AppCompatActivity {

    private int salesRepId;
    private String salesRepFirstName;
    private double salesTargetAmount;
    private int month,year;
    SalesRepWorkModel salesRepWorkInstance;

    SalesRepDatabaseHandler salesRepDatabaseHandlerInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_rep_work_profile);

        Bundle extras= getIntent().getExtras();
        if(extras==null){
            Toast.makeText(this, "There's No Matching Data in the Database", Toast.LENGTH_SHORT).show();
        }
        else {
            salesRepId = extras.getInt("salesRepId", 0);
            salesRepFirstName = extras.getString("salesRepName", "SalesRepFirstName");

        }

        TextView suggestionText = (TextView)findViewById(R.id.suggestionText);
        TextView salesRepReference=(TextView)findViewById(R.id.salesRepReference);
        TextView salesTargetText=(TextView)findViewById(R.id.tvSalesTarget);
        final EditText salesTargetValue=(EditText)findViewById(R.id.etSalesTarget);
        final DatePicker salesTargetDatePicker=(DatePicker)findViewById(R.id.datePickerSalesRep);
        Button submitButton=(Button)findViewById(R.id.submitButton);

        salesRepReference.setText(salesRepFirstName);

        salesRepDatabaseHandlerInstance= new SalesRepDatabaseHandler(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salesTargetAmount=Double.parseDouble(salesTargetValue.getText().toString());
                month = salesTargetDatePicker.getMonth()+1;
                year=salesTargetDatePicker.getYear();

                salesRepWorkInstance= new SalesRepWorkModel();

                for(int i=0;i<5;i++){
                salesRepWorkInstance.setSalesRepId(salesRepId);
                salesRepWorkInstance.setSalesTargetAmount(salesTargetAmount);
                salesRepWorkInstance.setsalesAchievedAmount(0.0);
                salesRepWorkInstance.setTargetMonth(month);
                salesRepWorkInstance.setTargetYear(year);
                }
                salesRepDatabaseHandlerInstance.addWorkSalesRep(salesRepWorkInstance);

                Intent backToProfile=new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(backToProfile);

            }
        });
    }


}
