package com.trev.Models;

/**
 * Created by trevor on 05/04/17.
 */
public class Card {

   private String suit;
   private String value;
   private int cardNumber;

    public Card(int cardNumber) {
        this.suit = "default";
        this.value = "default";
        this.cardNumber = cardNumber +1;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(int num) {
    if(num>=0 && num <52){
        if (num<13){
            this.suit = "Clubs";}
         else if (num<26){
            this.suit = "Spades";}
         else if (num<39){
            this.suit = "Hearts";}
         else{
            this.suit = "Diamonds";
        }}}

    public String getValue() {
        return value;
    }

    public void setValue(int i) {
        int num=0;
        if (i>=0 && i<=12){
            num = i;
        }else{
            num = i%13;}

        switch (num) {
            case 0: this.value = "Ace";
            break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:this.value = Integer.toString(num+1);
            break;
            case 10: this.value = "Jack";
            break;
            case 11: this.value = "Queen";
            break;
            case 12: this.value = "King";
            break;
        }
    }

    public int getPlayValue(){
        if (this.value.contains("Ace")) {
            return 1;
        } else if (this.value.contains("Jack") || this.value.contains("Queen") || this.value.contains("King") || this.value.contains("10")) {
            return 0;
        } else {
            int result = Integer.parseInt(this.value);
            return result;
        }
    }

    public int getCardNumber(){
        return cardNumber;
    }
    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
