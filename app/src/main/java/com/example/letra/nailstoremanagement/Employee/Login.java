package com.example.letra.nailstoremanagement.Employee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letra.nailstoremanagement.MainActivity;
import com.example.letra.nailstoremanagement.R;

public class Login extends AppCompatActivity {
    EditText txtID;
    EditText txtPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtID = (EditText)findViewById(R.id.txtID);
        txtPass = (EditText)findViewById(R.id.txtPass);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    Intent intent = new Intent(Login.this, Payment.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean validate(){

        String id = txtID.getText().toString();
        String pass = txtPass.getText().toString();

        if(id.isEmpty() && pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Username and Password can't be empty",Toast.LENGTH_SHORT);
            return false;
        }
        else if (id.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Username can't be empty",Toast.LENGTH_SHORT);
            return false;
        }
        else if (pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Password can't be empty",Toast.LENGTH_SHORT);
            return false;
        }
        //else if()
        //{
        //for invalid user or password from database
        // }
        else
            return true;

    }
}
