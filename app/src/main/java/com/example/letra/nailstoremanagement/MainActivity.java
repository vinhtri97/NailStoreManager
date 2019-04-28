package com.example.letra.nailstoremanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.letra.nailstoremanagement.CustomerActivities.InformationActivity;
import com.example.letra.nailstoremanagement.Database.DatabaseHandler;
import com.example.letra.nailstoremanagement.Employee.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        databaseHandler.getNailSkills();
        setContentView(R.layout.activity_main);
        //DatabaseHandler dbHandler = new DatabaseHandler(this);
        Button customerBtn = (Button) findViewById(R.id.customerBtn);
        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });

        Button employeeBtn = (Button) findViewById(R.id.employeeBtn);
        employeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CreditCardRecognition.class);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
