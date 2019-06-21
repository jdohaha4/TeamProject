package com.study.android.team_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

public class PetSitterAdapter extends BaseAdapter {
    private static final String TAG = "lecture";

    Context context;
    ArrayList<PetSitterDTO> items = new ArrayList<>();

    public PetSitterAdapter(Context context){
        this.context = context;
    }
    public void addItem(PetSitterDTO item){
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

        PetSitterItemView view = null;
        if(convertView == null){
            view = new PetSitterItemView(context);
        }else{
            view = (PetSitterItemView)convertView;
        }
        final PetSitterDTO item = items.get(position);
        view.setName(item.getName());
        view.setSex (item.getSex ());
        view.setHit (item.getHit ());
        view.setContents (item.getContents ());
        view.setImg (item.getImg ());


        return view;
    }
}