package com.example.letra.nailstoremanagement.CustomerActivities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.letra.nailstoremanagement.CustomerActivities.Adapter.NailSkillRecyclerAdapter;
import com.example.letra.nailstoremanagement.CustomerActivities.Adapter.StaffRecyclerAdapter;
import com.example.letra.nailstoremanagement.Database.DatabaseHandler;
import com.example.letra.nailstoremanagement.Database.Employee;
import com.example.letra.nailstoremanagement.Database.Nail_Skill;
import com.example.letra.nailstoremanagement.R;

import java.util.List;

public class StaffActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    FloatingActionButton skipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_staff);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewStaff);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<Employee> employeeList = databaseHandler.getEmployees();

        StaffRecyclerAdapter staffRecyclerAdapter = new StaffRecyclerAdapter(this,employeeList);
        recyclerView.setAdapter(staffRecyclerAdapter);
        skipBtn = findViewById(R.id.skipBtn);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,new ConfirmationActivity().getClass());
        startActivity(intent);
    }
}
