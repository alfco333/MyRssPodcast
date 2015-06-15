package com.alfco.myrsspodcast.fragments;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alfco.myrsspodcast.R;
import com.alfco.myrsspodcast.adapters.ChaptersAdapter;
import com.alfco.myrsspodcast.models.models.Podcast;
import com.alfco.myrsspodcast.models.models.Songs;
import com.alfco.myrsspodcast.restclient.request.RssFeedRequest;
import com.alfco.myrsspodcast.tools.Constants;

import com.alfco.myrsspodcast.tools.Interfaces.OnPlayButtonListener;
import com.alfco.myrsspodcast.tools.Util;
import com.google.gson.Gson;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by angel on 6/7/15.
 */
@EFragment(R.layout.fragment_home)
public class FragmentHome extends BaseFragment implements RequestListener<byte[]>,OnPlayButtonListener {


    /**********************************************************************************************
     *
     *                                      Simple variables
     *
     **********************************************************************************************/

    ChaptersAdapter chaptersAdapter;
    Podcast parsedPodcast;
    String urlPodcast="";
    private int playbackPosition=0;
    private String currentUrl;
    private boolean isPrepared=true;
    Bundle args;

    @StringRes(R.string.home_invalid_url)
            String invalidUrl;
    @StringRes(R.string.home_no_internet)
            String noInternet;
    @StringRes(R.string.home_request_error)
            String requestError;


    /**********************************************************************************************
     *
     *                                      Special variables
     *
     **********************************************************************************************/


    MediaPlayer player;



    /**********************************************************************************************
     *
     *                                          UI Variables
     *
     **********************************************************************************************/

    @ViewById
    ListView lvChapters;
    @ViewById
    TextView tvTitle;
    @ViewById
    EditText etUrl;
    @ViewById
    Button btLoadPodcast;
    @ViewById
    ImageView ivPodcastLogo;







    /**********************************************************************************************
     *
     *                                      Fragment's lifecycle
     *
     **********************************************************************************************/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


    @AfterViews
    void init(){
        initLogicalComponenets();
        assignListeners();
    }
    @ItemClick(R.id.lvChapters)
    void reproduce(Songs song){
        //

        if(reproduceType(song)) {

            if (!song.getGuid().isEmpty()) {

                if (song.getGuid().equalsIgnoreCase(currentUrl)) {
                    if (!player.isPlaying())
                        restartMedia();
                    else
                        pauseMedia();
                } else {
                    currentUrl = song.getGuid();
                    playMedia(currentUrl, player);
                }
            }
        }else{
            Bundle args=new Bundle();
            args.putString(Constants.KEY_SONG,new Gson().toJson(song));
            insertTemporal(Constants.TAG_FRAG_DETAIL,args);


        }

    }
    @Click(R.id.btLoadPodcast)
    void loadPodcast(){
        hideKb();
        urlPodcast=etUrl.getText().toString();
        if(Util.isValidURL(urlPodcast)){
            if(Util.isOnline(getActivity()))
                executePodcastRequest();
            else{
                toast(noInternet);
            }
        }else
            toast(invalidUrl);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        killMediaPlayer();

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

    private void initLogicalComponenets(){
        etUrl.setText(Constants.url);
        player=new MediaPlayer();
    }

    private void executePodcastRequest(){
        showLoading(true);
        RssFeedRequest request=new RssFeedRequest(urlPodcast);
        spiceManager.execute(request,this);


    }
    private void assignListeners(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                showLoading(false);
                lvChapters.setEnabled(true);
                toast("Algo salió mal con la reproducción de la url");
                return false;
            }
        });
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                showLoading(false);
                lvChapters.setEnabled(true);
                mediaPlayer.start();
            }
        });


    }

    private void fillListview(){
        if(chaptersAdapter==null)
        chaptersAdapter=new ChaptersAdapter(getActivity(),parsedPodcast.getChannel().getSongs(),this);
        lvChapters.setAdapter(chaptersAdapter);
        
    }

    private void parseXml(byte[] podcast){
        try {
            Util.le(new String(podcast, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        InputStream input= new ByteArrayInputStream(podcast);
        Serializer serializer=new Persister();
        try {
            parsedPodcast=serializer.read(Podcast.class,input);
            Util.le(parsedPodcast.getChannel().getAuthor());
            tvTitle.setText(parsedPodcast.getChannel().getTitle());
            Picasso.with(getActivity()).load(parsedPodcast.getChannel().getImage().getLink()).into(ivPodcastLogo);
            fillListview();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void playMedia(String url,MediaPlayer mediaPlayer){
        try {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();

            }

            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            lvChapters.setEnabled(false);
            showLoading(true);
   } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void pauseMedia(){
        if( player.isPlaying()&& player !=null){
            playbackPosition= player.getCurrentPosition();
            player.pause();
        }
    }
    private void restartMedia(){
        if( !player.isPlaying()&& player !=null){

            player.seekTo(playbackPosition);
            player.start();
        }
    }
    private void killMediaPlayer() {
        if(player !=null) {
            try {
                player.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean reproduceType(Songs song){
        String type="";
        boolean isAudio=false;
        if(song.getUrl().getType()!=null&&!song.getUrl().getType().isEmpty())
            type=song.getUrl().getType();
        switch(type){
            case Constants.typeAudioMpeg:
            case Constants.typeAudioM4a:
                isAudio=true;
                break;
            case Constants.typeVideoM4v:
            case Constants.typeVideoMp4:
                isAudio=false;
                break;


        }
        return isAudio;
    }
    /**********************************************************************************************
     *
     *                                     Listeners
     *
     **********************************************************************************************/

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        showLoading(false);
        toast(requestError);
    }

    @Override
    public void onRequestSuccess(byte[] podcast) {
        showLoading(false);
        if(podcast==null)return;
        parseXml(podcast);
    }


    @Override
    public void onPlayListener() {

    }
}
