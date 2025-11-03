package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateBonusNumberNotDuplicated(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
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
