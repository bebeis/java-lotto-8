package lotto.domain.vo;

import lotto.common.CommonErrorMessage;

public record Money(int amount) {
    private static final int LOWER_LIMIT = 0;

    public Money {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < LOWER_LIMIT) {
            throw new IllegalArgumentException(CommonErrorMessage.MONEY_NEGATIVE_VALUE.message());
        }
    }
}
