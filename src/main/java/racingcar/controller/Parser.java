package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import racingcar.common.exception.ErrorMessage;
import racingcar.presentation.dto.CarListRequestDto;

public class Parser {
    private static final String CAR_NAME_DELIMITER = ",";
    private static final int ATTEMPT_MIN_CONDITION = 0;

    public CarListRequestDto parseCarListRequest(String line) {
        validateInputIsPresent(line);

        String[] carNames = line.split(CAR_NAME_DELIMITER);

        List<String> list = Arrays.stream(carNames)
            .map(String::trim)
            .filter(name -> !name.isEmpty())
            .toList();
        validateCarNames(list);

        return new CarListRequestDto(list);
    }

    private void validateCarNames(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_EMPTY.toString());
        }
    }

    public int parseNumberOfAttempts(String clientMessage) {
        validateInputIsPresent(clientMessage);

        try {
            int attempts = Integer.parseInt(clientMessage.trim());
            if (attempts <= ATTEMPT_MIN_CONDITION) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ATTEMPT_COUNT.toString());
            }
            return attempts;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ATTEMPT_COUNT.toString());
        }
    }

    private void validateInputIsPresent(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CANNOT_BE_BLANK.toString());
        }
    }
}
