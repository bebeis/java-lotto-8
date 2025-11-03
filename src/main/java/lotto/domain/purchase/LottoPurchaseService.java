package lotto.domain.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;

public class LottoPurchaseService {
    private final LottoGenerator lottoGenerator;

    public LottoPurchaseService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> purchaseLottos(int amount) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        return lottoGenerator.generate(purchaseAmount);
    }
}
