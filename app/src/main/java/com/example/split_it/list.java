package com.example.split_it;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    MyDBHandler dbHandler;
    TextView Results;
    TextView people;
    TextView answer;


    private ListView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MyListView = findViewById(R.id.listView);
        Results = (TextView) findViewById(R.id.Results);
        dbHandler = new MyDBHandler(this, null, null, 1);
        people = findViewById(R.id.people);
        answer = findViewById(R.id.answer);
        populateListView();

        int temp = dbHandler.GetTotal();
        Results.setText("Split the total of $" + Integer.toString(temp) + " by: ");


    }

    //
    //  Taking the data from the data base and inserting intot the list view
    //
    //

    private void populateListView() {

        //get the data and append to a list
        Cursor data = dbHandler.getData();
        ArrayList<String> listData = new ArrayList<>();
        listData.add("Item name" + " \t " + "Item Price");
        while (data.moveToNext()) {
            //get the value from the database in column 1 and the column 2
            //then add it to the ArrayList
            listData.add(data.getString(1) + "\t\t\t\t$" + data.getString(2));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        MyListView.setAdapter(adapter);
    }

    public void splitButtonClicked(View view) {
        String s = people.getText().toString();

        if (!people.equals("")) {
            float splitby = Integer.parseInt(s);
            float num = dbHandler.GetTotal();

            double fin = num / splitby;
            answer.setText("Each person pays: $" + Math.round(fin * 100.0) / 100.0);
        } else {
            toastMessage("You must have a number to split");
        }
    }

    // prints the android messages to screen

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
