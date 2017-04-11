package com.trev.Controller;

import com.trev.Models.Card;
import com.trev.Models.Hand;
import com.trev.Models.Shoe;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Created by trevor on 06/04/17.
 */
public class Game {
    public static Game instance;
    public static int gameCount;

    private Shoe gameShoe;
    private Hand player;
    private Hand banker;
    private ArrayList<Hand> game;

    public Game() {
        instance = this;
        this.game = new ArrayList<Hand>();
    }

    public void startGame(){
        if(gameCount<1){
            this.setUpGame();
        }else{
            this.endGame();
        }
    }

    public void setUpGame(){
        System.out.println("puting cards in the shoe");
        Shoe shoe = new Shoe();
        shoe.createShoe();
        shoe.shuffleDecksTogether();
        this.gameShoe = shoe;

        //create players
        Hand player = new Hand("Player");
        this.setPlayer(player);
        Hand banker = new Hand("Banker");
        this.setBanker(banker);
        game.add(player);
        game.add(banker);
    }


    public void endGame(){
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nThank you for playing, Goodbye!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        exit(0);
    }

    public void playGame() {

        gameCount = gameCount + 1;
        System.out.println("game count is " + gameCount);
        // Deal out hands

        for (Hand hand : game) {
            Card card1 = gameShoe.draw();
            hand.addCardToHand(card1);
            Card card2 = gameShoe.draw();
            hand.addCardToHand(card2);
        }

        // Round 1 : get initial hand values

        System.out.println("\n Begin Dealing Cards \n");
        for (Hand hand : game) {
            int total = hand.getHandTotal();
            for (Card card : hand.getPlayerHand()) {
                System.out.println(hand.getPlayerName() + " draws the " + card.getValue() + " of " + card.getSuit());
                int value = card.getPlayValue();
                total += value;
                hand.setHandTotal(total);
            }
            if (hand.getHandTotal() >= 10) {
                hand.setHandTotal(hand.getHandTotal() - 10);
            }
        }

        System.out.println("\n Initial Hands\n");
        System.out.println(" Player's total in hand is " + player.getHandTotal());
        System.out.println(" Bankers's total in hand is " + banker.getHandTotal());

        // Decide Winner Of Round 1

        if (player.getHandTotal() <= 5) {
            Card thirdCard = gameShoe.draw();
            player.addCardToHand(thirdCard);
        } else if (player.getHandTotal() == 7 || player.getHandTotal() == 6) {
            System.out.println(" Player will stay");
        } else if (player.getHandTotal() >= 8) {
            System.out.println("\nNatural for PLayer");
            player.setResult("Natural");
        }

        if (banker.getHandTotal() <= 2) {
            Card requiredThirdCard = gameShoe.draw();
            banker.addCardToHand(requiredThirdCard);
        } else if (banker.getHandTotal() == 7) {
            System.out.println(" Banker will stay");
        } else if (banker.getHandTotal() >= 8) {
            System.out.println("\nNatural for Banker\n");
            banker.setResult("Natural");
        }

        if ((banker.getHandTotal() >= 8) && (player.getHandTotal() >= 8)) {
            if (banker.getHandTotal() > player.getHandTotal()) {
                System.out.println("\nBanker Wins");

            } else if (banker.getHandTotal() == player.getHandTotal()) {
                System.out.println(" It's a tie! ");
            } else {
                System.out.println("\nPlayer Wins");
            }
        }

        // Round 2: Third card decider
        if (banker.getResult() != null) {
            banker.setNumOfWins((banker.getNumOfWins() + 1));
        } else if (player.getResult() != null) {
            player.setNumOfWins((player.getNumOfWins() + 1));
        }


        if (banker.getResult() == null && player.getResult() == null) {
            System.out.println("\n**********************************\n  Now entering Third Card Round\n**********************************\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player.getHandTotal() <= 5) {
                Card playerThirdCard = gameShoe.draw();
                System.out.println("Player is dealt the " + playerThirdCard.getValue() + " of " + playerThirdCard.getSuit());
                int add = player.getHandTotal() + playerThirdCard.getPlayValue();
                if (add >= 10) {
                    add = add - 10;
                }
                player.setHandTotal(add);
                System.out.println("\nThe player's new hand is worth " + player.getHandTotal());
                System.out.println("\nThe banker's hand is worth " + banker.getHandTotal());
                System.out.println("\n-----------------------------------------------");
            }
            if ((banker.getHandTotal() <= 2) || (banker.getHandTotal() < player.getHandTotal() && banker.getHandTotal() >= 3 && banker.getHandTotal() <= 6)) {
                Card bankerThirdCard = gameShoe.draw();
                System.out.println("\nBanker is dealt the " + bankerThirdCard.getValue() + " of " + bankerThirdCard.getSuit());
                int addition = banker.getHandTotal() + bankerThirdCard.getPlayValue();
                if (addition >= 10) {
                    addition = addition - 10;
                }
                banker.setHandTotal(addition);
                System.out.println("\nThe player's hand is worth " + player.getHandTotal());
                System.out.println("\nThe banker's new hand is worth " + banker.getHandTotal());
                System.out.println("\n-------------------------------------------------");
            }
            if (banker.getHandTotal() > player.getHandTotal()) {
                System.out.println("\nBanker wins");
                banker.setNumOfWins((banker.getNumOfWins() + 1));
            } else if (banker.getHandTotal() == player.getHandTotal()) {
                System.out.println("\nIt's a Tie!");
            } else if (banker.getHandTotal() < player.getHandTotal()) {
                System.out.println("\nPlayer wins");
                player.setNumOfWins((player.getNumOfWins() + 1));
            }
        }
            System.out.println("\n############################\n");
            System.out.println("Scoreboard\n Player " + player.getNumOfWins() + "\n Banker " + banker.getNumOfWins() + "\n###########################\n");

    }

    public Hand getPlayer() {
        return player;
    }

    public void setPlayer(Hand player) {
        this.player = player;
    }

    public Hand getBanker() {
        return banker;
    }

    public void setBanker(Hand banker) {
        this.banker = banker;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        Game.gameCount = gameCount;
    }
}