package racingcar.presentation.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Distance;
import racingcar.domain.dto.CarStatusDto;
import racingcar.domain.dto.RaceExecutionResultDto;

class RaceFormatterTest {

    private RaceFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new RaceFormatter();
    }

    @Test
    void 레이스_실행_결과_포맷팅_테스트() {
        // given
        CarStatusDto car1 = new CarStatusDto("car1", new Distance(2));
        CarStatusDto car2 = new CarStatusDto("car2", new Distance(1));
        RaceExecutionResultDto result = new RaceExecutionResultDto(List.of(car1, car2));

        // when
        String formatted = formatter.format(List.of(result));

        // then
        String expected =
            RaceFormatter.EXECUTION_RESULT_HEADER + "\n" +
                formatRound(car1, car2) + "\n";

        assertThat(formatted).isEqualTo(expected);
    }

    @Test
    void 여러_라운드_결과_포맷팅_테스트() {
        // given
        CarStatusDto round1Car1 = new CarStatusDto("car1", new Distance(1));
        CarStatusDto round1Car2 = new CarStatusDto("car2", new Distance(0));
        RaceExecutionResultDto round1 = new RaceExecutionResultDto(List.of(round1Car1, round1Car2));

        CarStatusDto round2Car1 = new CarStatusDto("car1", new Distance(2));
        CarStatusDto round2Car2 = new CarStatusDto("car2", new Distance(1));
        RaceExecutionResultDto round2 = new RaceExecutionResultDto(List.of(round2Car1, round2Car2));

        // when
        String formatted = formatter.format(List.of(round1, round2));

        // then
        String expected =
            RaceFormatter.EXECUTION_RESULT_HEADER + "\n" +
                formatRound(round1Car1, round1Car2) + "\n" +
                formatRound(round2Car1, round2Car2) + "\n";

        assertThat(formatted).isEqualTo(expected);
    }
    
    @Test
    void 거리_0_표시_테스트() {
        // given
        RaceExecutionResultDto resultZero = new RaceExecutionResultDto(
            List.of(new CarStatusDto("car1", new Distance(0))));

        // when
        String formattedZero = formatter.format(List.of(resultZero));

        // then
        assertThat(formattedZero).contains("car1" + RaceFormatter.CAR_STATUS_SEPARATOR);
    }

    @Test
    void 우승자_포맷팅_테스트() {
        // given
        List<String> winners = List.of("car1", "car2");

        // when
        String formatted = formatter.formatWinner(winners);

        // then
        assertThat(formatted).isEqualTo(RaceFormatter.WINNER_PREFIX +
            "car1" + RaceFormatter.WINNER_SEPARATOR + "car2");
    }

    private String formatRound(CarStatusDto... cars) {
        StringBuilder sb = new StringBuilder();
        for (CarStatusDto car : cars) {
            sb.append(car.carName())
                .append(RaceFormatter.CAR_STATUS_SEPARATOR)
                .append(RaceFormatter.DISTANCE_EXPRESSION.repeat(car.distance().getDistance()))
                .append("\n");
        }
        return sb.toString();
    }
}