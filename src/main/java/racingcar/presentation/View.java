package racingcar.presentation;

import java.util.List;
import racingcar.domain.dto.RaceExecutionResultDto;
import racingcar.presentation.formatter.RaceFormatter;
import racingcar.presentation.input.Input;
import racingcar.presentation.output.ConsoleOutput;

public class View {

    private final Input input;
    private final ConsoleOutput consoleOutput;
    private final RaceFormatter raceFormatter;

    public View(Input input, ConsoleOutput consoleOutput, RaceFormatter raceFormatter) {
        this.input = input;
        this.consoleOutput = consoleOutput;
        this.raceFormatter = raceFormatter;
    }

    public String requestCarNames() {
        consoleOutput.printAskCarNames();
        return input.readLine();
    }

    public String requestNumberOfAttempts() {
        consoleOutput.printAskNumberOfAttempts();
        return input.readLine();
    }

    public void showRaceExecutionResult(List<RaceExecutionResultDto> raceExecutionResultDtos) {
        String formatMessage = raceFormatter.format(raceExecutionResultDtos);
        consoleOutput.println(formatMessage);
    }

    public void showResult(List<String> winners) {
        String formatMessage = raceFormatter.formatWinner(winners);
        consoleOutput.println(formatMessage);
    }
}
