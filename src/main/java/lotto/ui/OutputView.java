package lotto.ui;

import lotto.application.profit.ProfitRateResponse;
import lotto.application.purchase.PurchasedLottoResponse;
import lotto.application.winning.LottoWinningResultResponse;

public interface OutputView {
    
    void printPurchasedLottos(PurchasedLottoResponse response);

    void printWinningResult(LottoWinningResultResponse response);

    void printProfitRate(ProfitRateResponse response);

    void printError(String message);
}
