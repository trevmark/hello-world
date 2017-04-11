package com.trev.Models;

import java.util.ArrayList;

/**
 * Created by trevor on 06/04/17.
 */
public class Hand {

    private String playerName;
    private  ArrayList<Card> playerHand;
    private int handTotal =0;
    private String result = null;
    private int numOfWins =0;

    public Hand(String playerName) {
        this.playerName = playerName;
        this.playerHand = new ArrayList<Card>();
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public int getHandTotal(){
        return handTotal;
    }

    public void setHandTotal(int total){
        this.handTotal = total;
    }

    public void addCardToHand(Card card){
        this.playerHand.add(card);
    }

    public String getPlayerName(){
        return playerName;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String string) {
        this.result = string;
    }

    public void resetPlayerHand() {
        while (this.playerHand.size() > 0) {
            this.playerHand.remove(0);
            this.handTotal =0;
            this.setResult(null);
        }
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void setNumOfWins(int numOfWins) {
        this.numOfWins = numOfWins;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "playerName='" + playerName + '\'' +
                ", playerHand=" + playerHand +
                '}';
    }
}
