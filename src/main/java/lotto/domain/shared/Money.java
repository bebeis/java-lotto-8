package lotto.domain.shared;

public record Money(int amount) {
    private static final int LOWER_LIMIT = 0;

    public Money {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < LOWER_LIMIT) {
            throw new IllegalArgumentException(SharedErrorMessage.MONEY_NEGATIVE_VALUE.message());
        }
    }
}
