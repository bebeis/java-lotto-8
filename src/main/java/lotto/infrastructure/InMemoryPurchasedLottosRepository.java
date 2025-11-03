package lotto.infrastructure;

import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.purchase.PurchasedLottosRepository;

public class InMemoryPurchasedLottosRepository implements PurchasedLottosRepository {
    private PurchasedLottos purchasedLottos;

    @Override
    public void save(PurchasedLottos purchasedLottos) {
        this.purchasedLottos = purchasedLottos;
    }

    @Override
    public PurchasedLottos find() {
        if (purchasedLottos == null) {
            throw new IllegalStateException(InfraErrorMessage.EMPTY_PURCHASED_LOTTO.message());
        }
        return purchasedLottos;
    }
}
