package io.guidemy.learn.pokercardpatterndetector;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.guidemy.learn.pokercardpatterndetector.dto.CardDTO;
import io.guidemy.learn.pokercardpatterndetector.dto.CardWrapperDTO;
import io.guidemy.learn.pokercardpatterndetector.dto.ResultDTO;
import io.guidemy.learn.pokercardpatterndetector.model.PatternType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DetectionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("provideCardsForTesting")
    public void testDetectPattern(CardDTO[] cards, PatternType expectedPattern) throws Exception {
        CardWrapperDTO wrapperDTO = new CardWrapperDTO();
        wrapperDTO.setCards(cards);

        ResultDTO expectedResult = ResultDTO.builder().result(expectedPattern).build();

        mockMvc.perform(post("/api/detect")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(wrapperDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)));
    }

    private static Stream<Arguments> provideCardsForTesting() {
        return Stream.of(
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                        }, PatternType.CHEAT),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("SPADE").rank(10).build(),
                                CardDTO.builder().suit("SPADE").rank(11).build(),
                                CardDTO.builder().suit("SPADE").rank(12).build(),
                                CardDTO.builder().suit("SPADE").rank(13).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                        }, PatternType.ROYAL_FLUSH),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(5).build(),
                                CardDTO.builder().suit("HEART").rank(6).build(),
                                CardDTO.builder().suit("HEART").rank(7).build(),
                                CardDTO.builder().suit("HEART").rank(8).build(),
                                CardDTO.builder().suit("HEART").rank(9).build(),
                        }, PatternType.STRAIGHT_FLUSH),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(4).build(),
                                CardDTO.builder().suit("CLUB").rank(4).build(),
                                CardDTO.builder().suit("DIAMOND").rank(4).build(),
                                CardDTO.builder().suit("SPADE").rank(4).build(),
                                CardDTO.builder().suit("HEART").rank(9).build(),
                        }, PatternType.FOUR_OF_A_KIND),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(3).build(),
                                CardDTO.builder().suit("CLUB").rank(3).build(),
                                CardDTO.builder().suit("DIAMOND").rank(3).build(),
                                CardDTO.builder().suit("SPADE").rank(5).build(),
                                CardDTO.builder().suit("HEART").rank(5).build(),
                        }, PatternType.FULL_HOUSE),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("SPADE").rank(9).build(),
                                CardDTO.builder().suit("SPADE").rank(11).build(),
                                CardDTO.builder().suit("SPADE").rank(12).build(),
                                CardDTO.builder().suit("SPADE").rank(13).build(),
                                CardDTO.builder().suit("SPADE").rank(14).build(),
                        }, PatternType.FLUSH),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(2).build(),
                                CardDTO.builder().suit("CLUB").rank(3).build(),
                                CardDTO.builder().suit("DIAMOND").rank(4).build(),
                                CardDTO.builder().suit("SPADE").rank(5).build(),
                                CardDTO.builder().suit("HEART").rank(6).build(),
                        }, PatternType.STRAIGHT),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(7).build(),
                                CardDTO.builder().suit("CLUB").rank(7).build(),
                                CardDTO.builder().suit("DIAMOND").rank(7).build(),
                                CardDTO.builder().suit("SPADE").rank(4).build(),
                                CardDTO.builder().suit("HEART").rank(2).build(),
                        }, PatternType.THREE_OF_A_KIND),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(9).build(),
                                CardDTO.builder().suit("CLUB").rank(9).build(),
                                CardDTO.builder().suit("DIAMOND").rank(2).build(),
                                CardDTO.builder().suit("SPADE").rank(2).build(),
                                CardDTO.builder().suit("HEART").rank(7).build(),
                        }, PatternType.TWO_PAIR),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(4).build(),
                                CardDTO.builder().suit("CLUB").rank(4).build(),
                                CardDTO.builder().suit("DIAMOND").rank(3).build(),
                                CardDTO.builder().suit("SPADE").rank(9).build(),
                                CardDTO.builder().suit("HEART").rank(10).build(),
                        }, PatternType.ONE_PAIR),
                Arguments.of(
                        new CardDTO[]{
                                CardDTO.builder().suit("HEART").rank(4).build(),
                                CardDTO.builder().suit("CLUB").rank(8).build(),
                                CardDTO.builder().suit("DIAMOND").rank(3).build(),
                                CardDTO.builder().suit("SPADE").rank(9).build(),
                                CardDTO.builder().suit("HEART").rank(2).build(),
                        }, PatternType.NO_PATTERN)
                );
    }
}