package lotto.domain.purchase;

import lotto.common.ErrorMessage;

public enum PurchaseErrorMessage implements ErrorMessage {
    INVALID_PURCHASE_AMOUNT_UNIT("구매 금액은 %d원 단위로 나누어 떨어져야 합니다"),
    PURCHASE_AMOUNT_BELOW_MINIMUM("구매 금액은 %d원 이상이어야 합니다"),
    INVALID_LOTTO_NUMBER_COUNT("로또에 포함된 숫자의 개수는 %d개여야 합니다"),
    DUPLICATED_LOTTO_NUMBER("로또에 중복된 숫자가 존재합니다");

    private final String message;

    PurchaseErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
