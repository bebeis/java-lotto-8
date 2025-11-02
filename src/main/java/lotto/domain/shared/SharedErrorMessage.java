package lotto.domain.shared;

import lotto.common.ErrorMessage;

public enum SharedErrorMessage implements ErrorMessage {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 %d부터 %d 사이의 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또에 포함된 숫자의 개수는 %d개여야 합니다"),
    DUPLICATED_LOTTO_NUMBER("로또에 중복된 숫자가 존재합니다");

    private final String message;

    SharedErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
