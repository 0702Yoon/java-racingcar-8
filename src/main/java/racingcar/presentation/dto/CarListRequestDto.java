package racingcar.presentation.dto;

import java.util.List;

public record CarListRequestDto(
    List<String> carNames
) {

}
