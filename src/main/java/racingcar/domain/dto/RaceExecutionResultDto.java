package racingcar.domain.dto;

import java.util.List;

public record RaceExecutionResultDto(
    List<CarStatusDto> carStatusDtoList
) {
}
