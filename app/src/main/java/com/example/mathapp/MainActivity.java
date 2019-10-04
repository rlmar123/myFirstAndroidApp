package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String name;
    private Button goButton;
    private EditText enterName;

    private final int REQUESTCODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterName = findViewById(R.id.enter_text);

        goButton = findViewById(R.id.go_button);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = enterName.getText().toString().trim();

                //input validation
                if(!name.isEmpty())
                {
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                     myIntent.putExtra("name", name);

                    startActivity(myIntent);
                }

                else
                    Toast.makeText(MainActivity.this, "Please Try Again.", Toast.LENGTH_LONG).show();

            }
        });


    }
}
