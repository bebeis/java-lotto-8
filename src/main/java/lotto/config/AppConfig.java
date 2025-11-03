package lotto.config;

import lotto.application.profit.CalculateProfitRateController;
import lotto.application.profit.CalculateProfitRateService;
import lotto.application.purchase.PurchaseLottosController;
import lotto.application.purchase.PurchaseLottosService;
import lotto.ui.ConsoleInputReader;
import lotto.ui.ConsoleOutputView;
import lotto.ui.InputReader;
import lotto.ui.OutputView;
import lotto.application.winning.CalculateWinningResultController;
import lotto.application.winning.CalculateWinningResultService;
import lotto.domain.purchase.LottoPurchaseService;
import lotto.domain.purchase.PurchasedLottosRepository;
import lotto.domain.purchase.generator.LottoGenerator;
import lotto.domain.purchase.generator.LottoNumberGenerator;
import lotto.domain.purchase.generator.RandomLottoNumberGenerator;
import lotto.domain.result.LottoResultService;
import lotto.domain.result.LottoWinningResultRepository;
import lotto.domain.shared.LottoValidator;
import lotto.infrastructure.InMemoryLottoWinningResultRepository;
import lotto.infrastructure.InMemoryPurchasedLottosRepository;

public class AppConfig {
    private final PurchasedLottosRepository purchasedLottosRepository = new InMemoryPurchasedLottosRepository();
    private final LottoWinningResultRepository lottoWinningResultRepository = new InMemoryLottoWinningResultRepository();
    private final InputReader inputReader = new ConsoleInputReader();
    private final OutputView outputView = new ConsoleOutputView();

    public InputReader inputReader() {
        return inputReader;
    }

    public OutputView outputView() {
        return outputView;
    }

    public PurchasedLottosRepository purchasedLottosRepository() {
        return purchasedLottosRepository;
    }

    public LottoWinningResultRepository lottoWinningResultRepository() {
        return lottoWinningResultRepository;
    }

    public LottoNumberGenerator lottoNumberGenerator() {
        return new RandomLottoNumberGenerator();
    }

    public LottoGenerator lottoGenerator() {
        return new LottoGenerator(lottoNumberGenerator());
    }

    public LottoPurchaseService lottoPurchaseService() {
        return new LottoPurchaseService(lottoGenerator());
    }

    public LottoValidator lottoValidator() {
        return new LottoValidator();
    }

    public LottoResultService lottoResultService() {
        return new LottoResultService(lottoValidator());
    }

    public PurchaseLottosService purchaseLottosService() {
        return new PurchaseLottosService(
                lottoPurchaseService(),
                purchasedLottosRepository()
        );
    }

    public CalculateWinningResultService calculateWinningResultService() {
        return new CalculateWinningResultService(
                lottoResultService(),
                purchasedLottosRepository(),
                lottoWinningResultRepository()
        );
    }

    public CalculateProfitRateService calculateProfitRateService() {
        return new CalculateProfitRateService(
                lottoWinningResultRepository(),
                purchasedLottosRepository()
        );
    }

    public PurchaseLottosController purchaseLottosController() {
        return new PurchaseLottosController(
                purchaseLottosService(),
                inputReader(),
                outputView()
        );
    }

    public CalculateWinningResultController calculateWinningResultController() {
        return new CalculateWinningResultController(
                calculateWinningResultService(),
                inputReader(),
                outputView()
        );
    }

    public CalculateProfitRateController calculateProfitRateController() {
        return new CalculateProfitRateController(
                calculateProfitRateService(),
                outputView()
        );
    }
}
