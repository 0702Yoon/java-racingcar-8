package racingcar.presentation.output;

public class ConsoleOutput {
    static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    static final String ASK_NUMBER_OF_ATTEMPTS_MESSAGE = "시도할 회수는 몇회인가요?";

    public void printAskCarNames() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);
    }

    public void printAskNumberOfAttempts() {
        System.out.println(ASK_NUMBER_OF_ATTEMPTS_MESSAGE);
    }

    public void println(String message) {
        System.out.println(message);
    }
}
