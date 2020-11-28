package com.taskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taskdemo.model.Filter;

public class MainActivity extends AppCompatActivity {

    Button uploadBTN,viewBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadBTN=findViewById(R.id.uploadBTN);
        viewBTN=findViewById(R.id.viewBTN);
        Filter.college="ALL";
        Filter.company="ALL";
        Filter.interview="ALL";
        Filter.job="ALL";
        Filter.topic="ALL";

        uploadBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,UploadActivity.class);
                startActivity(i);
            }
        });

        viewBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(i);
            }
        });

    }
}