package com.example.letra.nailstoremanagement.CustomerActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.letra.nailstoremanagement.CustomerActivities.Adapter.NailSkillRecyclerAdapter;
import com.example.letra.nailstoremanagement.Database.DatabaseHandler;
import com.example.letra.nailstoremanagement.Database.Nail_Skill;
import com.example.letra.nailstoremanagement.R;

import java.util.List;

public class ServiceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCustomerService);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<Nail_Skill> nail_skills = databaseHandler.getNailSkills();

        NailSkillRecyclerAdapter nailSkillAdapter = new NailSkillRecyclerAdapter(this,nail_skills);
        recyclerView.setAdapter(nailSkillAdapter);
    }
}
