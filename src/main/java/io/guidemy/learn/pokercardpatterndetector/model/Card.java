package io.guidemy.learn.pokercardpatterndetector.model;


import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
    Do not modify this class
 */

@Data
@Builder
public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Rank rank;

    @Override
    public int compareTo(Card anotherCard) {
        return Comparator.comparingInt((Card c) -> c.rank.value)
                .thenComparingInt(c -> c.suit.value)
                .compare(this, anotherCard);
    }

    /*
        Do not modify the enums
     */
    public enum Rank {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private static final Map<Integer, Rank> rankByValue = new HashMap<>();

        static {
            for (Rank rank : Rank.values()) {
                rankByValue.put(rank.value, rank);
            }
        }

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Rank byValue(int value) {
            return rankByValue.get(value);
        }
    }

    public enum Suit {
        SPADE(4), HEART(3), CLUB(2), DIAMOND(1);

        private final int value;

        Suit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
