package com.example.altus.listviewbasics;

import android.app.ListActivity;
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
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class MainListActivity extends ListActivity implements Serializable{
    private final   String filename = "altus.json";
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
            try {//Check for more economical way to do this
                JSONTokener jsonTokener = new JSONTokener(readFromFile(filename));
                JSONArray jsonArray = new JSONArray(jsonTokener);
                jsonObject.put("Msges", jsonArray);
                Log.d("JsonOBject Reassigned", jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
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
        Log.d("INITIAL JSON OBJECT", jsonObject.toString());
    }

    private void writeToListView() throws JSONException {
        //List is a generic array class. Array list is an array implementation of a list interface
        messageDetails = new ArrayList<DetailSetter>();
        JSONArray jsonArray = jsonObject.getJSONArray("Msges");
        Log.d("ArrayValuesAtSet", jsonArray.toString());
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
        File file = new File(getExternalFilesDir(null), filename);
        Log.d("File pATH", file.getAbsolutePath());
        try {
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(String filename) {
        File file = new File(getExternalFilesDir(null), filename);
        String ret = "";
        try {
            //InputStream inputStream = openFileInput(filename);

            if ( file != null ) {
                FileInputStream fin = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                    Log.d("Line", line);
                }
                reader.close();
                fin.close();
                ret = stringBuilder.toString();
                return ret;
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
