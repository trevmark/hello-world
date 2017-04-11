package com.trev.Models;


import java.util.ArrayList;

/**
 * Created by trevor on 05/04/17.
 */
public class Shoe {
    private ArrayList<Card> discardPile;
    private ArrayList<Card> shuffled;
    private ArrayList<Deck> shoe;
    private static int count;

    public Shoe() {
        this.shoe = new ArrayList<Deck>();
        this.shuffled = new ArrayList<Card>();
        this.discardPile = new ArrayList<Card>();
    }

    public void addDeck(Deck deck){
        if(count<8){
        count++;
        this.shoe.add(deck);
    }
    else {
            System.out.println("\nMax deck count has been reached, you can only have 8 decks per shoe");}}

    public int getCount(){
        return count;
    }

    public void shuffleDecksTogether(){
        for (Deck deck : this.shoe){
            for(Card card :deck.getPlayingDeck()){
                shuffled.add(card);
            }
        }
    }

    public Card draw() {
        int randomNum = 0 + (int) (Math.random() * this.shuffled.size());
        Card selected = this.shuffled.get(randomNum);
        discardPile.add(selected);
        this.shuffled.remove(randomNum);
        return selected;
    }

//    public void discardPileReadout(){
//        System.out.println("there are "+discardPile.size()+" cards in the discard pile, they are");
//        for (Card card: this.discardPile){
//            System.out.println("\n the "+card.getValue()+" of "+card.getSuit());
//        }
//    }

    public Shoe getShuffledShoe(){
        this.shuffleDecksTogether();
        return this;
    }

    public void createShoe(){
        int numberOfDecks = 8;
        for(int j=0; j<numberOfDecks;j++){
            Deck deck = new Deck();
            for (int i =0 ; i<52; i++){
                Card c = new Card(i);
                c.setSuit(i);
                c.setValue(i);
                deck.addCardToDeck(c);
            }
//  display cards being generated
//            for ( Card card : deck.getPlayingDeck()) {
//                System.out.printf("the value is "+card.getValue()+" and the suit is "+card.getSuit()+"  "+card.getCardNumber()+"\n");
//                }
            this.addDeck(deck);
       }
        System.out.println("\nthe number of the decks currently the shoe is "+this.getCount()+"\n");
        System.out.println("Shuffling the decks together.......\n");
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "shoe=" + shoe +
                '}';
    }
}

