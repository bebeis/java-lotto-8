package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultServiceTest {

    private LottoResultService lottoResultService;
    private List<Integer> winningNumbers;
    private int bonusNumber;

    private static final Map<Rank, List<Integer>> RANK_TO_NUMBERS = Map.of(
            Rank.FIRST, List.of(1, 2, 3, 4, 5, 6),
            Rank.SECOND, List.of(1, 2, 3, 4, 5, 7),
            Rank.THIRD, List.of(1, 2, 3, 4, 5, 8),
            Rank.FOURTH, List.of(1, 2, 3, 4, 8, 9),
            Rank.FIFTH, List.of(1, 2, 3, 8, 9, 10)
    );

    @BeforeEach
    void setUp() {
        lottoResultService = new LottoResultService();
        winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        bonusNumber = 7;
    }

    @ParameterizedTest
    @DisplayName("로또 번호를 바탕으로 등수를 계산한다")
    @MethodSource("provideRankTestCases")
    void calculateRanks(List<Integer> lottoNumbers, Rank expectedRank, int expectedCount) {
        List<Lotto> lottos = List.of(createLotto(lottoNumbers));

        LottoWinningResult result = lottoResultService.calculateWinningResult(
                lottos, winningNumbers, bonusNumber);

        assertThat(result.getRankCounts().get(expectedRank)).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> provideRankTestCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), Rank.FIRST, 1),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), Rank.SECOND, 1),
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), Rank.THIRD, 1),
                Arguments.of(List.of(1, 2, 3, 4, 8, 9), Rank.FOURTH, 1),
                Arguments.of(List.of(1, 2, 3, 8, 9, 10), Rank.FIFTH, 1)
        );
    }

    @ParameterizedTest
    @DisplayName("구매 금액, 등수를 기반으로 수익률을 계산할 수 있다.")
    @MethodSource("provideProfitRateCases")
    void calculateProfitRate(int purchaseAmount, Rank rank, double expectedRate) {
        LottoWinningResult winningResult = createWinningResultByRank(rank);

        ProfitRate profitRate = lottoResultService.calculateProfitRate(winningResult, purchaseAmount);

        assertThat(profitRate.rate()).isEqualTo(expectedRate);
    }

    private static Stream<Arguments> provideProfitRateCases() {
        return Stream.of(
                Arguments.of(1000, Rank.FIRST, 200000000.0),
                Arguments.of(5000, Rank.SECOND, 600000.0),
                Arguments.of(1000, Rank.THIRD, 150000.0),
                Arguments.of(1000, Rank.FOURTH, 5000.0),
                Arguments.of(1000, Rank.FIFTH, 500.0),
                Arguments.of(1000, Rank.NONE, 0.0)
        );
    }

    private LottoWinningResult createWinningResultByRank(Rank rank) {
        List<LottoNumber> numbers = RANK_TO_NUMBERS
                .getOrDefault(rank, List.of(10, 11, 12, 13, 14, 15))
                .stream()
                .map(LottoNumber::new)
                .toList();

        List<Lotto> lottos = List.of(new Lotto(numbers));
        return lottoResultService.calculateWinningResult(lottos, winningNumbers, bonusNumber);
    }

    private Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream().map(LottoNumber::new).toList());
    }
}
