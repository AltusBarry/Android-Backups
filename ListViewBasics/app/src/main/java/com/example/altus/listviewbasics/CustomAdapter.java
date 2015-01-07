package com.example.altus.listviewbasics;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.altus.models.DetailSetter;

import java.util.List;

/**
 * Created by altus on 2015/01/07.
 */
public class CustomAdapter extends ArrayAdapter{
    private LayoutInflater inflater;

    public CustomAdapter(Activity activity, String[] items){
        super(activity, R.layout.list_item_layout, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.list_item_layout, parent, false);
    }
}
