package com.example.altus.fragmenttest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by altus on 2014/12/19.
 */
public class LM_Fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout... (Layout in res to inflate, the container to inflate to, whether it attaches to the root
        //Honestly... Not really sure...
        return inflater.inflate(R.layout.lm_fragment, container, false);
    }
}
