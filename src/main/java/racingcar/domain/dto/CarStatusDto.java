package racingcar.domain.dto;

import racingcar.domain.car.Distance;

public record CarStatusDto(
    String carName,
    Distance distance
) {
}
