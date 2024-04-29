package io.guidemy.learn.pokercardpatterndetector.model;

import java.util.*;

/*
    Do not modify this class
 */

public class CardRequest {
    private final Card[] cards;
    private Map<Card.Rank, Integer> rankCount;
    private Map<Card.Suit, Integer> suitCount;
    private boolean hasFlush = false;
    private boolean hasStraight = false;
    private boolean hasSmallestStraight = false;
    private boolean hasLargestStraight = false;

    private CardRequest(Card[] cards) {
        this.cards = cards;
    }

    public static CardRequest of(Card[] cards) {
        CardRequest request = new CardRequest(cards);
        request.initRankCount(cards);
        request.initSuitCount(cards);
        request.checkFlush();
        request.checkStraight();
        return request;
    }

    private void initRankCount(Card[] cards) {
        rankCount = new HashMap<>();

        for (Card card : cards) {
            Card.Rank rank = card.getRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
    }

    private void initSuitCount(Card[] cards) {
        suitCount = new HashMap<>();

        for (Card card : cards) {
            Card.Suit suit = card.getSuit();
            suitCount.put(suit, suitCount.getOrDefault(suit, 0) + 1);
        }
    }

    private void checkFlush() {
        for (Integer count : suitCount.values()) {
            if (count >= 5) {
                hasFlush = true;
                return;
            }
        }
    }

    private void checkStraight() {
        Set<Card.Rank> rankSet = new HashSet<>();
        for (Card card : cards) {
            rankSet.add(card.getRank());
        }
        if (rankSet.size() < 5) {
            return;
        }
        hasLargestStraight = rankSet.containsAll(
                LARGEST_STRAIGHT_PATTERN
        );
        hasSmallestStraight = rankSet.containsAll(
                SMALLEST_STRAIGHT_PATTERN
        );
        hasStraight = checkStraight(rankSet) || hasLargestStraight || hasSmallestStraight;
    }

    private boolean checkStraight(Set<Card.Rank> rankSet) {
        List<Card.Rank> rankList = new ArrayList<>(rankSet);

        rankList.sort(Comparator.comparingInt(Card.Rank::getValue));

        int count = 0;

        for (int i = 1; i < rankList.size(); i++) {
            if (rankList.get(i).getValue() - rankList.get(i - 1).getValue() == 1) {
                count++;
            } else {
                count = 0;
            }
        }

        return count >= 4;
    }


    public boolean getHasFlush() {
        return hasFlush;
    }

    public boolean getHasStraight() {
        return hasStraight;
    }

    public boolean getHasSmallestStraight() {
        return hasSmallestStraight;
    }

    public boolean getHasLargestStraight() {
        return hasLargestStraight;
    }


    public Map<Card.Rank, Integer> getRankCount() {
        return rankCount;
    }

    public Map<Card.Suit, Integer> getSuitCount() {
        return suitCount;
    }

    public Card[] getCards() {
        return cards;
    }

    private static final Set<Card.Rank> LARGEST_STRAIGHT_PATTERN = new HashSet<>() {{
        add(Card.Rank.TEN);
        add(Card.Rank.JACK);
        add(Card.Rank.QUEEN);
        add(Card.Rank.KING);
        add(Card.Rank.ACE);
    }};

    private static final Set<Card.Rank> SMALLEST_STRAIGHT_PATTERN = new HashSet<>() {{
        add(Card.Rank.TWO);
        add(Card.Rank.THREE);
        add(Card.Rank.FOUR);
        add(Card.Rank.FIVE);
        add(Card.Rank.ACE);
    }};
}
