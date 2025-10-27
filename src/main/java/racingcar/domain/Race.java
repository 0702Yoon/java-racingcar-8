package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.common.exception.ErrorMessage;
import racingcar.domain.car.Car;
import racingcar.domain.dto.CarStatusDto;
import racingcar.domain.strategy.MoveStrategy;

public class Race {
    private final List<Car> carList;

    private Race(List<Car> carList) {
        validateCarNamesDuplication(carList);
        this.carList = carList;
    }

    public static Race startWith(List<Car> carList) {
        return new Race(carList);
    }

    public List<CarStatusDto> getCurrentStatue() {
        List<CarStatusDto> carStatusDtoList = new ArrayList<>();
        for (Car car : carList) {
            carStatusDtoList.add(car.getStatus());
        }
        return carStatusDtoList;
    }

    public void moveCarsIfPossible(MoveStrategy moveStrategy) {
        for (Car car : carList) {
            if (moveStrategy.isMovable()) {
                car.advance();
            }
        }
    }

    public List<Car> getWinners() {
        int maxDistance = carList.stream()
            .mapToInt(Car::getDistance)
            .max()
            .orElse(0);

        List<Car> winners = new ArrayList<>();
        for (Car car : carList) {
            if (car.isAtMaxDistance(maxDistance)) {
                winners.add(car);
            }
        }
        return winners;
    }

    private void validateCarNamesDuplication(List<Car> cars) {

        Set<String> uniqueCarNames = new HashSet<>();

        for (Car car : cars) {
            uniqueCarNames.add(car.getName());
        }

        if (uniqueCarNames.size() != cars.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_CAR_NAME.toString());
        }
    }
}
