package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.common.exception.ErrorMessage;
import racingcar.presentation.dto.CarListRequestDto;

class ParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    void 자동차_이름_목록을_파싱할_수_있다() {
        // given
        String input = "car1,car2,car3";

        // when
        CarListRequestDto result = parser.parseCarListRequest(input);

        // then
        assertThat(result.carNames()).containsExactly("car1", "car2", "car3");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "   "})
    void 입력값이_빈_문자열이거나_공백만_있을_경우_예외를_던진다(String input) {
        // when & then
        assertThatThrownBy(() -> parser.parseCarListRequest(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorMessage.INPUT_CANNOT_BE_BLANK.toString());
    }

    @Test
    void 단일_자동차_이름을_파싱할_수_있다() {
        String input = "car1";

        // when
        CarListRequestDto result = parser.parseCarListRequest(input);

        // then
        assertThat(result.carNames()).containsExactly("car1");
    }


    @Test
    void 빈_이름은_제거된_후_파싱이_이루어진다() {
        // given
        String input = "car1, car2,,";

        // when
        CarListRequestDto result = parser.parseCarListRequest(input);

        //then
        assertThat(result.carNames()).containsExactly("car1", "car2");
    }

    @Test
    void 빈_이름만_존재하면_예외를_던진다() {
        // given
        String input = ",,,,";

        // when & then
        assertThatThrownBy(() -> parser.parseCarListRequest(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ErrorMessage.CAR_NAME_EMPTY.toString());
    }

    @Test
    void 시도_횟수를_파싱할_수_있다() {
        // given
        String input = "5";

        // when
        Integer result = parser.parseNumberOfAttempts(input);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void 시도_횟수_0_대해_예외가_발생한다() {
        // given
        String input = "0";

        // when & then
        assertThatThrownBy(() -> parser.parseNumberOfAttempts(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_ATTEMPT_COUNT.toString());
    }

    @Test
    void 잘못된_시도_횟수_형식에_대해_예외가_발생한다() {
        // given
        String input = "a";

        // when & then
        assertThatThrownBy(() -> parser.parseNumberOfAttempts(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_ATTEMPT_COUNT.toString());
    }

    @Test
    void 음수_시도_횟수에_대해_예외가_발생한다() {
        // given
        String input = "-1";

        // when & then
        assertThatThrownBy(() -> parser.parseNumberOfAttempts(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_ATTEMPT_COUNT.toString());
    }

}
