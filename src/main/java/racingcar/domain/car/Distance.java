package racingcar.domain.car;

import racingcar.common.exception.ErrorMessage;

public class Distance {

    private final int distance;

    public Distance(int distance) {
        validNegativeDistance(distance);
        this.distance = distance;
    }

    public int getDistance() {
        return this.distance;
    }

    private void validNegativeDistance(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException(ErrorMessage.DISTANCE_NOT_NEGATIVE.toString());
        }
    }
}
