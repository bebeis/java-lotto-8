package lotto.common;

public enum CommonErrorMessage implements ErrorMessage {
    MONEY_NEGATIVE_VALUE("돈은 음수일 수 없습니다.");

    private final String message;

    CommonErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
