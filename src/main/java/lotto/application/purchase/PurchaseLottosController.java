package lotto.application.purchase;

import lotto.common.RetryHandler;
import lotto.ui.InputReader;
import lotto.ui.OutputView;

public class PurchaseLottosController {
    private final PurchaseLottosService purchaseLottosService;
    private final InputReader inputReader;
    private final OutputView outputView;

    public PurchaseLottosController(
            PurchaseLottosService purchaseLottosService,
            InputReader inputReader,
            OutputView outputView) {
        this.purchaseLottosService = purchaseLottosService;
        this.inputReader = inputReader;
        this.outputView = outputView;
    }

    public void run() {
        PurchasedLottoResponse response = requestLottoPurchase();
        outputView.printPurchasedLottos(response);
    }

    private PurchasedLottoResponse requestLottoPurchase() {
        return RetryHandler.execute(outputView, () -> {
            int purchaseAmount = inputReader.readPurchaseAmount();
            return purchaseLottosService.purchaseLottos(purchaseAmount);
        });
    }
}
