package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView nameText;
    private TextView scoreText;

    private TextView leftFsctor;
    private TextView rightFactor;

    private EditText answerText;

    private Button answerButton;
    private Button nextButton;

    private String theName;
    private String userAnswer;

    private Bundle myBundle;

    private Player thePlayer;


    //to generate random numbers for math operations
    private Random factor;

    //factors
    private int left;
    private int right;

    private boolean isAnswered = false;

    private final int maxInt = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //retrieve Intent object from MainActivity
        myBundle = getIntent().getExtras();

        //create Player obj
        thePlayer = new Player();

        factor = new Random();

        //connect to activity_game_xml widgets
        nameText = findViewById(R.id.name_text);
        scoreText = findViewById(R.id.socre_text);

        leftFsctor = findViewById(R.id.left_factor);
        rightFactor = findViewById(R.id.right_factor);

        answerText = findViewById(R.id.answer_text);

        answerButton = findViewById(R.id.answer_button);
        nextButton = findViewById(R.id.next_button);

        //retrieve data from MainActivity
        if(myBundle != null)
        {
            theName = myBundle.getString("name");

            changeFactors();

            //create Player obj
            thePlayer = new Player(theName);
            nameText.setText("Name: " + thePlayer.getName());
            scoreText.setText("Score: " + Integer.toString(thePlayer.getScore()));
            updateFactors();

        } // end if

        //register the buttons to the onClick function
        answerButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    } //end onCreate

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.answer_button:
                userAnswer = answerText.getText().toString().trim();

                checkAnswer();

                break;

            case R.id.next_button:
                isAnswered = false;

                changeFactors();
                updateFactors();

                break;
        } //end switch

    } //end onClick

    public void checkAnswer()
    {
        //input validation
        if(!userAnswer.isEmpty())
        {
            int rightAnswer = Integer.parseInt(userAnswer);

            //answer is right
            if((theProduct() == rightAnswer) && !(isAnswered))
            {
                Toast.makeText(GameActivity.this, "You are correct!!", Toast.LENGTH_LONG).show();
                thePlayer.addScore();;

            }

            //answer is wrong
            else if((theProduct() != rightAnswer) && !(isAnswered))
            {
                Toast.makeText(GameActivity.this, "You are wrong!!", Toast.LENGTH_LONG).show();

                //prevents negative score
                if (thePlayer.getScore() <= 0)
                    thePlayer.zeroScore();

                //deduct points here
                else
                    thePlayer.subScore();

            }

            isAnswered = true;

        }

        //invalid input
        else
            Toast.makeText(GameActivity.this, "Please Try Again.", Toast.LENGTH_LONG).show();

        scoreText.setText("Score: " + Integer.toString(thePlayer.getScore()));

    }

    public int theProduct()
    {return left * right;}

    public void changeFactors()
    {
       //get factors
       left = factor.nextInt(maxInt);
       right = factor.nextInt(maxInt);
    }

    public void updateFactors()
    {
       leftFsctor.setText(Integer.toString(left));
       rightFactor.setText(Integer.toString(right));
    }

} //end gameActivity
