package lotto.domain.shared;

import lotto.common.CommonErrorMessage;

public record LottoNumber(int number) {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    public LottoNumber {
        validateNumberInRange(number);
    }

    private void validateNumberInRange(final int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(CommonErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.message()
                    .formatted(MINIMUM_NUMBER, MAXIMUM_NUMBER));
        }
    }
}
