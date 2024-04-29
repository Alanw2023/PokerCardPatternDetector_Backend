package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(9)
public class OnePairPredicate implements PatternPredicate {

    @Override
    public boolean detect(CardRequest request) {
        for (Integer count : request.getRankCount().values()) {
            if (count >= 2) {
                return true;
            }

        }
        return false;
    }

    @Override
    public PatternType getType() {
        return PatternType.ONE_PAIR;
    }
}
