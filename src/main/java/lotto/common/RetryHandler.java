package lotto.common;

import lotto.ui.OutputView;

import java.util.function.Supplier;

public final class RetryHandler {
    private RetryHandler() {
    }

    public static <T> T execute(OutputView outputView, Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
