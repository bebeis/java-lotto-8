package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateBonusNumberNotDuplicated(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(final List<Integer> winningNumbers, final int bonusNumber) {
        List<LottoNumber> lottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .toList();
        Lotto winningLotto = new Lotto(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);
        return new WinningLotto(winningLotto, bonusLottoNumber);
    }

    private void validateBonusNumberNotDuplicated(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ResultErrorMessage.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBERS
                    .message());
        }
    }

    public Rank findRankOf(final Lotto purchasedLotto) {
        int matchCount = winningLotto.matchCountWith(purchasedLotto);
        boolean bonusMatched = purchasedLotto.contains(bonusNumber);

        return Rank.findRankBy(matchCount, bonusMatched);
    }
}
