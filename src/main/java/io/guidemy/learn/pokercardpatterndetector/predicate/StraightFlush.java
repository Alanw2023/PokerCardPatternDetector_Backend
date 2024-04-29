package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(3)
public class StraightFlush implements PatternPredicate {
    @Override
    public boolean detect(CardRequest request) {
        return request.getHasFlush();
    }

    @Override
    public PatternType getType() {
        return PatternType.STRAIGHT_FLUSH;
    }
}
