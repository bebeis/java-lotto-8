package lotto.domain.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;

public record PurchasedLottos(List<Lotto> lottos, PurchaseAmount purchaseAmount) {

    public PurchasedLottos(List<Lotto> lottos, PurchaseAmount purchaseAmount) {
        this.lottos = List.copyOf(lottos);
        this.purchaseAmount = purchaseAmount;
    }

    public int amount() {
        return purchaseAmount.amount();
    }
}
