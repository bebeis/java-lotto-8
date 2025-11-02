package lotto.domain.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoTicketTest {

    @DisplayName("주어진 구매 금액만큼 로또를 발행한다")
    @ParameterizedTest
    @MethodSource("providePurchaseAmountAndResult")
    void issueLottoTicketByPurchaseAmount(PurchaseAmount amount, int expected) {
        LottoNumberGenerator lottoNumberGenerator = new RandomLottoNumberGenerator();

        // when
        LottoTicket lottoTicket = LottoTicket.issueFrom(amount, lottoNumberGenerator);

        // then
        assertNotNull(lottoTicket);
        assertThat(lottoTicket.getLottos().size()).isEqualTo(expected);
    }

    private static Stream<Arguments> providePurchaseAmountAndResult() {
        return Stream.of(
                Arguments.of(new PurchaseAmount(1000), 1),
                Arguments.of(new PurchaseAmount(2000), 2),
                Arguments.of(new PurchaseAmount(10000), 10)
        );
    }
}
