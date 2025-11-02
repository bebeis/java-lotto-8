package lotto.domain.purchase;

import lotto.common.ErrorMessage;

public enum PurchaseErrorMessage implements ErrorMessage {
    INVALID_PURCHASE_AMOUNT_UNIT("구매 금액은 %d원 단위로 나누어 떨어져야 합니다"),
    PURCHASE_AMOUNT_BELOW_MINIMUM("구매 금액은 %d원 이상이어야 합니다");

    private final String message;

    PurchaseErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
