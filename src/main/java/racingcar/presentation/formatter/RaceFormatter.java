package racingcar.presentation.formatter;

import java.util.List;
import racingcar.domain.dto.RaceExecutionResultDto;

public class RaceFormatter {
    static final String DISTANCE_EXPRESSION = "-";
    static final String EXECUTION_RESULT_HEADER = "실행 결과";
    static final String CAR_STATUS_SEPARATOR = " : ";
    static final String LINE_SEPARATOR = "\n";
    static final String ROUND_SEPARATOR = "\n";
    static final String WINNER_PREFIX = "최종 우승자 : ";
    static final String WINNER_SEPARATOR = ", ";

    public String format(List<RaceExecutionResultDto> raceExecutionResultDtos) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXECUTION_RESULT_HEADER).append(LINE_SEPARATOR);

        for (var result : raceExecutionResultDtos) {
            for (var carStatus : result.carStatusDtoList()) {
                String distanceDisplay = formatDistance(carStatus.distance().getDistance());
                sb.append(carStatus.carName())
                    .append(CAR_STATUS_SEPARATOR)
                    .append(distanceDisplay)
                    .append(LINE_SEPARATOR);
            }
            sb.append(ROUND_SEPARATOR);
        }
        return sb.toString();
    }

    public String formatWinner(List<String> winners) {
        String joinedWinners = String.join(WINNER_SEPARATOR, winners);
        return WINNER_PREFIX + joinedWinners;
    }

    private String formatDistance(int distance) {
        return DISTANCE_EXPRESSION.repeat(Math.max(0, distance));
    }
}
