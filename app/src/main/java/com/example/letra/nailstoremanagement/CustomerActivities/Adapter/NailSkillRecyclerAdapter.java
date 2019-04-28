package com.example.letra.nailstoremanagement.CustomerActivities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letra.nailstoremanagement.CustomerActivities.ServiceActivity;
import com.example.letra.nailstoremanagement.CustomerActivities.StaffActivity;
import com.example.letra.nailstoremanagement.Database.Nail_Skill;
import com.example.letra.nailstoremanagement.MainActivity;
import com.example.letra.nailstoremanagement.R;

import java.util.List;

public class NailSkillRecyclerAdapter extends RecyclerView.Adapter<NailSkillRecyclerAdapter.NailSkillViewHolder> implements View.OnClickListener{
    private  List<Nail_Skill> nail_skills;
    private AppCompatActivity appCompatActivity;
    public NailSkillRecyclerAdapter(AppCompatActivity appCompatActivity, List<Nail_Skill> nail_skills) {
        this.appCompatActivity = appCompatActivity;
        this.nail_skills = nail_skills;
    }

    @NonNull
    @Override
    public NailSkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nail_skill_card_view, parent, false);
        itemView.setOnClickListener(this);
        return new NailSkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NailSkillViewHolder nailSkillViewHolder, int position) {
        Nail_Skill nail_skill = nail_skills.get(position);

        Resources resources = appCompatActivity.getResources();
        final int resourceId = resources.getIdentifier(nail_skill.getImg_src(), "drawable", appCompatActivity.getPackageName());
        nailSkillViewHolder.nail_skill_image_view.setImageResource(resourceId);


        nailSkillViewHolder.nail_skill_name_text_view.setText(nail_skill.getName());
        nailSkillViewHolder.nail_skill_description_text_view.setText(nail_skill.getDescription());

    }

    @Override
    public int getItemCount() {
        return nail_skills.size();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = appCompatActivity.getSharedPreferences("Customer",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView nailSkillName = v.findViewById(R.id.nail_skill_name);
        editor.putString("service",nailSkillName.getText().toString());
        editor.commit();
        Log.d("NailSkillAdapter", "onClick: " + nailSkillName.getText().toString());

        Intent intent = new Intent(appCompatActivity.getBaseContext(),new StaffActivity().getClass());
        appCompatActivity.startActivity(intent);
    }

    public static class NailSkillViewHolder extends RecyclerView.ViewHolder{
        private ImageView nail_skill_image_view;
        private TextView  nail_skill_name_text_view;
        private TextView nail_skill_description_text_view;

        public NailSkillViewHolder(View itemView) {
            super(itemView);
            nail_skill_image_view = (ImageView) itemView.findViewById(R.id.nail_skill_image_view);
            nail_skill_name_text_view = (TextView) itemView.findViewById(R.id.nail_skill_name);
            nail_skill_description_text_view = (TextView) itemView.findViewById(R.id.nail_skill_description);
        }
    }
}
