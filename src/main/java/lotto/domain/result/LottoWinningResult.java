package lotto.domain.result;

import lotto.domain.shared.Lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoWinningResult {

    private final EnumMap<Rank, Long> rankCounts;

    public LottoWinningResult(WinningLotto winningLotto, List<Lotto> lottos) {
        this.rankCounts = lottos.stream()
                .map(winningLotto::findRankOf)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        () -> new EnumMap<>(Rank.class),
                        Collectors.counting()
                ));
    }

    public Map<Rank, Long> getRankCounts() {
        return Map.copyOf(rankCounts);
    }

    public long totalPrize() {
        return rankCounts.entrySet()
                .stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
