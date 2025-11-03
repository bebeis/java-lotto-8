package lotto.ui;

import lotto.application.profit.ProfitRateResponse;
import lotto.application.purchase.PurchasedLottoResponse;
import lotto.application.winning.LottoWinningResultResponse;
import lotto.domain.result.Rank;

import java.util.Arrays;
import java.util.Comparator;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printPurchasedLottos(PurchasedLottoResponse response) {
        System.out.print(OutputFormatter.formatPurchaseCount(response.lottos().size()));
        System.out.print(OutputFormatter.formatLottos(response.lottos()));
    }

    @Override
    public void printWinningResult(LottoWinningResultResponse response) {
        System.out.print(OutputFormatter.formatWinningStatisticsHeader());
        Arrays.stream(Rank.values())
                .sorted(Comparator.comparingInt(Rank::getPrize))
                .skip(1)
                .forEach(rank -> {
                    long count = response.rankCounts().getOrDefault(rank, 0L);
                    printRankStatistics(rank, count);
                });
    }

    private void printRankStatistics(Rank rank, long count) {
        String rankDescription = getRankDescription(rank);
        System.out.print(OutputFormatter.formatRankStatistics(rankDescription, rank.getPrize(), count));
    }

    private String getRankDescription(Rank rank) {
        if (rank.isRequiresBonusMatch()) {
            return rank.getMatchCount() + "개 일치, 보너스 볼 일치";
        }
        return rank.getMatchCount() + "개 일치";
    }

    @Override
    public void printProfitRate(ProfitRateResponse response) {
        System.out.print(OutputFormatter.formatProfitRate(response.round()));
    }

    @Override
    public void printError(String message) {
        System.out.print(message + System.lineSeparator());
    }
}
