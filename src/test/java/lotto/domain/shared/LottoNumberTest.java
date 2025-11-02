package lotto.domain.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("로또 번호는 1이상 45 이하의 값을 갖는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 30, 20, 44, 45})
    void lottoNumberMustBeBetween1And45(int value) {
        // given && when
        LottoNumber lottoNumber = new LottoNumber(value);

        // then
        assertThat(lottoNumber.number()).isEqualTo(value);
    }

    @DisplayName("로또 번호는 1미만 또는 45 초과의 값을 가질 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 46, 90})
    void lottoNumberOutOfRangeThrowsException(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SharedErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.message().formatted(1, 45));
    }
}
