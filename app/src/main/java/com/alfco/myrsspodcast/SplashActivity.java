package com.alfco.myrsspodcast;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alfco.myrsspodcast.tools.Constants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.Timer;
import java.util.TimerTask;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends ActionBarActivity {

    @AfterViews
    void init(){
        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                Intent intent;
                //FIXME override back button to avoid back when loading session

                intent = new Intent(SplashActivity.this, HomeActivity_.class);



                startActivity(intent);
                finish();
            }

        };

        Timer timer = new Timer();
        timer.schedule(task,Constants.pause);

    }
}
