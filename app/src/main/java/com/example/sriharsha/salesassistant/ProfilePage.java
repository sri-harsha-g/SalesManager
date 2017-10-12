package com.example.sriharsha.salesassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends AppCompatActivity {
        private TextView welcomeText;
        private ListView listView;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        welcomeText= (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome "+Login.firstName+"...!!");

        listView=(ListView)findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SalesRepDatabaseHandler salesRepDatabaseHandler= new SalesRepDatabaseHandler(this);
        SQLiteDatabase db= salesRepDatabaseHandler.getWritableDatabase();
        Cursor salesRepDetails= db.rawQuery("SELECT  * FROM salesrep", null);
        ProfilePageAdapter profilePageAdapter= new ProfilePageAdapter(this,salesRepDetails,0);
        listView.setAdapter(profilePageAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSalesRep= new Intent(getApplicationContext(),AddSalesRep.class);
                startActivity(intentSalesRep);
            }
        });
    }

}
