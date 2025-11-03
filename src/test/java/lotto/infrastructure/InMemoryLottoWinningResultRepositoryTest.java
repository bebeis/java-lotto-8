package lotto.infrastructure;

import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;
import lotto.domain.shared.Lotto;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryLottoWinningResultRepositoryTest {
    private InMemoryLottoWinningResultRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryLottoWinningResultRepository();
    }

    @DisplayName("당첨 결과를 저장하고 조회할 수 있다")
    @Test
    void saveAndFind() {
        // given
        WinningLotto winningLotto = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        List<Lotto> purchasedLottos = List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        LottoWinningResult winningResult = new LottoWinningResult(winningLotto, purchasedLottos);

        // when
        repository.save(winningResult);
        LottoWinningResult found = repository.find();

        // then
        assertThat(found).isEqualTo(winningResult);
        assertThat(found.getRankCounts()).isNotEmpty();
    }

    @Test
    @DisplayName("저장된 당첨 결과가 없을 때 조회하면 예외가 발생한다")
    void findWithoutSave() {
        // when && then
        assertThatThrownBy(() -> repository.find())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("당첨 번호 추첨 결과가 없습니다");
    }

    @Test
    @DisplayName("여러 등수가 포함된 당첨 결과를 저장하고 조회할 수 있다")
    void saveAndFindMultipleRanks() {
        // given
        WinningLotto winningLotto = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        List<Lotto> purchasedLottos = List.of(
                // 1등: 6개 일치
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                )),
                // 2등: 5개 일치 + 보너스
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(7)
                )),
                // 낙첨
                new Lotto(List.of(
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12),
                        new LottoNumber(13), new LottoNumber(14), new LottoNumber(15)
                ))
        );
        LottoWinningResult winningResult = new LottoWinningResult(winningLotto, purchasedLottos);

        // when
        repository.save(winningResult);
        LottoWinningResult found = repository.find();

        // then
        assertThat(found.getRankCounts()).isNotEmpty();
        assertThat(found.totalPrize()).isGreaterThan(0);
    }
}
