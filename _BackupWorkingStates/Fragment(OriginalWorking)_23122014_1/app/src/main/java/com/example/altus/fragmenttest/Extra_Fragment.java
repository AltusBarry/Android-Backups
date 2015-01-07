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
    private boolean nullTextview = true;
    private String tempMsg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.extra_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.sentmsg_);
        if(nullTextview){
            setMessage(tempMsg);
        }
        /*if (bundle != null) {
            msg = bundle.getString("msg");
            if (msg != ""){
                textView.setText(msg);
            }
        }*/
        return view;
    }

    public void setMessage(String msg){
        tempMsg = msg;
        if(msg != "") {
                if(textView == null){
                    nullTextview = true;
                }else {
                    textView.setText(tempMsg);
                    nullTextview = false;
                }

        }
    }

}


