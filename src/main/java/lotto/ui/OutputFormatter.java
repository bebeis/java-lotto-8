package lotto.ui;

import java.util.List;
import java.util.stream.Collectors;

public final class OutputFormatter {
    private static final String LOTTO_START = "[";
    private static final String LOTTO_END = "]";
    private static final String NUMBER_SEPARATOR = ", ";
    private static final String END_LINE = System.lineSeparator();

    private OutputFormatter() {
    }

    public static String formatLottos(final List<List<Integer>> lottos) {
        return lottos.stream()
                .map(OutputFormatter::formatLotto)
                .collect(Collectors.joining(END_LINE)) + END_LINE;
    }

    private static String formatLotto(final List<Integer> numbers) {
        String numbersString = numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(NUMBER_SEPARATOR));
        return LOTTO_START + numbersString + LOTTO_END;
    }

    public static String formatPurchaseCount(final int count) {
        return END_LINE + count + "개를 구매했습니다." + END_LINE;
    }

    public static String formatWinningStatisticsHeader() {
        return END_LINE + "당첨 통계" + END_LINE + "---" + END_LINE;
    }

    public static String formatRankStatistics(final String rankDescription, final int prize, final long count) {
        return String.format("%s (%,d원) - %d개", rankDescription, prize, count) + END_LINE;
    }

    public static String formatProfitRate(final double rate) {
        return "총 수익률은 %.1f%%입니다.".formatted(rate) + END_LINE;
    }
}
