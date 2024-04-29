package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.Card;
import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
    Do not modify this class
 */
@Component
@Order(4)
public class FullHouse implements PatternPredicate {


    @Override
    public boolean detect(CardRequest request) {
        int pairs = 0;
        int triplets = 0;

        for (Integer count : request.getRankCount().values()) {
            if (count == 2) {
                pairs++;
            }

            if (count == 3) {
                triplets++;
            }
        }

        return pairs == 1 && triplets == 1;
    }






    @Override
    public PatternType getType() {
        return PatternType.FULL_HOUSE;
    }


}
