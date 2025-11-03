package lotto.application.profit;

import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.LottoWinningResultRepository;
import lotto.domain.result.ProfitRate;

public class CalculateProfitRateService {
    private final LottoWinningResultRepository winningResultRepository;
    private final PurchasedLottosRepository purchasedLottosRepository;

    public CalculateProfitRateService(
            LottoWinningResultRepository winningResultRepository,
            PurchasedLottosRepository purchasedLottosRepository) {
        this.winningResultRepository = winningResultRepository;
        this.purchasedLottosRepository = purchasedLottosRepository;
    }

    public ProfitRateResponse calculateProfitRate() {
        LottoWinningResult winningResult = winningResultRepository.find();
        PurchasedLottos purchasedLottos = purchasedLottosRepository.find();
        ProfitRate profitRate = ProfitRate.from(
                winningResult.totalPrize(),
                purchasedLottos.amount()
        );
        return ProfitRateResponse.from(profitRate);
    }
}
