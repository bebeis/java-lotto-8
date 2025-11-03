package lotto.application.winning;

import lotto.common.RetryHandler;
import lotto.ui.InputReader;
import lotto.ui.OutputView;

import java.util.List;

public class CalculateWinningResultController {
    private final CalculateWinningResultService calculateWinningResultService;
    private final InputReader inputReader;
    private final OutputView outputView;

    public CalculateWinningResultController(
            CalculateWinningResultService calculateWinningResultService,
            InputReader inputReader,
            OutputView outputView) {
        this.calculateWinningResultService = calculateWinningResultService;
        this.inputReader = inputReader;
        this.outputView = outputView;
    }

    public void run() {
        List<Integer> winningNumbers = requestWinningNumbers();
        LottoWinningResultResponse response = calculateWinningResultByWinningAndBonus(winningNumbers);
        outputView.printWinningResult(response);
    }

    private List<Integer> requestWinningNumbers() {
        return RetryHandler.execute(outputView, () -> {
            List<Integer> winningNumbers = inputReader.readWinningNumbers();
            calculateWinningResultService.validateWinningNumbers(winningNumbers);
            return winningNumbers;
        });
    }

    private LottoWinningResultResponse calculateWinningResultByWinningAndBonus(List<Integer> winningNumbers) {
        return RetryHandler.execute(outputView, () -> {
            int bonusNumber = inputReader.readBonusNumber();
            return calculateWinningResultService.calculateWinningResult(winningNumbers, bonusNumber);
        });
    }
}
