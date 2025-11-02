package lotto.domain.vo;

import lotto.common.CommonErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("돈은 음수 값을 가질 수 없다.")
    @ParameterizedTest()
    @ValueSource(ints = {-1, -300})
    void moneyCannotHaveNegativeValue(int value) {
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CommonErrorMessage.MONEY_NEGATIVE_VALUE.message());
    }

    @DisplayName("돈은 0 이상의 값을 가질 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 3000})
    void moneyCanBeCreatedWithZeroOrPositiveValue(int value) {
        // given && when
        Money money = new Money(value);

        // then
        assertThat(money.amount()).isEqualTo(value);
    }

}
