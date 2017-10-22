package com.example.dinesh.pinlockview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

public class CheckActivity extends AppCompatActivity {
    public PinLockView pinLockView;
    TextView textView;
    public static String pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        textView.setText("Enter Pin");
        SharedPreferences sharedPreferences=CheckActivity.this.getSharedPreferences(getString(R.string.Pref_File),MODE_PRIVATE);
        pincode=sharedPreferences.getString(getString(R.string.Pin),"");




        pinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        pinLockView.setPinLockListener(mPinLockListener);
        IndicatorDots mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        pinLockView.attachIndicatorDots(mIndicatorDots);

    }


    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            if(pincode.equals(pin)){
                Intent i=new Intent();
                i.setClass(CheckActivity.this,FirstActivity.class);
                startActivity(i);
                finish();
            }else
            {
                pinLockView.resetPinLockView();
                Toast.makeText(CheckActivity.this,"Wrong Password! Try Again",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onEmpty() {
            Toast.makeText(CheckActivity.this,"Pin Empty",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            //
        }
    };
}
