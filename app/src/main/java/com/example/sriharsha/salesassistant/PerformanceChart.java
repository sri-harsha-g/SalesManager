package com.example.sriharsha.salesassistant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PerformanceChart extends AppCompatActivity {
    Bundle receievedSalesRepDetails;
    BarChart assesmentBarChart;
  //  String month;
    ArrayList<BarEntry> yDataAchievedSales= new ArrayList<>();
    ArrayList<BarEntry> yDataTargetSales= new ArrayList<>();
    ArrayList<BarDataSet> bardatasets= null;
    ArrayList<String>xData= new ArrayList<>();
    int salesRepId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_chart);
        receievedSalesRepDetails = getIntent().getExtras();
        salesRepId = receievedSalesRepDetails.getInt("SalesRepId", 0);
        assesmentBarChart = (BarChart) findViewById(R.id.performanceBarchart);

        SalesRepDatabaseHandler salesRepDatabaseHandler= new SalesRepDatabaseHandler(this);
        SQLiteDatabase db= salesRepDatabaseHandler.getWritableDatabase();
        Cursor assignmentDetails= db.rawQuery("SELECT * FROM salesrepworkprofile WHERE salesrepid = " +salesRepId ,null);
        int SalesTargetsCount=assignmentDetails.getCount();

        Float YvaluesTargettedSales[]= new Float[SalesTargetsCount];
        Float YvaluesachievedSales[]= new Float[SalesTargetsCount];
        String XvaluesYear[]= new String[SalesTargetsCount];
        String XvaluesMonth[]= new String[SalesTargetsCount];

        assignmentDetails.moveToFirst();
            for(int i=0;i<SalesTargetsCount;i++) {
                YvaluesTargettedSales[i]=assignmentDetails.getFloat(2);
                XvaluesMonth[i] =this.getMonth((assignmentDetails.getInt(3)));
                XvaluesYear[i] =assignmentDetails.getString(4);
                YvaluesachievedSales[i] = assignmentDetails.getFloat(5);
                assignmentDetails.moveToNext();
            }
//        for(int j=0;j< YvaluesTargettedSales.length;j++){
//            yDataTargetSales.add(new BarEntry(YvaluesTargettedSales[j],j));
//            }
        for(int i=0;i<YvaluesachievedSales.length;i++){

            yDataAchievedSales.add(new BarEntry(YvaluesachievedSales[i],i));

        }


        BarDataSet barDataSet = new BarDataSet(yDataAchievedSales,"Achieved Sales");
        //BarDataSet barDataSet2= new BarDataSet(yDataTargetSales,"Target Sales");

        for (int i=0;i<XvaluesMonth.length;i++){

            xData.add(XvaluesYear[i]+","+XvaluesMonth[i]);
        }

        bardatasets = new ArrayList<>();
        bardatasets.add(barDataSet);
//      bardatasets.add(barDataSet2);

        BarData barData= new BarData(xData, getDataSet());
        assesmentBarChart.setData(barData);
        assesmentBarChart.setDescription("Sales Performance");
        assesmentBarChart.setTouchEnabled(true);
    }

    private ArrayList<BarDataSet> getDataSet(){
        //BarDataSet to store the datasets
        ArrayList<BarDataSet> dataSets=null;

        SalesRepDatabaseHandler salesRepDatabaseHandler= new SalesRepDatabaseHandler(this);
        SQLiteDatabase db= salesRepDatabaseHandler.getWritableDatabase();
        Cursor assignmentDetails= db.rawQuery("SELECT * FROM salesrepworkprofile WHERE salesrepid = " +salesRepId ,null);
        //Get the total Sales Target Count for the SalesRep Id
        int SalesTargetsCount=assignmentDetails.getCount();

        //Arrays to store values of the Target Sales and Achieved Sales for Each Month
        Float YvaluesTargettedSales[]   = new Float[SalesTargetsCount];
        Float YvaluesachievedSales[]    = new Float[SalesTargetsCount];
        String XvaluesYear[]            = new String[SalesTargetsCount];
        String XvaluesMonth[]           = new String[SalesTargetsCount];

        //Move to the first row of the cursor to store the values in the Arrays
        assignmentDetails.moveToFirst();
        for(int i=0;i<SalesTargetsCount;i++) {
            YvaluesTargettedSales[i]=assignmentDetails.getFloat(2);//Store Target Sale Value
            XvaluesMonth[i] =this.getMonth((assignmentDetails.getInt(3)));//Store the Target Sale Month
            XvaluesYear[i] =assignmentDetails.getString(4);//Store the Target Sale Year
            YvaluesachievedSales[i] = assignmentDetails.getFloat(5);//Store the Achieved Sale Value
            assignmentDetails.moveToNext();//Move to next row in the Cursor
        }
        ArrayList<BarEntry> valueSetTargetSales = new ArrayList<>();//ArrayList Of Target Sales
        ArrayList<BarEntry>valueSetAchievedSales= new ArrayList<>();//ArrayList of Achieved Sales

        for(int i=0;i<YvaluesTargettedSales.length;i++)
            valueSetTargetSales.add(new BarEntry(YvaluesTargettedSales[i],i));
        for(int j=0;j<YvaluesachievedSales.length;j++)
            valueSetAchievedSales.add(new BarEntry(YvaluesachievedSales[j],j));

        BarDataSet barDataSet1 = new BarDataSet(valueSetTargetSales,"Target Sales");
        barDataSet1.setColor(Color.rgb(0,155,0));
        BarDataSet barDataSet2= new BarDataSet(valueSetAchievedSales,"Achieved Sales");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets= new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
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
            default: monthString = "***";
                break;
       }
        return  monthString;
    }
}
