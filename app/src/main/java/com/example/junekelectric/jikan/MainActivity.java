package com.example.junekelectric.jikan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    TextView mTimeTextView;
    TextView mResultTextView;
    Timer mTimer;
    Handler mHandler;
    int mTime;
    int mDefaultTime;

    public void start(View v){

        if (mTimer == null){
            Toast.makeText(this, mDefaultTime + "秒あててねっ", Toast.LENGTH_LONG).show();
            mTime = mDefaultTime;

            mTimer = new Timer(false);
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTime--;
                            Log.d("timeの数字＝", String.valueOf(mTime));
                        }
                    });
                }
            }, 0, 1000);
        }
    }

    public void stop(View v){

        if(mTimer != null) {
            mTimer.cancel();
            mTimer = null;

            if (mTime == 0) {
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText("おめでとー！");
                mResultTextView.setTextColor(Color.rgb(0, 51, 255));
                    Intent intent = new Intent(this, Success .class);
                    intent.putExtra("minutes", 10);
                    startActivity(intent);
                }
            } else if (mTime > 0) {
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText(("残念！（汗"));
                mResultTextView.setTextColor(Color.rgb(255, 51, 102));
                    Intent intent = new Intent(this, Fail .class);
                    intent.putExtra("minutes", 10);
                    startActivity(intent);
            } else {
                mTimeTextView.setText(String.valueOf(mTime));
                mResultTextView.setText(("残念！（汗"));
                mResultTextView.setTextColor(Color.rgb(255, 204, 102));
                    Intent intent = new Intent(this, Fail .class);
                    intent.putExtra("minutes", 10);
                    startActivity(intent);
            }
        }


    public void three(View v){
        mTime = 3;
    }

    public void five(View v){
        mTime = 5;
    }

    public void ten(View v){
        mTime = 10;
    }

    public void fifteen(View v){
        mTime = 15;
    }

    public void twenty(View v){
        mTime = 20;
    }

    public void twentyfive(View v){
        mTime = 25;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = (TextView) findViewById(R.id.textView);
        mResultTextView = (TextView) findViewById(R.id.textView2);
        mHandler = new Handler();
        Intent intent = getIntent();
        mDefaultTime = intent.getIntExtra("minutes", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}
