package io.guidemy.learn.pokercardpatterndetector.predicate;


import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;

public interface PatternPredicate {
    boolean detect(CardRequest request);

    PatternType getType();
}
