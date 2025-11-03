package lotto.domain.result;

import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class WinningLottoTest {

    @DisplayName("여러 로또 결과에 대해 올바른 Rank를 판단한다.")
    @ParameterizedTest
    @MethodSource("provideWinningAndPurchasedLottos")
    void decideRankWithVariousLottos(WinningLotto winningLotto, Lotto purchasedLotto, Rank expectedRank) {
        // when
        Rank rank = winningLotto.findRankOf(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(expectedRank);
    }

    static Stream<Arguments> provideWinningAndPurchasedLottos() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(10),
                        new LottoNumber(22),
                        new LottoNumber(31)
                )),
                new LottoNumber(42) // 보너스 번호
        );

        return Stream.of(
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(10),
                                new LottoNumber(22),
                                new LottoNumber(31)
                        )),
                        Rank.FIRST
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(10),
                                new LottoNumber(22),
                                new LottoNumber(42)
                        )),
                        Rank.SECOND
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(10),
                                new LottoNumber(22),
                                new LottoNumber(45)
                        )),
                        Rank.THIRD
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(10),
                                new LottoNumber(40),
                                new LottoNumber(43)
                        )),
                        Rank.FOURTH
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(40),
                                new LottoNumber(41),
                                new LottoNumber(42)
                        )),
                        Rank.FIFTH
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(30),
                                new LottoNumber(40),
                                new LottoNumber(41),
                                new LottoNumber(44)
                        )),
                        Rank.NONE
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(10),
                                new LottoNumber(42),
                                new LottoNumber(45)
                        )),
                        Rank.FOURTH
                ),
                Arguments.of(
                        winningLotto,
                        new Lotto(List.of(
                                new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(6),
                                new LottoNumber(7),
                                new LottoNumber(8),
                                new LottoNumber(9)
                        )),
                        Rank.NONE
                )
        );
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void validateBonusNumberNotDuplicated() {
        // given
        Lotto winningNumbers = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoNumber duplicatedBonusNumber = new LottoNumber(6);

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, duplicatedBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ResultErrorMessage.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBERS.message());
    }
}
