package racingcar.common.exception;


public enum ErrorMessage {

    CAR_NAME_TOO_LONG("자동차 이름은 %d 이하여야 합니다."),
    CAR_NAME_EMPTY("자동차 이름은 비어 있을 수 없습니다."),
    INVALID_ATTEMPT_COUNT("시도 횟수는 양수여야 합니다."),
    DUPLICATE_CAR_NAME("자동차 이름은 중복될 수 없습니다."),
    INPUT_CANNOT_BE_BLANK("입력값은 비어있거나 공백만으로 이루어질 수 없습니다."),
    DISTANCE_NOT_NEGATIVE("거리는 음수가 될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

    @Override
    public String toString() {
        return message;
    }
}