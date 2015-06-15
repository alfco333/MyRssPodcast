package com.alfco.myrsspodcast.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfco.myrsspodcast.R;
import com.alfco.myrsspodcast.models.models.Songs;
import com.alfco.myrsspodcast.tools.Constants;
import com.alfco.myrsspodcast.tools.Interfaces.NetworkActions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

/**
 * Created by angel on 6/14/15.
 */
@EFragment(R.layout.fragment_detail)
public class FragmentDetail extends BaseFragment {


    /**********************************************************************************************
     *
     *                                      Simple variables
     *
     **********************************************************************************************/

    @StringRes(R.string.detail_error_playing)
    String playingError;


    /**********************************************************************************************
     *
     *                                      Special variables
     *
     **********************************************************************************************/

    Songs song;



    /**********************************************************************************************
     *
     *                                          UI Variables
     *
     **********************************************************************************************/


    @ViewById
    TextView tvChapterTitle;
    @ViewById
    ImageView ivChapterLogo;
    @ViewById
    WebView wvChapterVideo;



    /**********************************************************************************************
     *
     *                                      Fragment's lifecycle
     *
     **********************************************************************************************/

    @AfterViews
    void init(){
       initLogicalComponents();


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        wvChapterVideo.loadUrl("about:blank");
        wvChapterVideo.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        wvChapterVideo.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wvChapterVideo.onPause();

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
        Bundle args=getArguments();
        if(args!=null)
            song=new Gson().fromJson(args.getString(Constants.KEY_SONG),Songs.class);
        Picasso.with(getActivity()).load(song.getImage().getLink()).fit().into(ivChapterLogo);
        tvChapterTitle.setText(song.getTitle());
        configWebView();
        try {


            if (song.getUrl().getUrl() != null) {
                wvChapterVideo.loadUrl(song.getUrl().getUrl());
            }
        }catch(RuntimeException e)
        {
            toast(playingError);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void configWebView(){
        wvChapterVideo.getSettings().setJavaScriptEnabled(true);
        wvChapterVideo.clearHistory();
        wvChapterVideo.clearCache(true);
        wvChapterVideo.getSettings().setSupportZoom(true);
        wvChapterVideo.getSettings().setBuiltInZoomControls(true);
        wvChapterVideo.getSettings().setDisplayZoomControls(false);
        wvChapterVideo.setWebChromeClient(new WebChromeClient());
        wvChapterVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                try{
                    showLoading(true);
                    wvChapterVideo.setEnabled(false);
                }catch(Exception e){
                    leh("Problem loading new web page");
                }

                //TRy catching exception
            }
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                try{
                    showLoading(false);
                    wvChapterVideo.setEnabled(true);
                }catch(Exception e){
                    leh("Problem when finishing loading web page");
                }

            }
        });
    }


    /**********************************************************************************************
     *
     *                                     Listeners
     *
     **********************************************************************************************/




}
