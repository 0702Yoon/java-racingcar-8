package racingcar.domain.strategy;

public class FixMoveStrategy implements MoveStrategy {
    private final boolean fixedResult;

    public FixMoveStrategy(boolean fixedResult) {
        this.fixedResult = fixedResult;
    }

    @Override
    public boolean isMovable() {
        return fixedResult;
    }
}
