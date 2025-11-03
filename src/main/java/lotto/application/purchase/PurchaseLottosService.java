package lotto.application.purchase;

import lotto.domain.purchase.LottoPurchaseService;
import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;

public class PurchaseLottosService {
    private final LottoPurchaseService purchaseService;
    private final PurchasedLottosRepository purchasedLottosRepository;

    public PurchaseLottosService(
            LottoPurchaseService purchaseService,
            PurchasedLottosRepository purchasedLottosRepository) {
        this.purchaseService = purchaseService;
        this.purchasedLottosRepository = purchasedLottosRepository;
    }

    public PurchasedLottoResponse purchaseLottos(int amount) {
        PurchasedLottos purchasedLottos = purchaseService.purchase(amount);
        purchasedLottosRepository.save(purchasedLottos);
        return PurchasedLottoResponse.from(purchasedLottos);
    }
}
