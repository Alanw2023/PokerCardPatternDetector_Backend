package io.guidemy.learn.pokercardpatterndetector.controller;

import io.guidemy.learn.pokercardpatterndetector.dto.CardWrapperDTO;
import io.guidemy.learn.pokercardpatterndetector.dto.ResultDTO;
import io.guidemy.learn.pokercardpatterndetector.mapper.CardRequestMapper;
import io.guidemy.learn.pokercardpatterndetector.model.CardRequest;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import io.guidemy.learn.pokercardpatterndetector.predicate.PatternPredicate;
import io.guidemy.learn.pokercardpatterndetector.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/detect")
public class DetectionController {

    // TODO: inject the required bean(s)
    @Autowired
private DetectionService detectionService;
    @Autowired
    private CardRequestMapper cardRequestMapper;
    @PostMapping
    public ResultDTO detect(@RequestBody CardWrapperDTO wrapperDTO) {
        // TODO: implement this method
        CardRequest cardRequest = cardRequestMapper.fromCardWrapperDTO(wrapperDTO);
        PatternType patternType = detectionService.detect(cardRequest);

        return ResultDTO.builder().result(patternType).build();
    }
}
