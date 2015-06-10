package com.alfco.myrsspodcast.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alfco.myrsspodcast.models.models.Songs;
import com.alfco.myrsspodcast.tools.Interfaces.OnPlayButtonListener;
import com.alfco.myrsspodcast.views.ViewRowChapter;
import com.alfco.myrsspodcast.views.ViewRowChapter_;

import java.util.ArrayList;

/**
 * Created by angel on 6/7/15.
 */
public class ChaptersAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Songs> songs;
    private OnPlayButtonListener listener;



    public ChaptersAdapter(Context context,ArrayList<Songs> songs,OnPlayButtonListener listener){
        this.context=context;
        this.songs=songs;
        this.listener=listener;

    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Songs getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewRowChapter row;
        if(view==null){
            row= ViewRowChapter_.build(context);
        }else{
            row=(ViewRowChapter)view;
        }
        row.setListener(listener);
        row.bindData(getItem(position));


        return row;
    }
}
