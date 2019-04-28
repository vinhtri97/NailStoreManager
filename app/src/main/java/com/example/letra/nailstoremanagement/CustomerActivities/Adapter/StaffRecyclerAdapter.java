package com.example.letra.nailstoremanagement.CustomerActivities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letra.nailstoremanagement.CustomerActivities.ConfirmationActivity;
import com.example.letra.nailstoremanagement.CustomerActivities.StaffActivity;
import com.example.letra.nailstoremanagement.Database.Employee;
import com.example.letra.nailstoremanagement.R;

import java.util.List;

public class StaffRecyclerAdapter extends RecyclerView.Adapter<StaffRecyclerAdapter.StaffViewHolder> implements View.OnClickListener {

    private List<Employee> employees;
    private StaffActivity staffActivity;
    public StaffRecyclerAdapter(StaffActivity appCompatActivity, List<Employee> employees) {
        this.staffActivity = appCompatActivity;
        this.employees = employees;
    }

    @NonNull
    @Override
    public StaffRecyclerAdapter.StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_card_view, parent, false);
        itemView.setOnClickListener(this);
        return new StaffRecyclerAdapter.StaffViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffRecyclerAdapter.StaffViewHolder staffViewHolder, int position) {
        Employee employee = employees.get(position);

        Resources resources = staffActivity.getResources();

        final int resourceId = resources.getIdentifier(employee.getAvatar_src(), "drawable", staffActivity.getPackageName());
        Log.d("onBindViewHolder", employee.getAvatar_src());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bitmap bitmap = BitmapFactory.decodeResource(resources,resourceId,options);
        staffViewHolder.staff_image_view.setImageBitmap(bitmap);
        staffViewHolder.staff_image_view.setTag(resourceId);

        staffViewHolder.staff_name_text_view.setText(employee.getFirstName());
        staffViewHolder.staff_phone_text_view.setText(employee.getPhone());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = staffActivity.getSharedPreferences("Customer",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        ImageView staffImageView = v.findViewById(R.id.staff_image_view);
        editor.putInt("staffImageViewID",(int) staffImageView.getTag());

        TextView staffName = v.findViewById(R.id.staff_name_txtView);
        editor.putString("staffName",staffName.getText().toString());
        editor.commit();

        Intent intent = new Intent(staffActivity.getBaseContext(),new ConfirmationActivity().getClass());
        staffActivity.startActivity(intent);
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder{
        private ImageView staff_image_view;

        public ImageView getStaff_image_view() {
            return staff_image_view;
        }

        private TextView staff_name_text_view;
        private TextView staff_phone_text_view;

        public StaffViewHolder(View itemView) {
            super(itemView);
            staff_image_view = (ImageView) itemView.findViewById(R.id.staff_image_view);
            staff_name_text_view = (TextView) itemView.findViewById(R.id.staff_name_txtView);
            staff_phone_text_view = (TextView) itemView.findViewById(R.id.staff_phone_txtView);
        }
    }
}