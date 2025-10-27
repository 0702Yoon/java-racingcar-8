package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.common.exception.ErrorMessage;

class DistanceTest {
    @Test
    @DisplayName("0 거리 객체를 생성할 수 있다")
    void createZeroDistance() {
        // given
        Integer distance = 0;

        // when
        Distance distanceObj = new Distance(distance);

        // then
        assertThat(distanceObj.getDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("음수 거리 객체를 생성할 수 없다.")
    void createNegativeDistance() {
        // given
        int distance = -1;

        // when & then
        assertThatThrownBy(() -> new Distance(distance)).isInstanceOf(IllegalArgumentException.class
        ).hasMessageContaining(ErrorMessage.DISTANCE_NOT_NEGATIVE.toString());
    }
}
