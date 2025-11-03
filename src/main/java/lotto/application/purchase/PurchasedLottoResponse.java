package lotto.application.purchase;

import lotto.domain.purchase.PurchasedLottos;
import lotto.domain.shared.Lotto;

import java.util.List;

public record PurchasedLottoResponse(List<List<Integer>> lottos) {

    public static PurchasedLottoResponse from(PurchasedLottos purchasedLottos) {
        return new PurchasedLottoResponse(
                purchasedLottos.lottos().stream()
                        .map(Lotto::getNumbers)
                        .toList()
        );
    }
}
