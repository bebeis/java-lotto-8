package lotto.application;

import lotto.application.purchase.PurchaseLottosService;
import lotto.application.purchase.PurchasedLottoResponse;
import lotto.domain.purchase.LottoGenerator;
import lotto.domain.purchase.LottoNumberGenerator;
import lotto.domain.purchase.LottoPurchaseService;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.infrastructure.InMemoryPurchasedLottosRepository;
import lotto.stub.FixedLottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseLottosServiceTest {
    private PurchaseLottosService purchaseLottosService;
    private PurchasedLottosRepository purchasedLottosRepository;

    @BeforeEach
    void setUp() {
        LottoNumberGenerator numberGenerator = new FixedLottoNumberGenerator();
        LottoGenerator lottoGenerator = new LottoGenerator(numberGenerator);
        purchasedLottosRepository = new InMemoryPurchasedLottosRepository();
        LottoPurchaseService purchaseService = new LottoPurchaseService(lottoGenerator);

        purchaseLottosService = new PurchaseLottosService(purchaseService, purchasedLottosRepository);
    }

    @DisplayName("구매 금액에 따라 로또를 발행하고 저장한다")
    @ParameterizedTest
    @CsvSource(value = {"3000,3", "1000,1", "10000,10"})
    void createPurchasedLottoAndSave(int amount, int count) {
        // given
        // when
        PurchasedLottoResponse response = purchaseLottosService.purchaseLottos(amount);

        // then
        assertThat(response.lottos()).hasSize(count);
        assertThat(purchasedLottosRepository.find().lottos()).hasSize(count);
        assertThat(purchasedLottosRepository.find().amount()).isEqualTo(amount);
    }
}
