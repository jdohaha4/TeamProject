package com.study.android.team_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class EBoardAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<EBoardDTO> items = new ArrayList<>();

    public EBoardAdapter(Context context){
        this.context = context;
    }
    public void addItem(EBoardDTO item){
        items.add(item);
    }


    @Override
    public int getCount(){return items.size();}
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        EBoardItemView view = null;
        if(convertView == null){
            view = new EBoardItemView(context);
        }else{
            view = (EBoardItemView)convertView;
        }
        final EBoardDTO item = items.get(position);
        view.setEname (item.getEname ());
        view.setEdate (item.getEdate ());
        view.setEstar (item.getEstar ());
        view.setEcontent (item.getEcontent ());
        view.setEsitter (item.getEsitter ());
        view.setEtitle (item.getEtitle ());
        return view;
    }
}