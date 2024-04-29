package io.guidemy.learn.pokercardpatterndetector.dto;

import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import lombok.Builder;
import lombok.Data;

/*
    Do not modify this class
 */

@Data
@Builder
public class ResultDTO {
    private PatternType result;
}
