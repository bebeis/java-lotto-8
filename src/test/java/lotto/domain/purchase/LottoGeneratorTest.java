package lotto.domain.purchase;

import lotto.domain.purchase.generator.LottoGenerator;
import lotto.domain.purchase.generator.LottoNumberGenerator;
import lotto.domain.purchase.generator.RandomLottoNumberGenerator;
import lotto.domain.shared.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @DisplayName("구매 금액만큼 로또가 발행된다")
    @ParameterizedTest(name = "{0}원을 지불하면 {1}장의 로또가 발행된다")
    @MethodSource("providePurchaseAmountAndResult")
    void generateLottoTickets(PurchaseAmount amount, int expectedCount) {
        // given
        LottoNumberGenerator numberGenerator = new RandomLottoNumberGenerator();
        LottoGenerator ticketGenerator = new LottoGenerator(numberGenerator);

        // when
        List<Lotto> lottos = ticketGenerator.generate(amount);

        // then
        assertThat(lottos).isNotNull();
        assertThat(lottos).hasSize(expectedCount);
        assertThat(lottos).allSatisfy(lotto -> assertThat(lotto).isNotNull());
    }

    private static Stream<Arguments> providePurchaseAmountAndResult() {
        return Stream.of(
                Arguments.of(new PurchaseAmount(1000), 1),
                Arguments.of(new PurchaseAmount(2000), 2),
                Arguments.of(new PurchaseAmount(5000), 5)
        );
    }
}
