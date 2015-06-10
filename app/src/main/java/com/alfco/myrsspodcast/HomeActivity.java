package com.alfco.myrsspodcast;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.alfco.myrsspodcast.fragments.FragmentHome;
import com.alfco.myrsspodcast.fragments.FragmentHome_;
import com.alfco.myrsspodcast.restclient.service.MyRssPodcastService;
import com.alfco.myrsspodcast.tools.Constants;
import com.alfco.myrsspodcast.tools.Interfaces.NetworkActions;
import com.octo.android.robospice.SpiceManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by angel on 6/6/15.
 */

@EActivity(R.layout.activity_home)
public class HomeActivity extends ActionBarActivity implements NetworkActions{

    /**********************************************************************************************
     *
     *                                      Simple variables
     *
     **********************************************************************************************/


    FragmentManager manager;

    /**********************************************************************************************
     *
     *                                      Special variables
     *
     **********************************************************************************************/

    private SpiceManager spiceManager=new SpiceManager(MyRssPodcastService.class);


    /**********************************************************************************************
     *
     *                                          UI Variables
     *
     **********************************************************************************************/


    @ViewById
    View loader;




    /**********************************************************************************************
     *
     *                                      Activity's lifecycle
     *
     **********************************************************************************************/

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(spiceManager.isStarted())
        spiceManager.shouldStop();
    }

    @Override
    public void onBackPressed() {

      super.onBackPressed();
    }

    @AfterViews
    void init(){
        initLogicalComponents();

    }


    /**********************************************************************************************
     *
     *                                      Public methods
     *
     **********************************************************************************************/




    /**********************************************************************************************
     *
     *                                      Private methods
     *
     **********************************************************************************************/
    private void initLogicalComponents(){
        FragmentHome home=new FragmentHome_();
        manager=getFragmentManager();
        manager.beginTransaction().add(R.id.container, home,Constants.TAG_FRAG_HOME).commit();

    }


    private void showLoading(boolean show){
        if(show)
            loader.setVisibility(View.VISIBLE);
        else
            loader.setVisibility(View.GONE);
    }


    /**********************************************************************************************
     *
     *                                     Listeners
     *
     **********************************************************************************************/


    @Override
    public void showLoader(boolean show) {
        showLoading(show);
    }


}
