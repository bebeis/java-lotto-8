package lotto.infrastructure;

import lotto.common.ErrorMessage;

public enum InfraErrorMessage implements ErrorMessage {
    EMPTY_PURCHASED_LOTTO("구매한 로또가 없습니다."),
    EMPTY_LOTTO_WINNING_RESULT("당첨 번호 추첨 결과가 없습니다.");

    private final String message;

    InfraErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
