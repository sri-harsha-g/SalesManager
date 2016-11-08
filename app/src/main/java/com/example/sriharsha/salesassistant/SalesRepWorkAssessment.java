package com.example.sriharsha.salesassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SalesRepWorkAssessment extends AppCompatActivity {

    TextView tvSalesRepFirstName,tvSalesRepWorkGreeting;
    ListView lvSalesRepWork;
    String salesRepFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_rep_work_assessment);

        Bundle receivedSalesRepParameters= getIntent().getExtras();

        if(receivedSalesRepParameters==null){
            Toast.makeText(this, "There's No Matching Data in the Database", Toast.LENGTH_SHORT).show();
        }
        else {
            salesRepFirstName = receivedSalesRepParameters.getString("SalesRepFirstName","SalesRepFirstName");
            int salesRepId= receivedSalesRepParameters.getInt("SalesRepId",0);
            Toast.makeText(this, "Received "+salesRepFirstName+"'s Data", Toast.LENGTH_SHORT).show();
        }

        tvSalesRepFirstName=(TextView)findViewById(R.id.salesRepFirstName);
        tvSalesRepWorkGreeting=(TextView)findViewById(R.id.salesRepGreeting);
        lvSalesRepWork=(ListView)findViewById(R.id.salesWorkRecords);

        tvSalesRepFirstName.setText(salesRepFirstName);


    }
}
