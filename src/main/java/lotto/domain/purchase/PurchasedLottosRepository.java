package lotto.domain.purchase;

public interface PurchasedLottosRepository {

    void save(PurchasedLottos purchasedLottos);
    
    PurchasedLottos find();
}
