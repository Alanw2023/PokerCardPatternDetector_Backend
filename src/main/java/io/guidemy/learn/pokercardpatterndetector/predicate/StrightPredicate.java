package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(6)
public class StrightPredicate implements PatternPredicate {
    @Override
    public boolean detect(CardRequest request) {
        return request.getHasStraight();
    }

    @Override
    public PatternType getType() {
        return PatternType.STRAIGHT;
    }
}
