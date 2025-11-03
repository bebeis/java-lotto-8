package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningResultTest {

    private WinningLotto winningLotto;
    private List<Lotto> lottos;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                new LottoNumber(7)
        );

        lottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),   // 1등
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(7))),   // 2등
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(45))),  // 3등
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(10), new LottoNumber(11)))  // 4등
        );
    }

    @DisplayName("로또 당첨 결과에서 등수별 당첨 개수를 집계할 수 있다.")
    @Test
    void calculatesRankCountsFromWinningNumbersAndTickets() {
        // when
        LottoWinningResult result = new LottoWinningResult(winningLotto, lottos);
        Map<Rank, Long> rankCounts = result.getRankCounts();

        // then
        assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(1L);
        assertThat(rankCounts.get(Rank.SECOND)).isEqualTo(1L);
        assertThat(rankCounts.get(Rank.THIRD)).isEqualTo(1L);
        assertThat(rankCounts.get(Rank.FOURTH)).isEqualTo(1L);
        assertThat(rankCounts.getOrDefault(Rank.FIFTH, 0L)).isEqualTo(0L);
    }

    @DisplayName("구매한 모든 로또의 총 당첨 금액을 계산할 수 있다.")
    @Test
    void calculatesTotalPrizeFromAllWinningTickets() {
        // when
        LottoWinningResult result = new LottoWinningResult(winningLotto, lottos);
        long totalPrize = result.totalPrize();

        // then
        long expected = Rank.FIRST.getPrize() +
                Rank.SECOND.getPrize() +
                Rank.THIRD.getPrize() +
                Rank.FOURTH.getPrize();

        assertThat(totalPrize).isEqualTo(expected);
    }
}
