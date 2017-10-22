package com.example.dinesh.pinlockview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    public static String pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        SharedPreferences sharedPreferences=FirstActivity.this.getSharedPreferences(getString(R.string.Pref_File),MODE_PRIVATE);
        pincode=sharedPreferences.getString(getString(R.string.Pin),"");

        Toast.makeText(FirstActivity.this,pincode,Toast.LENGTH_SHORT).show();


    }
}
