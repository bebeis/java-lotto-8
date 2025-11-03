package lotto.stub;

import lotto.application.profit.ProfitRateResponse;
import lotto.application.purchase.PurchasedLottoResponse;
import lotto.application.winning.LottoWinningResultResponse;
import lotto.ui.OutputView;

public class SpyOutputView implements OutputView {

    // 검증 대상 - 제대로 전달받았는지
    public PurchasedLottoResponse purchasedLottoResponse;
    public LottoWinningResultResponse lottoWinningResultResponse;
    public ProfitRateResponse profitRateResponse;

    @Override
    public void printPurchasedLottos(final PurchasedLottoResponse response) {
        this.purchasedLottoResponse = response;
    }

    @Override
    public void printWinningResult(final LottoWinningResultResponse response) {
        this.lottoWinningResultResponse = response;
    }

    @Override
    public void printProfitRate(final ProfitRateResponse response) {
        this.profitRateResponse = response;
    }

    @Override
    public void printError(final String message) {
        // Spy에서는 무시
    }
}
