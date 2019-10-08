package com.example.split_it;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText userInput;
    EditText userInputC;
    TextView recordsTextView;
    TextView Results;
    Button add_Button;
    Button delete_Button;
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.user_Input);
        userInputC = (EditText) findViewById(R.id.user_InputC);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        Results = (TextView) findViewById(R.id.Results);
        add_Button = findViewById(R.id.add_Button);
        delete_Button = findViewById(R.id.delete_Button);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();

        //
        // Updating the total when the app starts
        //
        int temp = dbHandler.GetTotal();
        Results.setText("Total: " + Integer.toString(temp));


        //
        // A check to see if the user actually entered an item name
        //

        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = userInput.getText().toString();
                if (newEntry.length() != 0) {
                        int numEntry = Integer.parseInt(userInputC.getText().toString());
                        AddData(newEntry,numEntry);
                        userInput.setText("");
                    } else {
                    toastMessage("You must put something in the name field!");
                }

            }
        });
    }

    //
    //Print the database
    // This method is used to check and see if everything is added to
    //

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput.setText("");
        userInputC.setText("");
    }


    @SuppressLint("SetTextI18n")
    public void AddData(String name, int amount){
        Products product = new Products(name, amount);
        dbHandler.addProduct(product);
        int temp = dbHandler.GetTotal();
        Results.setText("Total: " + Integer.toString(temp));
        printDatabase();
        toastMessage( name + " with the cost of $" + amount + " has successfully been added" );
    }


        /*
    @SuppressLint("SetTextI18n")
    public void addButtonClicked(View view){
        int usersinput = Integer.parseInt(userInputC.getText().toString());
        Products product = new Products(userInput.getText().toString(), usersinput);
        dbHandler.addProduct(product);
        int temp = dbHandler.GetTotal();
        Results.setText(Integer.toString(temp));
        printDatabase();
    }
        */

    //
    //Delete items
    //
    public void deleteButtonClicked(View view){
        String inputText = userInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        int temp = dbHandler.GetTotal();
        Results.setText("Total: " + Integer.toString(temp));
        printDatabase();

    }

    // Takes you to the list page to split the items and ciew what items are in the list

    public void splitButtonClciked(View view) {
        Intent intent = new Intent(MainActivity.this, list.class);
        startActivity(intent);
    }

    public void clearButtonClciked(View view) {
        dbHandler.clear();
        printDatabase();
        int temp = dbHandler.GetTotal();
        Results.setText("Total: " + Integer.toString(temp));
    }




    // prints the android messages to screen

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}