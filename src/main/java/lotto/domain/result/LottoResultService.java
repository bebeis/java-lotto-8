package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoValidator;

import java.util.List;

public class LottoResultService {
    private final LottoValidator lottoValidator;

    public LottoResultService(LottoValidator lottoValidator) {
        this.lottoValidator = lottoValidator;
    }

    public void validateWinningNumbers(List<Integer> winningNumbers) {
        lottoValidator.validateNumbers(winningNumbers);
    }

    public LottoWinningResult calculateWinningResult(
            List<Lotto> purchasedLottos,
            List<Integer> winningNumbers,
            int bonusNumber) {

        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);

        return new LottoWinningResult(winning, purchasedLottos);
    }

    public ProfitRate calculateProfitRate(
            LottoWinningResult winningResult,
            int purchaseAmount) {
        return ProfitRate.from(winningResult.totalPrize(), purchaseAmount);
    }
}
