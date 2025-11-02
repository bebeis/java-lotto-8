package lotto.domain.purchase;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import lotto.domain.shared.SharedErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    @Test
    void cannotCreateLottoWhenNumbersExceedSix() {
        assertThatThrownBy(() ->
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6),
                        new LottoNumber(7)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SharedErrorMessage.INVALID_LOTTO_NUMBER_COUNT.message().formatted(6));
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void cannotCreateLottoWhenNumbersUnderSix() {
        assertThatThrownBy(() ->
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SharedErrorMessage.INVALID_LOTTO_NUMBER_COUNT.message().formatted(6));
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void cannotCreateLottoWhenNumbersAreDuplicated() {
        assertThatThrownBy(() ->
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(5)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SharedErrorMessage.DUPLICATED_LOTTO_NUMBER.message());
    }

    @DisplayName("주어진 번호가 로또에 포함되어 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource("provideLottoNumberAndResult")
    void checkLottoNumberIsContainedInLotto(LottoNumber lottoNumber, boolean expected) {
        // given
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(3),
                new LottoNumber(10),
                new LottoNumber(30),
                new LottoNumber(40),
                new LottoNumber(45)
        ));

        // when
        boolean result = lotto.contains(lottoNumber);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLottoNumberAndResult() {
        return Stream.of(
                Arguments.of(new LottoNumber(1), true),
                Arguments.of(new LottoNumber(2), false),
                Arguments.of(new LottoNumber(3), true),
                Arguments.of(new LottoNumber(7), false)
        );
    }
}
