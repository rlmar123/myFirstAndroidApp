package com.example.mathapp;

public class Player {

    private String player_name;
    private int player_score;

    private final int score_up = 100;
    private final int score_down = 50;


    public Player()
    {
        //null for now
    }

    public Player(String name_of_player)
    {
        player_name = name_of_player;
        player_score = 0;
    }

    public String getName()
    {return player_name;}

    public int getScore()
    {return player_score;}

    //add 100 points
    public void addScore()
    { player_score += score_up;}

    //subtract 50 points
    public void subScore()
    { player_score -= score_down;}

    //if player score drops below 0
    public void zeroScore()
    { player_score = 0;}


}
