package com.example.altus.listviewbasics;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.altus.models.DetailSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by altus on 2015/01/07.
 */
public class CustomAdapter extends ArrayAdapter<DetailSetter>{
    private LayoutInflater inflater;

    public CustomAdapter(Activity activity, ArrayList items){
        super(activity, R.layout.list_item_layout, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        DetailSetter items = getItem(position);
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        TextView headerText = (TextView) convertView.findViewById(R.id.textHeaderView);
        TextView bodyText = (TextView) convertView.findViewById(R.id.textBodyView);

        headerText.setText(items.getTitle());
        bodyText.setText(items.getBody());

        return convertView;
    }
}
