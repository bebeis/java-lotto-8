package lotto.application.profit;

import lotto.ui.OutputView;

public class CalculateProfitRateController {
    private final CalculateProfitRateService calculateProfitRateService;
    private final OutputView outputView;

    public CalculateProfitRateController(
            CalculateProfitRateService calculateProfitRateService,
            OutputView outputView) {
        this.calculateProfitRateService = calculateProfitRateService;
        this.outputView = outputView;
    }

    public void run() {
        ProfitRateResponse response = calculateProfitRateService.calculateProfitRate();
        outputView.printProfitRate(response);
    }
}
