package poker;

import java.util.*;

public class Player {

    private List<Card> hand = new ArrayList<>();

    public void addCard(Card card) {
        hand.add(card);
    }

    public void showHand() {
        for (Card c : hand) {
            System.out.println(c);
        }
    }

    public List<Card> getHand() {
        return hand;
    }
}