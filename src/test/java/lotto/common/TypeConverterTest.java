package lotto.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TypeConverterTest {

    @Test
    @DisplayName("양수 문자열을 정수로 변환한다")
    void shouldConvertPositiveStringToInteger() {
        // given
        String input = "123";

        // when
        int result = TypeConverter.toInteger(input);

        // then
        assertThat(result).isEqualTo(123);
    }

    @Test
    @DisplayName("음수 문자열을 정수로 변환한다")
    void shouldConvertNegativeStringToInteger() {
        // given
        String input = "-456";

        // when
        int result = TypeConverter.toInteger(input);

        // then
        assertThat(result).isEqualTo(-456);
    }

    @Test
    @DisplayName("0을 정수로 변환한다")
    void shouldConvertZeroStringToInteger() {
        // given
        String input = "0";

        // when
        int result = TypeConverter.toInteger(input);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("큰 숫자 문자열을 정수로 변환한다")
    void shouldConvertLargeNumberStringToInteger() {
        // given
        String input = "2147483647"; // Integer.MAX_VALUE

        // when
        int result = TypeConverter.toInteger(input);

        // then
        assertThat(result).isEqualTo(2147483647);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12.34", "1a2", " ", "", "one", "1,000"})
    @DisplayName("정수 형식이 아닌 문자열 입력 시 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputIsNotInteger(String input) {
        // when && then
        assertThatThrownBy(() -> TypeConverter.toInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(UtilErrorMessage.STRING_TO_INTEGER_ERROR.message());
    }

    @Test
    @DisplayName("null 입력 시 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputIsNull() {
        // when && then
        assertThatThrownBy(() -> TypeConverter.toInteger(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(UtilErrorMessage.STRING_TO_INTEGER_ERROR.message());
    }

    @Test
    @DisplayName("Integer 범위를 초과하는 문자열 입력 시 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputExceedsIntegerRange() {
        // given
        String input = "9999999999999";

        // when & then
        assertThatThrownBy(() -> TypeConverter.toInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(UtilErrorMessage.STRING_TO_INTEGER_ERROR.message());
    }

    @Test
    @DisplayName("앞뒤 공백이 있는 숫자 문자열 입력 시 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputHasWhitespace() {
        // given
        String input = " 123 ";

        // when && then
        assertThatThrownBy(() -> TypeConverter.toInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(UtilErrorMessage.STRING_TO_INTEGER_ERROR.message());
    }
}
