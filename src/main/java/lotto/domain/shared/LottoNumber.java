package lotto.domain.shared;

public record LottoNumber(int number) {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

    public LottoNumber {
        validateNumberInRange(number);
    }

    private void validateNumberInRange(final int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(SharedErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.message()
                    .formatted(MINIMUM_NUMBER, MAXIMUM_NUMBER));
        }
    }
}
