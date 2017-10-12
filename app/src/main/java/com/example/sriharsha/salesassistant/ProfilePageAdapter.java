package com.example.sriharsha.salesassistant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProfilePageAdapter extends CursorAdapter{

   public ProfilePageAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.profile_page_content,parent,false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView tvBody = (TextView) view.findViewById(R.id.salesrepName);

        ImageView ivWork=(ImageView)view.findViewById(R.id.work_SalesRep);
        ImageView ivAssessment =(ImageView)view.findViewById(R.id.assessment_SalesRep);

        final int id=cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        final String firstName = cursor.getString(cursor.getColumnIndexOrThrow("firstname"));
        final String email= cursor.getString(cursor.getColumnIndexOrThrow("email"));

        tvBody.setText(firstName);

        ivWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toSalesRepWorkCreate= new Intent(context,SalesRepWorkCreate.class);
                toSalesRepWorkCreate.putExtra("salesRepId",id);
                toSalesRepWorkCreate.putExtra("salesRepName",firstName);
                context.startActivity(toSalesRepWorkCreate);
            }
        });


        ivAssessment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSalesRepAssesmment= new Intent(context,SalesRepWorkAssessment.class);
                toSalesRepAssesmment.putExtra("SalesRepFirstName",firstName);
                toSalesRepAssesmment.putExtra("SalesRepId",id);
                context.startActivity(toSalesRepAssesmment);
            }
        });

    }
}
