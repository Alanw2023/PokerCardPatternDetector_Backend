package io.guidemy.learn.pokercardpatterndetector.mapper;

import io.guidemy.learn.pokercardpatterndetector.dto.CardDTO;
import io.guidemy.learn.pokercardpatterndetector.dto.CardWrapperDTO;
import io.guidemy.learn.pokercardpatterndetector.model.Card;
import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import org.springframework.stereotype.Component;

@Component
public class CardRequestMapper {

    public CardRequest fromCardWrapperDTO(CardWrapperDTO wrapperDTO) {
        // TODO: implement this method
        CardDTO [] cardDTOS = wrapperDTO.getCards();
        Card [] cards = new Card[cardDTOS.length];
        for (int i = 0; i < cards.length ; i++) {
            cards[i] = Card.builder()
                    .rank(Card.Rank.byValue(cardDTOS[i].getRank()))
                    .suit(Card.Suit.valueOf(cardDTOS[i].getSuit()))
                    .build();
        }
        return CardRequest.of(cards);
    }

    private Card fromCardDTO(CardDTO dto) {
        // TODO: implement this method

        return  Card.builder()
                .rank(Card.Rank.byValue(dto.getRank()))
                .suit(Card.Suit.valueOf(dto.getSuit()))
                .build();
    }
}
