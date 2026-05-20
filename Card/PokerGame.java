// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package poker;

import java.util.List;

public class PokerGame {
   public PokerGame() {
   }

   public static void main(String[] var0) {
      Deck var1 = new Deck();
      var1.shuffle();
      Player var2 = new Player();
      var2.addCard(var1.dealCard());
      var2.addCard(var1.dealCard());
      System.out.println("Your Hand:");
      var2.showHand();
      evaluateHand(var2.getHand());
   }

   public static void evaluateHand(List<Card> var0) {
      if (((Card)var0.get(0)).getRank() == ((Card)var0.get(1)).getRank()) {
         System.out.println("You got a PAIR!");
      } else {
         System.out.println("High Card");
      }

   }
}
