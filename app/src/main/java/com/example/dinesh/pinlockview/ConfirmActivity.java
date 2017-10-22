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

public class ConfirmActivity extends AppCompatActivity {

    public PinLockView pinLockView;
    String password1;
    public TextView textView;
    IndicatorDots mIndicatorDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        textView.setText("Confirm Password");

        password1=getIntent().getStringExtra("password");
        pinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        pinLockView.setPinLockListener(mPinLockListener);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        pinLockView.attachIndicatorDots(mIndicatorDots);




    }

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            if(password1.equals(pin)){
                Toast.makeText(ConfirmActivity.this,"Password matched and set",Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences=ConfirmActivity.this.getSharedPreferences(getString(R.string.Pref_File),MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(getString(R.string.Pin),pin);
                editor.commit();
                Intent i=new Intent();
                i.setClass(ConfirmActivity.this,FirstActivity.class);
                startActivity(i);
                finish();



            }else{
                pinLockView.resetPinLockView();
                Toast.makeText(ConfirmActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();

            }



        }

        @Override
        public void onEmpty() {
            Toast.makeText(ConfirmActivity.this,"Pin Empty",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            //
        }
    };
}
