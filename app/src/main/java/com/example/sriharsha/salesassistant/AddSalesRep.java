package com.example.sriharsha.salesassistant;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSalesRep extends AppCompatActivity {
    EditText firstNameSalesRep,secondNameSalesRep,emailSalesRep,phoneSalesRep,addressSalesRep;
    Button addRepresentativeBtn;
    SalesRepDatabaseHandler salesRepDatabaseHandler;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_rep);

        firstNameSalesRep= (EditText)findViewById(R.id.firstName_SalesRep);
        secondNameSalesRep=(EditText)findViewById(R.id.secondName_SalesRep);
        emailSalesRep=(EditText)findViewById(R.id.email_SalesRep);
        phoneSalesRep=(EditText)findViewById(R.id.phone_SalesRep);
        addressSalesRep=(EditText)findViewById(R.id.address_SalesRep);
        addRepresentativeBtn=(Button)findViewById(R.id.buttonAddRep);

        salesRepDatabaseHandler= new SalesRepDatabaseHandler(this);

        addRepresentativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String firstNameSR=firstNameSalesRep.getText().toString();
                    String secondNameSR= secondNameSalesRep.getText().toString();
                    String emailSR=emailSalesRep.getText().toString();
                    String phoneSR=phoneSalesRep.getText().toString();
                    String addressSR= addressSalesRep.getText().toString();

                SalesRepModel salesRepModel=new SalesRepModel();
                for (int i=0;i<5;i++){

                    salesRepModel.set_firstname(firstNameSR);
                    salesRepModel.set_secondname(secondNameSR);
                    salesRepModel.set_email(emailSR);
                    salesRepModel.set_phonenumber(phoneSR);
                    salesRepModel.set_address(addressSR);
                }
                salesRepDatabaseHandler.addSalesRep(salesRepModel);

                Toast.makeText(getApplicationContext(), firstNameSR+" is added to Reps List",Toast.LENGTH_LONG ).show();
                Intent backToProfile= new Intent(getApplicationContext(),ProfilePage.class);
                startActivity(backToProfile);

            }
        });


    }
}
