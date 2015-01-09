package com.example.altus.listviewbasics;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.altus.models.DetailSetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;


public class MainListActivity extends ListActivity implements Serializable{
    private final   String filename = "listViewFile.json";
    private         ArrayList<DetailSetter> messageDetails;
    private         JSONObject jsonObject = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        if(savedInstanceState == null){
            try {
                assignNewText();//Assigns new text for testing.
                writeToFile(jsonObject.getString("Msges"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            try {
                jsonObject.put("Msges", readFromFile());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            try {
                writeToListView();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        //This is not a default Adapter
        CustomAdapter customAdapter = new CustomAdapter(this, messageDetails);
        // Assign adapter to ListView
        setListAdapter(customAdapter);
    }

    public void assignNewText() throws JSONException {
        JSONObject jsonContents;
        JSONArray jsonArray = new JSONArray();
        // Assigns text into header and body text
        for ( int i = 0; i < 20; i++ ) {
            jsonContents  = new JSONObject();
            jsonContents.put("header", ("A HEADER"+i));
            jsonContents.put("body", ("A Body "+i));
            jsonArray.put(jsonContents);
        }
        jsonObject.put("Msges", jsonArray);
    }

    private void writeToListView() throws JSONException {
        //List is a generic array class. Array list is an array implementation of a list interface
        messageDetails = new ArrayList<DetailSetter>();
        Log.d("JSON LIST: ", jsonObject.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("Msges");
        Log.d("Did it setArray:", "True");

        for(int i = 0; i< jsonArray.length(); i++) {
            DetailSetter detailSetter = new DetailSetter();
            detailSetter.setTitle(jsonArray.getJSONObject(i).getString("header"));
            detailSetter.setBody(jsonArray.getJSONObject(i).getString("body"));
            //Assigns new text into List array that should then be used to fil each list item with text
            messageDetails.add(detailSetter);
            //Write ArrayList(messageDetails) with all the detail out to file(technically)
        }
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
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
