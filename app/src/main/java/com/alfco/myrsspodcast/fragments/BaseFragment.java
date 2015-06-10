package com.alfco.myrsspodcast.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import com.alfco.myrsspodcast.restclient.service.MyRssPodcastService;
import com.alfco.myrsspodcast.tools.Interfaces.NetworkActions;
import com.alfco.myrsspodcast.tools.Util;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by angel on 6/7/15.
 */
public abstract class BaseFragment extends Fragment {


    private NetworkActions networkListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            networkListener=(NetworkActions)activity;
        }catch (ClassCastException e){
            leh("Must implement the interface");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        if(spiceManager.isStarted())
            spiceManager.shouldStop();
    }

    protected SpiceManager spiceManager = new SpiceManager(MyRssPodcastService.class);

    private boolean enableOwnLog = true;
    protected void disableLog(){
        enableOwnLog = false;
    }

    protected void le(String message){
        if (enableOwnLog){
            Util.le(message);
        }
    }

    protected void leh(String message){
        if (enableOwnLog){
            Util.le(message, true);
        }
    }

    protected void li(String message){
        if (enableOwnLog){
            Util.li(message);
        }
    }

    protected void lih(String message){
        if (enableOwnLog){
            Util.li(message, true);
        }
    }

    protected void ld(String message){
        if (enableOwnLog){
            Util.ld(message);
        }
    }

    protected void ldh(String message){
        if (enableOwnLog){
            Util.ld(message, true);
        }
    }

    protected void lw(String message){
        if (enableOwnLog){
            Util.lw(message);
        }
    }

    protected void lwh(String message){
        if (enableOwnLog){
            Util.lw(message, true);
        }
    }

    protected boolean isEmpty(String string){
        return Util.isEmpty(string);
    }

    protected void showView(View view){
        Util.showView(view);
    }

    protected void showView(int id){
        Util.showView(getView().findViewById(id));
    }

    protected void hideView(View view){
        Util.hideView(view);
    }

    protected void hideView(int id){
        Util.hideView(getView().findViewById(id));
    }

    protected View find(int id){
        return getView().findViewById(id);
    }

    protected void toast(String text){
        Util.showToast(getActivity(), text);
    }

    protected void hideKb(){
        Util.hideKeyboard(getActivity());
    }

    protected SpiceManager getSpiceManager(){
        return spiceManager;
    }

    protected void showLoading(boolean show){
        if(networkListener!=null)
            networkListener.showLoader(show);
    }



}
