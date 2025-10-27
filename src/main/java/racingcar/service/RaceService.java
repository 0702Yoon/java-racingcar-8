package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Race;
import racingcar.domain.car.Car;
import racingcar.domain.dto.CarStatusDto;
import racingcar.domain.dto.RaceExecutionResultDto;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.presentation.dto.CarListRequestDto;

public class RaceService {
    private final MoveStrategy moveStrategy;

    public RaceService(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public Race createRace(CarListRequestDto carListRequestDto) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carListRequestDto.carNames()) {
            cars.add(Car.from(carName));
        }
        return Race.startWith(cars);
    }

    public List<RaceExecutionResultDto> playRace(Race race, int attempts) {
        List<RaceExecutionResultDto> result = new ArrayList<>();
        for (int i = 0; i < attempts; i++) {
            race.moveCarsIfPossible(moveStrategy);
            List<CarStatusDto> currentStatus = race.getCurrentStatue();
            result.add(new RaceExecutionResultDto(currentStatus));
        }
        return result;
    }

    public List<String> getWinners(Race race) {
        return race.getWinners().stream()
            .map(Car::getName)
            .toList();
    }
}
