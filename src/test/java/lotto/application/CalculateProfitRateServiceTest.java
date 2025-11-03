package lotto.application;

import lotto.application.profit.CalculateProfitRateService;
import lotto.application.profit.ProfitRateResponse;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.domain.result.LottoResultService;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.LottoWinningResultRepository;
import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import lotto.infrastructure.InMemoryLottoWinningResultRepository;
import lotto.infrastructure.InMemoryPurchasedLottosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateProfitRateServiceTest {
    private CalculateProfitRateService calculateProfitRateService;
    private LottoWinningResultRepository winningResultRepository;
    private PurchasedLottosRepository purchasedLottosRepository;

    @BeforeEach
    void setUp() {
        winningResultRepository = new InMemoryLottoWinningResultRepository();
        purchasedLottosRepository = new InMemoryPurchasedLottosRepository();
        calculateProfitRateService = new CalculateProfitRateService(
                winningResultRepository,
                purchasedLottosRepository
        );
    }

    @Test
    @DisplayName("당첨 결과와 구매 금액으로 수익률을 계산한다")
    void calculatesProfitRateFromWinningResultAndPurchaseAmount() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        int amount = 1000;
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        PurchasedLottos purchasedLottos = new PurchasedLottos(lottos, purchaseAmount);
        purchasedLottosRepository.save(purchasedLottos);

        LottoResultService resultService = new LottoResultService();
        LottoWinningResult winningResult = resultService.calculateWinningResult(
                lottos,
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        winningResultRepository.save(winningResult);

        // when
        ProfitRateResponse response = calculateProfitRateService.calculateProfitRate();

        // then
        assertThat(response.rate()).isEqualTo(200_000_000.0);
        assertThat(response.round()).isEqualTo(200_000_000.0);
    }
}
