package com.alfco.myrsspodcast;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.alfco.myrsspodcast.tools.Constants;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Timer;
import java.util.TimerTask;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    @ViewById
    ImageView ivLogo;

    @AfterViews
    void init(){
        Picasso.with(SplashActivity.this).load(R.drawable.background_splash).fit().into(ivLogo);

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
