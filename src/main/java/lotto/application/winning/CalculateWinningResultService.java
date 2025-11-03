package lotto.application.winning;

import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.domain.result.LottoResultService;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.LottoWinningResultRepository;

import java.util.List;

public class CalculateWinningResultService {
    private final LottoResultService resultService;
    private final PurchasedLottosRepository purchasedLottosRepository;
    private final LottoWinningResultRepository winningResultRepository;

    public CalculateWinningResultService(
            LottoResultService resultService,
            PurchasedLottosRepository purchasedLottosRepository,
            LottoWinningResultRepository winningResultRepository) {
        this.resultService = resultService;
        this.purchasedLottosRepository = purchasedLottosRepository;
        this.winningResultRepository = winningResultRepository;
    }

    public LottoWinningResultResponse calculateWinningResult(
            List<Integer> winningNumbers,
            int bonusNumber) {
        PurchasedLottos purchasedLottos = purchasedLottosRepository.find();
        LottoWinningResult winningResult = resultService.calculateWinningResult(
                purchasedLottos.lottos(),
                winningNumbers,
                bonusNumber
        );
        winningResultRepository.save(winningResult);
        return LottoWinningResultResponse.from(winningResult);
    }
}
