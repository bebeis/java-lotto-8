package lotto.domain.purchase;

import lotto.domain.purchase.generator.RandomLottoNumberGenerator;
import lotto.domain.shared.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomLottoNumberGeneratorTest {

    @DisplayName("범위 내의 중복되지 않은 로또 번호 6개를 생성한다")
    @RepeatedTest(100)
    void generateSixLottoNumbersInRange() {
        // given
        RandomLottoNumberGenerator generator = new RandomLottoNumberGenerator();

        // when
        List<LottoNumber> lottoNumbers = generator.generate();

        // then
        assertAll(
                () -> assertThat(lottoNumbers).hasSize(6),
                () -> assertThat(lottoNumbers)
                        .allMatch(lottoNumber -> lottoNumber.number() >= 1 && lottoNumber.number() <= 45),
                () -> {
                    assertNotNull(lottoNumbers);
                    assertThat(new HashSet<>(lottoNumbers)).hasSize(6);
                }
        );
    }
}
