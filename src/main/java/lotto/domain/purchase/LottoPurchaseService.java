package lotto.domain.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;

public class LottoPurchaseService {
    private final LottoGenerator lottoGenerator;

    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public PurchasedLottos purchase(int amount) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        List<Lotto> lottos = lottoGenerator.generate(purchaseAmount);
        return new PurchasedLottos(lottos, purchaseAmount);
    }
}
