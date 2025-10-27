package racingcar.common.config;

import racingcar.controller.Parser;
import racingcar.controller.RaceController;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.presentation.View;
import racingcar.presentation.formatter.RaceFormatter;
import racingcar.presentation.input.ConsoleInput;
import racingcar.presentation.input.Input;
import racingcar.presentation.output.ConsoleOutput;
import racingcar.service.RaceService;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private AppConfig() {
    }

    public RaceController raceController() {
        return new RaceController(view(), parser(), raceService());
    }


    private View view() {
        return new View(input(), consoleOutput(), raceFormatter());
    }

    private Input input() {
        return new ConsoleInput();
    }

    private ConsoleOutput consoleOutput() {
        return new ConsoleOutput();
    }

    private RaceFormatter raceFormatter() {
        return new RaceFormatter();
    }

    private Parser parser() {
        return new Parser();
    }

    private RaceService raceService() {
        return new RaceService(moveStrategy());
    }

    private MoveStrategy moveStrategy() {
        return new RandomMoveStrategy();
    }
}
