package racingcar.domain.car;

import racingcar.common.exception.ErrorMessage;
import racingcar.domain.dto.CarStatusDto;

public class Car {
    final static int MAX_NAME_LENGTH = 5;
    private final static int ADVANCE_STEP = 1;
    private final static int INITIAL_DISTANCE = 0;

    private Distance distance;
    private final String carName;

    private Car(String carName) {
        validateCarName(carName);
        this.carName = carName;
        this.distance = new Distance(INITIAL_DISTANCE);
    }

    private void validateCarName(String carName) {
        if (carName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_TOO_LONG.format(MAX_NAME_LENGTH));
        }
        if (carName.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.CAR_NAME_EMPTY.toString());
        }
    }

    public static Car from(String carName) {
        return new Car(carName);
    }

    public void advance() {
        int preDistance = distance.getDistance();
        this.distance = new Distance(preDistance + ADVANCE_STEP);
    }

    public boolean isAtMaxDistance(int maxDistance) {
        return distance.getDistance() == maxDistance;
    }

    public CarStatusDto getStatus() {
        return new CarStatusDto(carName, distance);
    }

    public String getName() {
        return carName;
    }

    public int getDistance() {
        return distance.getDistance();
    }
}
