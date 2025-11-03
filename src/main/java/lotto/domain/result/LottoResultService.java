package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;

import java.util.List;

public class LottoResultService {

    public LottoWinningResult calculateWinningResult(
            List<Lotto> purchasedLottos,
            List<Integer> winningNumbers,
            int bonusNumber) {

        List<LottoNumber> lottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .toList();

        Lotto winningLotto = new Lotto(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);
        WinningLotto winning = new WinningLotto(winningLotto, bonusLottoNumber);

        return new LottoWinningResult(winning, purchasedLottos);
    }

    public ProfitRate calculateProfitRate(
            LottoWinningResult winningResult,
            int purchaseAmount) {
        return ProfitRate.from(winningResult.totalPrize(), purchaseAmount);
    }
}
