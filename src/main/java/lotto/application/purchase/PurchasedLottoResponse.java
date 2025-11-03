package lotto.application.purchase;

import lotto.domain.shared.Lotto;

import java.util.List;

public record PurchasedLottoResponse(List<Lotto> lottos) {

    public PurchasedLottoResponse(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }
}
