package racingcar;

import racingcar.common.config.AppConfig;
import racingcar.controller.RaceController;

public class Application {

    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();
        RaceController raceController = appConfig.raceController();
        raceController.run();
    }
}
