package lotto.domain.result;

import lotto.common.ErrorMessage;

public enum ResultErrorMessage implements ErrorMessage {
    INVALID_PURCHASE_AMOUNT_FOR_PROFIT("수익률을 계산하려면 구매 금액이 0원보다 커야 합니다"),
    BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBERS("보너스 번호는 당첨 번호와 중복될 수 없습니다");

    private final String message;

    ResultErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
