package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.domain.car.Car.MAX_NAME_LENGTH;

import org.junit.jupiter.api.Test;
import racingcar.common.exception.ErrorMessage;
import racingcar.domain.dto.CarStatusDto;

class CarTest {

    @Test
    void 자동차_이름이_5자를_초과하면_예외가_발생한다() {
        // given
        String longName = "123456";

        // when & then
        assertThatThrownBy(() -> Car.from(longName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.CAR_NAME_TOO_LONG.format(MAX_NAME_LENGTH));
    }

    @Test
    void 자동차_이름이_비어있으면_예외를_던진다() {
        // given
        String emptyName = "";

        // when & then
        assertThatThrownBy(() -> Car.from(emptyName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.CAR_NAME_EMPTY.toString());
    }

    @Test
    void 자동차_이름이_5자_이하면_정상적으로_생성된다() {
        // given
        String validName = "12345";

        // when
        Car car = Car.from(validName);

        // then
        assertThat(car.getName()).isEqualTo(validName);
    }

    @Test
    void 자동차가_전진하면_거리가_1_증가한다() {
        // given
        Car car = Car.from("test");

        // when
        car.advance();

        // then
        CarStatusDto status = car.getStatus();
        assertThat(status.distance().getDistance()).isEqualTo(1);
    }

    @Test
    void 자동차가_여러_번_전진하면_거리가_누적된다() {
        // given
        Car car = Car.from("test");
        int count = 3;
        // when
        for (int i = 0; i < count; i++) {
            car.advance();
        }
        // then
        CarStatusDto status = car.getStatus();
        assertThat(status.distance().getDistance()).isEqualTo(count);
    }

    @Test
    void 자동차가_최대_거리에_있는지_확인할_수_있다() {
        // given
        Car car = Car.from("test");
        car.advance();
        car.advance();
        int maxDistance = 2;
        // when & then
        assertThat(car.isAtMaxDistance(maxDistance)).isTrue();
        assertThat(car.isAtMaxDistance(maxDistance - 1)).isFalse();
    }
}
