package com.example.letra.nailstoremanagement.Employee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.letra.nailstoremanagement.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends AppCompatActivity {
    EditText txtCardNumber;
    EditText txtExpiration;
    EditText txtCVV;
    EditText txtName;
    Button btnContinue;
    Button btnScan;
    ImageView capturedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        txtCardNumber = (EditText) findViewById(R.id.txtCardNumber);
        txtExpiration = (EditText) findViewById(R.id.txtSecurityCode);
        txtCVV = (EditText) findViewById(R.id.txtCVV);
        txtName = (EditText) findViewById(R.id.txtName);
        capturedImageView = (ImageView) findViewById(R.id.capturedImage);

        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnScan = (Button) findViewById(R.id.btnScan);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //redirect
            }
        });
        btnScan.setOnClickListener(new ScanBtnOnClickListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
            capturedImageView.setImageBitmap(imageBitmap);
            CreditCardRecognition creditCardRecognition = new CreditCardRecognition(this, imageBitmap, txtCardNumber, txtExpiration, txtCVV);
            creditCardRecognition.execute();
        }

    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private class ScanBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(Payment.this,
                            "com.example.letra.nailstoremanagement.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
