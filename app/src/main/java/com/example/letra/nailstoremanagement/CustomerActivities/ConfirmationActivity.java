package com.example.letra.nailstoremanagement.CustomerActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.letra.nailstoremanagement.MainActivity;
import com.example.letra.nailstoremanagement.R;

public class ConfirmationActivity extends AppCompatActivity {
    TextView confirmName;
    TextView confirmDate;
    TextView confirmService;
    TextView confirmStaff;
    ImageView confirmStaffImageView;
    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirmation);
        confirmName = findViewById(R.id.confirmName);
        confirmDate = findViewById(R.id.confirmDate);
        confirmService = findViewById(R.id.confirmService);
        confirmStaff = findViewById(R.id.confirmStaff);
        confirmBtn = findViewById(R.id.confirmBtn);
        confirmStaffImageView = findViewById(R.id.confirmImageView);

        fullFillConfirmInfo();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),new MainActivity().getClass());
                startActivity(intent);
            }
        });
    }

    private void fullFillConfirmInfo() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Customer",Context.MODE_PRIVATE);
        confirmName.setText(sharedPreferences.getString("fullName","N/A"));
        confirmDate.setText(sharedPreferences.getString("date","N/A"));
        confirmService.setText(sharedPreferences.getString("service","N/A"));
        confirmStaff.setText(sharedPreferences.getString("staffName","N/A"));

        int confirmStaffID = sharedPreferences.getInt("staffImageViewID",-1);

        Log.d("confirmStaffIDCheck", Integer.toString(confirmStaffID));
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        if (confirmStaffID == -1)
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.confirmation_icon,options);
        else
            bitmap = BitmapFactory.decodeResource(getResources(),confirmStaffID,options);
        confirmStaffImageView.setImageBitmap(bitmap);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("fullName");
        editor.remove("date");
        editor.remove("service");
        editor.remove("staffName");
        editor.remove("staffImageViewID");
    }
}
