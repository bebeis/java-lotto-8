package lotto.common;

import java.util.Arrays;
import java.util.List;

import static lotto.common.UtilErrorMessage.EMPTY_CSV_VALUE;

public final class CommaSeparator {
    private static final String COMMA = ",";

    private CommaSeparator() {
    }

    public static List<String> split(final String commaSeperatedValue) {
        validateNotBlank(commaSeperatedValue);
        return Arrays.stream(commaSeperatedValue.split(COMMA))
                .map(String::trim)
                .toList();
    }

    private static void validateNotBlank(final String commaSeperatedValue) {
        if (commaSeperatedValue == null || commaSeperatedValue.isBlank()) {
            throw new IllegalArgumentException(EMPTY_CSV_VALUE.message());
        }
    }
}
