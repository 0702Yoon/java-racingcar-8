package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {
    final static Integer RANDOM_MIN_NUMBER = 0;
    final static Integer RANDOM_MAX_NUMBER = 9;
    final static Integer GO_CONDITION = 4;

    @Override
    public boolean isMovable() {
        int randomValue = Randoms.pickNumberInRange(RANDOM_MIN_NUMBER, RANDOM_MAX_NUMBER);
        return randomValue >= GO_CONDITION;
    }
}
