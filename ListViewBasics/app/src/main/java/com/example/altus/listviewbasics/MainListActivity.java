package com.example.altus.listviewbasics;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.altus.models.DetailSetter;

import java.util.ArrayList;
import java.util.List;


public class MainListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        //NEW AFTER WORKING
        //List is a generic array class. Array list is an array implementation of a list interface
        List<DetailSetter> messageDetails = new ArrayList<DetailSetter>();
        // Assigns text into header and body text
        for ( int i = 0; i < 20; i++ ) {
            DetailSetter detailSetter = new DetailSetter();
            detailSetter.setTitle("A nice header for Tweet # " +i);
            detailSetter.setBody("Some random body text for the tweet # " +i);
            //Assigns new text into List array that should then be used to fil each list item with text
            //CURRENTLY NOT FILLING ANYWHERE
            messageDetails.add(detailSetter);
        }
        //NOT DOING ANYTHING AS OF YET

        // Define a new Adapter(DEFAULT ADAPTER)
        // First parameter - Context
        // Second parameter - Layout for the row (simple_list_item_1) is a inbuilt android layout,
        //                      each row is a TextView
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        //This is not a default Adapter
        //Currently merely creating a list of 10 items with default layout text.
        CustomAdapter customAdapter = new CustomAdapter(this, new String[10]);
        // Assign adapter to ListView
        setListAdapter(customAdapter);
    }
    protected void onListItemClick (ListView l, View v, int position, long id){
        /*TextView t = (TextView) v.findViewById(R.id.textHeaderView);
        t.setText("This has been Clicked");*/
        Intent intent = new Intent(this, ListDetailActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(),"Position :" + position, Toast.LENGTH_LONG).show();
    }
}
