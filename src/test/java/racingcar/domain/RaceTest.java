package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.common.exception.ErrorMessage;
import racingcar.domain.car.Car;
import racingcar.domain.dto.CarStatusDto;
import racingcar.domain.strategy.FixMoveStrategy;
import racingcar.domain.strategy.MoveStrategy;

class RaceTest {

    @Test
    void 자동차_목록으로_Cars_객체를_생성할_수_있다() {
        // given
        int count = 3;
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carList.add(Car.from("car" + i));
        }
        // when
        Race race = Race.startWith(carList);

        // then
        List<CarStatusDto> statuses = race.getCurrentStatue();
        assertThat(statuses).hasSize(count);
        for (int i = 0; i < count; i++) {
            assertThat(statuses.get(i).carName()).isEqualTo("car" + i);
        }
    }

    @Test
    void 중복된_자동차_이름에_대해_예외가_발생한다() {
        // given
        List<Car> carList = List.of(Car.from("car"), Car.from("car"));

        // when & then
        assertThatThrownBy(() -> Race.startWith(carList))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.DUPLICATE_CAR_NAME.toString());
    }

    @Test
    void 모든_자동차가_같은_거리에_있으면_모두_우승자다() {
        // given
        int count = 3;
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carList.add(Car.from("car" + i));
        }
        Race race = Race.startWith(carList);
        MoveStrategy fixedStrategy = new FixMoveStrategy(true);
        for (int i = 0; i < count; i++) {
            race.moveCarsIfPossible(fixedStrategy);
        }

        // when
        List<Car> winners = race.getWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners.size()).isEqualTo(count);
    }
}
