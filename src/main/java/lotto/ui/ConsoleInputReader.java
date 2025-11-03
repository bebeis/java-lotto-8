package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.CommaSeparator;
import lotto.common.TypeConverter;

import java.util.List;

public class ConsoleInputReader implements InputReader {
    private static final String END_LINE = System.lineSeparator();
    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요." + END_LINE;
    private static final String WINNING_NUMBERS_PROMPT = END_LINE + "당첨 번호를 입력해 주세요." + END_LINE;
    private static final String BONUS_NUMBER_PROMPT = END_LINE + "보너스 번호를 입력해 주세요." + END_LINE;

    @Override
    public int readPurchaseAmount() {
        System.out.print(PURCHASE_AMOUNT_PROMPT);
        return TypeConverter.toInteger(Console.readLine());
    }

    @Override
    public List<Integer> readWinningNumbers() {
        System.out.print(WINNING_NUMBERS_PROMPT);
        String input = Console.readLine();
        return CommaSeparator.split(input).stream()
                .map(TypeConverter::toInteger)
                .toList();
    }

    @Override
    public int readBonusNumber() {
        System.out.print(BONUS_NUMBER_PROMPT);
        return TypeConverter.toInteger(Console.readLine());
    }
}
