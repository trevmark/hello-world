package com.trev.Models;

import java.util.ArrayList;
/**
 * Created by trevor on 05/04/17.
 */
public class Deck {

    private Card card;
    private ArrayList<Card> playingDeck;

    public Deck() {
        this.playingDeck = new ArrayList<Card>();
    }

    public void addCardToDeck(Card card){
        this.playingDeck.add(card);
    }

    public ArrayList<Card> getPlayingDeck() {
        return playingDeck;
    }

//    public Card draw(){
//        int randomNum = 1 + (int)(Math.random() * 52);
//        Card selected = this.playingDeck.get(randomNum);
//        return selected;
//    }
    @Override
    public String toString() {
        return "Deck{" +
                "playingDeck=" + playingDeck +
                '}';
    }
}

