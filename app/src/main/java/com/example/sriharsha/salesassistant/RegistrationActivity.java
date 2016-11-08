package com.example.sriharsha.salesassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstNameEt,secondNameEt,emailEt,phoneEt,passwordEt,confirmPwdEt;
    private Button registerBtn;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // get Instance of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        firstNameEt=(EditText)findViewById(R.id.firstNameEt);
        secondNameEt=(EditText)findViewById(R.id.secondNameEt);
        emailEt=(EditText)findViewById(R.id.emailIdEt);
        phoneEt=(EditText)findViewById(R.id.phoneEt);
        passwordEt=(EditText)findViewById(R.id.passwordEt);
        confirmPwdEt=(EditText)findViewById(R.id.confirmPwEt);
        registerBtn=(Button)findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName= firstNameEt.getText().toString();
                String secondName= secondNameEt.getText().toString();
                String emailId= emailEt.getText().toString();
                String phoneNumber= phoneEt.getText().toString();
                String passWord=passwordEt.getText().toString();
                String confirmPwd=confirmPwdEt.getText().toString();

                if(passWord.equals(confirmPwd))
                {
                    loginDataBaseAdapter.insertEntry(firstName,secondName,emailId,phoneNumber,passWord);
                    loginDataBaseAdapter.close();
                    Toast.makeText(getApplicationContext(), "Registration Successful",Toast.LENGTH_LONG ).show();
                    Intent intTakeToLogin= new Intent(getApplicationContext(),Login.class);
                    startActivity(intTakeToLogin);
                }
                else

                {

                    Toast.makeText(getApplicationContext(), "Your Passwords don't match",Toast.LENGTH_LONG ).show();
                }
            }
        });

    }
}
