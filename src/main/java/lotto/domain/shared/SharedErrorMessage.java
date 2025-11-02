package lotto.domain.shared;

import lotto.common.ErrorMessage;

public enum SharedErrorMessage implements ErrorMessage {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.");

    private final String message;

    SharedErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
