package lotto.application;

import lotto.application.winning.CalculateWinningResultService;
import lotto.application.winning.LottoWinningResultResponse;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.domain.result.LottoResultService;
import lotto.domain.result.LottoWinningResultRepository;
import lotto.domain.result.Rank;
import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import lotto.domain.shared.LottoValidator;
import lotto.infrastructure.InMemoryLottoWinningResultRepository;
import lotto.infrastructure.InMemoryPurchasedLottosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateWinningResultServiceTest {
    private CalculateWinningResultService calculateWinningResultService;
    private PurchasedLottosRepository purchasedLottosRepository;
    private LottoWinningResultRepository winningResultRepository;

    @BeforeEach
    void setUp() {
        purchasedLottosRepository = new InMemoryPurchasedLottosRepository();
        winningResultRepository = new InMemoryLottoWinningResultRepository();
        LottoValidator lottoValidator = new LottoValidator();
        LottoResultService resultService = new LottoResultService(lottoValidator);

        calculateWinningResultService = new CalculateWinningResultService(
                resultService,
                purchasedLottosRepository,
                winningResultRepository
        );
    }

    @DisplayName("구매한 로또와 당첨 번호로 당첨 결과를 계산하고 저장한다")
    @Test
    void calculateAndSaveWinningResult() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
        PurchasedLottos purchasedLottos = new PurchasedLottos(lottos, purchaseAmount);
        purchasedLottosRepository.save(purchasedLottos);

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        LottoWinningResultResponse response = calculateWinningResultService.calculateWinningResult(winningNumbers, bonusNumber);

        // then
        assertThat(response).isNotNull();
        assertThat(response.rankCounts()).containsEntry(Rank.FIRST, 1L);
        assertThat(response.rankCounts()).doesNotContainKeys(Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);
        assertThat(response.totalPrize()).isEqualTo(2_000_000_000L);
        assertThat(winningResultRepository.find()).isNotNull();
    }
}
