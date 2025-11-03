package lotto.common;

public enum UtilErrorMessage implements ErrorMessage {
    EMPTY_CSV_VALUE("비어있는 항목이 존재합니다"),
    STRING_TO_INTEGER_ERROR("21억 이하의 정수 형식 문자열이 아닙니다.");

    private final String message;

    UtilErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
