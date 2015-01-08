package com.example.altus.listviewbasics;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.altus.models.DetailSetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainListActivity extends ListActivity implements Serializable{
    private final   String filename = "listViewFile";
    private         FileOutputStream fileOutputStream;
    private         ObjectOutputStream objectOutputStream;
    private         ArrayList<DetailSetter> messageDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        //Write ArrayObject to file
        if(savedInstanceState == null){
            assignNewText();
            writeFile();
        }else{
            readFile();
        }
        //This is not a default Adapter
        CustomAdapter customAdapter = new CustomAdapter(this, messageDetails);
        // Assign adapter to ListView
        setListAdapter(customAdapter);
    }

    public void assignNewText(){
        //List is a generic array class. Array list is an array implementation of a list interface
        messageDetails = new ArrayList<DetailSetter>();
        // Assigns text into header and body text
        for ( int i = 0; i < 20; i++ ) {
            DetailSetter detailSetter = new DetailSetter();
            detailSetter.setTitle("A nice header for Tweet # " +i);
            detailSetter.setBody("Some random body text for the tweet # " +i);
            //Assigns new text into List array that should then be used to fil each list item with text
            messageDetails.add(detailSetter);
            //Write ArrayList(messageDetails) with all the detail out to file(technically)
        }
    }

    public void writeFile() {
        try {
            fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(messageDetails);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            FileInputStream fileInputStream = openFileInput(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            messageDetails = (ArrayList<DetailSetter>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onListItemClick (ListView l, View v, int position, long id){
        Intent intent = new Intent(this, ListDetailActivity.class);
        String header = messageDetails.get(position).getTitle();
        String body = messageDetails.get(position).getBody();
        //Assign header and body text directly into the detail activity.
        //Will be removed and done in the ListDetail.class itself as soon
        //as saving out files work
        intent.putExtra("HEADER", header);
        intent.putExtra("BODY", body);
        startActivity(intent);

        //UNNECESSARY ADDED FOR DEBUGGING PURPOSES
        Toast.makeText(getApplicationContext(),"Position :" + messageDetails.get(position).getTitle(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Text :" + position, Toast.LENGTH_LONG).show();
    }
}
