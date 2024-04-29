package io.guidemy.learn.pokercardpatterndetector.dto;

import lombok.Builder;
import lombok.Data;

/*
    Do not modify this class
 */

@Data
@Builder
public class CardDTO {
    private String suit;
    private Integer rank;
}
