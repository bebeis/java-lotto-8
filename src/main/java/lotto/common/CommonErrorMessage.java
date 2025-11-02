package lotto.common;

public enum CommonErrorMessage implements ErrorMessage {
    MONEY_NEGATIVE_VALUE("돈은 음수일 수 없습니다."),
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.");

    private final String message;

    CommonErrorMessage(final String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return PREFIX + message;
    }
}
