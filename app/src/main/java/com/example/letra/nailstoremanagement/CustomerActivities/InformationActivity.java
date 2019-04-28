package com.example.letra.nailstoremanagement.CustomerActivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.letra.nailstoremanagement.MainActivity;
import com.example.letra.nailstoremanagement.R;

import java.util.Calendar;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {
    Button continueBtn;
    EditText nameEditText;
    EditText phoneEditText;
    DatePicker datePicker;
    private boolean checkValidInput(){
        return  !nameEditText.getText().toString().matches("") && !phoneEditText.getText().toString().matches("");
    }
    private String getDate(){
        String day = Integer.toString(datePicker.getDayOfMonth());
        String month = Integer.toString(datePicker.getMonth());
        String year =  Integer.toString(datePicker.getYear());
        return month + "--" + day + "--" + year;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        continueBtn = findViewById(R.id.continueBtn);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        datePicker = findViewById(R.id.datePicker);
        continueBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (checkValidInput()) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("Customer",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fullName",nameEditText.getText().toString());
            editor.putString("phoneNumber",phoneEditText.getText().toString());
            editor.putString("date", getDate());
            editor.commit();
            Intent intent = new Intent(InformationActivity.this, ServiceActivity.class);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder dialog=new AlertDialog.Builder(this);
            dialog.setMessage("Please enter name and phone number");
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
    }
}
