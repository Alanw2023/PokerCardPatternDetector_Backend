package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(8)
public class TwoPairPredicate implements PatternPredicate {


    @Override
    public boolean detect(CardRequest request) {

        int pairs = 0;

        for (Integer count : request.getRankCount().values()) {
            if (count >= 2) {
                pairs++;
            }

            if (pairs >= 2) {
                return true;

            }
        }

        return false;
    }

    @Override
    public PatternType getType() {
        return PatternType.TWO_PAIR;
    }


}
