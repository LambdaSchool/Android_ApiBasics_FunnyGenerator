package com.lambdaschool.funnygenerator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_advice:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String result = AdviceDAO.getRandomAdvice();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTextMessage.setText(result);
                                }
                            });
                        }
                    }).start();
                    return true;
                case R.id.navigation_insult:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String result = InsultDAO.getRandomInsult();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTextMessage.setText(result);
                                }
                            });
                        }
                    }).start();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
