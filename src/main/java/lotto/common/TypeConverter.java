package lotto.common;

import static lotto.common.UtilErrorMessage.STRING_TO_INTEGER_ERROR;

public final class TypeConverter {

    private TypeConverter() {
    }

    public static int toInteger(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(STRING_TO_INTEGER_ERROR.message());
        }
    }
}
