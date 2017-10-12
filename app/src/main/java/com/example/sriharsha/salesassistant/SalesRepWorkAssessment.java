package com.example.sriharsha.salesassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SalesRepWorkAssessment extends AppCompatActivity {

    TextView tvSalesRepFirstName,tvSalesRepWorkGreeting;
    ListView lvSalesRepWork;
    static String salesRepFirstName;
    Button performanceBtn;
    int salesRepId;
    SalesRepWorkAssesmentAdapter workAssesmentAdapter;
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
            salesRepId= receivedSalesRepParameters.getInt("SalesRepId",0);
        }

        tvSalesRepFirstName=(TextView)findViewById(R.id.salesRepFirstName);
        tvSalesRepWorkGreeting=(TextView)findViewById(R.id.salesRepGreeting);
        lvSalesRepWork=(ListView)findViewById(R.id.salesWorkRecords);
        tvSalesRepFirstName.setText(salesRepFirstName);
        performanceBtn=(Button)findViewById(R.id.btnPerformance);

        SalesRepDatabaseHandler salesRepDatabaseHandler= new SalesRepDatabaseHandler(this);
        SQLiteDatabase db= salesRepDatabaseHandler.getWritableDatabase();
        Cursor assignmentDetails= db.rawQuery("SELECT * FROM salesrepworkprofile WHERE salesrepid = " +salesRepId ,null);
        workAssesmentAdapter= new SalesRepWorkAssesmentAdapter(this,assignmentDetails,0);
        lvSalesRepWork.setAdapter(workAssesmentAdapter);

        performanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPerformance= new Intent(getApplicationContext(),PerformanceChart.class);
                goToPerformance.putExtra("SalesRepId",salesRepId);
                startActivity(goToPerformance);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        workAssesmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
