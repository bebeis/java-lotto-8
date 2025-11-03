package lotto.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommaSeparatorTest {

    @Test
    @DisplayName("쉼표로 구분된 문자열을 분리한다")
    void shouldSplitByComma() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<String> result = CommaSeparator.split(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4", "5", "6");
    }

    @Test
    @DisplayName("공백이 포함된 문자열을 분리하고 trim 한다")
    void shouldSplitAndTrim() {
        // given
        String input = "1, 2, 3 , 4  ,5,  6";

        // when
        List<String> result = CommaSeparator.split(input);

        // then
        assertThat(result).containsExactly("1", "2", "3", "4", "5", "6");
    }

    @Test
    @DisplayName("쉼표가 없는 단일 문자열을 분리한다")
    void shouldSplitSingleValue() {
        // given
        String input = "123";

        // when
        List<String> result = CommaSeparator.split(input);

        // then
        assertThat(result).containsExactly("123");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    @DisplayName("null, 빈 문자열, 공백 문자열 입력 시 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputIsBlank(String input) {
        // when & then
        assertThatThrownBy(() -> CommaSeparator.split(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있는 항목이 존재합니다");
    }

    @Test
    @DisplayName("여러 개의 쉼표로 구분된 긴 문자열을 분리한다")
    void shouldSplitLongString() {
        // given
        String input = "apple,banana,cherry,date,elderberry";

        // when
        List<String> result = CommaSeparator.split(input);

        // then
        assertThat(result).containsExactly("apple", "banana", "cherry", "date", "elderberry");
    }
}

