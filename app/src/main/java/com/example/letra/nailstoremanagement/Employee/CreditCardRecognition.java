package com.example.letra.nailstoremanagement.Employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.letra.nailstoremanagement.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreditCardRecognition extends AsyncTask {
    private String cardNumber;
    private String expirationDate;
    private String securityCode;

    private EditText txtCardNumber;
    private EditText txtExpiration;
    private EditText txtCVV;
    private Context context;

    private Bitmap scannedBitMapImage;
    private ProgressDialog mDialog;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }


    public CreditCardRecognition(Context context, Bitmap scannedBitMapImage, EditText txtCardNumber, EditText txtExpiration, EditText txtCVV) {
        this.context = context;
        this.txtCardNumber = txtCardNumber;
        this.txtExpiration = txtExpiration;
        this.txtCVV = txtCVV;
        this.scannedBitMapImage = scannedBitMapImage;
    }

    public String RemoveSpace(String s) {
        String withoutspaces = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                withoutspaces += s.charAt(i);

        }
        return withoutspaces;

    }

    //find security code
    private String FindSecurityCode(List<String> recognisedText){
        String result ="";
        //delete space
        for(String text : recognisedText){
            text = RemoveSpace(text);
            if (android.text.TextUtils.isDigitsOnly(text) && text.length() == 3)
                result = text;
        }
        return result;
    }

    //find card number
    private String FindCardNumber(List<String> recognisedText){
        String result ="";
        //delete space
        for(String text : recognisedText){
            text = RemoveSpace(text);
            if (android.text.TextUtils.isDigitsOnly(text) && text.length() > 3)
                result = text;
        }
        return result;
    }

    //find expiration date
    private String FindExpirationDate(List<String> recognisedText){
        String result ="";
        //delete space
        for(String text : recognisedText){
            text = RemoveSpace(text);
            if (text.contains("/") && text.length() == 5)
                result = text;
        }
        return result;
    }
    private void recognizeCreditCard() {
        FirebaseApp.initializeApp(this.context);
        FirebaseVisionImage image;
        //image = FirebaseVisionImage.fromBitmap(BitmapFactory.decodeResource(this.context.getResources(),R.drawable.credit_card_sample));
        //scannedBitMapImage = BitmapFactory.decodeResource(this.context.getResources(),R.drawable.credit_card_sample);
        image = FirebaseVisionImage.fromBitmap(scannedBitMapImage);
        Log.d("BitmapInfo", "height: " + scannedBitMapImage.getHeight());
        Log.d("BitmapInfo", "width: " + scannedBitMapImage.getWidth());
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();
        Task<FirebaseVisionText> result = detector.detectInImage(image)

                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        //calculate and show time taken to get results
                        List<FirebaseVisionText.Block> blocks = firebaseVisionText.getBlocks();
                        StringBuilder recognisedText = new StringBuilder("");
                        List<String> recognisedTextList = new ArrayList<>();
                        for (int i = 0; i < blocks.size(); i++) {
                            recognisedText.append(blocks.get(i).getText() +"\n");

                            recognisedTextList.addAll(Arrays.asList(blocks.get(i).getText().split("\n")));
                        }
                        Log.d("recognize text",recognisedText.toString());
                        //cardTxtView.setText(recognisedText.get(0));
                        cardNumber = FindCardNumber(recognisedTextList);
                        securityCode = FindSecurityCode(recognisedTextList);
                        expirationDate = FindExpirationDate(recognisedTextList);

                        txtCardNumber.setText(cardNumber);
                        txtExpiration.setText(expirationDate);
                        txtCVV.setText(securityCode);
                    }})
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.d("FAIL", e.getMessage());
                    }
                });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mDialog = new ProgressDialog(this.context);
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        mDialog.dismiss();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        recognizeCreditCard();
        return null;
    }
}
