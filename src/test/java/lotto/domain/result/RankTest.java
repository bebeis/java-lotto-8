package lotto.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치 개수와 보너스 번호 여부에 따라 Rank가 결정된다")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "6, true, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH",
            "4, false, FOURTH",
            "3, true, FIFTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "1, true, NONE",
            "0, false, NONE"
    })
    void canDecideRankByMatchCountAndBonusMatch(int matchCount, boolean bonusMatch, Rank expected) {
        // when
        Rank result = Rank.findRankBy(matchCount, bonusMatch);

        // then
        assertThat(result).isEqualTo(expected);
    }

}
