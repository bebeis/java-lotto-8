package lotto.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProfitRateTest {

    @DisplayName("총 당첨 금액과 구매 금액을 바탕으로 수익률을 계산할 수 있다")
    @Test
    void calculateProfitRate() {
        // given
        long totalPrize = 5000;
        long purchaseAmount = 8000;

        // when
        ProfitRate profitRate = ProfitRate.from(totalPrize, purchaseAmount);

        // then
        assertThat(profitRate.rate()).isEqualTo(62.5);
    }

    @DisplayName("수익률은 소수점 둘째 자리에서 반올림된다")
    @Test
    void roundProfitRate() {
        // given
        ProfitRate profitRate = new ProfitRate(62.5123);

        // when
        double rounded = profitRate.round();

        // then
        assertThat(rounded).isEqualTo(62.5);
    }

    @DisplayName("구매 금액이 0 이하라면 예외가 발생한다")
    @Test
    void invalidPurchaseAmountThrowsException() {
        assertThatThrownBy(() -> ProfitRate.from(10000, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ResultErrorMessage.INVALID_PURCHASE_AMOUNT_FOR_PROFIT.message());

        assertThatThrownBy(() -> ProfitRate.from(10000, -5000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ResultErrorMessage.INVALID_PURCHASE_AMOUNT_FOR_PROFIT.message());
    }
}
