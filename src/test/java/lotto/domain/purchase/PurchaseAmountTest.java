package lotto.domain.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @DisplayName("구매 금액은 로또 티켓 1장 가격 미만일 수 없다.")
    @ParameterizedTest()
    @ValueSource(ints = {-100, 500})
    void cannotPurchase_whenAmountUnderLottoPrice(int value) {
        assertThatThrownBy(() -> new PurchaseAmount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PurchaseErrorMessage.PURCHASE_AMOUNT_BELOW_MINIMUM.message()
                        .formatted(1000));
    }

    @DisplayName("구매 금액은 로또 가격의 배수 단위로 지불할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 50000})
    void canPurchase_whenAmountMultipleOfLottoPrice(int value) {
        // given && when
        PurchaseAmount purchaseAmount = new PurchaseAmount(value);

        // then
        assertThat(purchaseAmount.amount()).isEqualTo(value);
    }

    @DisplayName("구매 금액으로부터 발행할 로또 개수를 얻는다.")
    @ParameterizedTest
    @CsvSource(value = {"3000,3", "1000,1"})
    void calculateLottoCount_fromPurchaseAmount(int input, int expected) {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);

        // when
        int lottoCount = purchaseAmount.lottoCount();

        // then
        assertThat(lottoCount).isEqualTo(expected);
    }
}
