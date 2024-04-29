package io.guidemy.learn.pokercardpatterndetector.predicate;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    Do not modify this class
 */
@Component
@Order(7)
public class ThreeOfAKind implements PatternPredicate {

    public boolean detect(CardRequest request) {
        for (Integer count : request.getRankCount().values()) {
            if (count >= 3) {
                return true;
            }

        }
        return false;
    }

    @Override
    public PatternType getType() {
        return PatternType.THREE_OF_A_KIND;
    }
}
