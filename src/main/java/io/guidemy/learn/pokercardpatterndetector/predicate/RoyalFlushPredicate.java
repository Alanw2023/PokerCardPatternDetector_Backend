package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(1)
public class RoyalFlushPredicate implements PatternPredicate {
    @Override
    public boolean detect(CardRequest request) {
        return request.getHasFlush() && request.getHasLargestStraight();
    }

    @Override
    public PatternType getType() {
        return PatternType.ROYAL_FLUSH;
    }
}
