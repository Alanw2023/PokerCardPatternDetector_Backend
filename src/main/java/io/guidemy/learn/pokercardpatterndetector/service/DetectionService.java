package io.guidemy.learn.pokercardpatterndetector.service;

import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import io.guidemy.learn.pokercardpatterndetector.predicate.PatternPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetectionService {
    // TODO: inject the required bean(s)
    @Autowired
    private List<PatternPredicate> patternPredicates;

    public PatternType detect(CardRequest cardRequest) {
        // TODO: implement this method
        for (PatternPredicate patternPredicate : patternPredicates){
            if (patternPredicate.detect(cardRequest)){
                return patternPredicate.getType();
            }
        }



        return PatternType.NO_PATTERN;
    }
}
