package com.example.sriharsha.salesassistant;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

public class SalesRepWorkAssesmentAdapter extends CursorAdapter {

    int salesRepId;

    public SalesRepWorkAssesmentAdapter(Context context, Cursor c, boolean autoRequery, int salesRepId) {
        super(context, c, autoRequery);
        this.salesRepId = salesRepId;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }


}
