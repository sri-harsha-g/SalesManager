package com.example.sriharsha.salesassistant;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SalesRepWorkAssesmentAdapter extends CursorAdapter implements DialogInterface.OnDismissListener {
    Double achievedSales;
    SalesRepDatabaseHandler salesRepDatabaseHandler;
    Bundle salesRepBundle;
    String salesRepFirsName;

    public SalesRepWorkAssesmentAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        onContentChanged();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.assessment_content, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView tvYear = (TextView) view.findViewById(R.id.salesTargetYear);
        TextView tvMonth = (TextView) view.findViewById(R.id.salesTargetMonth);
        TextView tvSalesTargetAmount = (TextView) view.findViewById(R.id.salesTargetAmount);
        TextView tvSalesAchievedAmount = (TextView) view.findViewById(R.id.salesAchieved);
        ImageView ivSalesRecordUpdate = (ImageView) view.findViewById(R.id.salesUpdateActionImage);

        final int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        final int salesRepId = cursor.getInt(cursor.getColumnIndexOrThrow("salesrepid"));
        final int salesTargetMonth = cursor.getInt(cursor.getColumnIndexOrThrow("targetassignedmonth"));
        final int salesTargetYear = cursor.getInt(cursor.getColumnIndexOrThrow("targetassignedyear"));
        final double salesTargetAmount = cursor.getDouble(cursor.getColumnIndexOrThrow("salestargetamount"));
        final double salesAchievedAmount = cursor.getDouble(cursor.getColumnIndexOrThrow("salesachievedamount"));

        tvYear.setText(Integer.toString(salesTargetYear));
        tvMonth.setText(this.getMonth(salesTargetMonth));
        tvSalesTargetAmount.setText(Double.toString(salesTargetAmount));
        tvSalesAchievedAmount.setText(Double.toString(salesAchievedAmount));

        ivSalesRecordUpdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("UPDATE RECORDS");
                alert.setMessage("Update Achieved Sales");

                // Set an EditText view to get user input
                final EditText input = new EditText(context);
                alert.setView(input);

                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        achievedSales = Double.parseDouble(input.getText().toString());
                        salesRepDatabaseHandler = new SalesRepDatabaseHandler(context);
                        SQLiteDatabase db = salesRepDatabaseHandler.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("salesachievedamount", achievedSales);
                        db.update("salesrepworkprofile", contentValues, "_id=?", new String[]{Integer.toString(id)});

                        Intent updateList= new Intent(context,SalesRepWorkAssessment.class);
                        updateList.putExtra("SalesRepId",salesRepId);
                        context.startActivity(updateList);

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

                alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {


                    }
                });

            }
        });

    }

    @Override
    protected void onContentChanged() {
        super.onContentChanged();
        notifyDataSetChanged();
    }


    private String getMonth(int monthNumber){
        String monthString;
        switch (monthNumber){
            case 1:  monthString = "Jan";
                break;
            case 2:  monthString = "Feb";
                break;
            case 3:  monthString = "Mar";
                break;
            case 4:  monthString = "Apr";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "Jun";
                break;
            case 7:  monthString = "Jul";
                break;
            case 8:  monthString = "Aug";
                break;
            case 9:  monthString = "Sep";
                break;
            case 10: monthString = "Oct";
                break;
            case 11: monthString = "Nov";
                break;
            case 12: monthString = "Dec";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return  monthString;
    }
}
