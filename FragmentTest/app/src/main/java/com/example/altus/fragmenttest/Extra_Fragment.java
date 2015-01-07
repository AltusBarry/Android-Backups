package com.example.altus.fragmenttest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by altus on 2014/12/19.
 */
public class Extra_Fragment extends Fragment {

    private TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.setRetainInstance(true);
        View view = inflater.inflate(R.layout.extra_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.sentmsg_);
        return view;
    }

    public void setMessage(String msg){
        if(msg != "") {
                    textView.setText(msg);

        }
    }

}


