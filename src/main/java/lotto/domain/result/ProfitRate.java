package lotto.domain.result;


public record ProfitRate(double rate) {

    public static ProfitRate from(long totalPrize, long purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        double rate = ((double) totalPrize / purchaseAmount) * 100;
        return new ProfitRate(rate);
    }

    private static void validatePurchaseAmount(final long purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ResultErrorMessage.INVALID_PURCHASE_AMOUNT_FOR_PROFIT
                    .message());
        }
    }

    public double round() {
        return Math.round(rate * 10) / 10.0;
    }
}
