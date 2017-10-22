package com.example.dinesh.pinlockview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

public class MainActivity extends AppCompatActivity {
   public PinLockView pinLockView;
    public static String pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences(getString(R.string.Pref_File),MODE_PRIVATE);
        pincode=sharedPreferences.getString(getString(R.string.Pin),"");
        
        if(pincode.equals("")){
            //
        }else{
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,CheckActivity.class);
            startActivity(intent);
            finish();

        }

        pinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
      pinLockView.setPinLockListener(mPinLockListener);
        IndicatorDots mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        pinLockView.attachIndicatorDots(mIndicatorDots);


    }
    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Intent i=new Intent();
            i.putExtra("password",pin);
            i.setClass(MainActivity.this,ConfirmActivity.class);
            startActivity(i);
            finish();

        }

        @Override
        public void onEmpty() {
            Toast.makeText(MainActivity.this,"Pin Empty",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
           //
        }
    };
}
