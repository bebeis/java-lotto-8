package lotto.domain.purchase;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoPurchaseServiceTest {

    private LottoPurchaseService lottoPurchaseService;

    @BeforeEach
    void setUp() {
        LottoNumberGenerator numberGenerator = new TestLottoNumberGenerator();
        LottoGenerator lottoGenerator = new LottoGenerator(numberGenerator);
        lottoPurchaseService = new LottoPurchaseService(lottoGenerator);
    }

    @DisplayName("주어진 금액만큼 로또를 구매할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1000, 1", "2000, 2", "10000, 10"})
    void purchaseLottosFromAmount(int amount, int expected) {
        // when
        List<Lotto> lottos = lottoPurchaseService.purchaseLottos(amount);

        // then
        assertThat(lottos).hasSize(expected);
    }

    @DisplayName("입력받은 구매 금액이 1000원 미만이면 로또를 구매할 수 없다..")
    @ParameterizedTest
    @ValueSource(ints = {0, 500, -100})
    void throwExceptionWhenAmountBelowMinimum(int amount) {
        assertThatThrownBy(() -> lottoPurchaseService.purchaseLottos(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 구매 금액이 1000원 단위가 아니면 로또를 구매할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {1500, 2500, 1234})
    void throwExceptionWhenAmountIsNotDivisible(int amount) {
        assertThatThrownBy(() -> lottoPurchaseService.purchaseLottos(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static class TestLottoNumberGenerator implements LottoNumberGenerator {
        @Override
        public List<LottoNumber> generate() {
            return List.of(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            );
        }
    }
}
