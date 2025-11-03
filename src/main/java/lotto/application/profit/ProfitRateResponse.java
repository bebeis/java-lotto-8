package lotto.application.profit;

import lotto.domain.result.ProfitRate;

public record ProfitRateResponse(double rate) {

    public static ProfitRateResponse from(ProfitRate profitRate) {
        return new ProfitRateResponse(profitRate.rate());
    }

    public double round() {
        return Math.round(rate * 10) / 10.0;
    }
}
