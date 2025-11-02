package lotto.domain.purchase;

public record PurchaseAmount(int amount) {
    public static final int LOTTO_PRICE = 1000;

    public PurchaseAmount {
        validateGreaterThanMinimumAmount(amount);
        validateAmountUnit(amount);
    }

    private void validateGreaterThanMinimumAmount(final int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(PurchaseErrorMessage.PURCHASE_AMOUNT_BELOW_MINIMUM
                    .message()
                    .formatted(LOTTO_PRICE));
        }
    }

    private void validateAmountUnit(final int amount) {
        if (isInvalidPurchaseUnit(amount)) {
            throw new IllegalArgumentException(PurchaseErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT
                    .message()
                    .formatted(LOTTO_PRICE));
        }
    }

    private boolean isInvalidPurchaseUnit(final int amount) {
        return amount % LOTTO_PRICE != 0;
    }

    public int lottoCount() {
        return amount / LOTTO_PRICE;
    }
}
