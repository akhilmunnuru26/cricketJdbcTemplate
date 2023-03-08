package com.example.player.model;
import java.util.*;


public class Player{
    private int playerId;
    private String playerName;
    private int jerseyNumber;
    private String role;

    public Player(int playerId,String playerName,int jerseyNumber,String role){
        this.playerId = playerId;
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.role = role;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public void setPlayerId(int playerId){
        this.playerId= playerId;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public int getJerseyNumber(){
        return this.jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber){
        this.jerseyNumber = jerseyNumber;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

}