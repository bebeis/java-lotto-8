package lotto.infrastructure;

import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.LottoWinningResultRepository;

public class InMemoryLottoWinningResultRepository implements LottoWinningResultRepository {
    private LottoWinningResult winningResult;

    @Override
    public void save(LottoWinningResult winningResult) {
        this.winningResult = winningResult;
    }

    @Override
    public LottoWinningResult find() {
        if (winningResult == null) {
            throw new IllegalStateException(InfraErrorMessage.EMPTY_LOTTO_WINNING_RESULT.message());
        }
        return winningResult;
    }
}
