package lotto.application.winning;

import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.Rank;

import java.util.Map;

public record LottoWinningResultResponse(Map<Rank, Long> rankCounts, long totalPrize) {

    public static LottoWinningResultResponse from(LottoWinningResult winningResult) {
        return new LottoWinningResultResponse(
                winningResult.getRankCounts(),
                winningResult.totalPrize()
        );
    }
}
