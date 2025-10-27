package racingcar.controller;

import java.util.List;
import racingcar.domain.Race;
import racingcar.domain.dto.RaceExecutionResultDto;
import racingcar.presentation.View;
import racingcar.presentation.dto.CarListRequestDto;
import racingcar.service.RaceService;

public class RaceController {
    private final View view;
    private final Parser parser;
    private final RaceService raceService;


    public RaceController(View view, Parser parser, RaceService raceService) {
        this.view = view;
        this.parser = parser;
        this.raceService = raceService;
    }

    public void run() {
        String message = view.requestCarNames();
        CarListRequestDto carListRequestDto = parser.parseCarListRequest(message);
        Race race = raceService.createRace(carListRequestDto);

        message = view.requestNumberOfAttempts();
        int numberOfAttempts = parser.parseNumberOfAttempts(message);
        List<RaceExecutionResultDto> raceExecutionResultDtos = raceService.playRace(race, numberOfAttempts);

        view.showRaceExecutionResult(raceExecutionResultDtos);

        List<String> winners = raceService.getWinners(race);
        view.showResult(winners);
    }
}
