package com.alfco.myrsspodcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alfco.myrsspodcast.R;
import com.alfco.myrsspodcast.models.models.Songs;
import com.alfco.myrsspodcast.tools.Interfaces.OnPlayButtonListener;
import com.alfco.myrsspodcast.tools.Util;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by angel on 6/7/15.
 */

@EViewGroup(R.layout.row_chapter)
public class ViewRowChapter extends RelativeLayout {

    @ViewById
    TextView tvTitle;
    @ViewById
    TextView tvDate,tvDuration;
    @ViewById
    ImageView ivLogo;

    OnPlayButtonListener listener;
    Context context;



    public ViewRowChapter(Context context) {
        super(context);
        this.context=context;
    }

    public ViewRowChapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public ViewRowChapter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }


    public void bindData(Songs song){
        tvTitle.setText(song.getTitle());

        tvDuration.setText(song.getDuration());
        Picasso.with(context).load(song.getImage().getLink()).resize(100, 100).into(ivLogo);

    }
    @Click(R.id.btPlay)
    void play(){
        if(listener!=null)
            listener.onPlayListener();

    }

    public OnPlayButtonListener getListener() {
        return listener;
    }

    public void setListener(OnPlayButtonListener listener) {
        this.listener = listener;
    }





}
